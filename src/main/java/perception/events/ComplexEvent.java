package perception.events;

import graph.CloudResourceType;
import utils.Triplet;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Class that represent a complex event.
 * This class is an event so it extends from event.
 * A complex event contain one or multiple monitoring information (eg. ram, disk, cpu)
 * of one or multiple cloud resource.
 * A complex event is generated using complex event generators (CEG)
 */
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

    /**
     * Constructor of a complex event (that focus only one cloud resource)
     *
     * @param type The type of the cloud resource
     * @param name The name of the cloud resource
     * @param score The total score of the cloud resource
     */
    public ComplexEvent(CloudResourceType type, String name, Integer score) {
        super(EventType.COMPLEX);
        this.resources = new LinkedList<>();
        this.resources.push(new Triplet<>(type, name, score));
    }

    /**
     * Getter on all resources impacted by the event
     * @return All resources impacted by the event
     */
    public Deque<Triplet<CloudResourceType, String, Integer>> getResources() {
        return resources;
    }

}
