package perception.core;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Class that allow to create a manager of event generator.
 * @param <EventGeneratorT> The type of the event generator managed
 */
public class EventGeneratorManager<EventGeneratorT extends EventGenerator>  {

    private Deque<EventGeneratorT> generators;
    private boolean logStream;

    /**
     * Constructor of the event generator
     */
    public EventGeneratorManager() {
        generators = new LinkedList<>();
        logStream = false;
    }

    /**
     * Add a event generator to manage
     * @param eventGenerator The event generator to manage
     */
    public void addEventGenerator(EventGeneratorT eventGenerator) {
        eventGenerator.setLogGeneratedEvents(logStream);
        generators.add(eventGenerator);
    }

    /**
     * Getter on the managed event generator
     * @return The managed events generators
     */
    public Deque<EventGeneratorT> getGenerators() {
        return generators;
    }

    /**
     * Allow to log the stream of the generated events
     * @param logStream true allow to log the stream of the generated events
     */
    public void setLogStream(boolean logStream) {
        if(logStream != this.logStream) {
            this.logStream = logStream;
            for(EventGeneratorT peg : generators) {
                peg.setLogGeneratedEvents(logStream);
            }
        }
    }

}
