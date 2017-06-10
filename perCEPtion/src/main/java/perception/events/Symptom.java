package perception.events;

import graph.CloudResourceType;
import utils.Triplet;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Classe représentant un symptôme.
 * Cette classe est un évènement, donc il s'agit d'une extension de {@link Event}.
 * Un symptôme contient une ou plusieurs informations système (RAM, Disque, CPU)
 * d'une ou plusieurs ressources.
 * Un symptôme peut-être généré par un {@link perception.simple_events_generator.SimpleEventGenerator}
 * ou un {@link perception.complex_event_generator.ComplexEventGenerator}.
 */
public abstract class Symptom extends Event {

    //List of resources impacted by the event => Triplet(Cloud resources type, name and score)
    private Deque<Triplet<CloudResourceType, String, Integer>> resources;
    private int cloudResourcesTotalScore;

    /**
     * Constructor of a symptom
     *
     * @param resources The list of resources that are implied in the symptom
     */
    public Symptom(Deque<Triplet<CloudResourceType, String, Integer>> resources) {
        super(EventType.SYMPTOM);
        this.resources = resources;
    }

    /**
     * Constructor of a symptom (that focus on one cloud resource)
     *
     * @param type The type of the cloud resource
     * @param name The name of the cloud resource
     * @param score The total score of the cloud resource
     */
    public Symptom(CloudResourceType type, String name, Integer score) {
        super(EventType.SYMPTOM);
        this.resources = new LinkedList<>();
        this.resources.push(new Triplet<>(type, name, score));
    }

    /**
     * Getter on all resources impacted by the symptom
     * @return All resources impacted by the symptom
     */
    public Deque<Triplet<CloudResourceType, String, Integer>> getCloudResources() {
        return resources;
    }

}
