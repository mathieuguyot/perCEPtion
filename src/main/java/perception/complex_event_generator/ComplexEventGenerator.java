package perception.complex_event_generator;

import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.streaming.api.datastream.DataStream;
import perception.core.EventGenerator;
import perception.core.PerceptionRunContext;
import perception.events.ComplexEvent;
import perception.events.Event;
import perception.events.PrimitiveEvent;

public abstract class ComplexEventGenerator extends EventGenerator {

    public ComplexEventGenerator(String name) {
        super(name);
    }


    public abstract Pattern<Event, ?> getPattern();

    public abstract PatternSelectFunction<Event, Event> getPatternSelectFunction();

    @Override
    public boolean beforeRun(PerceptionRunContext ctx) {
        boolean initOk = super.beforeRun(ctx);
        if(initOk) {
            Pattern<Event, ?> pattern = this.getPattern();
            PatternStream<Event> pStream = CEP.pattern(ctx.getPasacEventStream().getStream(), pattern);
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
