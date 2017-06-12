package perception.configurator.xml.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Classe utilitaire destiné au test unitaire permettant la récupération de certain
 * élément du fichier de configuration XML.
 *
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 *
 */
public class XMLManager {

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
    public static Element getElementRootFromFile(String filePath) throws ParserConfigurationException, SAXException, IOException {
        File fileXML = new File(filePath);
        // Recupération de la racine du document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xml = builder.parse(fileXML);
        Element root = xml.getDocumentElement();
        return root;
    }


}
