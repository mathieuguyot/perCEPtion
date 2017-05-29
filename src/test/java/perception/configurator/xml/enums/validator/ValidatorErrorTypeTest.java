package perception.configurator.xml.enums.validator;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ValidatorErrorTypeTest {

	@Test
	public void testGetLabel() {
		assertEquals("getLabel - WARNING", 
				"Warning du au parsing du fichier XML.", 
				ValidatorErrorType.WARNING.getLabel());
	}
	
	@Test
	public void testFromLabel() {
		assertEquals("fromLabel - FATAL_ERROR",
				ValidatorErrorType.FATAL_ERROR,
				ValidatorErrorType.fromLabel("Le fichier XML ne respect pas les règles de formatage XML."));
	}
	
	@Test
	public void testFromLabelIgnoreCase() {
		ValidatorErrorType res = ValidatorErrorType.fromLabel("La grammaire du Fichier XML n'esT pAS conforme au spécification du schéma XSD.");
		assertEquals("FromLibelle IgnoreCase - ERROR ", ValidatorErrorType.ERROR, res);
	}
	
	@Test
	public void testFromLabelInvalide() {
		ValidatorErrorType res = ValidatorErrorType.fromLabel("N'ImporTquOi");
		assertEquals("FromLibelle Invalide", null, res);
	}

	@Test
	public void testValuesAsList() {
		List<ValidatorErrorType> ValidatorErrorTypeList =
				Arrays.asList(
				ValidatorErrorType.ERROR,
				ValidatorErrorType.FATAL_ERROR,
				ValidatorErrorType.WARNING);
		assertEquals("valuesAsList - taille", 3, ValidatorErrorType.valuesAsList().size());
		assertEquals("valuesAsList - values", true, ValidatorErrorType.valuesAsList().containsAll(ValidatorErrorTypeList));
	}
	
	
}
