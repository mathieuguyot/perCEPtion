package perception.core;

import java.io.Serializable;

/**
 * Abstract class that represents an event generator.
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT1
 */
public abstract class EventGenerator implements Serializable {

    private boolean logGeneratedEvents;
    private boolean hasToGenerateEvents;

    /**
     * Constructor of the event generator
     */
    public EventGenerator() {
        this.hasToGenerateEvents = true;
        this.logGeneratedEvents = false;
    }

    /**
     * Allow to log or not the generated events
     * @param logGeneratedEvents true if the generated events are logged, false otherwise
     */
    public void setLogGeneratedEvents(boolean logGeneratedEvents) {
        this.logGeneratedEvents = logGeneratedEvents;
    }

    /**
     * Getter on the log of generated events
     * @return true if the events are logged, false otherwise
     */
    public boolean isLogGeneratedEvents() {
        return logGeneratedEvents;
    }

    /**
     * Getter on the generation of events
     * @return true if the events have to be generated, false otherwise
     */
    public boolean isHasToGenerateEvents() {
        return hasToGenerateEvents;
    }

    /**
     * Allow to generate or not the events
     * @param hasToGenerateEvents true if the events have to be generated, false otherwise
     */
    public void setHasToGenerateEvents(boolean hasToGenerateEvents) {
        this.hasToGenerateEvents = hasToGenerateEvents;
    }

}