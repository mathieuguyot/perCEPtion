package fr.emn.elastuff.perCEPtion;

/**
 * This class is used to store TTL constant
 * 
 * @author Kevin Keovilay
 * @author Benjamin Robert
 * @author Dimitri Saingre
 * 
 */
public class Constant {

	private int defaultTTL = 9000;

	private static Constant constant;

	private Constant() {
	}

	public static Constant getInstance() {
		if (constant == null) {
			constant = new Constant();
		}
		return constant;
	}

	public int getDefaultTTL() {
		return this.defaultTTL;
	}

}
