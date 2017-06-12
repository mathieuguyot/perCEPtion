package perception.configurator.xml.manager.parser;

import org.w3c.dom.Node;
import perception.configurator.xml.enums.general.XMLFileStructure;
import perception.configurator.xml.enums.parser.ParsingErrorType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import java.util.Optional;

/**
 * Classe utilitaire permettant la transformation d'un fichier XML en objet
 * métier.
 *
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class XMLFileParseToEventData {

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
