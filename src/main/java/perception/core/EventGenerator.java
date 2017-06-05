package perception.core;

import perception.services.PerceptionLogger;
import perception.services.PerceptionRunResource;

import java.io.Serializable;

/**
 * Abstract class that represents an event generator.
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT1
 */
public abstract class EventGenerator implements Serializable, PerceptionRunResource {

    private PerceptionLogger perceptionLogger;
    private boolean logGeneratedEvents;
    private boolean hasToGenerateEvents;
    private String name;

    /**
     * Constructor of the event generator
     */
    public EventGenerator(String name) {
        this.hasToGenerateEvents = true;
        this.logGeneratedEvents = false;
        this.name = name;
        perceptionLogger = null;
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

    /**
     * Getter on the name of the event generator
     * @return
     */
    public String getName() {
        return name;
    }

    public PerceptionLogger getPerceptionLogger() {
        return perceptionLogger;
    }

    public void setPerceptionLogger(PerceptionLogger perceptionLogger) {
        this.perceptionLogger = perceptionLogger;
    }

    @Override
    public boolean beforeRun(PerceptionRunContext ctx) {
        this.perceptionLogger = ctx.getPerceptionLogger();
        return true;
    }

    @Override
    public void endRun() {

    }

}
