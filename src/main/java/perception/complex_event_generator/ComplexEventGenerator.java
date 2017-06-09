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

import java.util.List;
import java.util.Map;

/**
 * Abstract class that represent a complex event generator (CEG).
 * A CEG generate complex events (CE) in a simple and complex event stream.
 * A CEG gets its data from PE, SE, CE that are generated
 * The execution of a CEG is called when a pattern is recognize (this pattern is defined by the CEG
 * with the getPattern() function.
 * After a regognition (using the pattern), the getPatternSelectFunction() is called to apply a
 * treatment on the recognized events stream.
 *
 * /!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\CEG
 * Warning, a CEG is a Perception run resource that means that its members can be modified during flink
 * execution, but nothing will append. If you want to update members of a CEG, you have to
 * shutdown flink execution, apply your changes and restart flink execution (via PerceptionCore)
 *  !\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\
 */
public abstract class ComplexEventGenerator extends EventGenerator {

    public ComplexEventGenerator(String name) {
        super(name);
    }

    /**
     * Recognition pattern of this CEG
     * @return the pattern of this CEG
     */
    public abstract Pattern<Event, ?> getPattern();

    /**
     * The treatment function of this CEG
     * @return The treatment function of this CEG
     */
    public abstract PatternSelectFunction<Event, Event> getPatternSelectFunction();

    @Override
    public boolean beforeRun(PerceptionRunContext ctx) {
        //TODO Improve doc of this function !
        boolean initOk = super.beforeRun(ctx);
        if(initOk) {
            Pattern<Event, ?> pattern = this.getPattern();
            PatternStream<Event> pStream = CEP.pattern(ctx.getPasacEventStream().getStream(), pattern);
            DataStream<Event> outStream = pStream.select(new PatternSelectFunction<Event, Event>() {
                @Override
                public Event select(Map<String, List<Event>> map) throws Exception {
                    Event e = getPatternSelectFunction().select(map);
                    if (isLogGeneratedEvents() && e != null) {
                        PerceptionRunContext.getPerceptionLogger().logComplexEvent((ComplexEvent) e, getName());
                    }
                    return e;
                }
            });
            ctx.getSacEventStream().mergeStream(outStream);
        }
        return initOk;
    }

    @Override
    public void endRun() {
        super.endRun();
    }

}
