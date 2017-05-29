package perception.core;

import perception.services.EventsLogger;
import perception.services.implementations.SysoutEventsLogger;

public class LoggerAccess {

    private static EventsLogger logger = new SysoutEventsLogger();

    public static EventsLogger getLogger() {
        return logger;
    }

    public static void setLogger(EventsLogger logger) {
        LoggerAccess.logger = logger;
    }

}
