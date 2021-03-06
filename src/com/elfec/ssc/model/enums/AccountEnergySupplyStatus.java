package com.elfec.ssc.model.enums;

/**
 * Enum que define los estados del suministro de energ�a
 * @author drodriguez
 *
 */
public enum AccountEnergySupplyStatus {
	/**
	 * Estado indefinido
	 */
	UNDEFINED("Indefinido, verificar"),
	/**
	 * Estado normal
	 */
	NORMAL("Normal"),
	/**
	 * Estado que indica que el tramite para conexi�n del suministro
	 * ya fue hecho
	 */
	CONNECTION_PENDING("Pendiente de conexi�n"),
	/**
	 * Estado que indica que se va a desconectar el suministro de energ�a
	 */
	DISCONNECTION_PENDING("Pendiente de desconexi�n"),
	/**
	 * Estado de que se cort� el suministro de energ�a por falta de pago (4)
	 */
	NONPAYMENT_OUTAGE("Cortado por mora"),
	/**
	 * Estado que indica que el suministro se di� de baja
	 */
	CANCELED_ACCOUNT("Dado de baja"),
	/**
	 * Estado que indica que el suministro es incrobrable
	 */
	IRRECOVERABLE("Incobrable"),
	/**
	 * Estado de que se cort� el suministro de energ�a por falta de pago (7)
	 */
	NONPAYMENT_OUTAGE2("Cortado por mora");
	
	
	private String string;
	private AccountEnergySupplyStatus(String string)
	{
		this.string = string;
	}
	@Override
	public String toString() {
        return string;
    }
	
	/**
	 * Obtiene el estado del suministro de energia equivalente al short proporcionado
	 * @param status
	 * @return
	 */
	public static AccountEnergySupplyStatus get(short status)
	{
		return AccountEnergySupplyStatus.values()[status];
	}
	
	/**
	 * Convierte el estado ddel suministro de energia a su short equivalente
	 * @return Short equivalente al estado
	 */
	public short toShort()
	{
		return (short)this.ordinal();
	}
}
