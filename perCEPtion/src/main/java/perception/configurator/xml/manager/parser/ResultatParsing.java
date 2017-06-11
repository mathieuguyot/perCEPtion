package perception.configurator.xml.manager.parser;

import perception.configurator.xml.enums.general.FileErrorType;
import perception.configurator.xml.enums.parser.ParsingErrorType;
import perception.configurator.xml.manager.model.PEData;
import perception.configurator.xml.manager.validator.ValidationResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Représentation du résultat du parsing d'un fichier XML comprenant les
 * erreurs liées au traitement du fichier et celles directement liées au parsing.
 * <p>
 * Note :
 * <p>
 * - cette classe comprend la liste des informations extraites du fichier XML et
 * permettant l'instanciation d'évenements primitifs
 * <p>
 * - le résultat de la validation est aussi sauvegardé, ce qui permet d'accéder
 * aux éventuelles erreurs survenues lors de la validation du fichier XML par le
 * {@link ValidationResult}
 *
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class ResultatParsing {

    // Attributs

        // Erreur de traitement du fichier XML
    private List<FileErrorType> fileErrorTypes;
    private List<ParsingErrorType> parsingErrorTypes;

        // Résultat de validation
    private ValidationResult validationResult;

        // Tableau association contenant les informations d'instanciation des primitives events
    private List<PEData> primitiveEventList;

    // Constructeur

    /**
     * Constructeur de la classe {@link ResultatParsing}.
     * <p>
     * Note : l'instanciation de la classe se réalise à travers la fabrique
     *
     * @param fileErrorTypes    liste des erreurs liées au traitement de fichier
     * @param parsingErrorTypes liste des erreurs liées au parsing du fichier
     * @param primitiveEventList tableau contenant les éléments permettant l'initialisation
     *                          des primitives events
     */
    private ResultatParsing(List<FileErrorType> fileErrorTypes, List<ParsingErrorType> parsingErrorTypes,
                            List<PEData> primitiveEventList) {
        this.fileErrorTypes = fileErrorTypes;
        this.parsingErrorTypes = parsingErrorTypes;
        this.primitiveEventList = primitiveEventList;
    }

    /**
     * Constructeur de la classe {@link ResultatParsing} permettant
     * l'initialisation des deux listes d'erreurs et du tableau associatif contenant
     * les informations pour l'initialisation des primitives events.
     */
    private ResultatParsing() {
        this.fileErrorTypes = new ArrayList<>();
        this.parsingErrorTypes = new ArrayList<>();
        this.primitiveEventList = new ArrayList<>();

    }

    // Services

    /**
     * Ajoute une erreur liée au traitement de fichier.
     *
     * @param fileErrorType erreur liée au traitement de fichier à ajouter
     */
    public void addFileErrorType(FileErrorType fileErrorType) {
        this.getFileErrorTypes().add(fileErrorType);
    }

    /**
     * Ajoute une erreur liée au parsing du fichier.
     *
     * @param parsingErrorType erreur liée au parsing du fichier à ajouter
     */
    public void addParsingErrorType(ParsingErrorType parsingErrorType) {
        this.getParsingErrorTypes().add(parsingErrorType);
    }

    /**
<<<<<<< HEAD:src/main/java/perception/configurator/xml/manager/parser/ResultatParsing.java
     * Ajoute une erreur liée au parsing du fichier avec complément d'information.
     *
     * @param parsingErrorType erreur liée au parsing du fichier à ajouter
     * @param complementMsg message complémentaire
     */
    public void addParsingErrorTypeWithComplementMessage(ParsingErrorType parsingErrorType, String complementMsg) {
        parsingErrorType.addComplement(complementMsg);
        this.addParsingErrorType(parsingErrorType);
    }

    /**
     * Ajoute les informations pour l'instanciation d'un primitives events.
     *
     * @param peData information de primitive event à ajouter à la liste pour instanciation
     */
    public void addPrimitiveEvent(PEData peData) {
        this.primitiveEventList.add(peData);
    }

    /**
     * Indique si des erreurs de parsing ont eues lieu.
     *
     * @return <code>true</code> si des erreurs sont survenues et <code>false</code> dans le cas contraire.
     */
    public boolean hasErrors() {
        boolean test = (!getFileErrorTypes().isEmpty()) ||
                (!this.getParsingErrorTypes().isEmpty());
        if(this.getValidationResult()==null) {
            return test;
        }
        return (test ||
                (this.getValidationResult().hasErrors()));
    }

    // Accesseurs

    public List<FileErrorType> getFileErrorTypes() {
        return fileErrorTypes;
    }

    public List<ParsingErrorType> getParsingErrorTypes() {
        return parsingErrorTypes;
    }

    public List<PEData> getPrimitiveEventList() {
        return primitiveEventList;
    }

    public void setFileErrorTypes(List<FileErrorType> fileErrorTypes) {
        this.fileErrorTypes = fileErrorTypes;
    }

    public void setParsingErrorTypes(List<ParsingErrorType> parsingErrorTypes) {
        this.parsingErrorTypes = parsingErrorTypes;
    }

    public void setPrimitiveEventList(List<PEData> primitiveEventList) {
        this.primitiveEventList = primitiveEventList;
    }

    public ValidationResult getValidationResult() {
        return validationResult;
    }

    public void setValidationResult(ValidationResult validationResult) {
        this.validationResult = validationResult;
    }

    // Fabrique

    /**
     * Fabrique de {@link ResultatParsing} permettant d'instancier la classe.
     *
     * @param fileErrorTypes    liste des erreurs liées au traitement de fichier
     * @param parsingErrorTypes liste des erreurs liées au parsing du fichier
     * @param primitiveEventList liste permetttant l'instanciation des primitives events extrait du fichier XML
     * @return instance de {@link ResultatParsing}
     */
    public static ResultatParsing FAB(List<FileErrorType> fileErrorTypes, List<ParsingErrorType> parsingErrorTypes,
                                      List<PEData> primitiveEventList) {
        return new ResultatParsing(fileErrorTypes, parsingErrorTypes, primitiveEventList);
    }

    /**
     * Fabrique de {@link ResultatParsing} permettant d'instancier la classe en initialisant les deux listes d'erreurs
     * et du tableau associatif permetttant l'instanciation des primitives events extraits du fichier XML
     *
     * @return instance de {@link ResultatParsing}
     */
    public static ResultatParsing FAB() {
        return new ResultatParsing();
    }

    // Services universels

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ResultatParsing other = (ResultatParsing) obj;
        if (fileErrorTypes == null) {
            if (other.fileErrorTypes != null)
                return false;
        } else if (!fileErrorTypes.equals(other.fileErrorTypes))
            return false;
        if (primitiveEventList == null) {
            if (other.primitiveEventList != null)
                return false;
        } else if (!primitiveEventList.equals(other.primitiveEventList))
            return false;
        if (parsingErrorTypes == null) {
            if (other.parsingErrorTypes != null)
                return false;
        } else if (!parsingErrorTypes.equals(other.parsingErrorTypes))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ResultatParsing{" +
                "fileErrorTypes=" + fileErrorTypes +
                ", parsingErrorTypes=" + parsingErrorTypes +
                ", validationResult=" + validationResult +
                ", primitiveEventList=" + primitiveEventList +
                "}";
    }
}
