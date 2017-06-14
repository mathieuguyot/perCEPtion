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
        String xMLFilePath = TestConstants.XMLFileValidationTestFolder + "testValidFile.xml";
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
        assertFalse("No errors", validationResult.hasErrors());
    }

    @Test
    public void testValidate_InvalidPrimitiveEventTypeRuntimeValue() {
        String xMLFilePath = TestConstants.XMLFileValidationTestFolder + "PrimitiveEvents/testValidate_InvalidPrimitiveEventTypeValue.xml";
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
        assertTrue("HasError", validationResult.hasErrors());
        assertEquals("Erreur fichier", null, validationResult.getFileErrorType());
        assertEquals("Renvoie de l'erreur - type", ValidatorErrorType.ERROR, validationResult.getValidationError().getValidatorErrorType());
        assertEquals("Renvoie de l'erreur - message",
                "cvc-datatype-valid.1.2.1 : 'coucouJeSuisUneBelleErreur' n'est pas une valeur valide pour 'integer'.",
                validationResult.getValidationError().getErrorMsg());
    }

    @Test
    public void testValidate_InvalidPrimitiveEventTypeRuntimeValue2() {
        String xMLFilePath = TestConstants.XMLFileValidationTestFolder + "PrimitiveEvents/testValidate_InvalidPrimitiveEventTypeRuntimeValue2.xml";
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
        assertTrue("HasError", validationResult.hasErrors());
        assertEquals("Erreur fichier", null, validationResult.getFileErrorType());
        assertEquals("Renvoie de l'erreur - type", ValidatorErrorType.ERROR, validationResult.getValidationError().getValidatorErrorType());
        assertEquals("Renvoie de l'erreur - message",
                "cvc-minInclusive-valid : La valeur '-12' n'est pas un facet valide par rapport à minInclusive '0' pour le type 'event_name_runtime'.",
                validationResult.getValidationError().getErrorMsg());
    }

    @Test
    public void testValidate_NoPrimitiveEventTypeNameValue() {
        String xMLFilePath = TestConstants.XMLFileValidationTestFolder + "PrimitiveEvents/testValidate_InvalidPrimitiveEventTypeNoNameValue.xml";
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
        assertTrue("HasError", validationResult.hasErrors());
        assertEquals("Erreur fichier", null, validationResult.getFileErrorType());
        assertEquals("Renvoie de l'erreur - type", ValidatorErrorType.ERROR, validationResult.getValidationError().getValidatorErrorType());
        assertEquals("Renvoie de l'erreur - message",
                "cvc-complex-type.2.4.a : Contenu non valide trouvé à partir de l'élément 'type'. L'une des valeurs '{name}' est attendue.",
                validationResult.getValidationError().getErrorMsg());
    }

    @Test
    public void testValidate_InvalidPrimitiveEventTypeActivatedValue() {
        String xMLFilePath = TestConstants.XMLFileValidationTestFolder + "PrimitiveEvents/testValidate_InvalidPrimitiveEventTypeActivatedValue.xml";
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
        assertTrue("HasError", validationResult.hasErrors());
        assertEquals("Erreur fichier", null, validationResult.getFileErrorType());
        assertEquals("Renvoie de l'erreur - type", ValidatorErrorType.ERROR, validationResult.getValidationError().getValidatorErrorType());
        assertEquals("Renvoie de l'erreur - message",
                "cvc-datatype-valid.1.2.1 : '123' n'est pas une valeur valide pour 'boolean'.",
                validationResult.getValidationError().getErrorMsg());
    }

    @Test
    public void testValidate_NoPrimitiveEventTypeRuntimeValue() {
        String xMLFilePath = TestConstants.XMLFileValidationTestFolder + "PrimitiveEvents/testValidate_InvalidPrimitiveEventTypeNoRuntimeValue.xml";
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
        assertTrue("HasError", validationResult.hasErrors());
        assertEquals("Erreur fichier", null, validationResult.getFileErrorType());
        assertEquals("Renvoie de l'erreur - type", ValidatorErrorType.ERROR, validationResult.getValidationError().getValidatorErrorType());
        assertEquals("Renvoie de l'erreur - message",
                "cvc-complex-type.2.4.b : Le contenu de l'élément 'primitive' n'est pas complet. L'un des éléments '{runtime}' est attendu.",
                validationResult.getValidationError().getErrorMsg());
    }

    @Test
    public void testValidate_NoPrimitiveEventTypeEnabledValue() {
        String xMLFilePath = TestConstants.XMLFileValidationTestFolder + "PrimitiveEvents/testValidate_InvalidPrimitiveEventTypeNoEnabledValue.xml";
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
        assertTrue("HasError", validationResult.hasErrors());
        assertEquals("Erreur fichier", null, validationResult.getFileErrorType());
        assertEquals("Renvoie de l'erreur - type", ValidatorErrorType.ERROR, validationResult.getValidationError().getValidatorErrorType());
        assertEquals("Renvoie de l'erreur - message",
                "cvc-complex-type.4 : L'attribut 'enabled' doit figurer dans l'élément 'primitive'.",
                validationResult.getValidationError().getErrorMsg());
    }

    @Test
    public void testValidate_InvalidPrimitiveEventTypeTypeValue() {
        String xMLFilePath = TestConstants.XMLFileValidationTestFolder + "PrimitiveEvents/testValidate_InvalidPrimitiveEventTypeTypeValue.xml";
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
        assertTrue("HasError", validationResult.hasErrors());
        assertEquals("Erreur fichier", null, validationResult.getFileErrorType());
        assertEquals("Renvoie de l'erreur - type", ValidatorErrorType.ERROR, validationResult.getValidationError().getValidatorErrorType());
        assertEquals("Renvoie de l'erreur - message",
                "cvc-enumeration-valid : La valeur 'PEG_BlankABC' n'est pas un facet valide par rapport à l'énumération '[PEG_Blank, PEG_Co_ResponseTime, PEG_Pm_Cpu, PEG_Pm_Disk, PEG_Pm_Ram, PEG_Vm_Cpu, PEG_Vm_Disk, PEG_Vm_Ram]'. Il doit s'agir d'une valeur provenant de l'énumération.",
                validationResult.getValidationError().getErrorMsg());
    }

    @Test
    public void testValidate_InvalidPrimitiveEventTypePrimitiveValue() {
        String xMLFilePath = TestConstants.XMLFileValidationTestFolder + "PrimitiveEvents/testValidate_InvalidPrimitiveEventTypePrimitiveValue.xml";
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
        assertTrue("HasError", validationResult.hasErrors());
        assertEquals("Erreur fichier", null, validationResult.getFileErrorType());
        assertEquals("Renvoie de l'erreur - type", ValidatorErrorType.ERROR, validationResult.getValidationError().getValidatorErrorType());
        assertEquals("Renvoie de l'erreur - message",
                "cvc-complex-type.2.4.a : Contenu non valide trouvé à partir de l'élément 'name'. L'une des valeurs '{primitive}' est attendue.",
                validationResult.getValidationError().getErrorMsg());
    }

    @Test
    public void testValidate_InvalidPrimitiveEventTypeNoParent() {
        String xMLFilePath = TestConstants.XMLFileValidationTestFolder + "PrimitiveEvents/testValidate_InvalidPrimitiveEventTypeNoParent.xml";
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
        assertTrue("HasError", validationResult.hasErrors());
        assertEquals("Erreur fichier", null, validationResult.getFileErrorType());
        assertEquals("Renvoie de l'erreur - type", ValidatorErrorType.ERROR, validationResult.getValidationError().getValidatorErrorType());
        assertEquals("Renvoie de l'erreur - message",
                "cvc-complex-type.2.4.a : Contenu non valide trouvé à partir de l'élément 'primitivesA'. L'une des valeurs '{primitives}' est attendue.",
                validationResult.getValidationError().getErrorMsg());
    }

    @Test
    public void testValidate_InvalidSimpleEventTypeNoParent() {
        String xMLFilePath = TestConstants.XMLFileValidationTestFolder + "SimpleEvents/testValidate_InvalidSimpleEventTypeNoParent.xml";
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
        assertTrue("HasError", validationResult.hasErrors());
        assertEquals("Erreur fichier", null, validationResult.getFileErrorType());
        assertEquals("Renvoie de l'erreur - type", ValidatorErrorType.ERROR, validationResult.getValidationError().getValidatorErrorType());
        assertEquals("Renvoie de l'erreur - message",
                "cvc-complex-type.2.4.a : Contenu non valide trouvé à partir de l'élément 'simple'. L'une des valeurs '{simples}' est attendue.",
                validationResult.getValidationError().getErrorMsg());
    }

    @Test
    public void testValidate_InvalidSimpleEventTypeTypeValue() {
        String xMLFilePath = TestConstants.XMLFileValidationTestFolder + "SimpleEvents/testValidate_InvalidSimpleEventTypeTypeValue.xml";
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
        assertTrue("HasError", validationResult.hasErrors());
        assertEquals("Erreur fichier", null, validationResult.getFileErrorType());
        assertEquals("Renvoie de l'erreur - type", ValidatorErrorType.ERROR, validationResult.getValidationError().getValidatorErrorType());
        assertEquals("Renvoie de l'erreur - message",
                "cvc-enumeration-valid : La valeur 'SE_Cpu_DropBlaBla' n'est pas un facet valide par rapport à l'énumération '[SE_Cpu_Drop, SEG_Cpu_Overload, SEG_Ram_Drop]'. Il doit s'agir d'une valeur provenant de l'énumération.",
                validationResult.getValidationError().getErrorMsg());
    }

    @Test
    public void testValidate_InvalidSimpleEventTypeNoParams() {
        String xMLFilePath = TestConstants.XMLFileValidationTestFolder + "SimpleEvents/testValidate_InvalidSimpleEventTypeNoParams.xml";
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
        assertTrue("HasError", validationResult.hasErrors());
        assertEquals("Erreur fichier", null, validationResult.getFileErrorType());
        assertEquals("Renvoie de l'erreur - type", ValidatorErrorType.ERROR, validationResult.getValidationError().getValidatorErrorType());
        assertEquals("Renvoie de l'erreur - message",
                "cvc-complex-type.2.4.b : Le contenu de l'élément 'simple' n'est pas complet. L'un des éléments '{params}' est attendu.",
                validationResult.getValidationError().getErrorMsg());
    }

    @Test
    public void testValidate_InvalidSimpleEventTypeNoParamType() {
        String xMLFilePath = TestConstants.XMLFileValidationTestFolder + "SimpleEvents/testValidate_InvalidSimpleEventTypeNoParamType.xml";
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
        assertTrue("HasError", validationResult.hasErrors());
        assertEquals("Erreur fichier", null, validationResult.getFileErrorType());
        assertEquals("Renvoie de l'erreur - type", ValidatorErrorType.ERROR, validationResult.getValidationError().getValidatorErrorType());
        assertEquals("Renvoie de l'erreur - message",
                "cvc-complex-type.4 : L'attribut 'type' doit figurer dans l'élément 'param'.",
                validationResult.getValidationError().getErrorMsg());
    }

    @Test
    public void testValidate_InvalidSimpleEventTypeConflictingParamType() {
        String xMLFilePath = TestConstants.XMLFileValidationTestFolder + "SimpleEvents/testValidate_InvalidSimpleEventTypeConflictingParamType.xml";
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
        assertTrue("HasError", validationResult.hasErrors());
        assertEquals("Erreur fichier", null, validationResult.getFileErrorType());
        assertEquals("Renvoie de l'erreur - type", ValidatorErrorType.ERROR, validationResult.getValidationError().getValidatorErrorType());
        assertEquals("Renvoie de l'erreur - message",
                "cvc-complex-type.4 : L'attribut 'type' doit figurer dans l'élément 'param'.",
                validationResult.getValidationError().getErrorMsg());
    }

    @Test
    public void testValidate_InvalidSimpleEventTypeWrongParamType() {
        String xMLFilePath = TestConstants.XMLFileValidationTestFolder + "SimpleEvents/testValidate_InvalidSimpleEventTypeWrongParamType.xml";
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
        assertTrue("HasError", validationResult.hasErrors());
        assertEquals("Erreur fichier", null, validationResult.getFileErrorType());
        assertEquals("Renvoie de l'erreur - type", ValidatorErrorType.FATAL_ERROR, validationResult.getValidationError().getValidatorErrorType());
        assertEquals("Renvoie de l'erreur - message",
                "Des guillemets ouvrants sont attendus pour l'attribut \"type\" associé à un type d'élément \"param\".",
                validationResult.getValidationError().getErrorMsg());
    }

    @Test
    public void testValidate_InvalidSimpleEventTypeNoParamTag() {
        String xMLFilePath = TestConstants.XMLFileValidationTestFolder + "SimpleEvents/testValidate_InvalidSimpleEventTypeNoParamTag.xml";
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
        assertTrue("HasError", validationResult.hasErrors());
        assertEquals("Erreur fichier", null, validationResult.getFileErrorType());
        assertEquals("Renvoie de l'erreur - type", ValidatorErrorType.ERROR, validationResult.getValidationError().getValidatorErrorType());
        assertEquals("Renvoie de l'erreur - message",
                "cvc-complex-type.4 : L'attribut 'tag' doit figurer dans l'élément 'param'.",
                validationResult.getValidationError().getErrorMsg());
    }

    @Test
    public void testValidate_InvalidSimpleEventTypeWrongParamTag() {
        String xMLFilePath = TestConstants.XMLFileValidationTestFolder + "SimpleEvents/testValidate_InvalidSimpleEventTypeWrongParamTag.xml";
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
        assertTrue("HasError", validationResult.hasErrors());
        assertEquals("Erreur fichier", null, validationResult.getFileErrorType());
        assertEquals("Renvoie de l'erreur - type", ValidatorErrorType.FATAL_ERROR, validationResult.getValidationError().getValidatorErrorType());
        assertEquals("Renvoie de l'erreur - message",
                "Des guillemets ouvrants sont attendus pour l'attribut \"tag\" associé à un type d'élément \"param\".",
                validationResult.getValidationError().getErrorMsg());
    }

}
