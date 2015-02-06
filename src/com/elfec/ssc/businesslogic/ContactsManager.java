package com.elfec.ssc.businesslogic;

import com.elfec.ssc.model.Contact;

/**
 * Se encarga de las operaciones relacionadas a los contactos
 * @author drodriguez
 *
 */
public class ContactsManager {

	/**
	 * Actualiza la informaci�n de contacto<br/>
	 * <b>NOTA.-</b> Reemplaza toda la informaci�n de contactos actual, por tanto 
	 * todos los campos tienen que ser enviados nuevamente y deben ser v�lidos 
	 * @param phone
	 * @param address
	 * @param email
	 * @param webPage
	 * @param facebook
	 * @param facebookId
	 */
	public static void updateContactData(String phone,String address,String email,
			String webPage,String facebook, String facebookId)
	{
		Contact contact = Contact.getDefaultContact();
		contact.setPhone(phone).setAddress(address).setEmail(email)
		.setWebPage(webPage).setFacebook(facebook).setFacebookId(facebookId)
		.save();
	}
}
