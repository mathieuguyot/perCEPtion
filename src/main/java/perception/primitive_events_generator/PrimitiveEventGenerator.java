package perception.primitive_events_generator;

import graph.CloudResource;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import perception.core.CloudResourcesAccess;
import perception.core.EventGenerator;
import perception.events.PrimitiveEvent;

import java.util.Optional;

public abstract class PrimitiveEventGenerator extends EventGenerator implements SourceFunction<PrimitiveEvent> {

    private boolean isRunning;
    private long msRefreshingRate;

    public PrimitiveEventGenerator(String name, long msRefreshingRate) {
        super(name);
        this.msRefreshingRate = msRefreshingRate;
        this.isRunning = true;
    }

    @Override
    public void run(SourceContext ctx) throws Exception {
        while(isRunning) {
            if(isHasToGenerateEvents()) {
                long startTime = System.currentTimeMillis();
                this.exec(ctx);
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                Thread.sleep(msRefreshingRate);
            }
        }
    }

    private void exec(SourceContext ctx) {
        for(CloudResource cr : CloudResourcesAccess.getInstance().getResources().values()) {
            Optional<PrimitiveEvent> optEvent = processResource(cr);
            if(optEvent.isPresent()) {
                ctx.collect(optEvent.get());
                if(this.isLogGeneratedEvents() && this.getPerceptionLogger() != null) {
                    this.getPerceptionLogger().logPrimitiveEvent(optEvent.get(), getName());
                }
            }
        }
    }

    @Override
    public void cancel() {
        isRunning = false;
    }

    protected abstract Optional<PrimitiveEvent> processResource(CloudResource cr);
}