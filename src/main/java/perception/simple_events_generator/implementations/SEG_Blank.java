package perception.simple_events_generator.implementations;

import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.IterativeCondition;
import perception.events.PrimitiveEvent;
import perception.events.SimpleEvent;
import perception.simple_events_generator.SimpleEventGenerator;

import java.util.List;
import java.util.Map;

public class SEG_Blank extends SimpleEventGenerator {

    public SEG_Blank(String name) {
        super(name);
    }

    @Override
    public Pattern<PrimitiveEvent, ?> getPattern() {
        return Pattern
                .<PrimitiveEvent>begin("NOPE")
                .where(new IterativeCondition<PrimitiveEvent>() {
                    @Override
                    public boolean filter(PrimitiveEvent event, Context<PrimitiveEvent> context) throws Exception {
                        return false;
                    }
                });
    }

    @Override
    public PatternSelectFunction<PrimitiveEvent, SimpleEvent> getPatternSelectFunction() {
        return new PatternSelectFunction<PrimitiveEvent, SimpleEvent>() {
            @Override
            public SimpleEvent select(Map<String, List<PrimitiveEvent>> map) throws Exception {
                return null;
            }
        };
    }
}
