package perception.events;

import graph.CloudResourceType;

/**
 * Classe représentant un évènement simple.
 * Cette classe est un évènement, donc il s'agit d'une extension de {@link Event}.
 * Un évènement simple contient une ou plusieurs informations système (RAM, Disque, CPU) d'une ressource.
 * Un évènement simple est généré via un {@link perception.simple_events_generator.SimpleEventGenerator}
 */
public abstract class SimpleEvent extends Event {

    final private String cloudResourceName;
    final private CloudResourceType cloudResourceType;
    final private int score;

    /**
     * Le constructeur de la classe {@link SimpleEvent}
     * @param cloudResourceName - Le nom de la ressource
     * @param cloudResourceType - Le type de la ressource
     */
    public SimpleEvent(String cloudResourceName, CloudResourceType cloudResourceType, int score) {
        super(EventType.SIMPLE);
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
     * @return Le type de la ressource
     */
    public CloudResourceType getCloudResourceType() {
        return cloudResourceType;
    }

    /**
     * Accesseur du score de la ressource
     * @return Le score de la ressource
     */
    public int getScore() {
        return score;
    }

}
