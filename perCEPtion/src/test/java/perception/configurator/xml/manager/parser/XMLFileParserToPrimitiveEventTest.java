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
import perception.configurator.xml.manager.model.PEData;

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

public class XMLFileParserToPrimitiveEventTest {

	// Le xPath
	XPathFactory xpf = XPathFactory.newInstance();
	XPath xPath = xpf.newXPath();

	@Test
	public void testParse_valideFile() throws ParserConfigurationException, SAXException, IOException {

	    List<PEData> primitiveEventList = new ArrayList<>();
		primitiveEventList.add(new PEData("MonPEGCoResponseTime","PEG_Co_ResponseTime", 78000L));
        primitiveEventList.add(new PEData("MonPEGPmCPU","PEG_Vm_Cpu", 11000L));
        primitiveEventList.add(new PEData("MonPEGVmCPU","PEG_Pm_Cpu", 98000L));

		ResultatParsing resultatParsing = XMLFileParserToPrimitiveEvent
				.parse(TestConstants.XMLFileParserTestFolder + "testParse_valideFile.xml");

		assertEquals("Parsing fichier XML valide", primitiveEventList, resultatParsing.getPrimitiveEventList());
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

        List<PEData> primitiveEventList = new ArrayList<>();
        primitiveEventList.add(new PEData("MonPEGCoResponseTime","PEG_Co_ResponseTime", 78000L));
        primitiveEventList.add(new PEData("MonPEGPmCPU","PEG_Vm_Cpu", 11000L));
        primitiveEventList.add(new PEData("MonPEGVmCPU","PEG_Pm_Cpu", 98000L));

        ResultatParsing resultatParsing = XMLFileParserToPrimitiveEvent
				.parse(TestConstants.XMLFileParserTestFolder +  "testParser_invalidFileFormatTXT.txt");
		assertEquals("Parsing fichier format invalide (TXT)", primitiveEventList, resultatParsing.getPrimitiveEventList());
		assertEquals("Liste erreurs fichier - taille", 0, resultatParsing.getFileErrorTypes().size());
		assertEquals("Liste erreurs parsing - taille", 0, resultatParsing.getParsingErrorTypes().size());
	}

	@Test
	public void testParse_invalidFileFormatCSV() throws ParserConfigurationException, SAXException, IOException {

        List<PEData> primitiveEventList = new ArrayList<>();
        primitiveEventList.add(new PEData("MonPEGCoResponseTime","PEG_Co_ResponseTime", 78945L));
        primitiveEventList.add(new PEData("MonPEGPmCPU","PEG_Pm_Cpu", 12000L));

        ResultatParsing resultatParsing = XMLFileParserToPrimitiveEvent
				.parse(TestConstants.XMLFileParserTestFolder + "testParse_invalidFileFormatCSV.csv");
		assertEquals("Parsing fichier format invalide (TXT)", primitiveEventList, resultatParsing.getPrimitiveEventList());
		assertEquals("Liste erreurs fichier - taille", 0, resultatParsing.getFileErrorTypes().size());
		assertEquals("Liste erreurs parsing - taille", 0, resultatParsing.getParsingErrorTypes().size());
	}

	@Test
	public void testParse_invalidFileFormatPPT() throws ParserConfigurationException, SAXException, IOException {

        List<PEData> primitiveEventList = new ArrayList<>();
        primitiveEventList.add(new PEData("MonPEGCoResponseTime","PEG_Co_ResponseTime", 78000L));
        primitiveEventList.add(new PEData("MonPEGVmCPU","PEG_Vm_Cpu", 11000L));
        primitiveEventList.add(new PEData("MonPEGPmCPU","PEG_Pm_Cpu", 98000L));

        ResultatParsing resultatParsing = XMLFileParserToPrimitiveEvent
				.parse(TestConstants.XMLFileParserTestFolder +  "testParser_invalidFileFormatPPT.ppt");
		assertEquals("Parsing fichier format invalide (TXT)", primitiveEventList, resultatParsing.getPrimitiveEventList());
		assertEquals("Liste erreurs fichier - taille", 0, resultatParsing.getFileErrorTypes().size());
		assertEquals("Liste erreurs parsing - taille", 0, resultatParsing.getParsingErrorTypes().size());
	}

