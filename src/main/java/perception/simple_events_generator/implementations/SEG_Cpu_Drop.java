package perception.simple_events_generator.implementations;

import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.IterativeCondition;
import perception.core.LoggerAccess;
import perception.events.PrimitiveEvent;
import perception.events.SimpleEvent;
import perception.events.primitive_events.PE_Cpu;
import perception.events.simple_events.SE_Cpu_Drop;
import perception.simple_events_generator.SimpleEventGenerator;

import java.util.List;
import java.util.Map;

public class SEG_Cpu_Drop extends SimpleEventGenerator {

    public SEG_Cpu_Drop(String name) {
        super(name);
    }

    @Override
    public Pattern<PrimitiveEvent, ?> getPattern() {
        return Pattern
                .<PrimitiveEvent>begin("IDLE")
                .subtype(PE_Cpu.class)
                .where(new IterativeCondition<PE_Cpu>() {
                    @Override
                    public boolean filter(PE_Cpu pe_cpu, Context<PE_Cpu> context) throws Exception {
                        if(pe_cpu.getCpuValue() > 50) {
                            return true;
                        }
                        return false;
                    }
                })
                .next("CPU DROP")
                .subtype(PE_Cpu.class)
                .where(new IterativeCondition<PE_Cpu>() {
                    @Override
                    public boolean filter(PE_Cpu pe_cpu, Context<PE_Cpu> context) throws Exception {
                        if(pe_cpu.getCpuValue() < 10) {
                            return true;
                        }
                        return false;
                    }
                });
    }

    @Override
    public PatternSelectFunction<PrimitiveEvent, SimpleEvent> getPatternSelectFunction() {
        return new PatternSelectFunction<PrimitiveEvent, SimpleEvent>() {
            @Override
            public SimpleEvent select(Map<String, List<PrimitiveEvent>> map) throws Exception {
                for (List<PrimitiveEvent> p : map.values()) {
                    for (PrimitiveEvent pe : p) {
                        SE_Cpu_Drop se = new SE_Cpu_Drop(pe.getCloudResourceName(), pe.getCloudResourceType(), ((PE_Cpu) pe).getCpuValue(), 0);
                        if(SEG_Cpu_Drop.super.isLogGeneratedEvents()) {
                            LoggerAccess.getLogger().logSimpleEvent(se, getName());
                        }
                        //return se;
                    }
                }
                return null;
            }
        };
    }

}
