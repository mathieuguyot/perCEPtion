package perception.configurator.xml.manager.parser;

import org.apache.commons.math3.complex.Complex;
import perception.configurator.xml.enums.general.FileErrorType;
import perception.configurator.xml.enums.parser.ParsingErrorType;
import perception.configurator.xml.manager.model.ComplexEventData;
import perception.configurator.xml.manager.model.PrimitiveEventData;
import perception.configurator.xml.manager.model.SimpleEventData;
import perception.configurator.xml.manager.validator.ValidationResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

        // Tableau contenant les informations d'instanciation des primitives events
    private List<PrimitiveEventData> primitiveEventList;

        // Tableau contenant les informations d'instanciation des simples events
    private List<SimpleEventData> simpleEventList;

        // Tableau contenant les informations d'instanciation des complexes events
    private List<ComplexEventData> complexEventList;

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
                            List<PrimitiveEventData> primitiveEventList, List<SimpleEventData> simpleEventList,
                            List<ComplexEventData> complexEventList) {
        this.fileErrorTypes = fileErrorTypes;
        this.parsingErrorTypes = parsingErrorTypes;
        this.primitiveEventList = primitiveEventList;
        this.simpleEventList = simpleEventList;
        this.complexEventList = complexEventList;
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
        this.simpleEventList = new ArrayList<>();
        this.complexEventList = new ArrayList<>();
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
     * Ajoute la liste d'erreurs liée au traitement de fichier.
     *
     * @param fileErrorTypeList liste d'erreurs liées au traitement de fichier à ajouter
     */
    public void addAllFileErrorTypes(List<FileErrorType> fileErrorTypeList) {
        this.getFileErrorTypes().addAll(fileErrorTypeList);
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
     * Ajoute toute les erreurs fournit liées au parsing du fichier.
     *
     * @param parsingErrorTypeList liste d'erreurs liées au parsing du fichier à ajouter
     */
    public void addAllParsingErrorTypes(List<ParsingErrorType> parsingErrorTypeList) {
        this.getParsingErrorTypes().addAll(parsingErrorTypeList);
    }

    /**
     * Ajoute une erreur liée au parsing du fichier avec complément d'information.
     *
     * @param parsingErrorType erreur liée au parsing du fichier à ajouter
     * @param complementMsg message complémentaire
     */
    public void addParsingErrorTypeWithComplementMessage(ParsingErrorType parsingErrorType, String complementMsg) {
        parsingErrorType.addComplement(complementMsg);
        if (!this.getParsingErrorTypes().contains(parsingErrorType)) {
            this.addParsingErrorType(parsingErrorType);
        }
    }

    /**
     * Ajoute les informations pour l'instanciation d'un primitives events.
     *
     * @param primitiveEventData information de primitive event à ajouter à la liste pour instanciation
     */
    public void addPrimitiveEvent(PrimitiveEventData primitiveEventData) {
        this.primitiveEventList.add(primitiveEventData);
    }

    /**
     * Ajoute toutes les informations pour l'instanciation d'un primitives events.
     *
     * @param primitiveEventDataList liste d'informations de primitives events à ajouter à la liste pour instanciation
     */
    public void addAllPrimitivesEvents(List<PrimitiveEventData> primitiveEventDataList) {
        this.primitiveEventList.addAll(primitiveEventDataList);
    }

    /**
     * Ajoute les informations pour l'instanciation d'un simples events.
     *
     * @param simpleEventData information de simples events à ajouter à la liste pour instanciation
     */
    public void addSimpleEvent(SimpleEventData simpleEventData) {
        this.simpleEventList.add(simpleEventData);
    }

    /**
     * Ajoute toutes les informations pour l'instanciation d'un simple events.
     *
     * @param simpleEventDataList liste d'informations de simples events à ajouter à la liste pour instanciation
     */
    public void addAllSimpleEvents(List<SimpleEventData> simpleEventDataList) {
        this.simpleEventList.addAll(simpleEventDataList);
    }

    /**
     * Ajoute les informations pour l'instanciation d'un complexe events.
     *
     * @param complexEventData information de complexes events à ajouter à la liste pour instanciation
     */
    public void addComplexEvent(ComplexEventData complexEventData) {
        this.complexEventList.add(complexEventData);
    }

    /**
     * Ajoute toutes les informations pour l'instanciation d'un complexe events.
     *
     * @param complexEventDataList liste d'informations de complexes events à ajouter à la liste pour instanciation
     */
    public void addAllComplexEvents(List<ComplexEventData> complexEventDataList) {
        this.complexEventList.addAll(complexEventDataList);
    }

    /**
     * Teste s'il existe un primitive event avec ce nom dans la liste des primitives events à créer.
     *
     * @param name
     *              le nom recherché dans la liste des primitives events à instancier
     * @return vrai s'il existe un primitive event avec ce nom dans la liste des primitives events à créer et false dans
     * le cas contraire
     */
    public boolean existingPrimitiveEventListWithName(String name) {
        for (PrimitiveEventData primitiveEventData : this.getPrimitiveEventList()) {
            if (primitiveEventData.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Teste s'il existe un simple event avec ce nom dans la liste des primitives events à créer.
     *
     * @param name
     *              le nom recherché dans la liste des simples events à instancier
     * @return vrai s'il existe un simple event avec ce nom dans la liste des simples events à créer et false dans
     * le cas contraire
     */
    public boolean existingSimpleEventListWithName(String name) {
        for (SimpleEventData simpleEventData: this.getSimpleEventList()) {
            if (simpleEventData.getEventName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Teste s'il existe un complex event avec ce nom dans la liste des primitives events à créer.
     *
     * @param name
     *              le nom recherché dans la liste des complexes events à instancier
     * @return vrai s'il existe un complex event avec ce nom dans la liste des simples events à créer et false dans
     * le cas contraire
     */
    public boolean existingComplexEventListWithName(String name) {
        for (SimpleEventData simpleEventData: this.getSimpleEventList()) {
            if (simpleEventData.getEventName().equals(name)) {
                return true;
            }
        }
        return false;
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

    /**
     * Permet l'impression est erreurs résultantes du parsing du fichier de configuration XML.
     * @return une chaîne de caractère comprenant les erreurs résultantes du parsing
     */
    public String printErrors() {
        String str = "Erreurs de parsing :";
        if (this.hasErrors()) {
            if(!this.fileErrorTypes.isEmpty()) {
                str += "\n\tErreurs de traitement du fichier XML de configuration :";
                for (FileErrorType fileErrorType : this.fileErrorTypes) {
                    str += "\n\t\t- " + fileErrorType;
                }
            }
            if(!this.parsingErrorTypes.isEmpty()) {
                str += "\n\tErreurs de parsing du fichier XML de configuration :";
                for (ParsingErrorType parsingErrorType : this.parsingErrorTypes) {
                    str += "\n\t\t- " + parsingErrorType;
                }
            }
            if(this.validationResult.hasErrors()) {
                str += this.validationResult.printErrors();
            }
            return str;
        }
        return "Aucune erreur de parsing.";
    }

    // Accesseurs

    public List<FileErrorType> getFileErrorTypes() {
        return fileErrorTypes;
    }

    public List<ParsingErrorType> getParsingErrorTypes() {
        return parsingErrorTypes;
    }

    public List<PrimitiveEventData> getPrimitiveEventList() {
        return primitiveEventList;
    }

    public void setFileErrorTypes(List<FileErrorType> fileErrorTypes) {
        this.fileErrorTypes = fileErrorTypes;
    }

    public void setParsingErrorTypes(List<ParsingErrorType> parsingErrorTypes) {
        this.parsingErrorTypes = parsingErrorTypes;
    }

    public void setPrimitiveEventList(List<PrimitiveEventData> primitiveEventList) {
        this.primitiveEventList = primitiveEventList;
    }

    public ValidationResult getValidationResult() {
        return validationResult;
    }

    public void setValidationResult(ValidationResult validationResult) {
        this.validationResult = validationResult;
    }

    public List<SimpleEventData> getSimpleEventList() {
        return simpleEventList;
    }

    public List<ComplexEventData> getComplexEventList() {
        return complexEventList;
    }

    public void setSimpleEventList(List<SimpleEventData> simpleEventList) {
        this.simpleEventList = simpleEventList;
    }

    public void setComplexEventList(List<ComplexEventData> complexEventList) {
        this.complexEventList = complexEventList;
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
                                      List<PrimitiveEventData> primitiveEventList, List<SimpleEventData> simpleEventList,
                                      List<ComplexEventData> complexEventList) {
        return new ResultatParsing(fileErrorTypes, parsingErrorTypes, primitiveEventList, simpleEventList, complexEventList);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultatParsing that = (ResultatParsing) o;

        if (fileErrorTypes != null ? !fileErrorTypes.equals(that.fileErrorTypes) : that.fileErrorTypes != null)
            return false;
        if (parsingErrorTypes != null ? !parsingErrorTypes.equals(that.parsingErrorTypes) : that.parsingErrorTypes != null)
            return false;
        if (validationResult != null ? !validationResult.equals(that.validationResult) : that.validationResult != null)
            return false;
        if (primitiveEventList != null ? !primitiveEventList.equals(that.primitiveEventList) : that.primitiveEventList != null)
            return false;
        if (simpleEventList != null ? !simpleEventList.equals(that.simpleEventList) : that.simpleEventList != null)
            return false;
        return complexEventList != null ? complexEventList.equals(that.complexEventList) : that.complexEventList == null;
    }

    @Override
    public String toString() {
        return "ResultatParsing{" +
                "fileErrorTypes=" + fileErrorTypes +
                ", parsingErrorTypes=" + parsingErrorTypes +
                ", validationResult=" + validationResult +
                ", \nprimitiveEventList=" + primitiveEventList +
                ", \nsimpleEventList=" + simpleEventList +
                ", \ncomplexEventList=" + complexEventList +
                '}';
    }

}
