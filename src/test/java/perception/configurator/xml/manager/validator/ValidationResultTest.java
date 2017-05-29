package perception.configurator.xml.manager.validator;

import org.junit.Test;
import perception.configurator.xml.enums.general.FileErrorType;
import perception.configurator.xml.enums.validator.ValidatorErrorType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidationResultTest {

	@Test
	public void testValidationResult() {
		ValidationError validationError = ValidationError.FAB("Un message d'erreur inutile", ValidatorErrorType.WARNING);
		FileErrorType fileErrorType = FileErrorType.INVALID_FILE_FORMAT;
		ValidationResult validationResult = ValidationResult.FAB(validationError, fileErrorType);
		assertEquals("Construction - validationError", validationError, validationResult.getValidationError());
		assertEquals("Construction - fileErrorType", fileErrorType, validationResult.getFileErrorType());
	}
	
	@Test
	public void testSetValidationError() {
		ValidationError validationError = ValidationError.FAB();
		FileErrorType fileErrorType = FileErrorType.INVALID_FILE_FORMAT;
		ValidationResult validationResult = ValidationResult.FAB(validationError, fileErrorType);
		validationResult.setFileErrorType(fileErrorType);
		validationResult.setValidationError(validationError);
		assertEquals("Construction - validationError", validationError, validationResult.getValidationError());
		assertEquals("Construction - fileErrorType", fileErrorType, validationResult.getFileErrorType());
	}
	
	@Test
	public void testHasError_OnlyValidationError() {
		ValidationError validationError = ValidationError.FAB("Un message d'erreur inutile", ValidatorErrorType.WARNING);
		ValidationResult validationResult = ValidationResult.FAB();
		assertTrue(validationResult.hasErrors());
	}
	
	@Test
	public void testHasError_OnlyFileErrorType() {
		FileErrorType fileErrorType = FileErrorType.INVALID_FILE_FORMAT;
		ValidationResult validationResult = ValidationResult.FAB();
		assertTrue(validationResult.hasErrors());
	}
	
	@Test
	public void testHasError_NoError() {
		ValidationResult validationResult = ValidationResult.FAB();
		assertFalse(validationResult.hasErrors());
	}
	
	@Test
	public void testHasError_FileErrorTypeAndValidationError() {
		ValidationError validationError = ValidationError.FAB();
		FileErrorType fileErrorType = FileErrorType.INVALID_FILE_FORMAT;
		ValidationResult validationResult = ValidationResult.FAB(validationError, fileErrorType);
		validationResult.setFileErrorType(fileErrorType);
		validationResult.setValidationError(validationError);
		assertTrue(validationResult.hasErrors());
	}

}
