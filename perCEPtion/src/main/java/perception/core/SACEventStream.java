package perception.core;

import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.streaming.api.datastream.DataStream;
import perception.events.Event;
import perception.events.PrimitiveEvent;
import perception.services.PerceptionRunResource;
import perception.simple_events_generator.SimpleEventGenerator;
import perception.simple_events_generator.implementations.SEG_Blank;

public class SACEventStream implements PerceptionRunResource {

    private DataStream<Event> stream;

    public SACEventStream() {
       this.stream = null;
    }

    public void mergeStream(DataStream<Event> dataStream) {
        stream = stream.union(dataStream);
    }

    public DataStream<Event> getStream() {
        return stream;
    }

    @Override
    public boolean beforeRun(PerceptionRunContext ctx) {
        //Add blank event
        SEG_Blank seg_blank = new SEG_Blank("BLANK");
        Pattern<PrimitiveEvent, ?> pattern = seg_blank.getPattern();
        PatternStream<PrimitiveEvent> pStream = CEP.pattern(ctx.getPrimitiveEventStream().getKeyedStream(), pattern);
        stream = pStream.select(seg_blank.getPatternSelectFunction());
        for(SimpleEventGenerator seg : ctx.getSimpleEventGeneratorManager().getGenerators()) {
            if(seg.isHasToGenerateEvents()) {
                seg.beforeRun(ctx);
            }
        }
        return true;
    }

    @Override
    public void endRun() {
        this.stream = null;
    }

}
