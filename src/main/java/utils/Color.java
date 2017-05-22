package utils;

/**
 * Enum that allow to list some colors
 */
public enum Color {

    BLACK      ("\033[0m"),
    RED        ("\033[31m"),
    GREEN      ("\033[32m"),
    YELLOW     ("\033[33m"),
    BLUE       ("\033[34m"),
    MAGENTA    ("\033[35m"),
    CYAN       ("\033[36m"),
    WHITE      ("\033[37m");

    private final String colorTag; //Color tag to do some fancy looking logs on cout !

    /**
     * Constructor of a color
     * @param colorTag The color tag
     */
    Color(String colorTag) {
        this.colorTag = colorTag;
    }

    /**
     * Getter on the color tag
     * @return The color tag
     */
    public String getColorTag() {
        return this.colorTag;
    }

}