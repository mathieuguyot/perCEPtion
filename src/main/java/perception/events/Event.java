package perception.events;

/**
 * Class that represent an event
 */
public abstract class Event {

    final private  EventType eventType; //Event type

    /**
     * Constructor of an event
     * @param eventType The type of the event
     */
    public Event(EventType eventType) {
        this.eventType = eventType;
    }

    /**
     * Getter on the event type
     * @return
     */
    public EventType getEventType() {
        return eventType;
    }

}
