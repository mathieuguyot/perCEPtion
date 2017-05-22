package perception;

import graph.Appli;
import graph.PM;
import graph.Tier;
import graph.VM;
import perception.primitive_events_generator.PrimitiveEventsGenerator;

import java.util.Random;

public class AppliGraphMock {

    private Appli appli;

    public AppliGraphMock() {
        this.initGraphStructure();
    }

    private void initGraphStructure() {
        appli = new Appli("app1");

        Tier tier1 = new Tier("T1");
        appli.addTier(tier1);
        tier1.setAppli(appli);

        PM pm1 = new PM("PM1");
        appli.addPM(pm1);
        pm1.setAppli(appli);
        PrimitiveEventsGenerator.addMonitoredResource(pm1);

        Random random = new Random();
        for(int i = 0; i < 5; i++) {
            VM vm1 = new VM("VM"+String.valueOf(i));
            vm1.setPm(pm1);
            vm1.setTier(tier1);

            vm1.setDiskConsumption(random.nextInt(100));
            vm1.setRamConsumption(random.nextInt(100));
            vm1.setCpuConsumption(random.nextInt(100));
            PrimitiveEventsGenerator.addMonitoredResource(vm1);
        }
    }

}
