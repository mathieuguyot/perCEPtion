package perception.core;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Classe permettant de créer un Gestionnaire d'Event Generator
 * @param <EventGeneratorT> - Le type d'évènements à gérer
 */
public class EventGeneratorManager<EventGeneratorT extends EventGenerator>  {

    private Deque<EventGeneratorT> generators;
    private boolean logStream;

    /**
     * Constructeur de la classe {@link EventGeneratorManager}
     */
    public EventGeneratorManager() {
        generators = new LinkedList<>();
        logStream = false;
    }

    /**
     * Ajoute un Event Generator à gérer
     * @param eventGenerator - L'Event Generator à gérer
     */
    public void addEventGenerator(EventGeneratorT eventGenerator) {
        eventGenerator.setLogGeneratedEvents(logStream);
        generators.add(eventGenerator);
    }

    /**
     * Accesseur des {@link EventGenerator} gérés
     * @return Les Event Generator gérés
     */
    public Deque<EventGeneratorT> getGenerators() {
        return generators;
    }

    /**
     * Active le logging pour les évènements générés
     * @param logStream - <code>true</code> si l'on permet de logger le flux d'évènements générés, <code>false</code> sinon
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
