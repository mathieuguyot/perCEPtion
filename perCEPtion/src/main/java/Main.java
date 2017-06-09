import graph.PM;
import perception.configurator.activator.manager.ActivationResult;
import perception.configurator.activator.manager.PEG.PEGActivator;
import perception.core.CloudResourcesAccess;
import perception.core.PerceptionCore;
import perception.pluginManager.PluginManager;
import simple_events_generator.SEG_Cpu_Drop;
import simple_events_generator.SEG_Ram_Drop;

import java.util.HashMap;
import java.util.Map;

//TEST CLASS ! weird stuff ahead :)
public class Main {

    public static void main(String[] args) throws Exception {
        //AppliGraphMock appli = new AppliGraphMock();

        PM pm1 = null;
        PM pm2 = null;
        for(int i = 0; i < 1; i++) {
            PM pm = new PM("PM-" + String.valueOf(i), 0);
            pm.setCpuConsumption(60);
            pm.setRamConsumption(70);
            CloudResourcesAccess.getInstance().addMonitoredResource(pm);
            if(i==0) {
                pm1 = pm;
            } if(i == 100) {
                pm2 = pm;
            }
        }

        PerceptionCore core = new PerceptionCore();

        PluginManager.getPluginManager().registerPlugin(MainPerceptionPlugin.getPlugin());

        //Activation of PEG
        core.getPrimitiveEventGeneratorManager().setLogStream(true);
        Map<String, Long> map = new HashMap<>();
        map.put("PEG_Pm_Cpu", 1000L);
        map.put("PEG_Pm_Ram", 1000L);
        ActivationResult activationResult = PEGActivator.activate(map, core);
        if(activationResult.hasErrors()) {
            System.out.println("Activation errors !!!");
            activationResult.getErrorMsg();
            return;
        }

        //PEG_Activator peg_activator = new PEG_Activator();
        //Map<String, Long> myPEGS = new HashMap<>();
        //myPEGS.put("PEG_Pm_Cpu", 1000L);
        //peg_activator.activate(myPEGS, core);

        //core.getPrimitiveEventGeneratorManager().addEventGenerator(new PEG_Pm_Cpu("My generator", 1000));
        //core.getPrimitiveEventGeneratorManager().addEventGenerator(new PEG_Pm_Disk(1000));
       // core.fet().addPEG(new PEG_Pm_Cpu(1000));


        //SEG
        core.getSimpleEventGeneratorManager().setLogStream(true);
        core.getSimpleEventGeneratorManager().addEventGenerator(new SEG_Cpu_Drop("My seg"));
        core.getSimpleEventGeneratorManager().addEventGenerator(new SEG_Ram_Drop("My ram seg"));

        //CEG
        core.getComplexEventGeneratorManager().setLogStream(true);
        //core.getComplexEventGeneratorManager().addEventGenerator(new CEG_DeadCpu("cpu dead"));

        //primitiveEventStream.addPEG(new PEG_Pm_Disk(100));
        //TEST SECTION
/*
        KeyedStream<PrimitiveEvent, String> kStream = core.getPrimitiveEventStream().getStream().keyBy(
                new KeySelector<PrimitiveEvent, String>() {
                    @Override
                    public String getKey(PrimitiveEvent primitiveEvent) throws Exception {
                        return primitiveEvent.getCloudResourceName();
                    }
                }
        );

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

        PatternStream<PrimitiveEvent> cpuStream = CEP.pattern(kStream, cpuPattern);

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

        oStream.print();
*/
        //END TEST SECTION
        core.run();

        Thread.sleep(5000);
        System.out.println("CPU DROP");
        pm1.setCpuConsumption(0);
        Thread.sleep(3000);
        System.out.println("CPU UP");
        pm1.setCpuConsumption(70);
        Thread.sleep(3000);
        System.out.println("CPU DROP AND RAM DROP");
        pm1.setCpuConsumption(2);
        pm1.setRamConsumption(6);
        Thread.sleep(3000);
        System.out.println("CPU UP");
        pm1.setCpuConsumption(80);
        Thread.sleep(3000);
        System.out.println("CPU DROP");
        pm1.setCpuConsumption(3);
        Thread.sleep(3000);
        System.out.println("CPU UP");
        pm1.setCpuConsumption(65);
        Thread.sleep(3000);
        System.out.println("CPU DROP");
        pm1.setCpuConsumption(7);
        Thread.sleep(3000);
        System.out.println("CPU UP");
        pm1.setCpuConsumption(100);
        Thread.sleep(3000);
        System.out.println("CPU DEAD");
        pm1.setCpuConsumption(0);


/*
        core.endRun();
        Thread.sleep(6000);
        core.getPrimitiveEventStream().addPEG(new PEG_Pm_Disk(1000));
        core.run();
        core.getPrimitiveEventStream().setLogStream(false);
        core.getPrimitiveEventStream().setLogStream(true);
        System.out.println("STOP");

        Thread.sleep(3000);
        System.out.println("CPU UP");
        pm1.setCpuConsumption(55);
        Thread.sleep(3000);
        System.out.println("CPU DROP");
        pm1.setCpuConsumption(0);
        //pm2.setCpuConsumption(2);*/
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
