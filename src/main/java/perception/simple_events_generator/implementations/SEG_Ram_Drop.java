package perception.simple_events_generator.implementations;

import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.IterativeCondition;
import org.apache.flink.streaming.api.windowing.time.Time;
import perception.events.Event;
import perception.events.PrimitiveEvent;
import perception.events.primitive_events.PE_Cpu;
import perception.events.primitive_events.PE_Ram;
import perception.events.simple_events.SE_Ram_Drop;
import perception.simple_events_generator.SimpleEventGenerator;

import java.util.List;
import java.util.Map;

public class SEG_Ram_Drop extends SimpleEventGenerator {

    public SEG_Ram_Drop(String name) {
        super(name);
    }

    @Override
    public Pattern<PrimitiveEvent, ?> getPattern() {
        return Pattern
                .<PrimitiveEvent>begin("IDLE")
                .subtype(PE_Ram.class)
                .where(new IterativeCondition<PE_Ram>() {
                    @Override
                    public boolean filter(PE_Ram pe_ram, Context<PE_Ram> context) throws Exception {
                        if(pe_ram.getRamValue() > 50) {
                            return true;
                        }
                        return false;
                    }
                })
                .next("IGNORE")
                .oneOrMore()
                .optional()
                .where(new IterativeCondition<PrimitiveEvent>() {
                    @Override
                    public boolean filter(PrimitiveEvent pe, Context<PrimitiveEvent> context) throws Exception {
                        if(pe.getClass() == PE_Ram.class) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                })
                .next("RAM DROP")
                .subtype(PE_Ram.class)
                .where(new IterativeCondition<PE_Ram>() {
                    @Override
                    public boolean filter(PE_Ram pe_ram, Context<PE_Ram> context) throws Exception {
                        if(pe_ram.getRamValue() < 10) {
                            return true;
                        }
                        return false;
                    }
                })
                .within(Time.milliseconds(2000));
    }

    @Override
    public PatternSelectFunction<PrimitiveEvent, Event> getPatternSelectFunction() {
        return new PatternSelectFunction<PrimitiveEvent, Event>() {
            @Override
            public Event select(Map<String, List<PrimitiveEvent>> map) throws Exception {
                boolean isSecondPE = false;
                int ramHigh = 0, ramLow = 0;
                for (List<PrimitiveEvent> p : map.values()) {
                    for (PrimitiveEvent pe : p) {
                        if(pe.getClass() == PE_Ram.class && !isSecondPE) {
                            ramHigh = ((PE_Ram) pe).getRamValue();
                            isSecondPE = true;
                        } else if(pe.getClass() == PE_Cpu.class && isSecondPE) {
                            ramLow = ((PE_Cpu) pe).getCpuValue();
                            SE_Ram_Drop se_ram_drop = new SE_Ram_Drop(pe.getCloudResourceName(),
                                    pe.getCloudResourceType(),
                                    pe.getScore(),
                                    ramHigh,
                                    ramLow);
                            return se_ram_drop;
                        }
                    }
                }
                return null;
            }
        };
    }

}
