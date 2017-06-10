package perception.configurator.xml.enums.parser;

import java.util.Arrays;
import java.util.List;

/**
 * Enumération listant les erreurs possibles propres au parsing du fichier XML.
 * 
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 *
 */
public enum ParsingErrorType {

	INVALID_PRIMITIVES_NODE(
			"Impossible de trouver les primitives events dans le fichier XML fourni. Arrêt du traitement du fichier."),

	INVALID_PRIMITIVE_NAME("Impossible de trouver le nom du primitive event."),

	INVALID_PRIMITIVE_RUNTIME("Impossible de trouver le runtime du primitive event."),

	INVALID_PRIMITIVE_ENABLED_ATTR("Impossible de trouver l'attribut enabled du primitive event.");

	// Attributs
	private String errorInfo;

	// Constructeur
	/**
	 * Constructeur de la classe {@link ParsingErrorType}.
	 * 
	 * @param errorInfo - message explicatif de l'erreur
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
	 * @param lab - le libellé de l'objet recherché
	 * @return l'objet de l'énumération correspondant au libellé fourni ou <code>null</code>
	 *         si le libellé est inconnu
	 */
	public static ParsingErrorType fromLabel(String lab) {
		return valuesAsList().stream().filter(m -> m.getLabel().equalsIgnoreCase(lab)).findAny().orElse(null);
	}

	/**
	 * Permet d'obtenir une liste des valeurs de l'énumération
	 * {@link ParsingErrorType}.
	 * 
	 * @return la liste des valeurs de l'énumération {@link ParsingErrorType}
	 */
	public static List<ParsingErrorType> valuesAsList() {
		return Arrays.asList(values());
	}

	// Accesseurs

	public String getLabel() {
		return errorInfo;
	}

}
