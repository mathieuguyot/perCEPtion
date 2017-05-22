import graph.PM;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.IterativeCondition;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import perception.FlinkEnvRunner;
import perception.PrimitiveEventStream;
import perception.events.PrimitiveEvent;
import perception.events.primitive_events.PE_Cpu;
import perception.primitive_events_generator.PrimitiveEventsGenerator;
import perception.primitive_events_generator.implementations.*;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //AppliGraphMock appli = new AppliGraphMock();

        PM pm1 = null;
        for(int i = 0; i < 1000000; i++) {
            PM pm = new PM("PM-" + String.valueOf(i));
            pm.setCpuConsumption(60);
            PrimitiveEventsGenerator.addMonitoredResource(pm);
            if(i==0) {
                pm1 = pm;
            }
        }

        PrimitiveEventStream primitiveEventStream = new PrimitiveEventStream(env);

       //primitiveEventStream.addPEG(new PEG_Pm_Ram(1000));
        //primitiveEventStream.addPEG(new PEG_Pm_Disk(1000));
        primitiveEventStream.addPEG(new PEG_Pm_Cpu(1000));
        //primitiveEventStream.addPEG(new PEG_Pm_Disk(10));
/*
        //TEST SECTION
        Pattern<PrimitiveEvent, ?> cpuPattern = Pattern
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
                });/*
                .next("IGNORE")
                .where(new IterativeCondition<PrimitiveEvent>() {
                    @Override
                    public boolean filter(PrimitiveEvent primitiveEvent, Context<PrimitiveEvent> context) throws Exception {
                        if(primitiveEvent instanceof PE_Cpu) {
                            return false;
                        }
                        return true;
                    }
                })
                .optional()
                .oneOrMore()
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
                .within(Time.milliseconds(1100));*/
/*
        PatternStream<PrimitiveEvent> cpuStream = CEP.pattern(primitiveEventStream.getStream(), cpuPattern);

        DataStream<PrimitiveEvent> oStream = cpuStream.select(new PatternSelectFunction<PrimitiveEvent, PrimitiveEvent>() {
            @Override
            public PrimitiveEvent select(Map<String, List<PrimitiveEvent>> map) throws Exception {
                for(List<PrimitiveEvent> p : map.values()) {
                    for(PrimitiveEvent pe : p) {
                            return new PE_Cpu(pe.getCloudResourceName(), pe.getCloudResourceType(), ((PE_Cpu)pe).getCpuValue());
                    }
                }
                return null;
            }
        });
*/
        //oStream.print();

        //END TEST SECTION
        FlinkEnvRunner envRunner = new FlinkEnvRunner(env);
        envRunner.start();

        primitiveEventStream.setDebugStream(false);

        Thread.sleep(5000);
        System.out.println("CPU DROP");
        pm1.setCpuConsumption(0);/*
        Thread.sleep(3000);
        System.out.println("CPU UP");
        pm1.setCpuConsumption(60);
        Thread.sleep(3000);
        System.out.println("CPU DROP");
        pm1.setCpuConsumption(0);*/
    }
/*
    public static void test2() {
        GraphSourceFunction monitor = new GraphSourceFunction(4000);
        //GraphSourceFunction monitor2 = new GraphSourceFunction(4000);

        Appli appli = new Appli("app1");

        Tier tier1 = new Tier("T1");
        appli.addTier(tier1);
        tier1.setAppli(appli);

        PM pm1 = new PM("PM1");
        appli.addPM(pm1);
        pm1.setAppli(appli);
        //monitor.addMonitoredResource(pm1);
        Random random = new Random();
        for(int i = 0; i < 5; i++) {
            VM vm1 = new VM("VM"+String.valueOf(i));
            vm1.setPm(pm1);
            vm1.setTier(tier1);

            vm1.setDiskConsumption(random.nextInt(100));
            vm1.setRamConsumption(random.nextInt(100));
            vm1.setVcpuConsumption(random.nextInt(100));
            monitor.addMonitoredResource(vm1);
            //monitor2.addMonitoredResource(vm1);
        }

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //StreamTableEnvironment tableEnv = TableEnvironment.getTableEnvironment(env);

        //tableEnv.registerFunction("cpu_pe", monitor);
        //tableEnv.sql("SELECT * FROM cpu_pe");

        DataStream<PrimitiveEvent> stream = env.addSource(monitor,"graph-monitor");

        Pattern<PrimitiveEvent, ?> cpuPattern = Pattern.<PrimitiveEvent>begin("start").subtype(PE_Cpu.class).where(evt -> evt.getCpuValue() > 50);

        PatternStream<PrimitiveEvent> patternStream = CEP.pattern(stream, cpuPattern);

        DataStream<PrimitiveEvent> oStream = patternStream.select(new PatternSelectFunction<PrimitiveEvent, PrimitiveEvent>() {
            @Override
            public PrimitiveEvent select(Map<String, PrimitiveEvent> pattern) throws Exception {
                for(PrimitiveEvent p : pattern.values()) {
                    p.display();
                }
                return null;
            }
        });

        //oStream.print();

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test1() throws Exception {

        GraphSourceFunction monitor = new GraphSourceFunction(4000);

        Appli appli = new Appli("app1");

        Tier tier1 = new Tier("T1");
        appli.addTier(tier1);
        tier1.setAppli(appli);

        PM pm1 = new PM("PM1");
        appli.addPM(pm1);
        pm1.setAppli(appli);
        Random random = new Random();
        for(int i = 0; i < 5; i++) {
            VM vm1 = new VM("VM"+String.valueOf(i));
            vm1.setPm(pm1);
            vm1.setTier(tier1);

            vm1.setDiskConsumption(random.nextInt(100));
            vm1.setRamConsumption(random.nextInt(100));
            vm1.setVcpuConsumption(random.nextInt(100));
            monitor.addMonitoredResource(vm1);
        }

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<PrimitiveEvent> stream = env.addSource(monitor,"graph-monitor");

        Pattern<PrimitiveEvent, ?> cpuPattern = Pattern.<PrimitiveEvent>begin("start").subtype(PE_Cpu.class).where(evt -> evt.getCpuValue() > 50);

        PatternStream<PrimitiveEvent> cpuStream = CEP.pattern(stream, cpuPattern);

        DataStream<PrimitiveEvent> oStream = cpuStream.select(new PatternSelectFunction<PrimitiveEvent, PrimitiveEvent>() {
            @Override
            public PrimitiveEvent select(Map<String, PrimitiveEvent> pattern) throws Exception {
                for(PrimitiveEvent p : pattern.values()) {
                    return new PE_Cpu(p.getCloudResourceName(), p.getCloudResourceType(), ((PE_Cpu)p).getCpuValue());
                }
                return null;
            }
        });

        oStream.print();

        env.execute("perception");

        Pattern<PrimitiveEvent, ?> ramPattern = Pattern.<PrimitiveEvent>begin("start").subtype(PE_Ram.class).where(evt -> evt.getRamValue() > 50);

        PatternStream<PrimitiveEvent> ramStream = CEP.pattern(stream, ramPattern);

        DataStream<PrimitiveEvent> oRamStream = ramStream.select(new PatternSelectFunction<PrimitiveEvent, PrimitiveEvent>() {
            @Override
            public PrimitiveEvent select(Map<String, PrimitiveEvent> pattern) throws Exception {
                for(PrimitiveEvent p : pattern.values()) {
                    return new PE_Ram(p.getCloudResourceName(), p.getCloudResourceType(), ((PE_Ram)p).getRamValue());
                }
                return null;
            }
        });

        oRamStream.print();

        env.execute("perception");
    }*/
}
