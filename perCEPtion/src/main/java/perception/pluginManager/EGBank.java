package perception.pluginManager;

import perception.core.EventGenerator;

import java.util.Deque;
import java.util.LinkedList;

public abstract class EGBank<EG extends EventGenerator> {

    private Deque<Class<? extends EG>> eventsGenerators;

    public EGBank() {
        eventsGenerators = new LinkedList<>();
    }

    public abstract boolean registerPlugin(PerceptionPlugin plugin);

    protected boolean pushEG(Class<? extends EG> eg) {
        if(this.isEgRegister(eg.getSimpleName())) {
            return false;
        }
        this.eventsGenerators.push(eg);
        return true;
    }

    public boolean isEgRegister(String egName) {
        for(Class<? extends EG> p : eventsGenerators) {
            if(p.getSimpleName().equals(egName)) {
                return true;
            }
        }
        return false;
    }

    public Class<? extends EG> getClassForEGName(String egName) throws ClassNotFoundException {
        for(Class<? extends EG> p : eventsGenerators) {
            if(p.getSimpleName().equals(egName)) {
                return p;
            }
        }
        String errMessage = "Cannot find event generator : " + egName;
        throw new ClassNotFoundException(errMessage);
    }

}
