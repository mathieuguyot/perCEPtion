package perception.services;

import perception.events.ComplexEvent;
import perception.events.PrimitiveEvent;
import perception.events.SimpleEvent;

import java.io.Serializable;

/**
 * Abstract class that defines the Events logger service used to log informations about
 * generated events.
 * The user that uses perCEPtion framework has to create an implementation of this service to use the
 * perCEPtion core stuff.
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public abstract class PerceptionLogger implements Serializable {

    /**
     * Allows to log a generated primitive event
     * @param primitiveEvent The generated primitive event
     * @param pegName Name of the primitive event generator that generates this event
     */
    public abstract void logPrimitiveEvent(PrimitiveEvent primitiveEvent, String pegName);

    /**
     * Allows to log a generated simple event
     * @param simpleEvent The generated simple event
     * @param segName Name of the simple event generator that generates this event
     */
    public abstract void logSimpleEvent(SimpleEvent simpleEvent, String segName);

    /**
     * Allows to log a generated complex event
     * @param complexEvent The generated complex event
     * @param cegName Name of the complex event generator that generates this event
     */
    public abstract void logComplexEvent(ComplexEvent complexEvent, String cegName);

    /**
     * Allows to log an informative message
     * @param message Information to give to the user
     */
    public abstract void logMessage(String message);

    /**
     * Allows to log an error message
     * @param error Error to display for the user
     */
    public abstract void logError(String error);


}
