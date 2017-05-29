package perception.simple_events_generator;

import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.streaming.api.datastream.DataStream;
import perception.core.EventGenerator;
import perception.core.PerceptionRunContext;
import perception.events.PrimitiveEvent;
import perception.events.SimpleEvent;
import perception.services.PerceptionRunResource;

import java.io.Serializable;

public abstract class SimpleEventGenerator extends EventGenerator implements PerceptionRunResource {

    public SimpleEventGenerator() {

    }

    public abstract Pattern<PrimitiveEvent, ?> getPattern();

    public abstract PatternSelectFunction<PrimitiveEvent, SimpleEvent> getPatternSelectFunction();


    @Override
    public boolean beforeRun(PerceptionRunContext ctx) {
        Pattern<PrimitiveEvent, ?> pattern = this.getPattern();
        PatternStream<PrimitiveEvent> pStream = CEP.pattern(ctx.getPrimitiveEventStream().getKeyedStream(), pattern);
        DataStream<SimpleEvent> outStream = pStream.select(this.getPatternSelectFunction());
        ctx.getSacEventStream().mergeStream(outStream);
        return true;
    }

    @Override
    public void endRun() {
    }

}