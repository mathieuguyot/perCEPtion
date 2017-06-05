package perception.services.implementations;

import perception.events.PrimitiveEvent;
import perception.events.SimpleEvent;
import perception.services.PerceptionLogger;

public class SysoutPerceptionLogger extends PerceptionLogger {

    @Override
    public void logPrimitiveEvent(PrimitiveEvent event, String pegName) {
        System.out.println(event.toString());
    }

    @Override
    public void logSimpleEvent(SimpleEvent simpleEvent, String segName) {
        System.out.println(simpleEvent.toString());
    }

}
