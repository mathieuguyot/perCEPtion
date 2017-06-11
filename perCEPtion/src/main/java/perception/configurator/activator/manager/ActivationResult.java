package perception.configurator.activator.manager;

import perception.configurator.activator.enums.errors.ActivationErrorType;

/**
 * Objet contenant les potentiels messages d'erreurs survenues lors de l'activation des EG
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class ActivationResult {

    // Attributs
    private String errorMsg;
    private ActivationErrorType activationErrorType;

    // Constructeur
    /**
     * Constructeur de la classe {@link ActivationResult}.
     *
     * Note : l'instanciation de la classe se réalise à travers la fabrique
     *
     * @param errorMsg
     *            message d'erreur généré par l'exception
     * @param activationErrorType
     *            erreur propre à l'instanciation du module
     */
    private ActivationResult(String errorMsg, ActivationErrorType activationErrorType) {
        this.errorMsg = errorMsg;
        this.activationErrorType = activationErrorType;
    }

    private ActivationResult() {
    }

    // Accesseurs

    public String getErrorMsg() {
        return errorMsg;
    }

    public ActivationErrorType getActivationErrorType() {
        return activationErrorType;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setActivationErrorType(ActivationErrorType activationErrorType) {
        this.activationErrorType = activationErrorType;
    }

    // Fabrique
    /**
     * Fabrique de {@link ActivationResult} permettant d'instancier la classe.
     *
     * @param errorMsg
     *            message d'erreur lié à l'exception générée
     * @param activationErrorType
     *            erreur propre à l'instanciation du module
     * @return instance de {@link ActivationResult}
     */
    public static ActivationResult FAB(String errorMsg, ActivationErrorType activationErrorType) {
        return new ActivationResult(errorMsg, activationErrorType);
    }

    /**
     * Fabrique de {@link ActivationResult} permettant d'instancier la classe.
     *
     * @return instance de {@link ActivationResult}
     */
    public static ActivationResult FAB() {
        return new ActivationResult();
    }

    /**
     * Fabrique de {@link ActivationResult} permettant d'instancier la classe.
     *
     * @param activationErrorType
     *            erreur propre à l'instanciation du module
     * @return instance de {@link ActivationResult}
     */
    public static ActivationResult FAB(ActivationErrorType activationErrorType) {
        return new ActivationResult(null, activationErrorType);
    }

    /**
     * Fabrique de {@link ActivationResult} permettant d'instancier la classe.
     *
     * @param errorMsg
     *            message d'erreur lié à l'exception générée
     * @return instance de {@link ActivationResult}
     */
    public static ActivationResult FAB(String errorMsg) {
        return new ActivationResult(errorMsg, null);
    }

    // Services
    /**
     * Indique si le résultat de la validation comporte des erreurs de
     * traitement de fichier ou de validation.
     *
     * @return vrai si le résultat comporte des erreurs et false dans le cas
     *         contraire
     */
    public boolean hasErrors() {
        return ((this.getActivationErrorType() != null) || (this.getErrorMsg() != null));
    }

    // Services universels
    @Override
    public String toString() {
        return "ActivationResult [errorMsg=" + errorMsg + ", activationErrorType=" + activationErrorType + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ActivationResult other = (ActivationResult) obj;
        if (activationErrorType != other.activationErrorType)
            return false;
        if (errorMsg == null) {
            if (other.errorMsg != null)
                return false;
        } else if (!errorMsg.equals(other.errorMsg))
            return false;
        return true;
    }

}
