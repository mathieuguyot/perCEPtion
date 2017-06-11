package perception.configurator.xml.manager.parser;

import org.xml.sax.SAXException;
import perception.configurator.xml.manager.model.PEData;
import perception.configurator.xml.manager.validator.ValidationResult;
import perception.configurator.xml.manager.validator.XMLFileValidator;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Classe utilitaire permettant la transformation d'un fichier XML en objet métier. Il s'agit ici de parser un fichier
 * XML en un tableau associatif permettant l'instanciation des évenements primitifs.
 *
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class XMLFileParser {

    /**
     * Parse le fichier XML spécifié tableau associatif permettant l'initialisation des primitives events. Avant le
     * parsing la validité du fichier XML est valider à l'aide du {@link XMLFileValidator}. Si le fichier XML n'est
     * pas valide, le parsing n'est pas réalisé.
     *
     * @param xMLFilePath chemin vers le fichier XML
     * @param xSDFilePath chemin vers le schéma XSD
     * @return représentation du résultat du parsing
     * @throws IOException                  {@link IOException}
     * @throws SAXException                 {@link SAXException}
     * @throws ParserConfigurationException {@link ParserConfigurationException}
     */
    public static ResultatParsing parse(String xMLFilePath, String xSDFilePath)
            throws ParserConfigurationException, SAXException, IOException {

        // Validation du fichier XML
        ValidationResult validationResult = XMLFileValidator.validate(xMLFilePath, xSDFilePath);

        // Instanciation du l'objet contenant les résultats de parsing
        ResultatParsing resultatParsing = ResultatParsing.FAB();

        // Enregistrement du résultat de validation
        resultatParsing.setValidationResult(validationResult);

        // Si le fichier n'est pas valide, on ne réalise pas de parsing
        if (!resultatParsing.hasErrors()) {
            resultatParsing = XMLFileParserToPrimitiveEvent.parse(xMLFilePath);
            List<PEData> listeJDD = resultatParsing.getPrimitiveEventList();
            resultatParsing.setPrimitiveEventList(listeJDD);
        }

        return resultatParsing;

    }

}
