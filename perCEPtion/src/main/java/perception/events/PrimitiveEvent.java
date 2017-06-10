package perception.events;

import graph.CloudResourceType;

/**
 * Classe représentant un évènement primitif.
 * Cette classe est un évènement, donc il s'agit d'une extension de {@link Event}.
 * Un évènement primitif contient une information système (RAM, CPU, Disque) d'une ressource.
 * Un évènement primitif est généré via un {@link perception.primitive_events_generator.PrimitiveEventGenerator}
 */
public abstract class PrimitiveEvent extends Event {

    final private String cloudResourceName;
    final private CloudResourceType cloudResourceType;
    final private int score;

    /**
     * Le constructeur de la classe {@link PrimitiveEvent}
     * @param cloudResourceName - Le nom de la ressource
     * @param cloudResourceType - Le type de la ressource
     */
    public PrimitiveEvent(String cloudResourceName, CloudResourceType cloudResourceType, int score)
    {
        super(EventType.PRIMITIVE);
        this.cloudResourceName = cloudResourceName;
        this.cloudResourceType = cloudResourceType;
        this.score = score;
    }

    /**
     * Accesseur du nom de la ressource
     * @return Le nom de la ressource
     */
    public String getCloudResourceName() {
        return cloudResourceName;
    }

    /**
     * Accesseur du type de la ressource
     * @return Le nom du type de la ressource
     */
    public CloudResourceType getCloudResourceType() {
        return cloudResourceType;
    }

    /**
     * Accesseur du score de l'évènement primitif
     * @return Le score de l'évènement primitif
     */
    public int getScore() {
        return score;
    }

}
