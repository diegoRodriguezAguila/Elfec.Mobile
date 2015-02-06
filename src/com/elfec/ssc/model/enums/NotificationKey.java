package com.elfec.ssc.model.enums;

/**
 * Representa las distintas keys de notificaciones
 * @author Diego
 *
 */
public enum NotificationKey {
	/**
	 * Notificaci�n de que se agreg� una nueva cuenta para ese cliente en otro dispositivo
	 */
	NEW_ACCOUNT("NewAccount"),
	/**
	 * Notificaci�n de que se elimin� una cuenta del cliente en otro dispositivo
	 */
	ACCOUNT_DELETED("AccountDeleted"),
	/**
	 * Notificaci�n de que se a�adieron puntos de ubicaci�n
	 */
	POINTS_UPDATE("PointsUpdate"),
	/**
	 * Notificaci�n de que se actualizaron los contactos de la empresa
	 */
	CONTACTS_UPDATE("ContactsUpdate"),
	/**
	 * Notificaci�n de corte programado
	 */
	SCHEDULED_OUTAGE("ScheduledOutage"),
	/**
	 * Notificaci�n de corte fortuito por incidente
	 */
	INCIDENTAL_OUTAGE("IncidentalOutage"),
	/**
	 * Notificaci�n de corte por no pago
	 */
	NONPAYMENT_OUTAGE("NonpaymentOutage"),
	/**
	 * Key de Notificaci�n indefinido
	 */
	MISCELLANEOUS("Miscellaneous");
	
	private String string;
	
	private NotificationKey(String string)
	{
		this.string = string;
	}
	
	@Override
	public String toString() {
	       return string;
    }

	/**
	 * Obtiene la key de notificaci�n que corresponde al short provisto
	 * @param status
	 * @return
	 */
	public static NotificationKey get(short status)
	{
		return NotificationKey.values()[status];
	}
	
	/**
	 * Obtiene el NotificationKey correspondiente a la key en cadena provista 
	 * @param keyAsString
	 * @return
	 */
	public static NotificationKey get(String keyAsString)
	{
		NotificationKey[] notifKeys = NotificationKey.values();
		for (int i = 0; i < notifKeys.length; i++) {
			if(notifKeys[i].toString().equals(keyAsString))
				return notifKeys[i];
		}
		return NotificationKey.MISCELLANEOUS;
	}
	
	/**
	 * Convierte la key de notificaci�n a su short equivalente
	 * @return Short equivalente a la key de notificaci�n
	 */
	public short toShort()
	{
		return (short)this.ordinal();
	}
}
