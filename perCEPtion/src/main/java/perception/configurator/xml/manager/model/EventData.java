package perception.configurator.xml.manager.model;

import utils.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Conteneur pour le paramétrage des Simple et Complex Event Generator. Générés lors du parsing et fourni aux Activator.
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public abstract class SimpleAndComplexEventData {

    // Attributs
    private String eventType;
    private String eventName;
    private List<Pair<String, String>> params;

    /**
     * Constructeur de la classe {@link SimpleAndComplexEventData}
     * @param eventType - Classe correspondant à l'Event Generator à activer
     * @param eventName - Nom que l'on souhaite donner à l'Event Generator
     */
    public SimpleAndComplexEventData(String eventType, String eventName) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.params = new ArrayList<>();
    }

    /**
     * Constructeur de la classe {@link SimpleAndComplexEventData}
     * @param eventType - Classe correspondant à l'Event Generator à activer
     * @param eventName - Nom que l'on souhaite donner à l'Event Generator
     * @param params - Liste de tuple comportant les types et valeur de paramètres
     */
    public SimpleAndComplexEventData(String eventType, String eventName, List<Pair<String, String>> params) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.params = params;
    }


    /**
     * Ajoute un couple {@link Pair} (Nom de l'attribut, Valeur de l'attribut) à la liste des paramètres
     * @param name - Nom de l'attribut à affecter
     * @param value - Valeur de l'attribut
     */
    public void addParams(String name, String value) {
        params.add(new Pair<String, String>(name, value));
    }

    public List<Pair<String, String>> getParamsList() {
        return params;
    }


    // Accesseurs
    public String getEventType() {
        return eventType;
    }

    public String getEventName() {
        return eventName;
    }

    // Modificateurs
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

}
