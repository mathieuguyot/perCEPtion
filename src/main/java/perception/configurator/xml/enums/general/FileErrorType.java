package perception.configurator.xml.enums.general;

import java.util.Arrays;
import java.util.List;

/**
 * Enumération listant les erreurs possible propre au traitement de fichiers par
 * le parser de fichiers XML.
 * 
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 *
 */
public enum FileErrorType {

	FILE_NOT_FOUND("Fichier introuvable."),
	EMPTY_FILE("Fichier vide."),
	INVALID_FILE_FORMAT("Fichier fournit d'extention autre que.xml."),
	SCHEMA_ERROR("Erreur lié au schéma XSD."),
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
	 * Note : la recherche du libellé se fait en ignorant la case
	 * 
	 * @param lab
	 *            le libellé de l'objet recherché
	 * @return l'objet de l'énumération correspondant au libellé fournit ou null
	 *         si le libellé est inconnu
	 */
	public static FileErrorType fromLabel(String lab) {
		return valuesAsList().stream().filter(m -> m.getLabel().equalsIgnoreCase(lab)).findAny().orElse(null);
	}

	/**
	 * Permet d'obtenir une liste des valeurs de l'énumération
	 * {@link FileErrorType}.
	 * 
	 * @return la liste des valeur de l'énumération {@link FileErrorType}
	 */
	public static List<FileErrorType> valuesAsList() {
		return Arrays.asList(values());
	}

	// Accesseurs

	public String getLabel() {
		return errorInfo;
	}

}
