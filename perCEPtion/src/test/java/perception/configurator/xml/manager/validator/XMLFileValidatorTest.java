package perception.configurator.xml.manager.validator;


import org.junit.Test;
import perception.configurator.xml.TestConstants;
import perception.configurator.xml.enums.validator.ValidatorErrorType;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class XMLFileValidatorTest {

    @Test
    public void testValidate_OK() {
        String xMLFilePath = "testingXMLFiles/XMLFileValidatorTest/XMLFileValidatorTest_validFile.xml";
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
        assertFalse("No errors", validationResult.hasErrors());
    }

    @Test
    public void testValidate_InvalidPrimitiveEventTypeValue() {
        String xMLFilePath = "testingXMLFiles/XMLFileValidatorTest/testValidate_InvalidConsomationValueType.xml";
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
        assertTrue("HasError", validationResult.hasErrors());
        assertEquals("Erreur fichier", null, validationResult.getFileErrorType());
        assertEquals("Renvoie de l'erreur - type", ValidatorErrorType.ERROR, validationResult.getValidationError().getValidatorErrorType());
        assertEquals("Renvoie de l'erreur - message",
                "cvc-minInclusive-valid : mon erreur de parsing",
                validationResult.getValidationError().getErrorMsg());
    }

}
