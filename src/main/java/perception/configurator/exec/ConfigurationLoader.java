package perception.configurator.exec;

import org.xml.sax.SAXException;
import perception.configurator.activator.manager.ActivationResult;
import perception.configurator.activator.manager.PEG.PEGActivator;
import perception.configurator.xml.manager.parser.ResultatParsing;
import perception.configurator.xml.manager.parser.XMLFileParser;
import perception.core.PerceptionCore;
import perception.services.PerceptionLogger;
import perception.services.implementations.SysoutPerceptionLogger;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Permet le chargement du fichier XML de configuration, son parcours et l'activation des modules décrits dans le fichier
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class ConfigurationLoader {

    /**
     * Point d'entrée pour la configuration des Event Generators.
     * Exécute l'ensemble des modules de configuration (validation, parcours et activation)
     * @param xmlFilePath
     *              chemin vers le fichier de configuration
     * @param core
     *              core du framework auquel seront ajoutés les Event Generator
     */
    public static void loadConfiguration(String xmlFilePath, PerceptionCore core) {
        try {
            // Instanciation du logger
            PerceptionLogger logger = new SysoutPerceptionLogger();

            // Validation et Parcours du fichier XML
            ResultatParsing parsingResult = XMLFileParser.parse(xmlFilePath,"schema.xsd");

            if (parsingResult.hasErrors()) {
                // Affichage des erreurs (en rouge) dans la console
                logger.logError(parsingResult.toString());
            } else {
                // Activation des Event Generators
                ActivationResult activationResult = PEGActivator.activate(parsingResult.getPrimitiveEventMap(), core);

                if (activationResult.hasErrors()) {
                    // Affichage des erreurs (en rouge) dans la console
                    logger.logError(activationResult.toString());
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

}
