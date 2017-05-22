package perception;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import perception.events.PrimitiveEvent;
import perception.primitive_events_generator.PrimitiveEventsGenerator;
import perception.primitive_events_generator.implementations.PEG_Blank;

import java.util.Deque;
import java.util.LinkedList;

public class PrimitiveEventStream {

    Deque<PrimitiveEventsGenerator> generators;
    private StreamExecutionEnvironment env;
    private DataStream<PrimitiveEvent> primitiveEventsStream;
    private boolean debugStream;

    public PrimitiveEventStream(StreamExecutionEnvironment env) {
        this.env = env;
        generators = new LinkedList<>();
        debugStream = false;
        //Init the primitive events stream with a blank primitive event generator that generate nothing.
        primitiveEventsStream = env.addSource(new PEG_Blank());
        blankMap();
    }

    public void addPEG(PrimitiveEventsGenerator primitiveEventsGenerator) {
        generators.add(primitiveEventsGenerator);
        DataStream<PrimitiveEvent> tmpStream = env.addSource(primitiveEventsGenerator);
        primitiveEventsStream = primitiveEventsStream.union(tmpStream);
        blankMap();
    }

    private void blankMap() {
        primitiveEventsStream.map(new MapFunction<PrimitiveEvent, PrimitiveEvent>() {
            @Override
            public PrimitiveEvent map(PrimitiveEvent value) throws Exception {
                return null;
            }
        });
    }

    public DataStream<PrimitiveEvent> getStream() {
        return primitiveEventsStream;
    }

    public boolean isDebugStream() {
        return debugStream;
    }

    public void setDebugStream(boolean debugStream) {
        if(debugStream != this.debugStream) {
            this.debugStream = debugStream;
            for(PrimitiveEventsGenerator peg : generators) {
                peg.setDebugGeneratedEvents(debugStream);
            }
        }
    }

}
