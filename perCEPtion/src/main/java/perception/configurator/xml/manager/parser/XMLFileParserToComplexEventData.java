package perception.configurator.xml.manager.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import perception.configurator.xml.enums.general.FileErrorType;
import perception.configurator.xml.enums.general.XMLFileStructure;
import perception.configurator.xml.enums.parser.ParsingErrorType;
import perception.configurator.xml.manager.model.ComplexEventData;
import utils.Pair;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe utilitaire permettant la transformation d'un fichier XML en objet
 * métier. Il s'agit ici de parser un fichier XML en un tableau associatif
 * permettant l'instanciation des complexes events extrait du fichier XML.
 *
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
class XMLFileParserToComplexEventData {

    private static String xmlNodePluralEventLabel = XMLFileStructure.EVENT_COMPLEXE.getLabel();
    private static ParsingErrorType parsingErrorType_pluralEventLabel = ParsingErrorType.EVENT_COMPLEXES_INVALID_NODE;
    private static ParsingErrorType parsingErrorType_pluralEventDuplicated = ParsingErrorType.EVENT_COMPLEXES_DUPLICATED_NAME;
    private static ParsingErrorType parsingErrorType_pluralEventInvalidName = ParsingErrorType.EVENT_COMPLEXES_INVALID_NAME;
    private static ParsingErrorType parsingErrorType_pluralEventInvalidType = ParsingErrorType.EVENT_COMPLEXES_INVALID_TYPE;

    /**
     * Extrait les informations pour l'instanciation des simples events.
     * Permet de passer d'un fichier XML à des objets métiers.
     *
     * @return {@link ResultatParsing} comprenant les informations résultant du traitement du fichier, de sa validation
     * et le tableau associatif permettant l'instanciation des simples events
     * @throws ParserConfigurationException {@link ParserConfigurationException}
     * @throws IOException                  {@link IOException}
     * @throws SAXException                 {@link SAXException}
     */
    public static ResultatParsing parse(String filePath)
            throws ParserConfigurationException, SAXException, IOException {

        XMLFileParseToEventData<XMLFileParserToComplexEventData> xmlFileParseToEventData =
                new XMLFileParseToEventData(
                        xmlNodePluralEventLabel,
                        parsingErrorType_pluralEventLabel,
                        parsingErrorType_pluralEventDuplicated,
                        parsingErrorType_pluralEventInvalidName,
                        parsingErrorType_pluralEventInvalidType
                );

        return xmlFileParseToEventData.parse(filePath);
    }

}
