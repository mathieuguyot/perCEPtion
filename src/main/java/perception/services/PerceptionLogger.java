package perception.services;

import perception.events.ComplexEvent;
import perception.events.PrimitiveEvent;
import perception.events.SimpleEvent;

import java.io.Serializable;

/**
 * Abstract class that define the Events logger service used to log informations about
 * generated events.
 * The user that use perCEPtion framework has to create an implementation of this service to use the
 * perCEPtion core stuff.
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public abstract class PerceptionLogger implements Serializable {

    /**
     * Allow to log a generated primitive event
     * @param primitiveEvent The generated primitive event
     * @param pegName Name of the primitive event generator that generate this event
     */
    public abstract void logPrimitiveEvent(PrimitiveEvent primitiveEvent, String pegName);

    /**
     * Allow to log a generated simple event
     * @param simpleEvent The generated simple event
     * @param segName Name of the simple event generator that generate this event
     */
    public abstract void logSimpleEvent(SimpleEvent simpleEvent, String segName);

    /**
     * Allow to log a generated complex event
     * @param complexEvent The generated complex event
     * @param cegName Name of the complex event generator that generate this event
     */
    public abstract void logComplexEvent(ComplexEvent complexEvent, String cegName);

}
