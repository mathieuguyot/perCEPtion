package perception.primitive_events_generator;

import graph.CloudResource;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import perception.events.PrimitiveEvent;
import utils.SysOutLogger;

import java.util.HashMap;
import java.util.Optional;

public abstract class PrimitiveEventsGenerator implements SourceFunction<PrimitiveEvent> {

    private volatile boolean isRunning = true;
    private static HashMap<String, CloudResource> resources = new HashMap<>();
    private long msRefreshingRate;
    private boolean debugGeneratedEvents;

    public PrimitiveEventsGenerator(long msRefreshingRate) {
        this.msRefreshingRate = msRefreshingRate;
    }

    public static boolean addMonitoredResource(CloudResource cr) {
        if(resources.containsKey(cr.getName())) {
            return false;
        }
        resources.put(cr.getName(), cr);
        return true;
    }

    @Override
    public void run(SourceContext ctx) throws Exception {
        while(isRunning) {
            long startTime = System.currentTimeMillis();
            this.exec(ctx);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            System.out.println(elapsedTime);
            Thread.sleep(msRefreshingRate);
        }
    }

    private void exec(SourceContext ctx) {
        for(CloudResource cr : resources.values()) {
            Optional<PrimitiveEvent> optEvent = processResource(cr);
            if(optEvent.isPresent()) {
                ctx.collect(optEvent.get());
                if(debugGeneratedEvents) {
                    SysOutLogger.logLn(optEvent.get().toString());
                }
            }
        }
    }

    @Override
    public void cancel() {
        isRunning = false;
    }

    public boolean isDebugGeneratedEvents() {
        return debugGeneratedEvents;
    }

    public void setDebugGeneratedEvents(boolean debugGeneratedEvents) {
        this.debugGeneratedEvents = debugGeneratedEvents;
    }

    protected abstract Optional<PrimitiveEvent> processResource(CloudResource cr);
}