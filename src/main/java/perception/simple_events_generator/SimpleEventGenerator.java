package perception.simple_events_generator;

import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.streaming.api.datastream.DataStream;
import perception.core.EventGenerator;
import perception.core.PerceptionRunContext;
import perception.events.Event;
import perception.events.PrimitiveEvent;

public abstract class SimpleEventGenerator extends EventGenerator {

    public SimpleEventGenerator(String name) {
        super(name);
    }

    public abstract Pattern<PrimitiveEvent, ?> getPattern();

    public abstract PatternSelectFunction<PrimitiveEvent, Event> getPatternSelectFunction();

    @Override
    public boolean beforeRun(PerceptionRunContext ctx) {
        boolean initOk = super.beforeRun(ctx);
        if(initOk) {
            Pattern<PrimitiveEvent, ?> pattern = this.getPattern();
            PatternStream<PrimitiveEvent> pStream = CEP.pattern(ctx.getPrimitiveEventStream().getKeyedStream(), pattern);
            DataStream<Event> outStream = pStream.select(this.getPatternSelectFunction());
            ctx.getSacEventStream().mergeStream(outStream);
        }
        return initOk;
    }

    @Override
    public void endRun() {
        super.endRun();
    }

}
