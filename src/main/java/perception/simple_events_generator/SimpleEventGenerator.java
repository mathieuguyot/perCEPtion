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
import perception.events.SimpleEvent;

import java.util.List;
import java.util.Map;

/**
 * Abstract class that represent a simple event generator (SEG).
 * A SEG generate Simple event (SE) in a simple and complex event stream.
 * A SEG gets its data from primitive events (PE) that are generated
 * The execution of a SEG is called when a pattern is recognize (this pattern is defined by the SEG
 * with the getPattern() function.
 * After a regognition (using the pattern), the getPatternSelectFunction() is called to apply a
 * treatment on the recognized events stream.
 *
 * /!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\
 * Warning, a SEG is a Perception run resource that means that its members can be modified during flink
 * execution, but nothing will append. If you want to update members of a SEG, you have to
 * shutdown flink execution, apply your changes and restart flink execution (via PerceptionCore)
 *  !\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\
 */
public abstract class SimpleEventGenerator extends EventGenerator {

    /**
     * Constructor of a SimpleEventGenerator
     * @param name the name of the SimpleEventGenerator
     */
    public SimpleEventGenerator(String name) {
        super(name);
    }

    /**
     * Recognition pattern of this SEG
     * @return the pattern of this SEG
     */
    public abstract Pattern<PrimitiveEvent, ?> getPattern();

    /**
     * The treatment function of this SEG
     * @return The treatment function of this SEG
     */
    public abstract PatternSelectFunction<PrimitiveEvent, Event> getPatternSelectFunction();

    @Override
    public boolean beforeRun(PerceptionRunContext ctx) {
        //TODO Improve doc of this function !
        boolean initOk = super.beforeRun(ctx);
        if(initOk) {
            Pattern<PrimitiveEvent, ?> pattern = this.getPattern();
            PatternStream<PrimitiveEvent> pStream = CEP.pattern(ctx.getPrimitiveEventStream().getKeyedStream(), pattern);
            DataStream<Event> outStream = pStream.select(new PatternSelectFunction<PrimitiveEvent, Event>() {
                @Override
                public Event select(Map<String, List<PrimitiveEvent>> map) throws Exception {
                    Event e = getPatternSelectFunction().select(map);
                    if(isLogGeneratedEvents()) {
                        PerceptionRunContext.getPerceptionLogger().logSimpleEvent((SimpleEvent)e, getName());
                    }
                    return null;
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
