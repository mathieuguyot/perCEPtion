package perception.configurator.xml.enums.parser;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParsingErrorTypeTest {

	@Test
	public void testGetLabel() {
		assertEquals("getLabel - INVALID_PRIMITIVES_NODE",
				"Impossible de trouver les primitives events dans le fichier XML fournit. Arrêt du traitement du fichier.",
				ParsingErrorType.INVALID_PRIMITIVES_NODE.getLabel());
		assertEquals("getLabel - INVALID_PRIMITIVE_NAME",
				"Impossible de trouver le nom du primitive event.",
				ParsingErrorType.INVALID_PRIMITIVE_NAME.getLabel());
		assertEquals("getLabel - INVALID_PRIMITIVE_RUNTIME",
				"Impossible de trouver le runtime du primitive event.",
				ParsingErrorType.INVALID_PRIMITIVE_RUNTIME.getLabel());
		assertEquals("getLabel - INVALID_PRIMITIVE_ENABLED_ATTR",
				"Impossible de trouver l'attribut enabled du primitive event.",
				ParsingErrorType.INVALID_PRIMITIVE_ENABLED_ATTR.getLabel());
	}
	
	@Test
	public void testFromLabel() {
		assertEquals("fromLabel - INVALID_PRIMITIVES_NODE",
				ParsingErrorType.INVALID_PRIMITIVES_NODE,
				ParsingErrorType.fromLabel("Impossible de trouver les primitives events dans le fichier XML fournit. Arrêt du traitement du fichier."));
		
		assertEquals("fromLabel - INVALID_PRIMITIVE_NAME",
				ParsingErrorType.INVALID_PRIMITIVE_NAME,
				ParsingErrorType.fromLabel("Impossible de trouver le nom du primitive event."));
		
		assertEquals("fromLabel - INVALID_PRIMITIVE_RUNTIME",
				ParsingErrorType.INVALID_PRIMITIVE_RUNTIME,
				ParsingErrorType.fromLabel("Impossible de trouver le runtime du primitive event."));
		
		assertEquals("fromLabel - INVALID_PRIMITIVE_ENABLED_ATTR",
				ParsingErrorType.INVALID_PRIMITIVE_ENABLED_ATTR,
				ParsingErrorType.fromLabel("Impossible de trouver l'attribut enabled du primitive event."));
		
	}
	
	@Test
	public void testFromLabelIgnoreCase() {
		ParsingErrorType res = ParsingErrorType.fromLabel("Impossible de trouver l'attribut enabled du primitive event.");
		assertEquals("FromLibelle IgnoreCase INVALID_PRIMITIVE_ENABLED_ATTR ", ParsingErrorType.INVALID_PRIMITIVE_ENABLED_ATTR, res);
	}
	
	@Test
	public void testFromLabelInvalide() {
		ParsingErrorType res = ParsingErrorType.fromLabel("N'ImporTquOi");
		assertEquals("FromLibelle Invalide", null, res);
	}

	@Test
	public void testValuesAsList() {
		List<ParsingErrorType> parsingErrorTypeList = Arrays.asList(
				ParsingErrorType.INVALID_PRIMITIVES_NODE,
				ParsingErrorType.INVALID_PRIMITIVE_NAME,
				ParsingErrorType.INVALID_PRIMITIVE_RUNTIME,
				ParsingErrorType.INVALID_PRIMITIVE_ENABLED_ATTR);
		assertEquals("valuesAsList - taille", 4, ParsingErrorType.valuesAsList().size());
		assertEquals("valuesAsList - values", true, ParsingErrorType.valuesAsList().containsAll(parsingErrorTypeList));
	}

}
