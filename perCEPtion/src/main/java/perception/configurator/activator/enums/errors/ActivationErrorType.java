package perception.configurator.activator.enums.errors;

import java.util.Arrays;
import java.util.List;

/**
 * Enumération des types d'erreurs rencontrés durant l'activation des EG
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public enum ActivationErrorType {

    CLASS_NOT_FOUND("Event Generator introuvable."),
    WRONG_PARAMETERS("Constructeur inexistant."),
    ACCESS_NOT_GRANTED("Impossible d'accéder au constructeur."),
    CONSTRUCTION_EXCEPTION("Erreur lors de le création du générateur."),
    IMPOSSIBLE_INSTANCIATION("Impossible d'instancier cette classe.");

    // Attributs
    private String errorInfo;

    // Constructeur
    /**
     * Constructeur de la classe {@link ActivationErrorType}.
     *
     * @param errorInfo
     *            message explicatif de l'erreur
     */
    ActivationErrorType(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    // Services

    /**
     * Permet la récupération du {@link ActivationErrorType} à partir de son libellé.
     * Note : la recherche du libellé se fait en ignorant la case
     *
     * @param lab
     *            le libellé de l'objet recherché
     * @return l'objet de l'énumération correspondant au libellé fournit ou null
     *         si le libellé est inconnu
     */
    public static ActivationErrorType fromLabel(String lab) {
        return valuesAsList().stream().filter(m -> m.getLabel().equalsIgnoreCase(lab)).findAny().orElse(null);
    }

    /**
     * Permet d'obtenir une liste des valeurs de l'énumération
     * {@link ActivationErrorType}.
     *
     * @return la liste des valeur de l'énumération {@link ActivationErrorType}
     */
    public static List<ActivationErrorType> valuesAsList() {
        return Arrays.asList(values());
    }

    // Accesseurs

    public String getLabel() {
        return errorInfo;
    }


}
