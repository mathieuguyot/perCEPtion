package perception.services.implementations;

import perception.events.ComplexEvent;
import perception.events.PrimitiveEvent;
import perception.events.SimpleEvent;
import perception.services.PerceptionLogger;

/**
 * Implémentation du logger dans la console par défaut
 */
public class SysoutPerceptionLogger extends PerceptionLogger {

    @Override
    public void logPrimitiveEvent(PrimitiveEvent event, String pegName) {
        System.out.println(event.toString());
    }

    @Override
    public void logSimpleEvent(SimpleEvent simpleEvent, String segName) {
        System.out.println(simpleEvent.toString());
    }

    @Override
    public void logComplexEvent(ComplexEvent complexEvent, String cegName) {
        System.out.println(complexEvent.toString());
    }

    @Override
    public void logMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void logError(String error) {
        System.err.println(error);
    }

}
