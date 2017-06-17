package perception.configurator.xml.enums.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Enumération listant les erreurs possibles propres au parsing du fichier XML.
 * 
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 *
 */
public enum ParsingErrorType {

	// Primitives events
	EVENT_PRIMITIVES_INVALID_NODE(
			"Impossible de trouver les primitives events dans le fichier XML fournit. Arrêt du traitement du fichier."),

    EVENT_PRIMITIVES_INVALID_NAME("Impossible de trouver le nom du primitive event."),

    EVENT_PRIMITIVES_INVALID_TYPE("Impossible de trouver le type du primitive event."),

    EVENT_PRIMITIVES_INVALID_RUNTIME("Impossible de trouver le runtime du primitive event."),

    EVENT_PRIMITIVES_DUPLICATED_NAME("Un même nom a été renseigné pour deux primitives events. Seul le première élément avec ce nom sera pris en compte."),

	// Simples events
	EVENT_SIMPLES_INVALID_NODE(
			"Impossible de trouver les simples events dans le fichier XML fournit. Arrêt du traitement du fichier."),

	EVENT_SIMPLES_DUPLICATED_NAME("Un même nom a été renseigné pour deux simples events. Seul le première élément avec ce nom sera pris en compte."),

	EVENT_SIMPLES_INVALID_NAME("Impossible de trouver le nom du simple event."),

	EVENT_SIMPLES_INVALID_TYPE("Impossible de trouver le type du simple event."),

	// Complexes events
	EVENT_COMPLEXES_INVALID_NODE(
			"Impossible de trouver les complexes events dans le fichier XML fournit. Arrêt du traitement du fichier."),

	EVENT_COMPLEXES_DUPLICATED_NAME("Un même nom a été renseigné pour deux complexes events. Seul le première élément avec ce nom sera pris en compte."),

	EVENT_COMPLEXES_INVALID_NAME("Impossible de trouver le nom du complex event."),

	EVENT_COMPLEXES_INVALID_TYPE("Impossible de trouver le type du complex event.");

	// Si l'attribut enabled de la balise <primitive> est abs on considère que le primitive event est à activer
	//INVALID_PRIMITIVE_ENABLED_ATTR("Impossible de trouver l'attribut enabled du primitive event.");

    // Attributs
	private String errorInfo;
    private List<String> complements;

	// Constructeur
	/**
	 * Constructeur de la classe {@link ParsingErrorType}.
	 * 
	 * @param errorInfo - message explicatif de l'erreur
	 */
	ParsingErrorType(String errorInfo) {
		this.errorInfo = errorInfo;
		this.complements = new ArrayList<>();
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

    public List<String> getComplements() {
        return complements;
    }

    public void resetComplements() {
        this.complements = new ArrayList<>();
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
