package graph;

/**
 * Abstract class that represents a cloud ressource
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public abstract class CloudResource {

	protected String name; //The name of the cloud resource
	protected CloudResourceType type;
	protected int score;

	/**
	 * Constructor of the cloud resource
	 * @param name The name of the cloud resource
	 */
	public CloudResource(String name, CloudResourceType type, int score) {
		super();
		this.name = name;
		this.type = type;
		this.score = score;
	}

	/**
	 * Getter on the name of the cloud resource
	 * @return The name of the cloud resource
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter on the type of the cloud resource
	 * @return The type of the cloud resource
	 */
	public CloudResourceType getType() {
		return type;
	}

    /**
     * Getter on the score of the cloud resource
     * @return The score of the cloud resource
     */
    public int getScore() {
        return score;
    }

    /**
     * Getter on the total score of the cloud resource (score of this resources + score of all child resources)
     * @return The total score of the cloud resource
     */
    public abstract int getTotalScore();

    /**
     * Setter on the score of the cloud resource
     * @param score The new score of the cloud resources
     */
    public void setScore(int score) {
        this.score = score;
    }

	/**
	 * Method get a string of indent (eg. "\t\t\t\t" for a 4 indent factor)
	 * @param indent The indent factor
	 * @return A string of indent
	 */
	protected String getIndent(int indent) {
	    StringBuilder indents = new StringBuilder();
	    for(int i = 0; i < indent; i++) {
            indents.append("\t");
        }
        return indents.toString();
    }
	
}
