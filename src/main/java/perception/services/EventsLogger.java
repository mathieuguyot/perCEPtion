package perception.services;

import perception.events.PrimitiveEvent;
import perception.events.SimpleEvent;

/**
 * Abstract class that define the Events logger service used to log informations about
 * generated events.
 * The user that use perCEPtion framework has to create an implementation of this service to use the
 * perCEPtion core stuff.
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public abstract class EventsLogger {

    /**
     * Allow to log a generated primitive event
     * @param event The generated primitive event
     */
    public abstract void logPrimitiveEvent(PrimitiveEvent event);

    /**
     * Allow to log a generated simple event
     * @param simpleEvent The generated simple event
     */
    public abstract void logSimpleEvent(SimpleEvent simpleEvent);

}
