package perception.services.implementations;

import perception.events.PrimitiveEvent;
import perception.events.SimpleEvent;
import perception.services.EventsLogger;

public class SysoutEventsLogger extends EventsLogger {

    @Override
    public void logPrimitiveEvent(PrimitiveEvent event, String pegName) {
        System.out.println(event.toString());
    }

    @Override
    public void logSimpleEvent(SimpleEvent simpleEvent, String segName) {
        System.out.println(simpleEvent.toString());
    }

}
