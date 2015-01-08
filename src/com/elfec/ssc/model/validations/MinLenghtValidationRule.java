package com.elfec.ssc.model.validations;

/**
 * Valida si el tama�o de una cadena es v�lido, es decir si su longitud es mayor o igual que el tama�o
 * m�nimo requerido
 * @author Diego
 *
 */
public class MinLenghtValidationRule implements IValidationRule<String> {

	private int minLenght;
	
	public MinLenghtValidationRule(int minLenght) {
		this.minLenght = minLenght;
	}
	
	public int getMinLenght() {
		return minLenght;
	}
	public void setMinLenght(int minLenght) {
		this.minLenght = minLenght;
	}
	
	@Override
	public boolean IsValid(String objectToValidate) {
		return  objectToValidate!=null && objectToValidate.length()>=minLenght;
	}

}