	@Test
	public void testParsePrimitiveEvents_OK() throws SAXException, IOException, ParserConfigurationException {

        List<PEData> primitiveEventList = new ArrayList<>();
        primitiveEventList.add(new PEData("MonPEGCoResponseTime","PEG_Co_ResponseTime", 78000L));
        primitiveEventList.add(new PEData("MonPEGPmCPU","PEG_Vm_Cpu", 11000L));
        primitiveEventList.add(new PEData("MonPEGVmCPU","PEG_Pm_Cpu", 98000L));

		String filePath = TestConstants.XMLFileParserTestFolder + "testParsePrimitiveEvents_OK.xml";

		ResultatParsing resultatParsing = ResultatParsing.FAB();
		XMLFileParserToPrimitiveEvent.parsePrimitivesEvent(xPath, this.getElementRootFromFile(filePath), resultatParsing);

		assertEquals("Parsing des primitives events d'un fichier xml valide", primitiveEventList,
				resultatParsing.getPrimitiveEventList());
		assertEquals("Liste erreurs fichier - taille", 0, resultatParsing.getFileErrorTypes().size());
		assertEquals("Liste erreurs parsing - taille", 0, resultatParsing.getParsingErrorTypes().size());

	}

	@Test
	public void testGetPrimitiveEventInFile_OK()
			throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

		String filePath = TestConstants.XMLFileParserTestFolder + "testGetPrimitiveEventInFile_OK.xml";

		ResultatParsing resultatParsing = ResultatParsing.FAB();
		NodeList listPrimitiveEventsExpected = (NodeList) xPath.evaluate("//primitive",
				this.getElementRootFromFile(filePath), XPathConstants.NODESET);
        Optional<NodeList> listPrimitiveEventsExpectedOptional = Optional.of(listPrimitiveEventsExpected);

		Optional<NodeList> listPrimitiveEventsActualOptional = XMLFileParserToPrimitiveEvent.getPrimitivesEventInFile(xPath,
				this.getElementRootFromFile(filePath), resultatParsing);

