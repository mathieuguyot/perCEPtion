package perception.services.implementations;

import perception.events.ComplexEvent;
import perception.events.PrimitiveEvent;
import perception.events.SimpleEvent;
import perception.services.PerceptionLogger;

/**
 * Default sysout implementation class for perception logger
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

}
