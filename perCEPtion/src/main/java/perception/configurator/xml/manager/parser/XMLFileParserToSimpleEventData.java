package perception.configurator.xml.manager.parser;

import org.xml.sax.SAXException;
import perception.configurator.xml.enums.general.XMLFileStructure;
import perception.configurator.xml.enums.parser.ParsingErrorType;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Classe utilitaire permettant la transformation d'un fichier XML en objet
 * métier. Il s'agit ici de parser un fichier XML en un tableau associatif
 * permettant l'instanciation des simples events extrait du fichier XML.
 *
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
class XMLFileParserToSimpleEventData {

    private static String xmlNodePluralEventLabel = XMLFileStructure.EVENT_SIMPLE.getLabel();
    private static ParsingErrorType parsingErrorType_pluralEventLabel = ParsingErrorType.EVENT_SIMPLES_INVALID_NODE;
    private static ParsingErrorType parsingErrorType_pluralEventDuplicated = ParsingErrorType.EVENT_SIMPLES_DUPLICATED_NAME;
    private static ParsingErrorType parsingErrorType_pluralEventInvalidName = ParsingErrorType.EVENT_SIMPLES_INVALID_NAME;
    private static ParsingErrorType parsingErrorType_pluralEventInvalidType = ParsingErrorType.EVENT_SIMPLES_INVALID_TYPE;

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

        XMLFileParseToEventData<XMLFileParserToSimpleEventData> xmlFileParseToEventData =
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
