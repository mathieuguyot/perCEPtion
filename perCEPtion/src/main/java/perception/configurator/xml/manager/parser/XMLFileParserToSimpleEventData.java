package perception.configurator.xml.manager.parser;

import org.xml.sax.SAXException;
import perception.configurator.xml.enums.general.XMLFileStructure;
import perception.configurator.xml.enums.parser.ParsingErrorType;
import perception.configurator.xml.manager.model.SimpleEventData;
import utils.Pair;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Classe utilitaire permettant la transformation d'un fichier XML en objet
 * métier. Il s'agit ici de parser un fichier XML en un tableau associatif
 * permettant l'instanciation des simples events extrait du fichier XML.
 *
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
class XMLFileParserToSimpleEventData extends XMLFileParseToEventData {

    private final static String xmlNodeSingularEventLabel = XMLFileStructure.EVENT_SIMPLE.getLabel();
    private final static ParsingErrorType parsingErrorType_pluralEventLabel = ParsingErrorType.EVENT_SIMPLES_INVALID_NODE;
    private final static ParsingErrorType parsingErrorType_pluralEventDuplicated = ParsingErrorType.EVENT_SIMPLES_DUPLICATED_NAME;
    private final static ParsingErrorType parsingErrorType_pluralEventInvalidName = ParsingErrorType.EVENT_SIMPLES_INVALID_NAME;
    private final static ParsingErrorType parsingErrorType_pluralEventInvalidType = ParsingErrorType.EVENT_SIMPLES_INVALID_TYPE;

    public XMLFileParserToSimpleEventData() {
        super(
                xmlNodeSingularEventLabel,
                parsingErrorType_pluralEventLabel,
                parsingErrorType_pluralEventDuplicated,
                parsingErrorType_pluralEventInvalidName,
                parsingErrorType_pluralEventInvalidType);
    }

    @Override
    public void addEventData(String eventName, String eventType, List<Pair<String, String>> pairs, ResultatParsing resultatParsing) {
        SimpleEventData simpleEventData = new SimpleEventData(eventType, eventName, pairs);
        resultatParsing.addSimpleEvent(simpleEventData);
    }

    @Override
    boolean existingEventWithNameInResultatParsing(String name, ResultatParsing resultatParsing) {
        return resultatParsing.existingSimpleEventListWithName(name);
    }

}
