package perception.configurator.xml.manager.parser;

import org.junit.Test;
import org.xml.sax.SAXException;
import perception.configurator.xml.TestConstants;
import perception.configurator.xml.manager.validator.ValidationResult;
import perception.configurator.xml.manager.validator.XMLFileValidator;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class XMLFileParserTest {

	private static final String xSDFilePath = "resources/schema.xsd";

	@Test
	public void testParse_XMLFileParser_OK() throws ParserConfigurationException, SAXException, IOException {
		
		String xMLFilePath = TestConstants.XMLFileParserTestFolder + "testParse_XMLFileParser_OK.xml";

		// Expected
		ResultatParsing expectedResultatParsing = ResultatParsing.FAB(new ArrayList<>(), new ArrayList<>(), new HashMap<>());

		Map<String, Long> expectedPrimitiveEvents = new HashMap<>();
		expectedPrimitiveEvents.put("PEG_Blank", 78945L);
		expectedPrimitiveEvents.put("PEG_Pm_Cpu", 12000L);

		expectedResultatParsing.setPrimitiveEventMap(expectedPrimitiveEvents);
		ValidationResult validationResult = ValidationResult.FAB();
		expectedResultatParsing.setValidationResult(validationResult);

		// Actual
		ResultatParsing actualResultat = XMLFileParser.parse(xMLFilePath, xSDFilePath);
		
		assertEquals(expectedResultatParsing, actualResultat);
		
	}
	
	@Test
	public void testInvalidFile_NoParsing() throws ParserConfigurationException, SAXException, IOException {

		String xMLFilePath = TestConstants.XMLFileParserTestFolder + "testInvalidFile_NoParsing.xml";
		
		ResultatParsing expectedResultatParsing = ResultatParsing.FAB(new ArrayList<>(), new ArrayList<>(), new HashMap<>());

        Map<String, Long> expectedPrimitiveEvents = new HashMap<>();
        //expectedPrimitiveEvents.put("PEG_Blank", 78945L);
        //expectedPrimitiveEvents.put("PEG_Pm_Cpu", 12000L);

		expectedResultatParsing.setPrimitiveEventMap(expectedPrimitiveEvents);

		ValidationResult expectedValidationResult = XMLFileValidator.validate(xMLFilePath, xSDFilePath);
		expectedResultatParsing.setValidationResult(expectedValidationResult);
		
		ResultatParsing actualResultat = XMLFileParser.parse(xMLFilePath, xSDFilePath);
		
		assertEquals(expectedResultatParsing, actualResultat);
		
	}
	
	
	@Test
	public void testDoNotParseIfInvalidPatternFile() throws ParserConfigurationException, SAXException, IOException {
		
		String filePath = TestConstants.XMLFileParserTestFolder + "testDoNotParseIfInvalidPatternFile.xml";
		
		ResultatParsing actualRes = XMLFileParser.parse(filePath, xSDFilePath);
		
		assertEquals(new HashMap<String, Long>(), actualRes.getPrimitiveEventMap());
		
	}

}
