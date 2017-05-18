package fr.emn.elastuff.graph;

import java.util.Observable;

/**
 * Abstract class that represents a cloud ressource
 * @author (review) Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public abstract class CloudResource extends Observable {

	protected String name; //The name of the cloud resource

	/**
	 * Constructor of the cloud resource
	 * @param name The name of the cloud resource
	 */
	public CloudResource(String name) {
		super();
		this.name = name;
	}

	/**
	 * Getter on the name of the cloud resource
	 * @return The name of the cloud resource
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter on the name of the cloud resource
	 * @param name The new name of the cloud resource
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Display general information about cloud resource
	 */
	public void display() {
	    this.display(0);
    }

	protected abstract void display(int indent);

	protected String getIndent(int indent) {
	    StringBuilder indents = new StringBuilder();
	    for(int i = 0; i < indent; i++) {
            indents.append("\t");
        }
        return indents.toString();
    }
	
}
