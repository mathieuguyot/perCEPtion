package perception.configurator.xml.enums.validator;

import java.util.Arrays;
import java.util.List;

/**
 * Enumération listant les erreurs possible au moment de la validation de
 * fichier XML.
 * 
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 *
 */
public enum ValidatorErrorType {

	ERROR("La grammaire du fichier XML n'est pas conforme au spécification du schéma XSD."),
	FATAL_ERROR("Le fichier XML ne respect pas les règles de formatage XML."),
	WARNING("Warning du au parsing du fichier XML.");

	// Attributs
	private String label;

	// Constructeur
	/**
	 * Constructeur de la classe {@link ValidatorErrorType}.
	 * 
	 * @param errorInfo
	 *            message explicatif de l'erreur
	 */
	ValidatorErrorType(String errorInfo) {
		this.label = errorInfo;
	}

	// Services

	/**
	 * Permet la récupération du {@link ValidatorErrorType} à partir de son
	 * libellé. Note : la recherche du libellé se fait en ignorant la case
	 * 
	 * @param lab
	 *            le libellé de l'objet recherché
	 * @return l'objet de l'énumération correspondant au libellé fournit ou null
	 *         si le libellé est inconnu
	 */
	public static ValidatorErrorType fromLabel(String lab) {
		return valuesAsList().stream().filter(m -> m.getLabel().equalsIgnoreCase(lab)).findAny().orElse(null);
	}

	/**
	 * Permet d'obtenir une liste des valeurs de l'énumération
	 * {@link ValidatorErrorType}.
	 * 
	 * @return la liste des valeur de l'énumération {@link ValidatorErrorType}
	 */
	public static List<ValidatorErrorType> valuesAsList() {
		return Arrays.asList(values());
	}

	// Accesseurs

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}