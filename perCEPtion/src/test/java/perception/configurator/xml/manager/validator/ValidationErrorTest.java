package perception.configurator.xml.manager.validator;

import org.junit.Test;
import perception.configurator.xml.enums.validator.ValidatorErrorType;

import static org.junit.Assert.assertEquals;

public class ValidationErrorTest {

	@Test
	public void testValidationError() {
		ValidationError validationError = ValidationError.FAB("Je suis un petit message d'erreur pas bien méchant...", ValidatorErrorType.FATAL_ERROR);
		assertEquals("getErrorMsg", "Je suis un petit message d'erreur pas bien méchant...", validationError.getErrorMsg());
		assertEquals("getValidatorErrorType", ValidatorErrorType.FATAL_ERROR, validationError.getValidatorErrorType());
	}

}
