package perception.events;

/**
 * Classe abstraite représentant un évènement
 */
public abstract class Event {

    final private  EventType eventType; //Type d'évènement

    /**
     * Constructeur de la classe {@link Event}
     * @param eventType - Le type de l'évènement
     */
    public Event(EventType eventType) {
        this.eventType = eventType;
    }

    /**
     * Accesseur du type d'évènement
     * @return Le type de l'évènement
     */
    public EventType getEventType() {
        return eventType;
    }

}
