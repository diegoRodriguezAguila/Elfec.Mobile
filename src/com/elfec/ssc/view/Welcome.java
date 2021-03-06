package com.elfec.ssc.view;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.elfec.ssc.R;
import com.elfec.ssc.helpers.ui.ButtonClicksHelper;
import com.elfec.ssc.presenter.WelcomePresenter;
import com.elfec.ssc.presenter.views.IWelcome;
import com.elfec.ssc.security.PreferencesManager;
import com.elfec.ssc.view.controls.AccountPickerDialogService;
import com.elfec.ssc.view.controls.events.OnAccountPicked;

public class Welcome extends AppCompatActivity implements IWelcome {

	private WelcomePresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		presenter = new WelcomePresenter(this);
		presenter.insertContact();
		Animation slideRight = AnimationUtils.loadAnimation(this,
				R.anim.slide_right_welcome);
		Animation slideLeft = AnimationUtils.loadAnimation(this,
				R.anim.slide_left_welcome);
		Animation slideBottom = AnimationUtils.loadAnimation(this,
				R.anim.abc_slide_in_bottom);
		findViewById(R.id.imgSSCLogo).startAnimation(slideRight);
		findViewById(R.id.lbl_app_title).startAnimation(slideRight);
		findViewById(R.id.welcome_description).startAnimation(slideRight);
		findViewById(R.id.welcome_logo_elfec).startAnimation(slideLeft);
		findViewById(R.id.welcome_create_gmail).startAnimation(slideLeft);
		findViewById(R.id.btn_select_account).startAnimation(slideBottom);
		findViewById(R.id.btn_decline_account).startAnimation(slideBottom);
		findViewById(R.id.welcome_to_app).startAnimation(
				AnimationUtils.loadAnimation(this, R.anim.abc_slide_in_top));
	}

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		finish();// go back to the previous Activity
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return super.onOptionsItemSelected(item);
	}

	public void btnSelectAccountClick(View view) {
		if (ButtonClicksHelper.canClickButton()) {
			AccountPickerDialogService.instanceService(this,
					new OnAccountPicked() {
						@Override
						public void onAccountPicked(String gmail) {
							presenter.handlePickedGmailAccount(gmail);
						}

						@Override
						public void onPickedCanceled() {
						}
					}).show();
		}
	}

	public void btnDeclineAccountClick(View view) {
		if (ButtonClicksHelper.canClickButton()) {
			goToMainMenu();
			getPreferences().setAppAlreadyUsed();
		}
	}

	// #region Interface Methods

	@Override
	public void goToMainMenu() {
		Intent i = new Intent(Welcome.this, MainMenu.class);
		startActivity(i);
		overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
		Welcome.this.finish();
	}

	@Override
	public PreferencesManager getPreferences() {
		return new PreferencesManager(getApplicationContext());
	}

	// #endregion
}
