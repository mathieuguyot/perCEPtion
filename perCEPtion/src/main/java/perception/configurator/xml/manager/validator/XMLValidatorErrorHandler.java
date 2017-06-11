package perception.configurator.xml.manager.validator;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import perception.configurator.xml.enums.validator.ValidatorErrorType;

/**
 * Permet la gestion des erreurs remontées au moment du parsing du fichier XML à
 * travers les exceptions qui y sont levées.
 * 
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 *
 */
class XMLValidatorErrorHandler implements ErrorHandler {

	// Attributs
	private ValidationError validationError;

	// Constructeur
	private XMLValidatorErrorHandler(ValidationError errorMsgList) {
		this.validationError = errorMsgList;
	}

	// Accesseurs

	public ValidationError getValidationError() {
		return validationError;
	}

	private void setValidationError(ValidationError validationError) {
		this.validationError = validationError;
	}

	// Services

	/**
	 * Méthode invoquée si la grammaire du fichier XML n'est pas respectée,
	 * conformément à la DTD ou au XSD.
	 * 
	 * @param arg0 - exception levée au moment du parsing
	 */
	@Override
	public void error(SAXParseException arg0) throws SAXException {
		this.setValidationError(ValidationError.FAB(arg0.getMessage(), ValidatorErrorType.ERROR));
		throw arg0;
	}

	/**
	 * Méthode invoquée si le document XML ne respecte pas les règles de
	 * formattage : les noms de balises ouvrantes et fermantes doivent être
	 * identiques, pas de caractères spéciaux dans les balises (<, >, ...).
	 * 
	 * @param arg0 - exception levée au moment du parsing
	 */
	@Override
	public void fatalError(SAXParseException arg0) throws SAXException {
		this.setValidationError(ValidationError.FAB(arg0.getMessage(), ValidatorErrorType.FATAL_ERROR));
		throw arg0;
	}

	/**
	 * Permet de gérer les différentes alertes que peut remonter le parseur.
	 * 
	 * @param arg0 - exception levée au moment du parsing
	 */
	@Override
	public void warning(SAXParseException arg0) throws SAXException {
		this.setValidationError(ValidationError.FAB(arg0.getMessage(), ValidatorErrorType.WARNING));
		throw arg0;
	}

	// Fabriques

	public static XMLValidatorErrorHandler FAB(ValidationError validationError) {
		return new XMLValidatorErrorHandler(validationError);
	}

	public static XMLValidatorErrorHandler FAB() {
		return new XMLValidatorErrorHandler(null);
	}

}
