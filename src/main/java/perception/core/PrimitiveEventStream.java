package perception.core;

import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import perception.events.PrimitiveEvent;
import perception.primitive_events_generator.PrimitiveEventGenerator;
import perception.primitive_events_generator.implementations.PEG_Blank;
import perception.services.PerceptionRunResource;

public class PrimitiveEventStream implements PerceptionRunResource {

    private DataStream<PrimitiveEvent> stream;
    private KeyedStream<PrimitiveEvent, String> keyedStream;

    public PrimitiveEventStream() {
        stream = null;
        keyedStream = null;
    }

    public KeyedStream<PrimitiveEvent, String> getKeyedStream() {
        return keyedStream;
    }

    @Override
    public boolean beforeRun(PerceptionRunContext ctx) {
        //Init the primitive events stream with a blank primitive event generator that generate nothing.
        stream = ctx.getEnv().addSource(new PEG_Blank("BLANK"));

        for(PrimitiveEventGenerator peg : ctx.getPrimitiveEventGeneratorManager().getGenerators()) {
            peg.beforeRun(ctx);
            DataStream<PrimitiveEvent> tmpStream = ctx.getEnv().addSource(peg);
            stream = stream.union(tmpStream);
        }
        keyStream();
        return true;
    }

    private void keyStream() {
        keyedStream = stream.keyBy(new KeySelector<PrimitiveEvent, String>() {
            @Override
            public String getKey(PrimitiveEvent primitiveEvent) throws Exception {
                return primitiveEvent.getCloudResourceName();
            }
        });
    }

    @Override
    public void endRun() {
        this.stream = null;
    }

}
