package perception.events;

import graph.CloudResourceType;
import utils.Triplet;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Classe représentant un évènement complexe.
 * Cette classe est un évènement, donc il s'agit d'une extension de {@link Event}.
 * Un évènement complexe contient une ou plusieurs informations systèmes (RAM, CPU, Disque, etc.)
 * de une ou plusieurs ressource de cloud.
 * Un évènement complexe est généré via un {@link perception.complex_event_generator.ComplexEventGenerator}
 */
public abstract class ComplexEvent extends Event {

    //Liste des ressources impactées par l'évènement => Triplet(Type de la ressource, nom et score)
    private Deque<Triplet<CloudResourceType, String, Integer>> resources;

    /**
     * Constructeur de la classe {@link ComplexEvent}
     *
     * @param resources - La liste des ressources impliquées par l'évènement complexe
     */
    public ComplexEvent(Deque<Triplet<CloudResourceType, String, Integer>> resources) {
        super(EventType.COMPLEX);
        this.resources = resources;
    }

    /**
     * Constructeur de la classe {@link ComplexEvent} (dédié à une seule ressource)
     *
     * @param type - Le type de la ressource
     * @param name - Le nom de la ressource
     * @param score - Le score total de la ressource
     */
    public ComplexEvent(CloudResourceType type, String name, Integer score) {
        super(EventType.COMPLEX);
        this.resources = new LinkedList<>();
        this.resources.push(new Triplet<>(type, name, score));
    }

    /**
     * Accesseur des ressources impactées par l'évènement
     * @return Toutes les ressources impactées par l'évènement
     */
    public Deque<Triplet<CloudResourceType, String, Integer>> getResources() {
        return resources;
    }

}
