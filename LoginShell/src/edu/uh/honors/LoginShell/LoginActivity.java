package edu.uh.honors.LoginShell;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import edu.uh.honors.LoginShell.LoginController.State;


/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 * 
 * 
 */
public class LoginActivity extends Activity {
	
	//Instance Variables
	//focusView contains the view which will receive focus when resetFocus is called
	View focusView = null;
	LoginController loginController=new LoginController(this);
	UserCredentials userData;
	//Keys
	public static final String EXTRA_USER_EMAIL=new String("edu.uh.honors.LoginActivity.userData");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_login);
		setupActionBar();
		loginController.changeState(State.INITIALIZED);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			// TODO: If Settings has multiple levels, Up should navigate up
			// that hierarchy.
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void setFocus(View focus){
		focusView=focus;
	}
	public void finishLogin(UserCredentials userData){
		this.userData=userData;
		Intent resultIntent=new Intent();
		resultIntent.putExtra(EXTRA_USER_EMAIL, this.userData.getEmail());
		setResult(RESULT_OK, resultIntent);
		finish();
	}

		
}
