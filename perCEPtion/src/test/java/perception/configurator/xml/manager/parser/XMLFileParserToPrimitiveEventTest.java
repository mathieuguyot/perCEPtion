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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class XMLFileParserToPrimitiveEventTest {

	// Le xPath
	XPathFactory xpf = XPathFactory.newInstance();
	XPath xPath = xpf.newXPath();

	@Test
	public void testParse_valideFile() throws ParserConfigurationException, SAXException, IOException {

		Map<String, Long> primitiveEventMap = new HashMap<>();
		primitiveEventMap.put("PEG_Co_ResponseTime", 78000L);
		primitiveEventMap.put("PEG_Pm_Cpu", 98000L);
		primitiveEventMap.put("PEG_Vm_Cpu", 11000L);

		ResultatParsing resultatParsing = XMLFileParserToPrimitiveEvent
				.parse(TestConstants.XMLFileParserTestFolder + "testParse_valideFile.xml");

		assertEquals("Parsing fichier XML valide", primitiveEventMap, resultatParsing.getPrimitiveEventMap());
		assertEquals("Liste erreurs fichier - taille", 0, resultatParsing.getFileErrorTypes().size());
		assertEquals("Liste erreurs parsing - taille", 0, resultatParsing.getParsingErrorTypes().size());
	}

	@Test
	public void testParse_inexistingFile() throws ParserConfigurationException, SAXException, IOException {
		ResultatParsing resultatParsing = XMLFileParserToPrimitiveEvent
				.parse(TestConstants.XMLFileParserTestFolder +  "inexistingFile.csv");
		List<FileErrorType> errList = resultatParsing.getFileErrorTypes();
		assertTrue("Fichier introuvable - type erreur", errList.contains(FileErrorType.FILE_NOT_FOUND));
		assertEquals("Liste erreurs fichier - taille", 1, errList.size());
		assertEquals("Liste erreurs parsing - taille", 0, resultatParsing.getParsingErrorTypes().size());
	}

	@Test
	public void testParse_invalidFileFormatTXT() throws ParserConfigurationException, SAXException, IOException {

		Map<String, Integer> primitiveEventMap = new HashMap<>();
        primitiveEventMap.put("PEG_Co_ResponseTime", 78000);
        primitiveEventMap.put("PEG_Pm_Cpu", 98000);
        primitiveEventMap.put("PEG_Vm_Cpu", 11000);

        ResultatParsing resultatParsing = XMLFileParserToPrimitiveEvent
				.parse(TestConstants.XMLFileParserTestFolder +  "testParser_invalidFileFormatTXT.txt");
		assertEquals("Parsing fichier format invalide (TXT)", primitiveEventMap, resultatParsing.getPrimitiveEventMap());
		assertEquals("Liste erreurs fichier - taille", 1, resultatParsing.getFileErrorTypes().size());
		assertEquals("Liste erreurs parsing - taille", 0, resultatParsing.getParsingErrorTypes().size());
	}

	@Test
	public void testParse_invalidFileFormatCSV() throws ParserConfigurationException, SAXException, IOException {

	    Map<String, Integer> primitiveEventMap = new HashMap<>();
        primitiveEventMap.put("PEG_Co_ResponseTime", 78000);
        primitiveEventMap.put("PEG_Pm_Cpu", 98000);
        primitiveEventMap.put("PEG_Vm_Cpu", 11000);

        ResultatParsing resultatParsing = XMLFileParserToPrimitiveEvent
				.parse(TestConstants.XMLFileParserTestFolder + "testParse_invalidFileFormatCSV.csv");
		assertEquals("Parsing fichier format invalide (TXT)", primitiveEventMap, resultatParsing.getPrimitiveEventMap());
		assertEquals("Liste erreurs fichier - taille", 1, resultatParsing.getFileErrorTypes().size());
		assertEquals("Liste erreurs parsing - taille", 0, resultatParsing.getParsingErrorTypes().size());
	}

	@Test
	public void testParse_invalidFileFormatPPT() throws ParserConfigurationException, SAXException, IOException {

	    Map<String, Integer> primitiveEventMap = new HashMap<>();
        primitiveEventMap.put("PEG_Co_ResponseTime", 78000);
        primitiveEventMap.put("PEG_Pm_Cpu", 98000);
        primitiveEventMap.put("PEG_Vm_Cpu", 11000);

        ResultatParsing resultatParsing = XMLFileParserToPrimitiveEvent
				.parse(TestConstants.XMLFileParserTestFolder +  "testParser_invalidFileFormatPPT.ppt");
		assertEquals("Parsing fichier format invalide (TXT)", primitiveEventMap, resultatParsing.getPrimitiveEventMap());
		assertEquals("Liste erreurs fichier - taille", 1, resultatParsing.getFileErrorTypes().size());
		assertEquals("Liste erreurs parsing - taille", 0, resultatParsing.getParsingErrorTypes().size());
	}

	@Test
	public void testParsePrimitiveEvents_OK() throws SAXException, IOException, ParserConfigurationException {

        Map<String, Long> primitiveEventMap = new HashMap<>();
        primitiveEventMap.put("PEG_Co_ResponseTime", 78000L);
        primitiveEventMap.put("PEG_Pm_Cpu", 98000L);
        primitiveEventMap.put("PEG_Vm_Cpu", 11000L);

		String filePath = TestConstants.XMLFileParserTestFolder + "testParsePrimitiveEvents_OK.xml";

		ResultatParsing resultatParsing = ResultatParsing.FAB();
		XMLFileParserToPrimitiveEvent.parsePrimitivesEvent(xPath, this.getElementRootFromFile(filePath), resultatParsing);

		assertEquals("Parsing des primitives events d'un fichier xml valide", primitiveEventMap,
				resultatParsing.getPrimitiveEventMap());
		assertEquals("Liste erreurs fichier - taille", 0, resultatParsing.getFileErrorTypes().size());
		assertEquals("Liste erreurs parsing - taille", 0, resultatParsing.getParsingErrorTypes().size());

	}

	@Test
	public void testGetPrimitiveEventInFile_OK()
			throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

		String filePath = TestConstants.XMLFileParserTestFolder + "testGetPrimitiveEventInFile_OK.xml";

		ResultatParsing resultatParsing = ResultatParsing.FAB();
		NodeList listPrimitiveEventsExpected = (NodeList) xPath.evaluate("//primitives",
				this.getElementRootFromFile(filePath), XPathConstants.NODESET);

		NodeList listPrimitiveEventsActual = XMLFileParserToPrimitiveEvent.getPrimitivesEventInFile(xPath,
				this.getElementRootFromFile(filePath), resultatParsing);

		assertEquals("Recupération primitives events du fichier", listPrimitiveEventsExpected, listPrimitiveEventsActual);

		assertTrue("Parsing des primitives events d'un fichier xml valide",
				resultatParsing.getPrimitiveEventMap().isEmpty());
		assertTrue("Liste erreurs fichier - taille", resultatParsing.getFileErrorTypes().isEmpty());
		assertTrue("Liste erreurs parsing - taille", resultatParsing.getParsingErrorTypes().isEmpty());

	}

    @Test
    public void testGetPrimitiveEventInFile_NoPrimitiveEventInFile()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserTestFolder + "testGetPrimitiveEventInFile_NoPrimitiveEventInFile.xml";

        ResultatParsing resultatParsing = ResultatParsing.FAB();
        NodeList listPrimitiveEventsExpected = (NodeList) xPath.evaluate("//primitives",
                this.getElementRootFromFile(filePath), XPathConstants.NODESET);

        NodeList listPrimitiveEventsActual = XMLFileParserToPrimitiveEvent.getPrimitivesEventInFile(xPath,
                this.getElementRootFromFile(filePath), resultatParsing);

        assertEquals("Recupération primitives events du fichier", listPrimitiveEventsExpected, listPrimitiveEventsActual);

        assertTrue("Parsing des primitives events d'un fichier xml valide",
                resultatParsing.getPrimitiveEventMap().isEmpty());
        assertTrue("Liste erreurs fichier - taille", resultatParsing.getFileErrorTypes().isEmpty());
        assertTrue("Liste erreurs parsing - taille", resultatParsing.getParsingErrorTypes().containsAll(Collections.singletonList(ParsingErrorType.INVALID_PRIMITIVES_NODE)));

    }

	@Test
	public void testCreateAllPrimitivesEvents_OK()
			throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        Map<String, Integer> primitiveEventMap = new HashMap<>();
        primitiveEventMap.put("PEG_Co_ResponseTime", 78000);
        primitiveEventMap.put("PEG_Pm_Cpu", 98000);
        primitiveEventMap.put("PEG_Vm_Cpu", 11000);

		ResultatParsing resultatParsing = ResultatParsing.FAB();
		String filePath = TestConstants.XMLFileParserTestFolder + "testCreateAllPrimitivesEvents_OK.xml";

		NodeList listPrimitiveEventExpected = (NodeList) xPath.evaluate("//primitives",
				this.getElementRootFromFile(filePath), XPathConstants.NODESET);

		XMLFileParserToPrimitiveEvent.createAllPrimitivesEvents(xPath, listPrimitiveEventExpected, resultatParsing);

		assertEquals("Creation des primitives events", primitiveEventMap, resultatParsing.getPrimitiveEventMap());
		assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
		assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

	}

	@Test
    public void testCreateAllPrimitivesEvents_KO_NoPrimitivesNode() throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {

        ResultatParsing resultatParsing = ResultatParsing.FAB();
        String filePath = TestConstants.XMLFileParserTestFolder +  "testCreateAllPrimitivesEvents_OK.xml";

        NodeList listJeuxDeDonneesExpected = (NodeList) xPath.evaluate("//primitives",
                this.getElementRootFromFile(filePath), XPathConstants.NODESET);

        XMLFileParserToPrimitiveEvent.createAllPrimitivesEvents(xPath, listJeuxDeDonneesExpected, resultatParsing);

        assertEquals("Creation des primitives events", null, resultatParsing.getPrimitiveEventMap());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().containsAll(Collections.singletonList(ParsingErrorType.INVALID_PRIMITIVES_NODE)));
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void createAllPrimitivesEvents_ParseOnlyEnabledPrimitiveEvents() throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {

        Map<String, Integer> primitiveEventMap = new HashMap<>();
        primitiveEventMap.put("PEG_Co_ResponseTime", 78000);
        primitiveEventMap.put("PEG_Pm_Cpu", 9000);
        primitiveEventMap.put("PEG_Vm_Cpu", 1000);

        ResultatParsing resultatParsing = ResultatParsing.FAB();
        String filePath = TestConstants.XMLFileParserTestFolder + "createAllPrimitivesEvents_ParseOnlyEnabledPrimitiveEvents.xml";

        NodeList listPrimitiveEventExpected = (NodeList) xPath.evaluate("//primitives",
                this.getElementRootFromFile(filePath), XPathConstants.NODESET);

        XMLFileParserToPrimitiveEvent.createAllPrimitivesEvents(xPath, listPrimitiveEventExpected, resultatParsing);

        assertEquals("Creation des primitives events", primitiveEventMap, resultatParsing.getPrimitiveEventMap());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());


    }

    @Test
    public void createAllPrimitivesEvents_ParseOnlyOneAPrimitiveEventWithSameName() {
        fail("Ajouter dans le code une erreur de parsing si on a deux fois le même primitive event name");
    }

	@Test
	public void testGetPrimitiveEventNameFromFile_OK()
			throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

		String filePath = TestConstants.XMLFileParserTestFolder + "testGetPrimitiveEventNameFromFile_OK.xml";
		ResultatParsing resultatParsing = ResultatParsing.FAB();

		NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitive",
				this.getElementRootFromFile(filePath), XPathConstants.NODESET);
		// Récupération du premier primitive event
		Node node = nodeListJeuxDeDonneesFromFile.item(0);

		// Appel de la méthode à tester
        String primitiveEventNameActual = XMLFileParserToPrimitiveEvent.getPrimitiveEventNameFromFile(xPath, node, resultatParsing);

		assertEquals("Recupération du nom du primitive event", "PEG_Vm_Disk", primitiveEventNameActual);
		assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventMap().isEmpty());
		assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
		assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

	}

    @Test
    public void testGetPrimitiveEventNameFromFile_KO()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserTestFolder + "testGetPrimitiveEventNameFromFile_KO.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitives",
                this.getElementRootFromFile(filePath), XPathConstants.NODESET);
        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(1);
        String primitiveEventNameActual = XMLFileParserToPrimitiveEvent.getPrimitiveEventNameFromFile(xPath, node, resultatParsing);

        assertEquals("Recupération du nom du primitive event", null, primitiveEventNameActual);
        assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventMap().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().containsAll(Collections.singletonList(ParsingErrorType.INVALID_PRIMITIVE_NAME)));
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void testGetPrimitiveEventRuntimeFromFile_OK()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserTestFolder + "testGetPrimitiveEventRuntimeFromFile_OK.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitive",
                this.getElementRootFromFile(filePath), XPathConstants.NODESET);

        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(0);
        Long primitiveEventRuntimeActual = XMLFileParserToPrimitiveEvent.getPrimitiveEventRuntimeFromFile(xPath, node, resultatParsing);

        assertEquals("Recupération du runtime du primitive event", new Long(78000), primitiveEventRuntimeActual);
        assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventMap().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void testGetPrimitiveEventRuntimeFromFile_KO()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserTestFolder + "testGetPrimitiveEventRuntimeFromFile_KO.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitives",
                this.getElementRootFromFile(filePath), XPathConstants.NODESET);
        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(1);
        Long primitiveEventNameActual = XMLFileParserToPrimitiveEvent.getPrimitiveEventRuntimeFromFile(xPath, node, resultatParsing);

        assertEquals("Recupération du runtime du primitive event", null, primitiveEventNameActual);
        assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventMap().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().containsAll(Collections.singletonList(ParsingErrorType.INVALID_PRIMITIVE_RUNTIME)));
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void testIsEnabledPrimitiveEvent_OK_EnabledTrue()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserTestFolder + "testIsEnabledPrimitiveEvent_OK_EnabledTrue.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitives",
                this.getElementRootFromFile(filePath), XPathConstants.NODESET);
        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(1);
        boolean primitiveEventEnabledActual = XMLFileParserToPrimitiveEvent.isEnabledPrimitiveEvent(xPath, node, resultatParsing);

        assertTrue("Recupération du runtime du primitive event", primitiveEventEnabledActual);
        assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventMap().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    public void testIsEnabledPrimitiveEvent_OK_EnabledFalse()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserTestFolder + "testIsEnabledPrimitiveEvent_OK_EnabledFalse.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitives",
                this.getElementRootFromFile(filePath), XPathConstants.NODESET);
        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(1);
        boolean primitiveEventEnabledActual = XMLFileParserToPrimitiveEvent.isEnabledPrimitiveEvent(xPath, node, resultatParsing);

        assertTrue("Recupération du runtime du primitive event", primitiveEventEnabledActual);
        assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventMap().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void testIsEnabledPrimitiveEvent_KO()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserTestFolder + "testIsEnabledPrimitiveEvent_KO.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitives",
                this.getElementRootFromFile(filePath), XPathConstants.NODESET);
        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(1);
        boolean primitiveEventEnabledActual = XMLFileParserToPrimitiveEvent.isEnabledPrimitiveEvent(xPath, node, resultatParsing);

        assertEquals("Recupération de l'attribut enabled du primitive event", null, primitiveEventEnabledActual);
        assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventMap().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().containsAll(Collections.singletonList(ParsingErrorType.INVALID_PRIMITIVE_ENABLED_ATTR)));
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }


	/**
	 * Recupère l'élément racine du fichier XML.
	 * 
	 * @param filePath
	 *            - le chemin vers le fichier
	 * @return l'élément racine du fichier XML
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	Element getElementRootFromFile(String filePath) throws ParserConfigurationException, SAXException, IOException {
		File fileXML = new File(filePath);
		// Recupération de la racine du document
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document xml = builder.parse(fileXML);
		Element root = xml.getDocumentElement();
		return root;
	}

}
