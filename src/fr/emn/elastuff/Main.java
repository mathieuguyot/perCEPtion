package fr.emn.elastuff;

import fr.emn.elastuff.graph.*;

public class Main {

    public static void main(String[] args) {
        Appli appli = new Appli("app1");

        Tier tier1 = new Tier("T1");
        Tier tier2 = new Tier("T2");

        PM pm1 = new PM("PM1");
        PM pm2 = new PM("PM2");

        VM vm1 = new VM("VM1");
        VM vm2 = new VM("VM2");
        VM vm3 = new VM("VM3");

        Co co1 = new Co("co1", "mySQL", 50);
        Co co2 = new Co("co2", "mySQL", 40);
        Co co3 = new Co("co3", "mySQL", 30);
        Co co4 = new Co("co4", "mySQL", 20);

        // graph
        appli.addPM(pm1);
        appli.addPM(pm2);
        appli.addTier(tier1);
        appli.addTier(tier2);

        tier1.addVM(vm1);
        tier2.addVM(vm2);
        tier2.addVM(vm3);

        try {
            pm1.addVM(vm1);
            pm2.addVM(vm2);
            pm2.addVM(vm3);
        } catch (UnsupportedOperationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        vm1.addCo(co1);
        vm1.addCo(co2);
        vm2.addCo(co3);
        vm3.addCo(co4);

        vm1.setTier(tier1);
        vm2.setTier(tier1);
        vm3.setTier(tier2);

        co1.setVm(vm1);
        co2.setVm(vm1);
        co3.setVm(vm2);
        co4.setVm(vm3);

        appli.display();
    }

}
