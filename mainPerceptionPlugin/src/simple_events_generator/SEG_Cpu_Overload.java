package simple_events_generator;

/**
 * Created by asus on 06/06/2017.
 */
public class SEG_Cpu_Overload /*extends SimpleEventGenerator*/ {
/*
    private int highCpu;
    private long msRefreshingRate;

    public SEG_Cpu_Overload(String name) {
        super(name);
    }

    public SEG_Cpu_Overload(String name, long msRefreshingRate, int highCpu) {
        super(name);
        this.highCpu = highCpu;
        this.msRefreshingRate = msRefreshingRate;
    }

    @Override
    public Pattern<PrimitiveEvent, ?> getPattern() {
        return Pattern
                .<PrimitiveEvent>begin("HIGH CPU")
                .subtype(PE_Cpu.class)
                .where(new IterativeCondition<PE_Cpu>() {
                    @Override
                    public boolean filter(PE_Cpu pe_cpu, Context<PE_Cpu> context) throws Exception {
                        if(pe_cpu.getCpuValue() >= highCpu) {
                            return true;
                        }
                        return false;
                    }
                })
                .next("HIGH STEADY")
                .subtype(PE_Cpu.class)
                .where(new IterativeCondition<PE_Cpu>() {
                    @Override
                    public boolean filter(PE_Cpu pe_cpu, Context<PE_Cpu> context) throws Exception {
                        if(pe_cpu.getCpuValue() >= highCpu) {
                            return true;
                        }
                        return false;
                    }
                })
                .within(Time.milliseconds(msRefreshingRate));
    }

    @Override
    public PatternSelectFunction<PrimitiveEvent, SimpleEvent> getPatternSelectFunction() {
        return new PatternSelectFunction<PrimitiveEvent, SimpleEvent>() {
            @Override
            public SimpleEvent select(Map<String, List<PrimitiveEvent>> map) throws Exception {
                boolean isSecondPE = false;
                int cpuHigh = 0;
                for (List<PrimitiveEvent> p : map.values()) {
                    for (PrimitiveEvent pe : p) {
                        if(pe.getClass() == PE_Cpu.class && !isSecondPE) {
                            //SE_Cpu_Drop se = new SE_Cpu_Drop(pe.getCloudResourceName(), pe.getCloudResourceType(), ((PE_Cpu) pe).getCpuValue(), 0);
                            cpuHigh = ((PE_Cpu) pe).getCpuValue();
                            isSecondPE = true;
                        } else if(pe.getClass() == PE_Cpu.class && isSecondPE) {
                            SY_Cpu_Overload sy = new SY_Cpu_Overload(pe.getCloudResourceName(),
                                    pe.getCloudResourceType(),
                                    pe.getScore(),
                                    cpuHigh);
                            PerceptionRunContext.getSymptomQueue().pushSymptom(sy);
                        }
                    }
                }
                return null;
            }
        };
    }*/
}
