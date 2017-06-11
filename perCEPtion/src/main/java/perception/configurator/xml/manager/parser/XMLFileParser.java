package perception.configurator.xml.manager.parser;

import org.xml.sax.SAXException;
import perception.configurator.xml.manager.model.PrimitiveEventData;
import perception.configurator.xml.manager.validator.ValidationResult;
import perception.configurator.xml.manager.validator.XMLFileValidator;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Classe utilitaire permettant la transformation d'un fichier XML en objet métier. Il s'agit ici de parser un fichier
 * XML en un tableau associatif permettant l'instanciation des évenements primitifs.
 *
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class XMLFileParser {

    /**
     * Parse le fichier XML spécifié en tableau associatif permettant l'initialisation des primitives events. Avant le
     * parsing, la validité du fichier XML est vérifiée à l'aide du {@link XMLFileValidator}. Si le fichier XML n'est
     * pas valide, le parsing n'est pas réalisé.
     *
     * @param xMLFilePath - chemin vers le fichier XML
     * @param xSDFilePath - chemin vers le schéma XSD
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
        ResultatParsing mainResultatParsing = ResultatParsing.FAB();

        // Enregistrement du résultat de validation
        mainResultatParsing.setValidationResult(validationResult);

        // Si le fichier n'est pas valide, on ne réalise pas de parsing
        if (!mainResultatParsing.hasErrors()) {

            ResultatParsing resultatParsingPEData = parsePrimitiveEvents(xMLFilePath);
            ResultatParsing resultatParsingSEData = parseSimpleEvents(xMLFilePath);
            ResultatParsing resultatParsingCEData = parseComplexEvents(xMLFilePath);

            mergeResultatsParsingsWithTheMainOne(mainResultatParsing, resultatParsingPEData, resultatParsingSEData, resultatParsingCEData);

        }

        return mainResultatParsing;

    }

    private static ResultatParsing parsePrimitiveEvents(String xMLFilePath) throws IOException, SAXException, ParserConfigurationException {

        ResultatParsing resultatParsing = XMLFileParserToPrimitiveEventData.parse(xMLFilePath);
        List<PrimitiveEventData> listePrimitiveEventData = resultatParsing.getPrimitiveEventList();
        resultatParsing.setPrimitiveEventList(listePrimitiveEventData);

        return resultatParsing;

    }

    private static ResultatParsing parseSimpleEvents(String xMLFilePath) throws IOException, SAXException, ParserConfigurationException {

        ResultatParsing resultatParsing = XMLFileParserToSimpleEventData.parse(xMLFilePath);
        List<PrimitiveEventData> listePrimitiveEventData = resultatParsing.getPrimitiveEventList();
        resultatParsing.setPrimitiveEventList(listePrimitiveEventData);

        return resultatParsing;
    }

    private static ResultatParsing parseComplexEvents(String xMLFilePath) throws IOException, SAXException, ParserConfigurationException {

        ResultatParsing resultatParsing = XMLFileParserToComplexEventData.parse(xMLFilePath);
        List<PrimitiveEventData> listePrimitiveEventData = resultatParsing.getPrimitiveEventList();
        resultatParsing.setPrimitiveEventList(listePrimitiveEventData);

        return resultatParsing;
    }

    private static ResultatParsing mergeResultatsParsingsWithTheMainOne(ResultatParsing mainResultatParsing, ResultatParsing
            resultatParsingPEData, ResultatParsing resultatParsingSEData, ResultatParsing resultatParsingCEData) {

        mainResultatParsing.addAllFileErrorTypes(resultatParsingPEData.getFileErrorTypes());
        mainResultatParsing.addAllFileErrorTypes(resultatParsingSEData.getFileErrorTypes());
        mainResultatParsing.addAllFileErrorTypes(resultatParsingCEData.getFileErrorTypes());

        mainResultatParsing.addAllParsingErrorTypes(resultatParsingPEData.getParsingErrorTypes());
        mainResultatParsing.addAllParsingErrorTypes(resultatParsingSEData.getParsingErrorTypes());
        mainResultatParsing.addAllParsingErrorTypes(resultatParsingCEData.getParsingErrorTypes());

        mainResultatParsing.addAllPrimitivesEvents(resultatParsingPEData.getPrimitiveEventList());
        mainResultatParsing.addAllSimpleEvents(resultatParsingSEData.getSimpleEventList());
        mainResultatParsing.addAllComplexEvents(resultatParsingCEData.getComplexEventList());

        return mainResultatParsing;

    }

}
