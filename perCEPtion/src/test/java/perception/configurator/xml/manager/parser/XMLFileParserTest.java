package perception.configurator.xml.manager.parser;

import org.junit.Test;
import org.xml.sax.SAXException;
import perception.configurator.xml.TestConstants;
import perception.configurator.xml.manager.model.PrimitiveEventData;
import perception.configurator.xml.manager.validator.ValidationResult;
import perception.configurator.xml.manager.validator.XMLFileValidator;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class XMLFileParserTest {

	@Test
	public void testXMLConfigurationParser_PrimitiveSimpleComplexEvents() {

		String xMLFilePath = TestConstants.XMLFileParserEventsTestFolder + "testParse_XMLFileParser_OK.xml";

		// Expected
		ResultatParsing expectedResultatParsing = ResultatParsing.FAB();

		List<PrimitiveEventData> expectedPedataList = new ArrayList<>();
		expectedPedataList.add(new PrimitiveEventData("PEG_Blank1", "PEG_Blank", 78945L));
		expectedPedataList.add(new PrimitiveEventData("PEG_Pm_Cpu1", "PEG_Pm_Cpu", 12000L));

		expectedResultatParsing.setPrimitiveEventList(expectedPedataList);

		// Actual
		//ResultatParsing actualResultat = XMLFileParser.parse(xMLFilePath, TestConstants.XMLFileXSD);

		//assertEquals(expectedResultatParsing, actualResultat);

	}

	@Test
	public void testParse_XMLFileParser_OK() throws ParserConfigurationException, SAXException, IOException {
		
		String xMLFilePath = TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testParse_XMLFileParser_OK.xml";

		// Expected
		ResultatParsing expectedResultatParsing = ResultatParsing.FAB();

		List<PrimitiveEventData> expectedPedataList = new ArrayList<>();
		expectedPedataList.add(new PrimitiveEventData("PEG_Blank1", "PEG_Blank", 78945L));
		expectedPedataList.add(new PrimitiveEventData("PEG_Pm_Cpu1", "PEG_Pm_Cpu", 12000L));

		expectedResultatParsing.setPrimitiveEventList(expectedPedataList);

		// Actual
		ResultatParsing actualResultat = XMLFileParser.parse(xMLFilePath, TestConstants.XMLFileXSD);
		
		assertEquals(expectedResultatParsing, actualResultat);
		
	}
	
	@Test
	public void testInvalidFile_NoParsing() throws ParserConfigurationException, SAXException, IOException {

		String xMLFilePath = TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testInvalidFile_NoParsing.xml";
		
		ResultatParsing expectedResultatParsing = ResultatParsing.FAB();

        List<PrimitiveEventData> expectedPrimitiveEvents = new ArrayList<>();
        //expectedPrimitiveEvents.put("PEG_Blank", 78945L);
        //expectedPrimitiveEvents.put("PEG_Pm_Cpu", 12000L);

		expectedResultatParsing.setPrimitiveEventList(expectedPrimitiveEvents);

		ValidationResult expectedValidationResult = XMLFileValidator.validate(xMLFilePath, TestConstants.XMLFileXSD);
		expectedResultatParsing.setValidationResult(expectedValidationResult);
		
		ResultatParsing actualResultat = XMLFileParser.parse(xMLFilePath, TestConstants.XMLFileXSD);
		
		assertEquals(expectedResultatParsing, actualResultat);
		
	}
	
	
	@Test
	public void testDoNotParseIfInvalidPatternFile() throws ParserConfigurationException, SAXException, IOException {
		
		String filePath = TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testDoNotParseIfInvalidPatternFile.xml";
		
		ResultatParsing actualRes = XMLFileParser.parse(filePath, TestConstants.XMLFileXSD);
		
		assertEquals(new ArrayList<PrimitiveEventData>(), actualRes.getPrimitiveEventList());
		
	}

	@Test
	public void testParseCompleXMLConfiguratorFile() {
		fail("TODO");
	}

	@Test
	public void testParsePrimitiveEvents() {
		fail("TODO");
	}

	@Test
	public void testParseSimpleEvents() {
		fail("TODO");
	}

	@Test
	public void testMergeResultatsParsingsWithMainOne() {
		fail("TODO");
	}

}
