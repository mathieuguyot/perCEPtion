package perception.core;

import perception.services.PerceptionRunResource;

import java.io.Serializable;

/**
 * Classe abstraite représenant un Event Generator.
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT1
 */
public abstract class EventGenerator implements Serializable, PerceptionRunResource {

    private boolean logGeneratedEvents;
    private boolean hasToGenerateEvents;
    private String name;

    /**
     * Constructeur de la classe {@link EventGenerator}
     * @param name - Nom du générateur à créer
     */
    public EventGenerator(String name) {
        this.hasToGenerateEvents = true;
        this.logGeneratedEvents = false;
        this.name = name;
    }

    /**
     * Active ou non le logging pour les évènement générés.
     * @param logGeneratedEvents - <code>true</code> si le logging est activé, <code>false</code> sinon
     */
    public void setLogGeneratedEvents(boolean logGeneratedEvents) {
        this.logGeneratedEvents = logGeneratedEvents;
    }

    /**
     * Accesseur de l'activation des logs.
     * @return <code>true</code> si les évènements sont loggés, <code>false</code> sinon
     */
    public boolean isLogGeneratedEvents() {
        return logGeneratedEvents;
    }

    /**
     * Accesseur de la génération d'évènements
     * @return <code>true</code> si les évènements doivent être générés, <code>false</code> sinon
     */
    public boolean isHasToGenerateEvents() {
        return hasToGenerateEvents;
    }

    /**
     * Active ou non la génération d'évènements
     * @param hasToGenerateEvents - <code>true</code> si les évènements doivent être générés, <code>false</code> sinon
     */
    public void setHasToGenerateEvents(boolean hasToGenerateEvents) {
        this.hasToGenerateEvents = hasToGenerateEvents;
    }

    /**
     * Acceseur du nom de l'Event Generator
     * @return Le nom de l'Event Generator
     */
    public String getName() {
        return name;
    }


    @Override
    public boolean beforeRun(PerceptionRunContext ctx) {
        return true;
    }

    @Override
    public void endRun() {

    }

}
