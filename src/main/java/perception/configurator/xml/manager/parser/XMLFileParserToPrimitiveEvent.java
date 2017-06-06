package perception.configurator.xml.manager.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import perception.configurator.xml.enums.general.FileErrorType;
import perception.configurator.xml.enums.general.XMLFileStructure;
import perception.configurator.xml.enums.parser.ParsingErrorType;

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

/**
 * Classe utilitaire permettant la transformation d'un fichier XML en objet
 * métier. Il s'agit ici de parser un fichier XML en un tableau associatif
 * permettant l'instanciation des primitives events extrait du fichier XML.
 *
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
class XMLFileParserToPrimitiveEvent {

    /**
     * Extrait les informations pour l'instanciation des primitives events.
     * Permet de passer d'un fichier XML à des objets métiers.
     *
     * @param filePath le chemin vers de le fichier de scénario
     * @return {@link ResultatParsing} comprenant les informations résultant du traitement du fichier, de sa validation
     * et le tableau associatif permettant l'instanciation des primitives events
     * @throws ParserConfigurationException {@link ParserConfigurationException}
     * @throws IOException                  {@link IOException}
     * @throws SAXException                 {@link SAXException}
     */
    public static ResultatParsing parse(String filePath)
            throws ParserConfigurationException, SAXException, IOException {

        // Initialisation de l'objet résultant du parsing
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        // Récupération d'une instance de factory qui fournira un parser
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Parsing du fichier xml via un objet File et récupération d'un objet
        // Document qui permet de représenter la hiérarchie d'objet créee pendant le
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

        // Si le ficher est introuvable, le parsing est arrété
        if (test) {
            // Récupération d'un objet Element qui représente un élément XML
            // Ici, cet élément sera la racine du document
            Element root = xml.getDocumentElement();

            // Récupération d'une instance de factory qui fournira un objet
            // permettant d'utiliser le languge xpath
            XPathFactory xpf = XPathFactory.newInstance();
            XPath xPath = xpf.newXPath();

            XMLFileParserToPrimitiveEvent.parsePrimitivesEvent(xPath, root, resultatParsing);
        }

        return resultatParsing;

    }

    /**
     * Parse tous les primitives events du fichier XML fournit de configuration des modules du sytème. Un
     * {@link ResultatParsing} est passé en paramètres et sera mis à jour au cours du traitement.
     *
     * @param xPath           le xPath
     * @param root            la racine du fichier XML de configuration des modules du système
     * @param resultatParsing le résultat du parsing qui sera mis à jour au cours du traitement
     */
    protected static void parsePrimitivesEvent(XPath xPath, Element root, ResultatParsing resultatParsing) {

        NodeList primitiveEventFromFile = getPrimitivesEventInFile(xPath, root, resultatParsing);

        // Si la liste est null c'est que le fichier ne comporte pas de primitives events
        if (primitiveEventFromFile != null) {
            createAllPrimitivesEvents(xPath, primitiveEventFromFile, resultatParsing);
        }

    }

    /**
     * Récupération de tous les primitives events dans le fichier XML fournit. Un {@link ResultatParsing} est passé en
     * paramètres et sera mis à jour au cours du traitement.
     *
     * @param xPath           le xPath
     * @param root            l'élément racine du fichier XML
     * @param resultatParsing le résultat du parsing qui sera mis à jour au cours du traitement
     * @return une liste de primitive events ou null si le fichier n'en comporte pas, dans ce cas, le
     * {@link ResultatParsing} est mis à jour
     */
    protected static NodeList getPrimitivesEventInFile(XPath xPath, Element root, ResultatParsing resultatParsing) {

        // Récupération de tout les primitives events du fichier avec XPath
        String expXPathJeuxDeDonnees = "//" + XMLFileStructure.PRIMITIVES.getLabel();
        NodeList listPrimitiveEvent = null;
        try {
            listPrimitiveEvent = (NodeList) xPath.evaluate(expXPathJeuxDeDonnees, root, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            resultatParsing.addParsingErrorType(ParsingErrorType.INVALID_PRIMITIVES_NODE);
            e.printStackTrace();
        }

        return listPrimitiveEvent;

    }

    /**
     * Création de toutes les informations permettant l'instanciation des primitives events à partir du fichier XML.
     *
     * @param xPath                       le xPath
     * @param listPrimitiveEventsFromFile la liste des primitives events du fichier
     * @param resultatParsing             le résultat du parsing qui sera mis à jour au cours du traitement, dans ce cas,
     *                                    le {@link ResultatParsing} est mis à jour
     */
    protected static void createAllPrimitivesEvents(XPath xPath, NodeList listPrimitiveEventsFromFile,
                                                    ResultatParsing resultatParsing) {

        for (int i = 0; i < listPrimitiveEventsFromFile.getLength(); i++) {

            Node node = listPrimitiveEventsFromFile.item(i);

            // Récupération des éléments du primitive event actuel
            String primitiveEventName = null;
            Long primitiveEventRuntime = null;
            boolean primitiveEventEnabled = isEnabledPrimitiveEvent(xPath, node, resultatParsing);
            if (primitiveEventEnabled) {
                primitiveEventName = getPrimitiveEventNameFromFile(xPath, node, resultatParsing);
                primitiveEventRuntime = getPrimitiveEventRuntimeFromFile(xPath, node, resultatParsing);
            }

            // Si on a aucune erreur dans le fichier les informations d'instanciation du primitive event courant est
            // ajouté au résultat du parsing
            if (primitiveEventName != null && primitiveEventRuntime != null) {
                resultatParsing.addPrimitiveEvent(primitiveEventName, primitiveEventRuntime);
            }

        }

    }

    /**
     * Récupére du nom donnée dans le fichier XML pour le primitive event spécifié.
     *
     * @param xPath           le XPath
     * @param node            le noeud dans le fichier correspondant au primitive event
     * @param resultatParsing le résultat du parsing qui sera mis à jour au cours du traitement
     * @return le nom du primitive event ou null s'il est impossible de trouver l'information dans le fichier, dans ce cas,
     * le {@link ResultatParsing} est mis à jour
     */
    protected static String getPrimitiveEventNameFromFile(XPath xPath, Node node, ResultatParsing resultatParsing) {
        String name = null;
        try {
            String strSelectName = XMLFileStructure.PRIMITIVE_NAME.getLabel();
            name = "" + xPath.evaluate(strSelectName, node, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            resultatParsing.addParsingErrorType(ParsingErrorType.INVALID_PRIMITIVE_NAME);
            // System.out.println("Impossible de trouver le nom du primitive event : " + node);
            e.printStackTrace();
        }
        return name;
    }

    /**
     * Récupére du runtime donnée dans le fichier XML pour le primitive event spécifié.
     *
     * @param xPath           le XPath
     * @param node            le noeud dans le fichier correspondant au primitive event
     * @param resultatParsing le résultat du parsing
     * @return le nom du primitive event ou null s'il est impossible de trouver l'information dans le fichier, dans ce
     * cas, le {@link ResultatParsing} est mis à jour
     */
    protected static Long getPrimitiveEventRuntimeFromFile(XPath xPath, Node node, ResultatParsing resultatParsing) {
        int runtTime = Integer.MIN_VALUE;
        try {
            String strSelectName = XMLFileStructure.PRIMITIVE_RUNTIME.getLabel();
            runtTime = ((Double) xPath.evaluate(strSelectName, node, XPathConstants.NUMBER)).intValue();
        } catch (XPathExpressionException e) {
            resultatParsing.addParsingErrorType(ParsingErrorType.INVALID_PRIMITIVE_RUNTIME);
            // System.out.println("Impossible de trouver le nom du primitive event : " + node);
            e.printStackTrace();
        }
        return (long) runtTime;
    }

    /**
     * Indique si le primitive event est enabled ou non. Si l'attribut n'est pas présent, le primitive event est
     * considéré comme actif.
     *
     * @param xPath           - le XPath
     * @param node            - le noeud dans le fichier correspondant au primitive event
     * @param resultatParsing - le résultat du parsing
     * @return vrai si le primitive event est activé et false dans le cas contraire, dans ce cas, le
     * {@link ResultatParsing} n'est pas mis à jour
     */
    protected static boolean isEnabledPrimitiveEvent(XPath xPath, Node node, ResultatParsing resultatParsing) {
        boolean enabled = false;
        try {
            String primitiveEventEnabled = (String) xPath.evaluate("@" + XMLFileStructure.PRIMITIVE_ATTR_ENABLED.getLabel(), node, XPathConstants.STRING);
            if (primitiveEventEnabled.equals("enabled")) {
                enabled = true;
            }
        } catch (XPathExpressionException e) {
            // L'attribut n'est pas présent on considère que le primitive event est à activer
            enabled = true;
        }
        return enabled;
    }

}
