package graph;

import java.util.Observable;

/**
 * Abstract class that represents a cloud ressource
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public abstract class CloudResource {

	protected String name; //The name of the cloud resource
	protected CloudResourceType type;

	/**
	 * Constructor of the cloud resource
	 * @param name The name of the cloud resource
	 */
	public CloudResource(String name, CloudResourceType type) {
		super();
		this.name = name;
		this.type = type;
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
	 * Display general information about cloud resource
	 */
	public void display() {
	    this.display(0);
    }

	/**
	 * Display the cloud resource
	 * @param indent The indent factor of the display
	 */
	protected abstract void display(int indent);

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
