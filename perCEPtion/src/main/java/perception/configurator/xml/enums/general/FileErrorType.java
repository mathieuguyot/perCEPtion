package perception.configurator.xml.enums.general;

import java.util.Arrays;
import java.util.List;

/**
 * Enumération listant les erreurs possibles propres au traitement de fichiers par
 * le parser de fichiers XML.
 * 
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 *
 */
public enum FileErrorType {

	FILE_NOT_FOUND("Fichier introuvable."),
	EMPTY_FILE("Fichier vide."),
	INVALID_FILE_FORMAT("Fichier fourni d'extension autre que.xml."),
	SCHEMA_ERROR("Erreur liée au schéma XSD."),
	FILE_READING("Erreur au moment du parsing du fichier.");

	// Attributs
	private String errorInfo;

	// Constructeur
	/**
	 * Constructeur de la classe {@link FileErrorType}.
	 * 
	 * @param errorInfo
	 *            message explicatif de l'erreur
	 */
	FileErrorType(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	// Services

	/**
	 * Permet la récupération du {@link FileErrorType} à partir de son libellé.
	 * Note : la recherche du libellé se fait en ignorant la casse
	 * 
	 * @param lab
	 *            le libellé de l'objet recherché
	 * @return l'objet de l'énumération correspondant au libellé fourni ou <code>null</code>
	 *         si le libellé est inconnu
	 */
	public static FileErrorType fromLabel(String lab) {
		return valuesAsList().stream().filter(m -> m.getLabel().equalsIgnoreCase(lab)).findAny().orElse(null);
	}

	/**
	 * Permet d'obtenir une liste des valeurs de l'énumération
	 * {@link FileErrorType}.
	 * 
	 * @return la liste des valeurs de l'énumération {@link FileErrorType}
	 */
	public static List<FileErrorType> valuesAsList() {
		return Arrays.asList(values());
	}

	// Accesseurs

	public String getLabel() {
		return errorInfo;
	}

	// Services

	/**
	 * Permet l'impression le type de l'erreur de traitement du fichier de configuration XML.
	 * @return une chaîne de caractère comprenant le type de l'erreur de traitement de fichier
	 */
	public String printErrors() {
		return "File error type : " + this.toString() + " : " + this.getLabel();
	}

}
