package perception.configurator.xml.enums.parser;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParsingErrorTypeTest {

	@Test
	public void testGetLabel() {
		assertEquals("getLabel - PRIMITIVES_EVENT_INVALID_NODE",
				"Impossible de trouver les primitives events dans le fichier XML fournit. Arrêt du traitement du fichier.",
				ParsingErrorType.PRIMITIVES_EVENT_INVALID_NODE.getLabel());
		assertEquals("getLabel - PRIMITIVES_EVENT_INVALID_NAME",
				"Impossible de trouver le nom du primitive event.",
				ParsingErrorType.PRIMITIVES_EVENT_INVALID_NAME.getLabel());
		assertEquals("getLabel - PRIMITIVES_EVENT_INVALID_RUNTIME",
				"Impossible de trouver le runtime du primitive event.",
				ParsingErrorType.PRIMITIVES_EVENT_INVALID_RUNTIME.getLabel());
		/*assertEquals("getLabel - INVALID_PRIMITIVE_ENABLED_ATTR",
				"Impossible de trouver l'attribut enabled du primitive event.",
				ParsingErrorType.INVALID_PRIMITIVE_ENABLED_ATTR.getLabel());*/
	}
	
	@Test
	public void testFromLabel() {
		assertEquals("fromLabel - PRIMITIVES_EVENT_INVALID_NODE",
				ParsingErrorType.PRIMITIVES_EVENT_INVALID_NODE,
				ParsingErrorType.fromLabel("Impossible de trouver les primitives events dans le fichier XML fournit. Arrêt du traitement du fichier."));
		
		assertEquals("fromLabel - PRIMITIVES_EVENT_INVALID_NAME",
				ParsingErrorType.PRIMITIVES_EVENT_INVALID_NAME,
				ParsingErrorType.fromLabel("Impossible de trouver le nom du primitive event."));
		
		assertEquals("fromLabel - PRIMITIVES_EVENT_INVALID_RUNTIME",
				ParsingErrorType.PRIMITIVES_EVENT_INVALID_RUNTIME,
				ParsingErrorType.fromLabel("Impossible de trouver le runtime du primitive event."));
		
		/*assertEquals("fromLabel - INVALID_PRIMITIVE_ENABLED_ATTR",
				ParsingErrorType.INVALID_PRIMITIVE_ENABLED_ATTR,
				ParsingErrorType.fromLabel("Impossible de trouver l'attribut enabled du primitive event."));*/
		
	}
	
	@Test
	public void testFromLabelIgnoreCase() {
		ParsingErrorType res = ParsingErrorType.fromLabel("Impossible de trouver les primitIves EventS dans le fichier XML fournit. Arrêt du traitement du fichier.");
		assertEquals("FromLibelle IgnoreCase PRIMITIVES_EVENT_INVALID_NODE ", ParsingErrorType.PRIMITIVES_EVENT_INVALID_NODE, res);
	}
	
	@Test
	public void testFromLabelInvalide() {
		ParsingErrorType res = ParsingErrorType.fromLabel("N'ImporTquOi");
		assertEquals("FromLibelle Invalide", null, res);
	}

	@Test
	public void testValuesAsList() {
		List<ParsingErrorType> parsingErrorTypeList = Arrays.asList(
				ParsingErrorType.PRIMITIVES_EVENT_INVALID_NODE,
				ParsingErrorType.PRIMITIVES_EVENT_INVALID_NAME,
				ParsingErrorType.PRIMITIVES_EVENT_INVALID_RUNTIME,
				ParsingErrorType.PRIMITIVES_EVENT_DUPLICATED_NAME,
				ParsingErrorType.PRIMITIVES_EVENT_INVALID_TYPE);
				//ParsingErrorType.INVALID_PRIMITIVE_ENABLED_ATTR);
		assertEquals("valuesAsList - taille", 5, ParsingErrorType.valuesAsList().size());
		assertTrue("valuesAsList - values", ParsingErrorType.valuesAsList().containsAll(parsingErrorTypeList));
	}

}
