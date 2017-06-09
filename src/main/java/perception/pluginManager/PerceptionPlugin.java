package perception.pluginManager;

import perception.complex_event_generator.ComplexEventGenerator;
import perception.primitive_events_generator.PrimitiveEventGenerator;
import perception.simple_events_generator.SimpleEventGenerator;

import java.util.Deque;
import java.util.LinkedList;

public abstract class PerceptionPlugin {

    private String pluginName;
    private int version;
    private Deque<Class<? extends PrimitiveEventGenerator>> pegs;
    private Deque<Class<? extends SimpleEventGenerator>> segs;
    private Deque<Class<? extends ComplexEventGenerator>> cegs;

    public PerceptionPlugin(String pluginName, int version) {
        this.pluginName = pluginName;
        this.version = version;
        this.pegs = new LinkedList<>();
        this.segs = new LinkedList<>();
        this.cegs = new LinkedList<>();
        this.initPlugin();
    }

    abstract protected void initPlugin();

    final public Deque<Class<? extends PrimitiveEventGenerator>> getPegs() {
        return pegs;
    }

    final public String getPluginName() {
        return pluginName;
    }

    final public int getVersion() {
        return version;
    }

    final public Deque<Class<? extends SimpleEventGenerator>> getSegs() {
        return segs;
    }

    final public Deque<Class<? extends ComplexEventGenerator>> getCegs() {
        return cegs;
    }

    final protected boolean isPegExists(String pegClassName) {
        for(Class<? extends PrimitiveEventGenerator> p : pegs) {
            if(p.getSimpleName().equals(pegClassName)) {
                return true;
            }
        }
        return false;
    }

    final protected boolean isSegExists(String segClassName) {
        for(Class<? extends SimpleEventGenerator> p : segs) {
            if(p.getSimpleName().equals(segClassName)) {
                return true;
            }
        }
        return false;
    }

    final protected boolean isCegExists(String cegClassName) {
        for(Class<? extends ComplexEventGenerator> p : cegs) {
            if(p.getSimpleName().equals(cegClassName)) {
                return true;
            }
        }
        return false;
    }

    final protected boolean registerPEG(Class<? extends PrimitiveEventGenerator> pegClass) {
        if(this.isPegExists(pegClass.getSimpleName())) {
            return false;
        }
        pegs.push(pegClass);
        return true;
    }

    final protected boolean registerSEG(Class<? extends SimpleEventGenerator> segClass) {
        if(this.isPegExists(segClass.getSimpleName())) {
            return false;
        }
        segs.push(segClass);
        return true;
    }

    final protected boolean registerCEG(Class<? extends ComplexEventGenerator> cegClass) {
        if(this.isPegExists(cegClass.getSimpleName())) {
            return false;
        }
        cegs.push(cegClass);
        return true;
    }

}
