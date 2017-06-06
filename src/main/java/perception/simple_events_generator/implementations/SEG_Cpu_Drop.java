package perception.simple_events_generator.implementations;

import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.IterativeCondition;
import org.apache.flink.streaming.api.windowing.time.Time;
import perception.core.PerceptionRunContext;
import perception.events.PrimitiveEvent;
import perception.events.SimpleEvent;
import perception.events.primitive_events.PE_Cpu;
import perception.events.symptoms.SY_Cpu_Drop;
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
                .next("IGNORE")
                .oneOrMore()
                .optional()
                .where(new IterativeCondition<PrimitiveEvent>() {
                    @Override
                    public boolean filter(PrimitiveEvent pe, Context<PrimitiveEvent> context) throws Exception {
                        if(pe.getClass() == PE_Cpu.class) {
                            return false;
                        } else {
                            return true;
                        }
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
                })
                .within(Time.milliseconds(2000));
    }

    @Override
    public PatternSelectFunction<PrimitiveEvent, SimpleEvent> getPatternSelectFunction() {
        return new PatternSelectFunction<PrimitiveEvent, SimpleEvent>() {
            @Override
            public SimpleEvent select(Map<String, List<PrimitiveEvent>> map) throws Exception {
                boolean isSecondPE = false;
                int cpuHigh = 0, cpuLow = 0;
                for (List<PrimitiveEvent> p : map.values()) {
                    for (PrimitiveEvent pe : p) {
                        if(pe.getClass() == PE_Cpu.class && !isSecondPE) {
                            //SE_Cpu_Drop se = new SE_Cpu_Drop(pe.getCloudResourceName(), pe.getCloudResourceType(), ((PE_Cpu) pe).getCpuValue(), 0);
                            cpuHigh = ((PE_Cpu) pe).getCpuValue();
                            isSecondPE = true;
                        } else if(pe.getClass() == PE_Cpu.class && isSecondPE) {
                            cpuLow = ((PE_Cpu) pe).getCpuValue();
                            SY_Cpu_Drop sy = new SY_Cpu_Drop(pe.getCloudResourceName(),
                                                             pe.getCloudResourceType(),
                                                             pe.getScore(),
                                                             cpuHigh,
                                                             cpuLow);
                            PerceptionRunContext.getSymptomQueue().pushSymptom(sy);
                        }
                    }
                }
                return null;
            }
        };
    }

}
