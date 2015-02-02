package com.elfec.ssc.view.gcmservices;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.elfec.ssc.businesslogic.ElfecAccountsManager;
import com.elfec.ssc.businesslogic.ElfecNotificationManager;
import com.elfec.ssc.helpers.ViewPresenterManager;
import com.elfec.ssc.model.Client;
import com.elfec.ssc.model.enums.NotificationKey;
import com.elfec.ssc.model.enums.NotificationType;
import com.elfec.ssc.presenter.ViewAccountsPresenter;
import com.elfec.ssc.view.ViewAccounts;

/**
 * Maneja las notificaciones GCM de nuevas cuentas
 * @author Diego
 *
 */
public class NewAccountGCMHandler implements IGCMHandler {

	private final int NOTIF_ID = 1;
	@Override
	public Class<? extends Activity> getActivityClass() {
		return ViewAccounts.class;
	}

	@Override
	public void handleGCMessage(Bundle messageInfo,	NotificationManager notifManager, NotificationCompat.Builder builder) {
		Client ownerClient = Client.getClientByGmail(messageInfo.getString("gmail"));
		if(ownerClient != null)
		{
			ElfecAccountsManager.registerAccount(ownerClient, messageInfo.getString("number"), messageInfo.getString("nus"));
			ElfecNotificationManager.SaveNotification(messageInfo.getString("title"), messageInfo.getString("content"),
					NotificationType.get(messageInfo.getShort("type")), NotificationKey.get(messageInfo.getString("key")));
			ViewAccountsPresenter presenter = ViewPresenterManager
					.getPresenter();
			if (presenter != null)
				presenter.gatherAccounts();
			notifManager.notify(NOTIF_ID, builder.build());
		}
	}

}
