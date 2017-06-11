package perception.configurator.xml.enums.parser;

import java.util.Arrays;
import java.util.List;

/**
 * Enumération listant les erreurs possible propre au parsing du fichier XML.
 * 
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 *
 */
public enum ParsingErrorType {

	PRIMITIVES_EVENT_INVALID_NODE(
			"Impossible de trouver les primitives events dans le fichier XML fournit. Arrêt du traitement du fichier."),

    PRIMITIVES_EVENT_INVALID_NAME("Impossible de trouver le nom du primitive event."),

    PRIMITIVES_EVENT_INVALID_TYPE("Impossible de trouver le type du primitive event."),

    PRIMITIVES_EVENT_INVALID_RUNTIME("Impossible de trouver le runtime du primitive event."),

    PRIMITIVES_EVENT_DUPLICATED_NAME("Un même nom a été renseigné pour deux primitives events.");

	// Si l'attribut enabled de la balise <primitive> est abs on considère que le primitive event est à activer
	//INVALID_PRIMITIVE_ENABLED_ATTR("Impossible de trouver l'attribut enabled du primitive event.");

    // Attributs
	private String errorInfo;
    private List<String> complements;

	// Constructeur
	/**
	 * Constructeur de la classe {@link ParsingErrorType}.
	 * 
	 * @param errorInfo
	 *            message explicatif de l'erreur
	 */
	ParsingErrorType(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	// Services

	/**
	 * Permet la récupération du {@link ParsingErrorType} à partir de son
	 * libellé.
	 *
	 * Note : la recherche du libellé se fait en ignorant la case
	 * 
	 * @param lab
	 *            le libellé de l'objet recherché
	 * @return l'objet de l'énumération correspondant au libellé fournit ou null
	 *         si le libellé est inconnu
	 */
	public static ParsingErrorType fromLabel(String lab) {
		return valuesAsList().stream().filter(m -> m.getLabel().equalsIgnoreCase(lab)).findAny().orElse(null);
	}

	/**
	 * Permet d'obtenir une liste des valeurs de l'énumération
	 * {@link ParsingErrorType}.
	 * 
	 * @return la liste des valeur de l'énumération {@link ParsingErrorType}
	 */
	public static List<ParsingErrorType> valuesAsList() {
		return Arrays.asList(values());
	}

	// Accesseurs

	public String getLabel() {
		return errorInfo;
	}

    public List<String> getCompements() {
        return complements;
    }

    public void addComplement(String complement) {
	    this.complements.add(complement);
    }

    @Override
    public String toString() {
	    String toStr = "ParsingErrorType{" +
                "errorInfo='" + errorInfo + '\'';
	    if(!complements.isEmpty()) {
	        toStr += ", pour : ";
            for (String str : complements) {
                toStr += "\n" + str;
            }
        }
        return toStr + '}';
    }
}
