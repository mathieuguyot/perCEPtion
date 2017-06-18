package perception.configurator.xml.manager.parser;

import org.junit.Test;
import org.xml.sax.SAXException;
import perception.configurator.xml.TestConstants;
import perception.configurator.xml.manager.model.ComplexEventData;
import perception.configurator.xml.manager.model.PrimitiveEventData;
import perception.configurator.xml.manager.model.SimpleEventData;
import perception.configurator.xml.manager.validator.ValidationResult;
import perception.configurator.xml.manager.validator.XMLFileValidator;
import utils.Pair;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class XMLFileParserTest {

	@Test
	public void testXMLConfigurationParser_PrimitiveSimpleComplexEvents() throws IOException, SAXException, ParserConfigurationException {

		String xMLFilePath = TestConstants.XMLFileParserEventsTestFolder + "testXMLConfigurationParser.xml";

		// Expected
		ResultatParsing expectedResultatParsing = ResultatParsing.FAB();
		expectedResultatParsing.setValidationResult(ValidationResult.FAB());

		List<PrimitiveEventData> expectedPedataList = new ArrayList<>();
		expectedPedataList.add(new PrimitiveEventData("MonPEGBlank", "PEG_Blank", 60000L));
		expectedPedataList.add(new PrimitiveEventData("MonPEGPmCpu", "PEG_Pm_Cpu", 12000L));

		List<SimpleEventData> expectedSEdataList = new ArrayList<>();
		expectedSEdataList.add(new SimpleEventData(
				"SEG_Cpu_Drop",
				"MonSimpleEvent1",
				Arrays.asList(
						new Pair<>("Long", "45958"),
						new Pair<>("String", "Param1"),
						new Pair<>("String", "param2"),
						new Pair<>("Integer", "78")
				)));
		expectedSEdataList.add(new SimpleEventData(
				"SEG_Cpu_Overload",
				"MonSimpleEvent2",
				Arrays.asList(
						new Pair<>("Long", "1245"),
						new Pair<>("String", "localhost:8080"),
						new Pair<>("String", "param3"),
						new Pair<>("Integer", "45")
				)));

		List<ComplexEventData> expectedCEdataList = new ArrayList<>();
		expectedCEdataList.add(new ComplexEventData(
				"CEG_Cpu_Dead",
				"MonComplexEvent1",
				Arrays.asList(
						new Pair<>("Long", "12"),
						new Pair<>("String", "Param4"),
						new Pair<>("String", "param5"),
						new Pair<>("Integer", "78")
				)));
		expectedCEdataList.add(new ComplexEventData(
				"CEG_Cpu_Dead",
				"MonComplexEvent2",
				Arrays.asList(
						new Pair<>("Long", "5"),
						new Pair<>("String", "Kikou Toi"),
						new Pair<>("String", "param6"),
						new Pair<>("Integer", "4")
				)));

		expectedResultatParsing.setPrimitiveEventList(expectedPedataList);
        expectedResultatParsing.setSimpleEventList(expectedSEdataList);
        expectedResultatParsing.setComplexEventList(expectedCEdataList);

		// Actual
		ResultatParsing actualResultat = XMLFileParser.parse(xMLFilePath, TestConstants.XMLFileXSD);

		assertEquals("Primitive events - parsing result", expectedResultatParsing.getPrimitiveEventList(), actualResultat.getPrimitiveEventList());
		assertEquals("Simple events - parsing result", expectedResultatParsing.getSimpleEventList(), actualResultat.getSimpleEventList());
		assertEquals("Complex events - parsing result", expectedResultatParsing.getComplexEventList(), actualResultat.getComplexEventList());
		assertEquals("Résultat de parsing de primitives, simples et complexes events", expectedResultatParsing, actualResultat);

	}

	@Test
	public void testParse_XMLFileParser_OK() throws ParserConfigurationException, SAXException, IOException {
		
		String xMLFilePath = TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testParse_XMLFileParser_OK.xml";

		// Expected
		ResultatParsing expectedResultatParsing = ResultatParsing.FAB();

		List<PrimitiveEventData> expectedPedataList = new ArrayList<>();
		expectedPedataList.add(new PrimitiveEventData("PEG_Pm_Cpu1", "PEG_Pm_Cpu", 12000L));

		expectedResultatParsing.setPrimitiveEventList(expectedPedataList);

		ValidationResult validationResult = ValidationResult.FAB(null,null);
		expectedResultatParsing.setValidationResult(validationResult);

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
