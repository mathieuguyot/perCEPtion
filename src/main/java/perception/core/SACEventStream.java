package perception.core;

import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.streaming.api.datastream.DataStream;
import perception.events.PrimitiveEvent;
import perception.events.SimpleEvent;
import perception.services.PerceptionRunResource;
import perception.simple_events_generator.SimpleEventGenerator;
import perception.simple_events_generator.implementations.SEG_Blank;

import java.util.Deque;
import java.util.LinkedList;

public class SACEventStream implements PerceptionRunResource {

    private DataStream<SimpleEvent> stream;

    public SACEventStream() {
       this.stream = null;
    }

    public void mergeStream(DataStream<SimpleEvent> dataStream) {
        stream = stream.union(dataStream);
    }

    @Override
    public boolean beforeRun(PerceptionRunContext ctx) {
        //Add blank event
        SEG_Blank seg_blank = new SEG_Blank("BLANK");
        Pattern<PrimitiveEvent, ?> pattern = seg_blank.getPattern();
        PatternStream<PrimitiveEvent> pStream = CEP.pattern(ctx.getPrimitiveEventStream().getKeyedStream(), pattern);
        stream = pStream.select(seg_blank.getPatternSelectFunction());
        for(SimpleEventGenerator seg : ctx.getSimpleEventGeneratorManager().getGenerators()) {
            seg.beforeRun(ctx);
        }
        return true;
    }

    @Override
    public void endRun() {
        this.stream = null;
    }

}
