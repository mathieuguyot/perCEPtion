package perception.configurator.xml.manager.validator;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import perception.configurator.xml.enums.general.FileErrorType;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
/**
 * Classe utilitaire permettant la validation d'un fichier XML selon, notamment,
 * deux critères :
 * - le respect des règles de formattage attendu pour un fichier XML,
 * - la conformité au schéma XSD.
 *
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class XMLFileValidator {

    /**
     * Permet la validation d'un fichier XML selon les deux critères.
     * <p>Note :
     * seule la première erreur rencontrée lors de la validation est retournée</p>
     *
     * @param xMLFilePath - chemin vers le fichier XML à valider
     * @param xSDFilePath - chemin vers le schéma XSD
     * @return objet représentant un résultat de validation qui est complété
     * si des erreurs de validation ou de traitement de fichiers sont
     * rencontrées.
     */
    public static ValidationResult validate(String xMLFilePath, String xSDFilePath) {

        // Création de la liste de messages d'erreurs que le handler va modifier
        // si le fichier n'est pas valide
        ValidationResult validationResult = ValidationResult.FAB();

        // Création de l'objet permettant la gestion des erreurs
        XMLValidatorErrorHandler errHandler = XMLValidatorErrorHandler.FAB();

        // Récupération d'une instance de la classe DocumentBuilderFactory qui
        // fournira un parseur
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		/*
         * Si on ne traite pas correctement le namespace, l'attribut
		 * "xsi:noNamespaceSchemaLocation" est pris comme un attribut ordinaire,
		 * et comme il n'est pas prévu par le schéma, on a l'erreur : ERROR :
		 * cvc-complex-type.3.2.2 : L'attribut 'xs:noNamespaceSchemaLocation'
		 * n'est pas autorisé dans l'élément 'data'.
		 */
        factory.setNamespaceAware(true);

        try {

            // Validation via un schema définit dans un fichier XSD
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // Création du schéma XSD
            Schema schema = schemaFactory.newSchema(new File(xSDFilePath));

            // Affectation à la factory pour que le document prenne en compte le
            // fichier XSD
            factory.setSchema(schema);

            // Activer la vérification du fichier
            // factory.setValidating(true);

            // Recupération du parseur
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Affectation du gestionnaire d'erreurs au document pour
            // interception des erreurs éventuelles
            builder.setErrorHandler(errHandler);

            File fileXML = new File(xMLFilePath);

            // On rajoute un bloc de capture
            // pour intercepter les erreurs au cas où il y en a
            try {
                builder.parse(fileXML);
            } catch (SAXParseException e) {
                // En fonction du type de problème (FATALERROR, ERROR ou
                // WARNING)
                // La bonne méthode est appelée dans XMLValidatorErrorHandler
            }

        } catch (ParserConfigurationException e) {
            validationResult.setFileErrorType(FileErrorType.FILE_READING);
            e.printStackTrace();
        } catch (SAXException e) {
            validationResult.setFileErrorType(FileErrorType.SCHEMA_ERROR);
            e.printStackTrace();
        } catch (IOException e) {
            validationResult.setFileErrorType(FileErrorType.FILE_NOT_FOUND);
            e.printStackTrace();
        }

        // Mise à jour du résultat de la validation
        validationResult.setValidationError(errHandler.getValidationError());

        return validationResult;

    }

}
