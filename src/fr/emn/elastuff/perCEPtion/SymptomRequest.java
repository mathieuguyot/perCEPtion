package fr.emn.elastuff.perCEPtion;

/**
 * This class is used to represent a Request corresponding to a Symptom. It is
 * the same than a Request but with a time to live
 * 
 * @author Kevin Keovilay
 * @author Benjamin Robert
 * @author Dimitri Saingre
 * @see Request
 */
public class SymptomRequest extends Request {

	// Time to live : time before being purged in the queue
	private int ttl;

	public SymptomRequest(String name, String command, Event event, int ttl) {
		super(name, command, event);
		this.ttl = ttl;
	}

	public int getTTL() {
		return this.ttl;
	}

	public String toString() {
		return "SymptomRequest[" + this.name + ", " + this.event + ", " + this.command + ", " + this.ttl + "]";
	}
}
