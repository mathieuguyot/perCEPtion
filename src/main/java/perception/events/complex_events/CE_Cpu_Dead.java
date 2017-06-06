package perception.events.complex_events;

import graph.CloudResourceType;
import perception.events.ComplexEvent;
import utils.Triplet;

import java.util.Deque;

public class CE_Cpu_Dead extends ComplexEvent {


    public CE_Cpu_Dead(Deque<Triplet<CloudResourceType, String, Integer>> resources) {
        super(resources);
    }

    public CE_Cpu_Dead(CloudResourceType type, String name, Integer score) {
        super(type, name, score);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("CE_Cpu_Dead [\n");
        for(Triplet<CloudResourceType, String, Integer> t : getResources()) {
            ret.append("\t" + t.getFirst() + "-" + t.getSecond() + "-" + t.getThird() + "\n");
        }
        ret.append("]");
        return ret.toString();
    }

}
