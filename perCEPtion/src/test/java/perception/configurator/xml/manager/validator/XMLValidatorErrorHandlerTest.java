package perception.configurator.xml.manager.validator;

import org.junit.Test;
import perception.configurator.xml.enums.validator.ValidatorErrorType;

import static org.junit.Assert.assertEquals;

public class XMLValidatorErrorHandlerTest {

	@Test
	public void testXMLValidatorErrorHandler() {
		ValidationError validationError = ValidationError.FAB("Je suis une erreur", ValidatorErrorType.ERROR);
		XMLValidatorErrorHandler xmlValidatorErrorHandler = XMLValidatorErrorHandler.FAB(validationError);
		assertEquals("Constructeur, Fabrique et accesseur", validationError, xmlValidatorErrorHandler.getValidationError());
	}

}
