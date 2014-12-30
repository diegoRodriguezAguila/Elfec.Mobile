package com.elfec.ssc.view;

import com.alertdialogpro.AlertDialogPro;
import com.elfec.ssc.R;
import com.elfec.ssc.presenter.ViewAccountsPresenter;
import com.elfec.ssc.presenter.views.IViewAccounts;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;

public class ViewAccounts extends ActionBarActivity implements IViewAccounts {

	private ViewAccountsPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_accounts);
        presenter = new ViewAccountsPresenter(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_accounts, menu);
        return true;
    }
    
    public void showDialog(View v)
    {
    	(new AlertDialogPro.Builder(this)).setTitle("CUADRO DE DI�LOGO")
        .setMessage("Hola, esta es una prueba de que se muestre un di�logo")
        .setPositiveButton("Aceptar", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				presenter.invokeAccountWS();
			}
		}).setNegativeButton("Cancelar", null).setNeutralButton("Ignorar", null)
        .show();
    }
}