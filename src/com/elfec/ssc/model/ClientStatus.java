package com.elfec.ssc.model;

/**
 * Representa los distintos estados de un cliente a nivel local
 * @author Diego
 *
 */
public enum ClientStatus {

	/**
	 * Estado cuando un cliente fu� registrado en la aplicaci�n pero otro es el que esta activo
	 */
	Registrado, 
	/**
	 * Estado cuando un cliente es el activo y que se utiliza para la aplicaci�n
	 */
	Activo;
	/**
	 * Obtiene el estado del cliente equivalente al short provisto
	 * @param status
	 * @return
	 */
	public static ClientStatus get(short status)
	{
		return ClientStatus.values()[status];
	}
	
	/**
	 * Convierte el estado del cliente actual a su short equivalente
	 * @return Short equivalente al estado
	 */
	public short toShort()
	{
		return (short)this.ordinal();
	}
}
