package com.elfec.ssc.presenter;

import java.util.List;

import android.os.Looper;

import com.elfec.ssc.businesslogic.ClientManager;
import com.elfec.ssc.businesslogic.webservices.AccountWS;
import com.elfec.ssc.helpers.ActiveClientThreadMutex;
import com.elfec.ssc.helpers.threading.OnReleaseThread;
import com.elfec.ssc.model.Account;
import com.elfec.ssc.model.Client;
import com.elfec.ssc.model.events.IWSFinishEvent;
import com.elfec.ssc.model.webservices.WSResponse;
import com.elfec.ssc.presenter.views.IViewAccounts;

public class ViewAccountsPresenter {

	private IViewAccounts view;
	
	public ViewAccountsPresenter(IViewAccounts view)
	{
		this.view = view;
	}
	public void invokeRemoveAccountWS(final String nus)
	{
		final String imei=view.getIMEI();
		Thread thread=new Thread(new Runnable() {			
			@Override
			public void run() 
			{
				Looper.prepare();
				AccountWS accountWS = new AccountWS();
				final Client client=Client.getActiveClient();
				accountWS.removeAccount(client.getGmail(), nus, imei, new IWSFinishEvent<Boolean>() {
					
					@Override
					public void executeOnFinished(WSResponse<Boolean> result) {
						if(result.getResult())
						{
							Account.deleteAccount(client.getGmail(), nus);
							view.refreshAccounts();
						}
						else
						{
							view.displayErrors(result.getErrors());
						}
						
					}
				});				
				Looper.loop();
			}
		});
		thread.start();
	}
	
	/**
	 * Obtiene las cuentas del cliente tanto remota como localmente
	 */
	public void invokeAccountWS()
	{
		final Thread thread=new Thread(new Runnable() {			
			@Override
			public void run() 
			{
				Looper.prepare();
				AccountWS accountWS = new AccountWS();
				Client client=Client.getActiveClient();
				if(view.getPreferences().isFirstLoadAccounts())
				{
				accountWS.getAllAccounts(client.getGmail(), new IWSFinishEvent<List<Account>>() 
						{
							@Override
							public void executeOnFinished(WSResponse<List<Account>> result) 
							{
								final List<Account> accounts=result.getResult();
								ClientManager.RegisterClientAccounts(accounts);
								view.show(accounts);
								view.getPreferences().setLoadAccountsAlreadyUsed();
								view.hideWSWaiting();
							}

						});
				}
				else
					view.show(client.getActiveAccounts());
				Looper.loop();
			}
		});
		view.ShowWaitingWS();
		if(ActiveClientThreadMutex.isFree())
			thread.start();
		else ActiveClientThreadMutex.addOnThreadReleasedEvent(new OnReleaseThread() {
			@Override
			public void threadReleased() {
				thread.start();
			}
		});
	}
	
}
