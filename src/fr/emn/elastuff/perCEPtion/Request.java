package fr.emn.elastuff.perCEPtion;

/**
 * This class is used to represent an Esper Request
 * 
 * @author Kevin Keovilay
 * @author Benjamin Robert
 * @author Dimitri Saingre
 * 
 */
public class Request {
	protected String command;
	protected String name;
	protected Event event;

	/**
	 * The Request constructor
	 * 
	 * @param name
	 *            the name of the request
	 * @param command
	 *            the Esper command
	 * @param event
	 *            the type of the corresponding event
	 * @see Event
	 */
	public Request(String name, String command, Event event) {
		this.command = command;
		this.name = name;
		this.event = event;
	}

	public String getCommand() {
		return command;
	}

	public String getName() {
		return name;
	}

	public Event getEvent() {
		return event;
	}

	public String toString() {
		return "Request[" + this.name + ", " + this.event + ", " + this.command + "]";
	}

}
