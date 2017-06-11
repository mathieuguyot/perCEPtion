package perception.configurator.xml.manager.model;

/**
 * Classe permettant l'enregistrement des informations du fichier de configuration pour l'instanciation des
 * simples events.
 *
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class SimpleEventData extends SimpleAndComplexeEventData {

    /**
     * Constructeur de la classe {@link SimpleEventData}
     *
     * @param eventType - Classe correspondant à l'Event Generator à activer
     * @param eventName - Nom que l'on souhaite donner à l'Event Generator
     */
    public SimpleEventData(String eventType, String eventName) {
        super(eventType, eventName);
    }

}
