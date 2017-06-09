package perception.configurator.xml.enums.general;

import java.util.Arrays;
import java.util.List;

/**
 * Enumération définisant les intitulés des balises du fichier XML contenant le
 * scénario.
 * 
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 *
 */
public enum XMLFileStructure {

	// Balise du fichier XML
	RACINE_PERCEPTION("perception"),
	EVENTS("events"),
	PRIMITIVES("primitives"),
	PRIMITIVE("primitive"),
	PRIMITIVE_ATTR_ENABLED("enabled"),
	PRIMITIVE_NAME("name"),
	PRIMITIVE_RUNTIME("runtime"),

	// Valeur d'attributs lié au schéma
	NAMESPACE("http://www.w3.org/2001/XMLSchema-instance");

	// Attributs
	private String label;

	// Constructeur
	/**
	 * Constructeur de la classe {@link XMLFileStructure}.
	 * 
	 * @param baliseLabel
	 *            label de la balise XML
	 */
	XMLFileStructure(String baliseLabel) {
		this.label = baliseLabel;
	}

	// Services

	/**
	 * Permet la récupération du {@link XMLFileStructure} à partir de son
	 * libellé. Note : la recherche du libellé se fait en ignorant la case
	 * 
	 * @param lab
	 *            le libellé de l'objet recherché
	 * @return l'objet de l'énumération correspondant au libellé fournit ou null
	 *         si le libellé est inconnu
	 */
	public static XMLFileStructure fromLabel(String lab) {
		return valuesAsList().stream().filter(m -> m.getLabel().equalsIgnoreCase(lab)).findAny().orElse(null);
	}

	/**
	 * Permet d'obtenir une liste des valeurs de l'énumération
	 * {@link XMLFileStructure}.
	 * 
	 * @return la liste des valeur de l'énumération {@link XMLFileStructure}
	 */
	public static List<XMLFileStructure> valuesAsList() {
		return Arrays.asList(values());
	}

	// Accesseurs

	public String getLabel() {
		return label;
	}

}
