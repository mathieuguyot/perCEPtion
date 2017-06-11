package complex_events_generator;

import events.complex_events.CE_Cpu_Dead;
import events.simple_events.SE_Cpu_Drop;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.IterativeCondition;
import perception.complex_event_generator.ComplexEventGenerator;
import perception.events.Event;

import java.util.List;
import java.util.Map;

public class CEG_DeadCpu extends ComplexEventGenerator {

    public CEG_DeadCpu(String name) {
        super(name);
    }

    @Override
    public Pattern<Event, ?> getPattern() {
        return Pattern
                .<Event>begin("DEAD_CPU")
                .subtype(SE_Cpu_Drop.class)
                .where(new IterativeCondition<SE_Cpu_Drop>() {
                    @Override
                    public boolean filter(SE_Cpu_Drop se, Context<SE_Cpu_Drop> context) throws Exception {
                        if(se.getCpuValueAfterDrop() == 0) {
                            return true;
                        }
                        return false;
                    }
                });
    }

    @Override
    public PatternSelectFunction<Event, Event> getPatternSelectFunction() {
        return new PatternSelectFunction<Event, Event>() {
            @Override
            public Event select(Map<String, List<Event>> map) throws Exception {
                for (List<Event> le : map.values()) {
                    for(Event e : le) {
                        if(e instanceof SE_Cpu_Drop) {
                            SE_Cpu_Drop se = (SE_Cpu_Drop)e;
                            CE_Cpu_Dead ce = new CE_Cpu_Dead(se.getCloudResourceType(), se.getCloudResourceName(), se.getScore());
                            return ce;
                        }
                    }
                }
                return null;
            }
        };
    }

}
