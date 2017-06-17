package perception.configurator.xml.manager.model;

/**
 * Classe permettant l'enregistrement des informations pour l'instanciation des
 * primitives events à partir des éléments du fichier de configuration XML.
 *
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class PrimitiveEventData {

    // Attributs
    private String name;
    private String type;
    private Long runTime;

    /**
     * Constructeur de la classe {@link PrimitiveEventData}.
     *
     * @param name
     *              nom unique du primitive event
     * @param type
     *              type du primitive event
     * @param runTime
     *              runtime à appliquer au primitive event
     */
    public PrimitiveEventData(String name, String type, Long runTime) {
        this.name = name;
        this.type = type;
        this.runTime = runTime;
    }

    // Accesseurs
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Long getRunTime() {
        return runTime;
    }

    @Override
    public String toString() {
        return "\n\tPrimitiveEventData{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", runTime=" + runTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrimitiveEventData primitiveEventData = (PrimitiveEventData) o;

        if (name != null ? !name.equals(primitiveEventData.name) : primitiveEventData.name != null) return false;
        if (type != null ? !type.equals(primitiveEventData.type) : primitiveEventData.type != null) return false;
        return runTime != null ? runTime.equals(primitiveEventData.runTime) : primitiveEventData.runTime == null;
    }

}
