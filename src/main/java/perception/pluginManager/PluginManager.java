package perception.pluginManager;

import perception.complex_event_generator.ComplexEventGenerator;
import perception.primitive_events_generator.PrimitiveEventGenerator;
import perception.simple_events_generator.SimpleEventGenerator;

public class PluginManager {

    private static PluginManager pluginManager = new PluginManager();

    public static PluginManager getPluginManager() {
        return pluginManager;
    }

    private EGBank<PrimitiveEventGenerator> pegBank;
    private EGBank<SimpleEventGenerator> segBank;
    private EGBank<ComplexEventGenerator> cegBank;

    private PluginManager() {
        this.createPEGBank();
        this.createSEGBank();
    }

    public EGBank<PrimitiveEventGenerator> getPegBank() {
        return pegBank;
    }

    public EGBank<SimpleEventGenerator> getSegBank() {
        return segBank;
    }

    public EGBank<ComplexEventGenerator> getCegBank() {
        return cegBank;
    }

    private void createPEGBank() {
        pegBank = new EGBank<PrimitiveEventGenerator>() {
            @Override
            public boolean registerPlugin(PerceptionPlugin plugin) {
                for(Class<? extends PrimitiveEventGenerator> eg : plugin.getPegs()) {
                    if(!this.pushEG(eg)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    private void createSEGBank() {
        segBank = new EGBank<SimpleEventGenerator>() {
            @Override
            public boolean registerPlugin(PerceptionPlugin plugin) {
                for(Class<? extends SimpleEventGenerator> eg : plugin.getSegs()) {
                    if(!this.pushEG(eg)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    private void createCEGBank() {
        cegBank = new EGBank<ComplexEventGenerator>() {
            @Override
            public boolean registerPlugin(PerceptionPlugin plugin) {
                for(Class<? extends ComplexEventGenerator> eg : plugin.getCegs()) {
                    if(!this.pushEG(eg)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    public boolean registerPlugin(PerceptionPlugin plugin) {
        boolean isRegistrationOk = true;
        isRegistrationOk = pegBank.registerPlugin(plugin);
        if(isRegistrationOk) {
            isRegistrationOk = segBank.registerPlugin(plugin);
        }
        if(isRegistrationOk) {
            isRegistrationOk = cegBank.registerPlugin(plugin);
        }
        return isRegistrationOk;
    }

}
