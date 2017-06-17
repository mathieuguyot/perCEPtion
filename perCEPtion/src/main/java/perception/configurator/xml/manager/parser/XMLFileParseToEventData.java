package perception.configurator.xml.manager.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import perception.configurator.xml.enums.general.FileErrorType;
import perception.configurator.xml.enums.general.XMLFileStructure;
import perception.configurator.xml.enums.parser.ParsingErrorType;
import perception.configurator.xml.manager.model.SimpleEventData;
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
 * métier.
 *
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class XMLFileParseToEventData<T> {

    private static String xmlNodeParamAttrType = XMLFileStructure.EVENT_PARAM_ATTR_TYPE.getLabel();

    private String xmlNodePluralEventLabel;
    private ParsingErrorType parsingErrorType_pluralEventLabel;
    private ParsingErrorType parsingErrorType_pluralEventDuplicated;
    private ParsingErrorType parsingErrorType_pluralEventInvalidName;
    private ParsingErrorType parsingErrorType_pluralEventInvalidType;

    public XMLFileParseToEventData(
            String xmlNodePluralEventLabel,
            ParsingErrorType parsingErrorType_pluralEventLabel,
            ParsingErrorType parsingErrorType_pluralEventDuplicated,
            ParsingErrorType parsingErrorType_pluralEventInvalidName,
            ParsingErrorType parsingErrorType_pluralEventInvalidType) {
        this.xmlNodePluralEventLabel = xmlNodePluralEventLabel;
        this.parsingErrorType_pluralEventLabel = parsingErrorType_pluralEventLabel;
        this.parsingErrorType_pluralEventDuplicated = parsingErrorType_pluralEventDuplicated;
        this.parsingErrorType_pluralEventInvalidName = parsingErrorType_pluralEventInvalidName;
        this.parsingErrorType_pluralEventInvalidType = parsingErrorType_pluralEventInvalidType;
    }

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
    public ResultatParsing parse(String filePath)
            throws ParserConfigurationException, SAXException, IOException {

        // Initialisation de l'objet résultant du parsing
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        // Récupération d'une instance de factory qui fournira un parser
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Parsing du fichier xml via un objet File et récupération d'un objet
        // Document qui permet de représenter la hiérarchie d'objet créée pendant le
        // parsing
        Document xml = null;
        boolean test = true;

        try {
            // Création du parser
            DocumentBuilder builder = factory.newDocumentBuilder();
            File fileXML = new File(filePath);

            xml = builder.parse(fileXML);

        } catch (FileNotFoundException ex) {
            resultatParsing.addFileErrorType(FileErrorType.FILE_NOT_FOUND);
            ex.printStackTrace();
            test = false;
        }

        // Si le ficher est introuvable, le parsing est arrêté
        if (test) {
            // Récupération d'un objet Element qui représente un élément XML
            // Ici, cet élément sera la racine du document
            Element root = xml.getDocumentElement();

            // Récupération d'une instance de factory qui fournira un objet
            // permettant d'utiliser le languge xpath
            XPathFactory xpf = XPathFactory.newInstance();
            XPath xPath = xpf.newXPath();

            this.parseSimpleEvents(xPath, root, resultatParsing);
        }

        return resultatParsing;

    }

    /**
     * Parse tous les simples events du fichier XML fourni de configuration des évènements du sytème. Un
     * {@link ResultatParsing} est passé en paramètre et sera mis à jour au cours du traitement.
     *
     * @param xPath           - le xPath
     * @param root            - la racine du fichier XML de configuration des modules du système
     * @param resultatParsing - le résultat du parsing qui sera mis à jour au cours du traitement
     */
    protected void parseSimpleEvents(XPath xPath, Element root, ResultatParsing resultatParsing) {

        Optional<NodeList> simpleEventFromFileOp = getSimpleEventsInFile(xPath, root, resultatParsing);

        // Si la liste est absente c'est que le fichier ne comporte pas de simples events
        simpleEventFromFileOp.ifPresent(nodeList -> createAllSimpleEvents(xPath, nodeList, resultatParsing));

    }

    /**
     * Récupération de tous les simples events dans le fichier XML fourni. Un {@link ResultatParsing} est passé en
     * paramètre et sera mis à jour au cours du traitement.
     *
     * @param xPath           le xPath
     * @param root            l'élément racine du fichier XML
     * @param resultatParsing le résultat du parsing qui sera mis à jour au cours du traitement
     * @return un optional contenant éventuellement la liste de simple events. Il est vide si le fichier n'en comporte pas, dans ce cas, le
     * {@link ResultatParsing} est mis à jour
     */
    protected Optional<NodeList> getSimpleEventsInFile(XPath xPath, Element root, ResultatParsing resultatParsing) {

        // Récupération de tout les simples events du fichier avec XPath
        String expXPathJeuxDeDonnees = "//" + xmlNodePluralEventLabel;
        Optional<NodeList> listSimpleEventOp = Optional.empty();
        try {
            NodeList listPrimitiveEvent = (NodeList) xPath.evaluate(expXPathJeuxDeDonnees, root, XPathConstants.NODESET);
            listSimpleEventOp = Optional.of(listPrimitiveEvent);
        } catch (XPathExpressionException e) {
            resultatParsing.addParsingErrorType(parsingErrorType_pluralEventLabel);
            e.printStackTrace();
        }

        return listSimpleEventOp;

    }

    /**
     * Création de toutes les informations permettant l'instanciation des simples events à partir du fichier XML.
     *
     * @param xPath                       - le xPath
     * @param listSimpleEventsFromFile - la liste des simples events du fichier
     * @param resultatParsing             - le résultat du parsing qui sera mis à jour au cours du traitement, dans ce cas,
     *                                    le {@link ResultatParsing} est mis à jour
     */
    protected void createAllSimpleEvents(XPath xPath, NodeList listSimpleEventsFromFile,
                                                ResultatParsing resultatParsing) {

        for (int i = 0; i < listSimpleEventsFromFile.getLength(); i++) {

            Node node = listSimpleEventsFromFile.item(i);

            Optional<String> simpleEventName = Optional.empty();
            Optional<String> simpleEventType = Optional.empty();
            Optional<List<Pair<String, String>>> simpleEventParamList = Optional.empty();

            // Récupération des éléments du simple event actuel
            boolean primitiveEventEnabled = XMLFileParseToEventData.isEnabledEvent(xPath, node);
            if (primitiveEventEnabled) {
                simpleEventName = getSimpleEventNameFromFile(xPath, node, resultatParsing);
                simpleEventType = getSimpleEventTypeFromFile(xPath, node, resultatParsing);
                simpleEventParamList = getSimpleEventParamListFromFile(xPath, node, resultatParsing);
            }

            // Si on a aucune erreur dans le fichier les informations d'instanciation du simple event courant est
            // ajouté au résultat du parsing
            if (simpleEventName.isPresent() && simpleEventType.isPresent() && simpleEventParamList.isPresent()) {
                SimpleEventData simpleEventData = new SimpleEventData(simpleEventName.get(), simpleEventType.get(), simpleEventParamList.get());
                resultatParsing.addSimpleEvent(simpleEventData);
            }

        }

    }

    /**
     * Récupére le nom donné dans le fichier XML pour le simple event spécifié.
     *
     * @param xPath           le XPath
     * @param node            le noeud dans le fichier correspondant au simple event
     * @param resultatParsing le résultat du parsing qui sera mis à jour au cours du traitement
     * @return un optional contenant le nom du simple event ou étant vide s'il est impossible de trouver l'information
     * dans le fichier, dans ce cas, le {@link ResultatParsing} est mis à jour
     */
    protected Optional<String> getSimpleEventNameFromFile(XPath xPath, Node node, ResultatParsing resultatParsing) {
        Optional<String> nameOp = Optional.empty();
        try {
            String strSelectName = XMLFileStructure.EVENT_NAME.getLabel();
            String name = "" + xPath.evaluate(strSelectName, node, XPathConstants.STRING);
            if(name.equals("")) {
                throw new XPathExpressionException("Missing simple event name.");
            }
            else if(resultatParsing.existingSimpleEventListWithName(name)) {
                resultatParsing.addParsingErrorTypeWithComplementMessage(parsingErrorType_pluralEventDuplicated, name);
            }
            else {
                nameOp = Optional.of(name);
            }
        } catch (XPathExpressionException e) {
            resultatParsing.addParsingErrorType(parsingErrorType_pluralEventInvalidName);
            // System.out.println("Impossible de trouver le nom du simple event : " + node);
            e.printStackTrace();
        }
        return nameOp;
    }

    /**
     * Récupére le type donnée dans le fichier XML pour le simple event spécifié.
     *
     * @param xPath           le XPath
     * @param node            le noeud dans le fichier correspondant au simple event
     * @param resultatParsing le résultat du parsing qui sera mis à jour au cours du traitement
     * @return un optional contenant le nom du simple event ou étant vide s'il est impossible de trouver l'information
     * dans le fichier, dans ce cas, le {@link ResultatParsing} est mis à jour
     */
    protected Optional<String> getSimpleEventTypeFromFile(XPath xPath, Node node, ResultatParsing resultatParsing) {
        Optional<String> typeOp = Optional.empty();
        try {
            String strSelectName = XMLFileStructure.EVENT_TYPE.getLabel();
            String type = "" + xPath.evaluate(strSelectName, node, XPathConstants.STRING);
            if(type.equals("")) {
                throw new XPathExpressionException("Missing simple event type.");
            } else {
                typeOp = Optional.of(type);
            }
        } catch (XPathExpressionException e) {
            resultatParsing.addParsingErrorType(parsingErrorType_pluralEventInvalidType);
            // System.out.println("Impossible de trouver le nom du simple event : " + node);
            e.printStackTrace();
        }
        return typeOp;
    }

    /**
     * Récupération de tous les params events dans le fichier XML fourni. Un {@link ResultatParsing} est passé en
     * paramètre et sera mis à jour au cours du traitement.
     *
     * @param xPath           le xPath
     * @param node            le noeud corespondant à un simple event du fichier XML de configuration
     * @param resultatParsing le résultat du parsing qui sera mis à jour au cours du traitement
     * @return un optional contenant éventuellement la liste d'events. Il est vide si le fichier n'en comporte pas, dans ce cas, le
     * {@link ResultatParsing} est mis à jour
     */
    protected Optional<List<Pair<String, String>>> getSimpleEventParamListFromFile(XPath xPath, Node node, ResultatParsing resultatParsing) {

        // Récupération de tout les simples events du fichier avec XPath
        String expXPathJeuxDeDonnees = "//" + XMLFileStructure.EVENT_PARAM.getLabel();
        Optional<List<Pair<String, String>>> listParamEventOp = Optional.empty();
        try {

            NodeList listParamEventNode = (NodeList) xPath.evaluate(expXPathJeuxDeDonnees, node, XPathConstants.NODESET);

            List<Pair<String, String>> eventParamList = getEventParams(xPath, listParamEventNode, resultatParsing);

            listParamEventOp = Optional.of(eventParamList);

        } catch (XPathExpressionException e) {
            resultatParsing.addParsingErrorType(parsingErrorType_pluralEventLabel);
            e.printStackTrace();
        }

        return listParamEventOp;

    }

    /**
     * Permet la récupération des paramètres pour l'event à partir des informations du fichier XML de configuration.
     *
     * @param xPath                     le xPath
     * @param listParamsEventsFromFile  la liste des paramètres de l'event
     * @param resultatParsing           le résultat de parsing qui sera mit à jour au cours du traitement en cas d'erreur de parsing
     * @return une liste de tuples comportant le type et la valeur du paramètre
     */
    protected List<Pair<String, String>> getEventParams(XPath xPath, NodeList listParamsEventsFromFile,
                                                               ResultatParsing resultatParsing) {

        List<Pair<String, String>> listEventParams = new ArrayList<>();

        for (int i = 0; i < listParamsEventsFromFile.getLength(); i++) {

            Node node = listParamsEventsFromFile.item(i);

            // Récupération des éléments du simple event actuel
            Optional<String> eventParamTypeOp = getSimpleEventParamTypeFromFile(xPath, node, resultatParsing);
            String eventParamValue = getSimpleEventParamValueFromFile(node);

            // Si on a aucune erreur dans le fichier les informations d'instanciation du simple event courant est
            // ajouté au résultat du parsing
            if (eventParamTypeOp.isPresent()) {
                Pair<String, String> param = new Pair<>(eventParamTypeOp.get(), eventParamValue);
                listEventParams.add(param);
            }

        }

        return listEventParams;

    }

    /**
     * Permet la récupération de la valeur du type du paramètre du simple event fournit en entrée.
     *
     * @param xPath             le xPath
     * @param node              le noeud corespondant à un simple event du fichier XML de configuration
     * @param resultatParsing   le résultat de parsing qui sera mit à jour au cours du traitement en cas d'erreur de parsing
     * @return le type du paramètre du simple event fournit en entrée
     */
    protected Optional<String> getSimpleEventParamTypeFromFile(XPath xPath, Node node, ResultatParsing resultatParsing) {
        Optional<String> eventParamTypeOp = Optional.empty();
        try {
            String eventParamType = (String) xPath.evaluate("@" + xmlNodeParamAttrType, node, XPathConstants.STRING);
            if (eventParamType.equals("")) {
                resultatParsing.addParsingErrorType(parsingErrorType_pluralEventInvalidType);
            } else {
                eventParamTypeOp = Optional.of(eventParamType);
            }
        } catch (XPathExpressionException e) {
            resultatParsing.addParsingErrorType(parsingErrorType_pluralEventInvalidType);
        }
        return eventParamTypeOp;
    }

    /**
     * Permet la récupération de la valeur du paramètre du simple event fournit en entrée.
     *
     * @param node      le noeud corespondant à un simple event du fichier XML de configuration
     * @return la valeur du paramètre du simple event fournit en entrée
     */
    protected String getSimpleEventParamValueFromFile(Node node) {
        String eventParamValue = node.getNodeValue();
        return eventParamValue;
    }

    /**
     * Indique si le simple event est enabled ou non. Si l'attribut n'est pas présent, l'event est
     * considéré comme actif.
     *
     * @param xPath           - le XPath
     * @param node            - le noeud dans le fichier correspondant au simple event
     * @param resultatParsing - le résultat du parsing
     * @return <code>true</code> si l'event est activé et <code>false</code> dans le cas contraire, dans ce cas, le
     * {@link ResultatParsing} n'est pas mis à jour
     */
    protected static boolean isEnabledEvent(XPath xPath, Node node) {
        boolean enabled = false;
        try {
            String primitiveEventEnabled = (String) xPath.evaluate("@" + XMLFileStructure.EVENT_ATTR_ENABLED.getLabel(), node, XPathConstants.STRING);
            if (primitiveEventEnabled.equals("true") || primitiveEventEnabled.equals("")) {
                enabled = true;
            }
        } catch (XPathExpressionException e) {
            // L'attribut n'est pas présent on considère que le simple event est à activer
            enabled = true;
        }
        return enabled;
    }

}
