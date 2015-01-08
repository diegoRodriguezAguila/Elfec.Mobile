package com.elfec.ssc.model.validations;

/**
 * Provee de una interfaz para reglas de validaci�n
 * @author Diego
 *
 * @param <T>
 */
public interface IValidationRule<T> {
	
	/**
	 * Valida si es que el objeto cumple con los requisitos de la regla de validaci�n
	 * @param objectToValidate
	 * @return
	 */
	public boolean IsValid(T objectToValidate);
}
