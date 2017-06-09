package perception.pluginManager;

import perception.complex_event_generator.ComplexEventGenerator;
import perception.primitive_events_generator.PrimitiveEventGenerator;
import perception.simple_events_generator.SimpleEventGenerator;
import utils.Pair;

import java.util.Deque;
import java.util.LinkedList;

public abstract class PerceptionPlugin {

    private String pluginName;
    private int version;
    private Deque<Pair<String, Class<? extends PrimitiveEventGenerator>>> pegs;
    private Deque<Pair<String, Class<? extends SimpleEventGenerator>>> segs;
    private Deque<Pair<String, Class<? extends ComplexEventGenerator>>> cegs;

    public Deque<Pair<String, Class<? extends PrimitiveEventGenerator>>> getPegs() {
        return pegs;
    }

    protected PerceptionPlugin(String pluginName, int version) {
        this.pluginName = pluginName;
        this.version = version;
        this.pegs = new LinkedList<>();
        this.initPlugin();
    }

    final protected boolean isPegExists(String pegClassName) {
        for(Pair<String, Class<? extends PrimitiveEventGenerator>> p : pegs) {
            if(p.getFirst().equals(pegClassName)) {
                return true;
            }
        }
        return false;
    }

    final protected boolean isSegExists(String segClassName) {
        for(Pair<String, Class<? extends SimpleEventGenerator>> p : segs) {
            if(p.getFirst().equals(segClassName)) {
                return true;
            }
        }
        return false;
    }

    final protected boolean isCegExists(String cegClassName) {
        for(Pair<String, Class<? extends ComplexEventGenerator>> p : cegs) {
            if(p.getFirst().equals(cegClassName)) {
                return true;
            }
        }
        return false;
    }

    final protected boolean registerPEG(Class<? extends PrimitiveEventGenerator> pegClass, String pegClassName) {
        if(this.isPegExists(pegClassName)) {
            return false;
        }
        pegs.push(new Pair<>(pegClassName, pegClass));
        return true;
    }

    final protected boolean registerSEG(Class<? extends SimpleEventGenerator> segClass, String segClassName) {
        if(this.isPegExists(segClassName)) {
            return false;
        }
        segs.push(new Pair<>(segClassName, segClass));
        return true;
    }

    final protected boolean registerCEG(Class<? extends ComplexEventGenerator> cegClass, String cegClassName) {
        if(this.isPegExists(cegClassName)) {
            return false;
        }
        cegs.push(new Pair<>(cegClassName, cegClass));
        return true;
    }

    abstract protected void initPlugin();

}
