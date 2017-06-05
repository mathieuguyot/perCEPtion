package perception.events;

import graph.CloudResourceType;
import utils.Triplet;

import java.util.Deque;

public abstract class ComplexEvent extends Event {

    //List of resources impacted by the event => Triplet(Cloud resources type, name and score)
    private Deque<Triplet<CloudResourceType, String, Integer>> resources;

    /**
     * Constructor of a complex event
     *
     * @param resources The list of resources that are implied in the complex event
     */
    public ComplexEvent(Deque<Triplet<CloudResourceType, String, Integer>> resources) {
        super(EventType.COMPLEX);
        this.resources = resources;
    }

    public Deque<Triplet<CloudResourceType, String, Integer>> getResources() {
        return resources;
    }

}
