package perception.configurator.xml.manager.parser;

import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import perception.configurator.xml.TestConstants;
import perception.configurator.xml.utils.XMLManager;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class XMLFileParseToEventDataTest {

    // Le xPath
    XPathFactory xpf = XPathFactory.newInstance();
    XPath xPath = xpf.newXPath();

    @Test
    public void testIsEnabledPrimitiveEvent_OK_EnabledTrue()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testIsEnabledPrimitiveEvent_OK_EnabledTrue.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitive",
                XMLManager.getElementRootFromFile(filePath), XPathConstants.NODESET);
        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(1);
        boolean primitiveEventEnabledActual = XMLFileParseToEventData.isEnabledEvent(xPath, node);

        assertTrue("Recupération du runtime du primitive event", primitiveEventEnabledActual);
        assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventList().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void testIsEnabledPrimitiveEvent_OK_EnabledFalse()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testIsEnabledPrimitiveEvent_OK_EnabledFalse.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitive",
                XMLManager.getElementRootFromFile(filePath), XPathConstants.NODESET);
        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(1);
        boolean primitiveEventEnabledActual = XMLFileParseToEventData.isEnabledEvent(xPath, node);

        assertTrue("Recupération du runtime du primitive event", primitiveEventEnabledActual);
        assertTrue("Map de primitive events", resultatParsing.getPrimitiveEventList().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }

    @Test
    public void testIsEnabledPrimitiveEvent_NoEnableAttribut_ActivatePrimitiveEvent()
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String filePath = TestConstants.XMLFileParserPrimitiveEventsTestFolder + "testIsEnabledPrimitiveEvent_KO.xml";
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        NodeList nodeListJeuxDeDonneesFromFile = (NodeList) xPath.evaluate("//primitive",
                XMLManager.getElementRootFromFile(filePath), XPathConstants.NODESET);
        // Récupération du premier primitive event
        Node node = nodeListJeuxDeDonneesFromFile.item(0);
        boolean primitiveEventEnabledActual = XMLFileParseToEventData.isEnabledEvent(xPath, node);

        assertTrue("Recupération de l'attribut enabled du primitive event", primitiveEventEnabledActual);
        assertTrue("Liste de primitive events", resultatParsing.getPrimitiveEventList().isEmpty());
        assertTrue("Liste erreur parsing", resultatParsing.getParsingErrorTypes().isEmpty());
        assertTrue("Liste erreur fichier", resultatParsing.getFileErrorTypes().isEmpty());

    }
}
