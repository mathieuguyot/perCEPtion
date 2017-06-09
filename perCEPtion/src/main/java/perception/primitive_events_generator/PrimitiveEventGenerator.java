package perception.primitive_events_generator;

import graph.CloudResource;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import perception.core.CloudResourcesAccess;
import perception.core.EventGenerator;
import perception.core.PerceptionRunContext;
import perception.events.PrimitiveEvent;

import java.util.Optional;

/**
 * Abstract class that represent a primitive event generator (PEG).
 * A PEG generate Primitive event (PE) in a primitive event stream.
 * The execution of a PEG is called every x milliseconds specified by the user who used the PEG.
 *
 * /!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\
 * Warning, a PEG is a Perception run resource that means that its members can be modified during flink
 * execution, but nothing will append. If you want to update members of a PEG, you have to
 * shutdown flink execution, apply your changes and restart flink execution (via PerceptionCore)
 * /!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\
 */
public abstract class PrimitiveEventGenerator extends EventGenerator
        implements SourceFunction<PrimitiveEvent>
{

    private boolean isRunning; //Indicates if the PEG run or not
    private long msRefreshingRate; //Refreshing rate of the PEG (called every msRefreshingRate)

    /**
     * Constructor of a PrimitiveEventGenerator
     * @param name the name of the PrimitiveEventGenerator
     * @param msRefreshingRate refreshing rate of the PrimitiveEventGenerator (called every msRefreshingRate)
     */
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
                //TODO if elapsedTime > msRefreshingRate, up a warning, the monitoring system is to low
                Thread.sleep(msRefreshingRate);
            }
        }
    }

    /**
     * Execution loop of the PEG, For each cloud resource the processResource method will be called
     * @param ctx The source context that collect generated primitive events
     */
    private void exec(SourceContext ctx) {
        //TODO Improve doc of this function !
        for(CloudResource cr : CloudResourcesAccess.getInstance().getResources().values()) {
            Optional<PrimitiveEvent> optEvent = processResource(cr);
            if(optEvent.isPresent()) {
                ctx.collect(optEvent.get());
                if(this.isLogGeneratedEvents() && PerceptionRunContext.getPerceptionLogger() != null) {
                    PerceptionRunContext.getPerceptionLogger().logPrimitiveEvent(optEvent.get(), getName());
                }
            }
        }
    }

    @Override
    public void cancel() {}

    /**
     * Abstract fonctions that non-abstract PEG has to define.
     * Here the PEG will decide to return a PE or nothing depending of the given cloud resource.
     * @param cr The cloud resource to process
     * @return A primitive event to put in Primitive event stream or Optional.empty
     */
    protected abstract Optional<PrimitiveEvent> processResource(CloudResource cr);
}