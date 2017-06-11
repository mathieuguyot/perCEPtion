package perception.configurator.activator.enums.constructors;

import java.util.Arrays;
import java.util.List;

/**
 * Enumération des types de paramètres pouvant être affectés à un EG
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public enum ParamTypes {

    LONG("long", long.class),
    STRING("String", String.class),
    INT("int", int.class),
    BOOLEAN("boolean", boolean.class),
    DOUBLE("double", double.class),
    FLOAT("float", float.class);

    // Attributs
    private String paramName;
    private Class<?> implementationClass;

    // Constructeur

    /**
     * Constructeur de la classe {@link ParamTypes}.
     *
     * @param paramName             nom du paramètre
     * @param implementationClass classe d'implémentation du paramètre
     */
    ParamTypes(String paramName, Class<?> implementationClass) {
        this.paramName = paramName;
        this.implementationClass = implementationClass;
    }

    // Services

    /**
     * Permet la récupération du {@link ParamTypes} à partir de son libellé.
     * Note : la recherche du libellé se fait en ignorant la case
     *
     * @param lab le libellé de l'objet recherché
     * @return l'objet de l'énumération correspondant au libellé fournit ou null
     * si le libellé est inconnu
     */
    public static ParamTypes fromLabel(String lab) {
        return valuesAsList().stream().filter(m -> m.getParamName().equalsIgnoreCase(lab)).findAny().orElse(null);
    }

    /**
     * Permet de récupérer la classe d'implémentation pour le nom de paramètre fourni.
     * @param paramName nom du paramètre dont on souhaite récupérer la classe d'implémentation
     * @return la classe d'implémentation correspondant au type de paramètre fourni
     */
    public static Class<?> getClassForParamName(String paramName) throws ClassNotFoundException {
        Class<?> implementationClass = ParamTypes.fromLabel(paramName).getClass();
        if (implementationClass == null) {
            throw new ClassNotFoundException();
        }
        return implementationClass;
    }

    /**
     * Permet d'obtenir une liste des valeurs de l'énumération
     * {@link ParamTypes}.
     *
     * @return la liste des valeurs de l'énumération {@link ParamTypes}
     */
    public static List<ParamTypes> valuesAsList() {
        return Arrays.asList(values());
    }

    // Accesseurs

    public String getParamName() {
        return paramName;
    }

    public Class<?> getImplementationClass() {
        return implementationClass;
    }

}
