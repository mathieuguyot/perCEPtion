package graph;

/**
 * Classe abstraite représentant une Ressource de CloudAbstract class that represents a cloud ressource
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public abstract class CloudResource {

	protected String name; //Nom de la ressource
	protected CloudResourceType type;
	protected int score;

	/**
	 * Constructeur de la ressource de Cloud
	 * @param name - Nom de la ressource de Cloud
	 */
	public CloudResource(String name, CloudResourceType type, int score) {
		super();
		this.name = name;
		this.type = type;
		this.score = score;
	}

	/**
	 * Accesseur du nom de la ressource
	 * @return Le nom de la ressource
	 */
	public String getName() {
		return name;
	}

	/**
	 * Accesseur du type de la ressource
	 * @return Le nom de la ressource
	 */
	public CloudResourceType getType() {
		return type;
	}

    /**
     * Accesseur du score de la ressource
     * @return Le score de la ressource
     */
    public int getScore() {
        return score;
    }

    /**
     * Accesseur du score total de la ressource (score de la ressource + score des sous-ressources)
     * @return Score total de la ressource
     */
    public abstract int getTotalScore();

    /**
     * Modificateur du score de la ressource
     * @param score - Nouveau score à affecter à la ressource
     */
    public void setScore(int score) {
        this.score = score;
    }

	/**
	 * Méthode d'indentation (cad. "\t\t\t\t" pour un facteur d'indentation de 4)
	 * @param indent - Facteur d'indentation
	 * @return Une String d'indentation
	 */
	protected String getIndent(int indent) {
	    StringBuilder indents = new StringBuilder();
	    for(int i = 0; i < indent; i++) {
            indents.append("\t");
        }
        return indents.toString();
    }
	
}
