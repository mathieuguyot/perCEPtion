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
	public void testParsePrimitiveEvents()  throws ParserConfigurationException, SAXException, IOException {
        String filePath = TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testValid_ParsePrimitiveEvents.xml";

        List<PrimitiveEventData> expectedPrimitiveEvents = new ArrayList<>();
        expectedPrimitiveEvents.add(new PrimitiveEventData("Mon_PEG_Pm_Cpu", "PEG_Pm_Cpu", 12000L));

        ResultatParsing actualRes = XMLFileParser.parse(filePath, TestConstants.XMLFileXSD);

		assertEquals(expectedPrimitiveEvents, actualRes.getPrimitiveEventList());
	}

	@Test
	public void testParseSimpleEvents() throws IOException, SAXException, ParserConfigurationException {
        String filePath = TestConstants.XMLFileParserSimpleEventsTestFolder + "testParseSimpleEvents_OK.xml";

        List<Pair<String,String>> params = new ArrayList<>();
        params.add(new Pair<String,String>("Long", "1245"));

        List<SimpleEventData> expectedSimpleEvents = new ArrayList<>();
        expectedSimpleEvents.add(new SimpleEventData("SEG_Cpu_Drop","MonSimpleEvent2", params));

        ResultatParsing actualRes = XMLFileParser.parse(filePath, TestConstants.XMLFileXSD);

        assertEquals(expectedSimpleEvents, actualRes.getSimpleEventList());
	}

	@Test
	public void testMergeResultatsParsingsWithMainOne() throws IOException, SAXException, ParserConfigurationException {
		String filePath = TestConstants.XMLFileParserEventsTestFolder + "testXMLConfigurationParser.xml";

		List<Pair<String, String>> paramsSE1 = new ArrayList<>();
		paramsSE1.add(new Pair<String,String>("Long", "45958"));
        paramsSE1.add(new Pair<String,String>("String", "Param1"));
        paramsSE1.add(new Pair<String,String>("String", "param2"));
        paramsSE1.add(new Pair<String,String>("Integer", "78"));

        List<Pair<String, String>> paramsSE2 = new ArrayList<>();
        paramsSE2.add(new Pair<String,String>("Long", "1245"));
        paramsSE2.add(new Pair<String,String>("String", "localhost:8080"));
        paramsSE2.add(new Pair<String,String>("String", "param3"));
        paramsSE2.add(new Pair<String,String>("Integer", "45"));

        List<Pair<String, String>> paramsCE1 = new ArrayList<>();
        paramsCE1.add(new Pair<String,String>("Long", "12"));
        paramsCE1.add(new Pair<String,String>("String", "Param4"));
        paramsCE1.add(new Pair<String,String>("String", "param5"));
        paramsCE1.add(new Pair<String,String>("Integer", "78"));

        List<Pair<String, String>> paramsCE2 = new ArrayList<>();
        paramsCE2.add(new Pair<String,String>("Long", "5"));
        paramsCE2.add(new Pair<String,String>("String", "Kikou Toi"));
        paramsCE2.add(new Pair<String,String>("String", "param6"));
        paramsCE2.add(new Pair<String,String>("Integer", "4"));

        List<PrimitiveEventData> expectedPrimitiveEvents = new ArrayList<>();
        expectedPrimitiveEvents.add(new PrimitiveEventData("MonPEGBlank", "PEG_Blank", 60000L));
        expectedPrimitiveEvents.add(new PrimitiveEventData("MonPEGPmCpu", "PEG_Pm_Cpu", 12000L));

        List<SimpleEventData> expectedSimpleEvents = new ArrayList<>();
        expectedSimpleEvents.add(new SimpleEventData("SEG_Cpu_Drop", "MonSimpleEvent1", paramsSE1));
        expectedSimpleEvents.add(new SimpleEventData("SEG_Cpu_Overload", "MonSimpleEvent2", paramsSE2));

        List<ComplexEventData> expectedComplexEvents = new ArrayList<>();
        expectedComplexEvents.add(new ComplexEventData("CEG_Cpu_Dead", "MonComplexEvent1", paramsCE1));
        expectedComplexEvents.add(new ComplexEventData("CEG_Cpu_Dead", "MonComplexEvent2", paramsCE2));

        ResultatParsing actualRes = XMLFileParser.parse(filePath, TestConstants.XMLFileXSD);

        assertEquals(expectedPrimitiveEvents, actualRes.getPrimitiveEventList());
        assertEquals(expectedSimpleEvents, actualRes.getSimpleEventList());
        assertEquals(expectedComplexEvents, actualRes.getComplexEventList());
	}

}
