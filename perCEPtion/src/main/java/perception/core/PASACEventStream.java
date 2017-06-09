package perception.core;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import perception.complex_event_generator.ComplexEventGenerator;
import perception.events.Event;
import perception.events.PrimitiveEvent;
import perception.services.PerceptionRunResource;

public class PASACEventStream implements PerceptionRunResource {

    private DataStream<Event> stream;

    public PASACEventStream() {
        this.stream = null;
    }

    public DataStream<Event> getStream() {
        return stream;
    }

    @Override
    public boolean beforeRun(PerceptionRunContext ctx) {
        this.stream = ctx.getPrimitiveEventStream().getKeyedStream().map(new MapFunction<PrimitiveEvent, Event>() {
            @Override
            public Event map(PrimitiveEvent primitiveEvent) throws Exception {
                return (Event)primitiveEvent;
            }
        });
        this.stream = this.stream.union(ctx.getSacEventStream().getStream());
        for(ComplexEventGenerator ceg : ctx.getComplexEventGeneratorManager().getGenerators()) {
            if(ceg.isHasToGenerateEvents()) {
                ceg.beforeRun(ctx);
            }
        }
        return true;
    }

    @Override
    public void endRun() {
        this.stream = null;
    }

}

