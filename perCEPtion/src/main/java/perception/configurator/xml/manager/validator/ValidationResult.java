package perception.configurator.xml.manager.validator;

import perception.configurator.xml.enums.general.FileErrorType;

/**
 * Représentation du résultat de la validation de fichier composée des
 * éventuelles erreurs propres au traitement du fichier ou/et à la validation du
 * fichier.
 * 
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 *
 */
public class ValidationResult {

	// Attributs
	private ValidationError validationError;
	private FileErrorType fileErrorType;

	// Constructeur
	/**
	 * Constructeur de la classe {@link ValidationResult}.
	 * 
	 * Note : l'instanciation de la classe se réalise à travers la fabrique
	 * 
	 * @param validationError - erreur propre à la validation
	 * @param fileErrorType - erreur propre au traitement de fichier
	 */
	private ValidationResult(ValidationError validationError, FileErrorType fileErrorType) {
		this.validationError = validationError;
		this.fileErrorType = fileErrorType;
	}

	private ValidationResult() {
	}

	// Accesseurs

	public ValidationError getValidationError() {
		return validationError;
	}

	public FileErrorType getFileErrorType() {
		return fileErrorType;
	}

	public void setValidationError(ValidationError validationError) {
		this.validationError = validationError;
	}

	public void setFileErrorType(FileErrorType fileErrorType) {
		this.fileErrorType = fileErrorType;
	}

	// Fabrique
	/**
	 * Fabrique de {@link ValidationResult} permettant d'instancier la classe.
	 * 
	 * @param validationError - erreur propre à la validation
	 * @param fileErrorType - erreur propre au traitement de fichier
	 * @return instance de {@link ValidationResult}
	 */
	public static ValidationResult FAB(ValidationError validationError, FileErrorType fileErrorType) {
		return new ValidationResult(validationError, fileErrorType);
	}

	/**
	 * Fabrique de {@link ValidationResult} permettant d'instancier la classe.
	 *
	 * @return instance de {@link ValidationResult}
	 */
	public static ValidationResult FAB() {
		return new ValidationResult();
	}

	/**
	 * Fabrique de {@link ValidationResult} permettant d'instancier la classe.
	 *
	 * @param fileErrorType - erreur propre au traitement de fichier
	 * @return instance de {@link ValidationResult}
	 */
	public static ValidationResult FAB(FileErrorType fileErrorType) {
		return new ValidationResult(null, fileErrorType);
	}

	/**
	 * Fabrique de {@link ValidationResult} permettant d'instancier la classe.
	 *
	 * @param validationError - erreur propre à la validation
	 * @return instance de {@link ValidationResult}
	 */
	public static ValidationResult FAB(ValidationError validationError) {
		return new ValidationResult(validationError, null);
	}

	// Services
	/**
	 * Indique si le résultat de la validation comporte des erreurs de
	 * traitement de fichier ou de validation.
	 * 
	 * @return <code>true</code> si le résultat comporte des erreurs et <code>false</code> dans le cas
	 *         contraire
	 */
	public boolean hasErrors() {
		return ((this.getFileErrorType() != null) || (this.getValidationError() != null));
	}

	// Services universels
	@Override
	public String toString() {
		return "ValidationResult [validationError=" + validationError + ", fileErrorType=" + fileErrorType + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValidationResult other = (ValidationResult) obj;
		if (fileErrorType != other.fileErrorType)
			return false;
		if (validationError == null) {
			if (other.validationError != null)
				return false;
		} else if (!validationError.equals(other.validationError))
			return false;
		return true;
	}
	
	

}
