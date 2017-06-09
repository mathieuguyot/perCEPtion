package perception.configurator.xml.manager.validator;

import perception.configurator.xml.enums.validator.ValidatorErrorType;

/**
 * Représentation des erreurs de validation du fichier à travers un message
 * d'erreur et un type d'erreur.
 * 
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 *
 */
public class ValidationError {

	// Attributs
	private String errorMsg;
	private ValidatorErrorType validatorErrorType;

	// Constructeur
	/**
	 * Constructeur de la classe {@link ValidationError}.
	 * 
	 * Note : l'instanciation de la classe se réalise à travers la fabrique
	 * 
	 * @param errorMsg message d'erreur correspondant à l'erreur
	 * @param validatorErrorType type d'erreur de validation rencontré
	 */
	private ValidationError(String errorMsg, ValidatorErrorType validatorErrorType) {
		this.errorMsg = errorMsg;
		this.validatorErrorType = validatorErrorType;
	}

	private ValidationError() {

	}

	// Accesseurs

	public String getErrorMsg() {
		return errorMsg;
	}

	public ValidatorErrorType getValidatorErrorType() {
		return validatorErrorType;
	}

	// Fabriques
	/**
	 * Fabrique de {@link ValidationError} permettant d'instancier la classe.
	 * 
	 * @param errorMsg message d'erreur correspondant à l'erreur
	 * @param validatorErrorType type d'erreur de validation rencontré
	 * @return instance de {@link ValidationError}
	 */
	public static ValidationError FAB(String errorMsg, ValidatorErrorType validatorErrorType) {
		return new ValidationError(errorMsg, validatorErrorType);
	}

	/**
	 * Fabrique de {@link ValidationError} permettant d'instancier la classe.
	 *
	 * @return instance de {@link ValidationError}
	 */
	public static ValidationError FAB() {
		return new ValidationError();
	}
	
	// Services universels
	
	@Override
	public String toString() {
		return "ValidationError [errorMsg=" + errorMsg + ", validatorErrorType=" + validatorErrorType + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValidationError other = (ValidationError) obj;
		if (errorMsg == null) {
			if (other.errorMsg != null)
				return false;
		} else if (!errorMsg.equals(other.errorMsg))
			return false;
		if (validatorErrorType != other.validatorErrorType)
			return false;
		return true;
	}

	

}
