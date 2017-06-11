package perception.configurator.xml.manager.model;

/**
 * Classe permettant l'enregistrement des informations du fichier de configuration pour l'instanciation des
 * complexes events.
 *
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class ComplexEventData extends SimpleAndComplexEventData {

    /**
     * Constructeur de la classe {@link SimpleAndComplexEventData}
     *
     * @param eventType - Classe correspondant à l'Event Generator à activer
     * @param eventName - Nom que l'on souhaite donner à l'Event Generator
     */
    public ComplexEventData(String eventType, String eventName) {
        super(eventType, eventName);
    }

}
