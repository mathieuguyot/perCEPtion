package perception.configurator.xml.manager.parser;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import perception.configurator.xml.TestConstants;
import perception.configurator.xml.enums.general.FileErrorType;
import perception.configurator.xml.enums.parser.ParsingErrorType;
import perception.configurator.xml.manager.model.PrimitiveEventData;
import perception.configurator.xml.utils.XMLManager;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class XMLFileParserToPrimitiveEventDataTest {

	// Le xPath
	XPathFactory xpf = XPathFactory.newInstance();
	XPath xPath = xpf.newXPath();

	@Test
	public void testParse_valideFile() throws ParserConfigurationException, SAXException, IOException {

	    List<PrimitiveEventData> primitiveEventList = new ArrayList<>();
		primitiveEventList.add(new PrimitiveEventData("MonPEGCoResponseTime","PEG_Co_ResponseTime", 78000L));
        primitiveEventList.add(new PrimitiveEventData("MonPEGPmCPU","PEG_Vm_Cpu", 11000L));
        primitiveEventList.add(new PrimitiveEventData("MonPEGVmCPU","PEG_Pm_Cpu", 98000L));

		ResultatParsing resultatParsing = XMLFileParserToPrimitiveEventData
				.parse(TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testParse_valideFile.xml");

		assertEquals("Parsing fichier XML valide", primitiveEventList, resultatParsing.getPrimitiveEventList());
		assertEquals("Liste erreurs fichier - taille", 0, resultatParsing.getFileErrorTypes().size());
		assertEquals("Liste erreurs parsing - taille", 0, resultatParsing.getParsingErrorTypes().size());
	}

	@Test
	public void testParse_inexistingFile() throws ParserConfigurationException, SAXException, IOException {
		ResultatParsing resultatParsing = XMLFileParserToPrimitiveEventData
				.parse(TestConstants.XMLFileParserPrimitiveEventsTestFolder +  "inexistingFile.csv");
		List<FileErrorType> errList = resultatParsing.getFileErrorTypes();
		assertTrue("Fichier introuvable - type erreur", errList.contains(FileErrorType.FILE_NOT_FOUND));
		assertEquals("Liste erreurs fichier - taille", 1, errList.size());
		assertEquals("Liste erreurs parsing - taille", 0, resultatParsing.getParsingErrorTypes().size());
	}

	@Test
	public void testParse_invalidFileFormatTXT() throws ParserConfigurationException, SAXException, IOException {

        List<PrimitiveEventData> primitiveEventList = new ArrayList<>();
        primitiveEventList.add(new PrimitiveEventData("MonPEGCoResponseTime","PEG_Co_ResponseTime", 78000L));
        primitiveEventList.add(new PrimitiveEventData("MonPEGPmCPU","PEG_Vm_Cpu", 11000L));
        primitiveEventList.add(new PrimitiveEventData("MonPEGVmCPU","PEG_Pm_Cpu", 98000L));

        ResultatParsing resultatParsing = XMLFileParserToPrimitiveEventData
				.parse(TestConstants.XMLFileParserPrimitiveEventsTestFolder +  "testParser_invalidFileFormatTXT.txt");
		assertEquals("Parsing fichier format invalide (TXT)", primitiveEventList, resultatParsing.getPrimitiveEventList());
		assertEquals("Liste erreurs fichier - taille", 0, resultatParsing.getFileErrorTypes().size());
		assertEquals("Liste erreurs parsing - taille", 0, resultatParsing.getParsingErrorTypes().size());
	}

	@Test
	public void testParse_invalidFileFormatCSV() throws ParserConfigurationException, SAXException, IOException {

        List<PrimitiveEventData> primitiveEventList = new ArrayList<>();
        primitiveEventList.add(new PrimitiveEventData("MonPEGCoResponseTime","PEG_Co_ResponseTime", 78945L));
        primitiveEventList.add(new PrimitiveEventData("MonPEGPmCPU","PEG_Pm_Cpu", 12000L));

        ResultatParsing resultatParsing = XMLFileParserToPrimitiveEventData
				.parse(TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testParse_invalidFileFormatCSV.csv");
		assertEquals("Parsing fichier format invalide (TXT)", primitiveEventList, resultatParsing.getPrimitiveEventList());
		assertEquals("Liste erreurs fichier - taille", 0, resultatParsing.getFileErrorTypes().size());
		assertEquals("Liste erreurs parsing - taille", 0, resultatParsing.getParsingErrorTypes().size());
	}

	@Test
	public void testParse_invalidFileFormatPPT() throws ParserConfigurationException, SAXException, IOException {

        List<PrimitiveEventData> primitiveEventList = new ArrayList<>();
        primitiveEventList.add(new PrimitiveEventData("MonPEGCoResponseTime","PEG_Co_ResponseTime", 78000L));
        primitiveEventList.add(new PrimitiveEventData("MonPEGVmCPU","PEG_Vm_Cpu", 11000L));
        primitiveEventList.add(new PrimitiveEventData("MonPEGPmCPU","PEG_Pm_Cpu", 98000L));

        ResultatParsing resultatParsing = XMLFileParserToPrimitiveEventData
				.parse(TestConstants.XMLFileParserPrimitiveEventsTestFolder +  "testParser_invalidFileFormatPPT.ppt");
		assertEquals("Parsing fichier format invalide (TXT)", primitiveEventList, resultatParsing.getPrimitiveEventList());
		assertEquals("Liste erreurs fichier - taille", 0, resultatParsing.getFileErrorTypes().size());
		assertEquals("Liste erreurs parsing - taille", 0, resultatParsing.getParsingErrorTypes().size());
	}

	@Test
	public void testParsePrimitiveEvents_OK() throws SAXException, IOException, ParserConfigurationException {

        List<PrimitiveEventData> primitiveEventList = new ArrayList<>();
        primitiveEventList.add(new PrimitiveEventData("MonPEGCoResponseTime","PEG_Co_ResponseTime", 78000L));
        primitiveEventList.add(new PrimitiveEventData("MonPEGPmCPU","PEG_Vm_Cpu", 11000L));
        primitiveEventList.add(new PrimitiveEventData("MonPEGVmCPU","PEG_Pm_Cpu", 98000L));

		String filePath = TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testParsePrimitiveEvents_OK.xml";

		ResultatParsing resultatParsing = ResultatParsing.FAB();
		XMLFileParserToPrimitiveEventData.parsePrimitivesEvent(xPath, XMLManager.getElementRootFromFile(filePath), resultatParsing);

		assertEquals("Parsing des primitives events d'un fichier xml valide", primitiveEventList,
				resultatParsing.getPrimitiveEventList());
		assertEquals("Liste erreurs fichier - taille", 0, resultatParsing.getFileErrorTypes().size());
		assertEquals("Liste erreurs parsing - taille", 0, resultatParsing.getParsingErrorTypes().size());

	}

	@Test
	public void testGetPrimitiveEventInFile_OK()
			throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

		String filePath = TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testGetPrimitiveEventInFile_OK.xml";

		ResultatParsing resultatParsing = ResultatParsing.FAB();
		NodeList listPrimitiveEventsExpected = (NodeList) xPath.evaluate("//primitive",
                XMLManager.getElementRootFromFile(filePath), XPathConstants.NODESET);
        Optional<NodeList> listPrimitiveEventsExpectedOptional = Optional.of(listPrimitiveEventsExpected);

		Optional<NodeList> listPrimitiveEventsActualOptional = XMLFileParserToPrimitiveEventData.getPrimitivesEventInFile(xPath,
                XMLManager.getElementRootFromFile(filePath), resultatParsing);

		assertEquals("Recupération primitives events du fichier", listPrimitiveEventsExpectedOptional.get().getLength(), listPrimitiveEventsActualOptional.get().getLength());
		assertTrue("Parsing des primitives events d'un fichier xml valide",
				resultatParsing.getPrimitiveEventList().isEmpty());
		assertTrue("Liste erreurs fichier - taille", resultatParsing.getFileErrorTypes().isEmpty());
		assertTrue("Liste erreurs parsing - taille", resultatParsing.getParsingErrorTypes().isEmpty());

	}

    @Test
    public void testGetPrimitiveEventInFile_NoPrimitiveEventInFile()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testGetPrimitiveEventInFile_NoPrimitiveEventInFile.xml";

        ResultatParsing resultatParsing = ResultatParsing.FAB();
        NodeList listPrimitiveEventsExpected = (NodeList) xPath.evaluate("//primitive",
                XMLManager.getElementRootFromFile(filePath), XPathConstants.NODESET);

        Optional<NodeList> listPrimitiveEventsActual = XMLFileParserToPrimitiveEventData.getPrimitivesEventInFile(xPath,
                XMLManager.getElementRootFromFile(filePath), resultatParsing);

        assertEquals("Recupération primitives events du fichier", listPrimitiveEventsExpected.getLength(), listPrimitiveEventsActual.get().getLength());
        assertTrue("Parsing des primitives events d'un fichier xml valide",
                resultatParsing.getPrimitiveEventList().isEmpty());
        assertTrue("Liste erreurs fichier - taille", resultatParsing.getFileErrorTypes().isEmpty());
        assertTrue("Liste erreurs parsing - contenu", resultatParsing.getParsingErrorTypes().isEmpty());

    }

	@Test
	public void testCreateAllPrimitivesEvents_OK()
			throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        List<PrimitiveEventData> primitiveEventList = new ArrayList<>();
        primitiveEventList.add(new PrimitiveEventData("MonPEGCoResponseTime","PEG_Co_ResponseTime", 78000L));
        primitiveEventList.add(new PrimitiveEventData("MonPEGVmCpu","PEG_Vm_Cpu", 11000L));
        primitiveEventList.add(new PrimitiveEventData("MonPEGPmCpu","PEG_Pm_Cpu", 98000L));

		ResultatParsing resultatParsing = ResultatParsing.FAB();
		String filePath = TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testCreateAllPrimitivesEvents_OK.xml";

		NodeList listPrimitiveEventExpected = (NodeList) xPath.evaluate("//primitive",
                XMLManager.getElementRootFromFile(filePath), XPathConstants.NODESET);

		XMLFileParserToPrimitiveEventData.createAllPrimitivesEvents(xPath, listPrimitiveEventExpected, resultatParsing);

		assertEquals("Creation des primitives events", primitiveEventList, resultatParsing.getPrimitiveEventList());
		assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
		assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

	}

	@Test
    public void testCreateAllPrimitivesEvents_KO_NoPrimitivesNode() throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {

        ResultatParsing resultatParsing = ResultatParsing.FAB();
        String filePath = TestConstants.XMLFileParserPrimitiveEventsTestFolder +  "testCreateAllPrimitivesEvents_KO_NoPrimitivesNode.xml";

        NodeList listExpected = (NodeList) xPath.evaluate("//primitive",
                XMLManager.getElementRootFromFile(filePath), XPathConstants.NODESET);

        XMLFileParserToPrimitiveEventData.createAllPrimitivesEvents(xPath, listExpected, resultatParsing);

        assertEquals("Creation des primitives events", new ArrayList<PrimitiveEventData>(), resultatParsing.getPrimitiveEventList());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void testCreateAllPrimitivesEvents_ParseOnlyEnabledPrimitiveEvents() throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {

        List<PrimitiveEventData> primitiveEventList = new ArrayList<>();
        primitiveEventList.add(new PrimitiveEventData("MonPEGCoResponseTime","PEG_Co_ResponseTime", 78000L));
        primitiveEventList.add(new PrimitiveEventData("MonPEGPmCPU","PEG_Pm_Cpu", 9000L));
        primitiveEventList.add(new PrimitiveEventData("MonPEGVmCPU","PEG_Vm_Cpu", 1000L));

        ResultatParsing resultatParsing = ResultatParsing.FAB();
        String filePath = TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testCreateAllPrimitivesEvents_ParseOnlyEnabledPE.xml";

        NodeList listPrimitiveEventExpected = (NodeList) xPath.evaluate("//primitive",
                XMLManager.getElementRootFromFile(filePath), XPathConstants.NODESET);

        XMLFileParserToPrimitiveEventData.createAllPrimitivesEvents(xPath, listPrimitiveEventExpected, resultatParsing);

        assertEquals("Creation des primitives events", primitiveEventList, resultatParsing.getPrimitiveEventList());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void testCreateAllPrimitivesEvents_NoParsingOfPrimitiveEventWithSameName() throws IOException, SAXException, ParserConfigurationException {

        ResultatParsing resultatParsing = XMLFileParserToPrimitiveEventData
                .parse(TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testCreateAllPrimitivesEvents_NoParsingOfPEWithSameName.xml");

        assertFalse("Parsing fichier XML valide", resultatParsing.getPrimitiveEventList().isEmpty());
        assertEquals("Parsing fichier XML valide", 5, resultatParsing.getPrimitiveEventList().size());

        assertEquals("Liste erreurs fichier - taille", 0, resultatParsing.getFileErrorTypes().size());

        System.out.println(resultatParsing.getParsingErrorTypes());
        assertEquals("Liste erreurs parsing - taille", 1, resultatParsing.getParsingErrorTypes().size());
        assertEquals("Liste erreurs fichier - contenu", Arrays.asList(ParsingErrorType.EVENT_PRIMITIVES_DUPLICATED_NAME), resultatParsing.getParsingErrorTypes());
        assertEquals("Liste erreurs fichier - complement erreur", Arrays.asList("monPE1", "MonPE1", "MonPE2", "MonPE2"), ParsingErrorType.EVENT_PRIMITIVES_DUPLICATED_NAME.getComplements());

    }

	@Test
	public void testGetPrimitiveEventNameFromFile_OK()
			throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

		String filePath = TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testGetPrimitiveEventNameFromFile_OK.xml";
		ResultatParsing resultatParsing = ResultatParsing.FAB();

		NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitive",
                XMLManager.getElementRootFromFile(filePath), XPathConstants.NODESET);
		// Récupération du premier primitive event
		Node node = nodeListJeuxDeDonneesFromFile.item(0);

		// Appel de la méthode à tester
        Optional<String> primitiveEventNameActual = XMLFileParserToPrimitiveEventData.getPrimitiveEventNameFromFile(xPath, node, resultatParsing);

		assertEquals("Recupération du nom du primitive event", "MonPEGVmDisk", primitiveEventNameActual.get());
		assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventList().isEmpty());
		assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
		assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

	}

    @Test
    public void testGetPrimitiveEventNameFromFile_KO()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testGetPrimitiveEventNameFromFile_KO.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitive",
                XMLManager.getElementRootFromFile(filePath), XPathConstants.NODESET);
        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(0);
        Optional<String> primitiveEventNameActual = XMLFileParserToPrimitiveEventData.getPrimitiveEventNameFromFile(xPath, node, resultatParsing);

        assertFalse("Recupération du name du primitive event", primitiveEventNameActual.isPresent());
        assertTrue("Liste de primitive events", resultatParsing.getPrimitiveEventList().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().containsAll(Collections.singletonList(ParsingErrorType.EVENT_PRIMITIVES_INVALID_NAME)));
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void testGetPrimitiveEventRuntimeFromFile_OK()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testGetPrimitiveEventRuntimeFromFile_OK.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitive",
                XMLManager.getElementRootFromFile(filePath), XPathConstants.NODESET);

        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(0);
        Optional<Long> primitiveEventRuntimeActual = XMLFileParserToPrimitiveEventData.getPrimitiveEventRuntimeFromFile(xPath, node, resultatParsing);

        assertEquals("Recupération du runtime du primitive event", new Long(900), primitiveEventRuntimeActual.get());
        assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventList().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void testGetPrimitiveEventTypeFromFileFromFile_KO()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testGetPrimitiveEventTypeFromFile_KO.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitive",
                XMLManager.getElementRootFromFile(filePath), XPathConstants.NODESET);
        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(0);
        Optional<String> primitiveEventNameActual = XMLFileParserToPrimitiveEventData.getPrimitiveEventTypeFromFile(xPath, node, resultatParsing);

        assertFalse("Recupération du type du primitive event", primitiveEventNameActual.isPresent());
        assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventList().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().containsAll(Collections.singletonList(ParsingErrorType.EVENT_PRIMITIVES_INVALID_TYPE)));
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void testGetPrimitiveEventTypeFromFileFromFile_OK()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testGetPrimitiveEventTypeFromFile_OK.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitive",
                XMLManager.getElementRootFromFile(filePath), XPathConstants.NODESET);

        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(0);
        Optional<String> primitiveEventTypeActual = XMLFileParserToPrimitiveEventData.getPrimitiveEventTypeFromFile(xPath, node, resultatParsing);

        assertEquals("Recupération du runtime du primitive event", "PEG_Vm_Disk", primitiveEventTypeActual.get());
        assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventList().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void testGetPrimitiveEventRuntimeFromFile_KO()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testGetPrimitiveEventRuntimeFromFile_KO.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitive",
                XMLManager.getElementRootFromFile(filePath), XPathConstants.NODESET);
        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(0);
        Optional<Long> primitiveEventRuntimeActual = XMLFileParserToPrimitiveEventData.getPrimitiveEventRuntimeFromFile(xPath, node, resultatParsing);

        assertFalse("Recupération du runtime du primitive event", primitiveEventRuntimeActual.isPresent());
        assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventList().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().containsAll(Collections.singletonList(ParsingErrorType.EVENT_PRIMITIVES_INVALID_RUNTIME)));
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

}
