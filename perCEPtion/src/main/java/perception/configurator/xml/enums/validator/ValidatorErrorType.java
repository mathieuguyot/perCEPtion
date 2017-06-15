package perception.configurator.xml.enums.validator;

import java.util.Arrays;
import java.util.List;

/**
 * Enumération listant les erreurs possibles au moment de la validation de
 * fichier XML.
 * 
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 *
 */
public enum ValidatorErrorType {

	ERROR("La grammaire du fichier XML n'est pas conforme à la spécification du schéma XSD."),
	FATAL_ERROR("Le fichier XML ne respecte pas les règles de formatage XML."),
	WARNING("Warning lié au parsing du fichier XML.");

	// Attributs
	private String label;

	// Constructeur
	/**
	 * Constructeur de la classe {@link ValidatorErrorType}.
	 * 
	 * @param errorInfo - message explicatif de l'erreur
	 */
	ValidatorErrorType(String errorInfo) {
		this.label = errorInfo;
	}

	// Services

	/**
	 * Permet la récupération du {@link ValidatorErrorType} à partir de son
	 * libellé. Note : la recherche du libellé se fait en ignorant la casse
	 * 
	 * @param lab - le libellé de l'objet recherché
	 * @return l'objet de l'énumération correspondant au libellé fourni ou <code>null</code>
	 *         si le libellé est inconnu
	 */
	public static ValidatorErrorType fromLabel(String lab) {
		return valuesAsList().stream().filter(m -> m.getLabel().equalsIgnoreCase(lab)).findAny().orElse(null);
	}

	/**
	 * Permet d'obtenir une liste des valeurs de l'énumération
	 * {@link ValidatorErrorType}.
	 * 
	 * @return la liste des valeurs de l'énumération {@link ValidatorErrorType}
	 */
	public static List<ValidatorErrorType> valuesAsList() {
		return Arrays.asList(values());
	}

	// Accesseurs

	public String getLabel() {
		return label;
	}

	// Services

	/**
	 * Permet l'impression du type d'erreur de validation.
	 * @return une chaîne de caractère du type de l'erreur de validation
	 */
	public String printError() {
		return "Validation error type - " + this.toString() + " : " + this.getLabel();
	}
}