package utils;

import java.util.Deque;

/**
 * Sysout logger wrapper to create color logs
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class SysOutLogger {

    /**
     * Log a message (new line) on sysout
     * @param message The message to log
     */
    public static void logLn(String message) {
        SysOutLogger.logLn(message, Color.BLACK);
    }

    /**
     * Log a message (new line) with the given color on sysout
     * @param message The message to log
     * @param color The color used to log the message
     */
    public static void logLn(String message, Color color) {
        System.out.println(color.getColorTag() + message + Color.BLACK.getColorTag());
    }

    /**
     * Log a message on sysout
     * @param message The message to log
     */
    public static void log(String message) {
        SysOutLogger.log(message, Color.BLACK);
    }

    /**
     * Log a message with the given color on sysout
     * @param message The message to log
     * @param color The color used to log the message
     */
    public static void log(String message, Color color) {
        System.out.print(color.getColorTag() + message + Color.BLACK.getColorTag());
    }

}


