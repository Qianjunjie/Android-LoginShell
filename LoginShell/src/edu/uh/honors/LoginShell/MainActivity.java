package edu.uh.honors.LoginShell;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;

/**
 * This is a dummy activity designed to simulate a real application that utilizes
 * a username/password login. 
 * 
 * Preconditions: Standalone.  User launches this dummy app directly. 
 * Postcondition: Displays the results after a successful login.
 * @author steven
 *
 */
public class MainActivity extends Activity {
	
	boolean loggedIn=false;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		setupActionBar();
		
		//Start activity to be tested here.
		if (loggedIn==false){
			Intent intent=new Intent(this, LoginActivity.class);
			startActivityForResult(intent, 1);
		}
	}
	
	protected void onActivityResult(){
		loggedIn=true;
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
