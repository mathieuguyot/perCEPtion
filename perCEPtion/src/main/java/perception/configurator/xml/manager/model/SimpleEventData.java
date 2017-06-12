package perception.configurator.xml.manager.model;

import utils.Pair;

import java.util.List;

/**
 * Classe permettant l'enregistrement des informations du fichier de configuration pour l'instanciation des
 * simples events.
 *
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class SimpleEventData extends SimpleAndComplexEventData {

    /**
     * Constructeur de la classe {@link SimpleEventData}
     *
     * @param eventType - Classe correspondant à l'Event Generator à activer
     * @param eventName - Nom que l'on souhaite donner à l'Event Generator
     * @param params - Liste des types et valeurs de param
     */
    public SimpleEventData(String eventType, String eventName, List<Pair<String, String>> params) {
        super(eventType, eventName, params);
    }

}
