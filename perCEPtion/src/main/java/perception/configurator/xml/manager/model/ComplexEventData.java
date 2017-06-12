package perception.configurator.xml.manager.model;

import utils.Pair;

import java.util.List;

/**
 * Classe permettant l'enregistrement des informations du fichier de configuration pour l'instanciation des
 * complexes events.
 *
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class ComplexEventData extends SimpleAndComplexEventData {

    /**
     * Constructeur de la classe {@link ComplexEventData}
     * @param eventType - Classe correspondant à l'Event Generator à activer
     * @param eventName - Nom que l'on souhaite donner à l'Event Generator
     * @param params - Liste de tuple comportant les types et valeur de paramètres
     */
    public ComplexEventData(String eventType, String eventName, List<Pair<String, String>> params) {
        super(eventType, eventName, params);
    }

}