		assertEquals("Recupération primitives events du fichier", listPrimitiveEventsExpectedOptional.get().getLength(), listPrimitiveEventsActualOptional.get().getLength());
		assertTrue("Parsing des primitives events d'un fichier xml valide",
				resultatParsing.getPrimitiveEventList().isEmpty());
		assertTrue("Liste erreurs fichier - taille", resultatParsing.getFileErrorTypes().isEmpty());
		assertTrue("Liste erreurs parsing - taille", resultatParsing.getParsingErrorTypes().isEmpty());

	}

    @Test
    public void testGetPrimitiveEventInFile_NoPrimitiveEventInFile()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserTestFolder + "testGetPrimitiveEventInFile_NoPrimitiveEventInFile.xml";

        ResultatParsing resultatParsing = ResultatParsing.FAB();
        NodeList listPrimitiveEventsExpected = (NodeList) xPath.evaluate("//primitive",
                this.getElementRootFromFile(filePath), XPathConstants.NODESET);

        Optional<NodeList> listPrimitiveEventsActual = XMLFileParserToPrimitiveEvent.getPrimitivesEventInFile(xPath,
                this.getElementRootFromFile(filePath), resultatParsing);

        assertEquals("Recupération primitives events du fichier", listPrimitiveEventsExpected.getLength(), listPrimitiveEventsActual.get().getLength());
        assertTrue("Parsing des primitives events d'un fichier xml valide",
                resultatParsing.getPrimitiveEventList().isEmpty());
        assertTrue("Liste erreurs fichier - taille", resultatParsing.getFileErrorTypes().isEmpty());
        assertTrue("Liste erreurs parsing - contenu", resultatParsing.getParsingErrorTypes().isEmpty());

    }

	@Test
	public void testCreateAllPrimitivesEvents_OK()
			throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        List<PEData> primitiveEventList = new ArrayList<>();
        primitiveEventList.add(new PEData("MonPEGCoResponseTime","PEG_Co_ResponseTime", 78000L));
        primitiveEventList.add(new PEData("MonPEGVmCpu","PEG_Vm_Cpu", 11000L));
        primitiveEventList.add(new PEData("MonPEGPmCpu","PEG_Pm_Cpu", 98000L));

		ResultatParsing resultatParsing = ResultatParsing.FAB();
		String filePath = TestConstants.XMLFileParserTestFolder + "testCreateAllPrimitivesEvents_OK.xml";

		NodeList listPrimitiveEventExpected = (NodeList) xPath.evaluate("//primitive",
				this.getElementRootFromFile(filePath), XPathConstants.NODESET);

		XMLFileParserToPrimitiveEvent.createAllPrimitivesEvents(xPath, listPrimitiveEventExpected, resultatParsing);

		assertEquals("Creation des primitives events", primitiveEventList, resultatParsing.getPrimitiveEventList());
		assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
		assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

	}

	@Test
    public void testCreateAllPrimitivesEvents_KO_NoPrimitivesNode() throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {

        ResultatParsing resultatParsing = ResultatParsing.FAB();
        String filePath = TestConstants.XMLFileParserTestFolder +  "testCreateAllPrimitivesEvents_KO_NoPrimitivesNode.xml";

        NodeList listExpected = (NodeList) xPath.evaluate("//primitive",
                this.getElementRootFromFile(filePath), XPathConstants.NODESET);

        XMLFileParserToPrimitiveEvent.createAllPrimitivesEvents(xPath, listExpected, resultatParsing);

        assertEquals("Creation des primitives events", new ArrayList<PEData>(), resultatParsing.getPrimitiveEventList());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void testCreateAllPrimitivesEvents_ParseOnlyEnabledPrimitiveEvents() throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {

        List<PEData> primitiveEventList = new ArrayList<>();
        primitiveEventList.add(new PEData("MonPEGCoResponseTime","PEG_Co_ResponseTime", 78000L));
        primitiveEventList.add(new PEData("MonPEGPmCPU","PEG_Pm_Cpu", 9000L));
        primitiveEventList.add(new PEData("MonPEGVmCPU","PEG_Vm_Cpu", 1000L));

        ResultatParsing resultatParsing = ResultatParsing.FAB();
        String filePath = TestConstants.XMLFileParserTestFolder + "testCreateAllPrimitivesEvents_ParseOnlyEnabledPE.xml";

        NodeList listPrimitiveEventExpected = (NodeList) xPath.evaluate("//primitive",
                this.getElementRootFromFile(filePath), XPathConstants.NODESET);

        XMLFileParserToPrimitiveEvent.createAllPrimitivesEvents(xPath, listPrimitiveEventExpected, resultatParsing);

        assertEquals("Creation des primitives events", primitiveEventList, resultatParsing.getPrimitiveEventList());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void testCreateAllPrimitivesEvents_NoParsingOfPrimitiveEventWithSameName() throws IOException, SAXException, ParserConfigurationException {

        ResultatParsing resultatParsing = XMLFileParserToPrimitiveEvent
                .parse(TestConstants.XMLFileParserTestFolder + "testCreateAllPrimitivesEvents_NoParsingOfPrimitiveEventWithSameName.xml");

        assertFalse("Parsing fichier XML valide", resultatParsing.getPrimitiveEventList().isEmpty());
        assertEquals("Parsing fichier XML valide", 5, resultatParsing.getPrimitiveEventList().size());

        assertEquals("Liste erreurs fichier - taille", 0, resultatParsing.getFileErrorTypes().size());

        System.out.println(resultatParsing.getParsingErrorTypes());
        assertEquals("Liste erreurs parsing - taille", 1, resultatParsing.getParsingErrorTypes().size());
        assertEquals("Liste erreurs fichier - contenu", Arrays.asList(ParsingErrorType.PRIMITIVES_EVENT_DUPLICATED_NAME), resultatParsing.getParsingErrorTypes());
        assertEquals("Liste erreurs fichier - complement erreur", Arrays.asList("monPE1", "MonPE1", "MonPE2", "MonPE2"), ParsingErrorType.PRIMITIVES_EVENT_DUPLICATED_NAME.getComplements());

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
        Optional<String> primitiveEventNameActual = XMLFileParserToPrimitiveEvent.getPrimitiveEventNameFromFile(xPath, node, resultatParsing);

		assertEquals("Recupération du nom du primitive event", "MonPEGVmDisk", primitiveEventNameActual.get());
		assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventList().isEmpty());
		assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
		assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

	}

    @Test
    public void testGetPrimitiveEventNameFromFile_KO()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserTestFolder + "testGetPrimitiveEventNameFromFile_KO.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitive",
                this.getElementRootFromFile(filePath), XPathConstants.NODESET);
        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(0);
        Optional<String> primitiveEventNameActual = XMLFileParserToPrimitiveEvent.getPrimitiveEventNameFromFile(xPath, node, resultatParsing);

        assertFalse("Recupération du name du primitive event", primitiveEventNameActual.isPresent());
        assertTrue("Liste de primitive events", resultatParsing.getPrimitiveEventList().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().containsAll(Collections.singletonList(ParsingErrorType.PRIMITIVES_EVENT_INVALID_NAME)));
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
        Optional<Long> primitiveEventRuntimeActual = XMLFileParserToPrimitiveEvent.getPrimitiveEventRuntimeFromFile(xPath, node, resultatParsing);

        assertEquals("Recupération du runtime du primitive event", new Long(900), primitiveEventRuntimeActual.get());
        assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventList().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void testGetPrimitiveEventTypeFromFileFromFile_KO()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserTestFolder + "testGetPrimitiveEventTypeFromFile_KO.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitive",
                this.getElementRootFromFile(filePath), XPathConstants.NODESET);
        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(0);
        Optional<String> primitiveEventNameActual = XMLFileParserToPrimitiveEvent.getPrimitiveEventTypeFromFile(xPath, node, resultatParsing);

        assertFalse("Recupération du type du primitive event", primitiveEventNameActual.isPresent());
        assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventList().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().containsAll(Collections.singletonList(ParsingErrorType.PRIMITIVES_EVENT_INVALID_TYPE)));
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void testGetPrimitiveEventTypeFromFileFromFile_OK()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserTestFolder + "testGetPrimitiveEventTypeFromFile_OK.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitive",
                this.getElementRootFromFile(filePath), XPathConstants.NODESET);

        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(0);
        Optional<String> primitiveEventTypeActual = XMLFileParserToPrimitiveEvent.getPrimitiveEventTypeFromFile(xPath, node, resultatParsing);

        assertEquals("Recupération du runtime du primitive event", "PEG_Vm_Disk", primitiveEventTypeActual.get());
        assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventList().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void testGetPrimitiveEventRuntimeFromFile_KO()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserTestFolder + "testGetPrimitiveEventRuntimeFromFile_KO.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitive",
                this.getElementRootFromFile(filePath), XPathConstants.NODESET);
        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(0);
        Optional<Long> primitiveEventRuntimeActual = XMLFileParserToPrimitiveEvent.getPrimitiveEventRuntimeFromFile(xPath, node, resultatParsing);

        assertFalse("Recupération du runtime du primitive event", primitiveEventRuntimeActual.isPresent());
        assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventList().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().containsAll(Collections.singletonList(ParsingErrorType.PRIMITIVES_EVENT_INVALID_RUNTIME)));
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void testIsEnabledPrimitiveEvent_OK_EnabledTrue()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserTestFolder + "testIsEnabledPrimitiveEvent_OK_EnabledTrue.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitive",
                this.getElementRootFromFile(filePath), XPathConstants.NODESET);
        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(1);
        boolean primitiveEventEnabledActual = XMLFileParserToPrimitiveEvent.isEnabledPrimitiveEvent(xPath, node, resultatParsing);

        assertTrue("Recupération du runtime du primitive event", primitiveEventEnabledActual);
        assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventList().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void testIsEnabledPrimitiveEvent_OK_EnabledFalse()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserTestFolder + "testIsEnabledPrimitiveEvent_OK_EnabledFalse.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitive",
                this.getElementRootFromFile(filePath), XPathConstants.NODESET);
        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(1);
        boolean primitiveEventEnabledActual = XMLFileParserToPrimitiveEvent.isEnabledPrimitiveEvent(xPath, node, resultatParsing);

        assertTrue("Recupération du runtime du primitive event", primitiveEventEnabledActual);
        assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventList().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void testIsEnabledPrimitiveEvent_NoEnableAttribut_ActivatePrimitiveEvent()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserTestFolder + "testIsEnabledPrimitiveEvent_KO.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitive",
                this.getElementRootFromFile(filePath), XPathConstants.NODESET);
        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(0);
        boolean primitiveEventEnabledActual = XMLFileParserToPrimitiveEvent.isEnabledPrimitiveEvent(xPath, node, resultatParsing);

        assertTrue("Recupération de l'attribut enabled du primitive event", primitiveEventEnabledActual);
        assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventList().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
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
