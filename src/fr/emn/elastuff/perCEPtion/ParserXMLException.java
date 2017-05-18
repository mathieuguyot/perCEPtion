package fr.emn.elastuff.perCEPtion;

@SuppressWarnings("serial")
/**
 * class ParserXMLException is use into the class ParserXML to instantiate Exception if the xml file isn't correct
 *
 */
public class ParserXMLException extends Exception {

	/**
	 * Constructeur heritate from the class Exception
	 * @param string
	 */
	public ParserXMLException(String string) {
		super(string);
	}

}
