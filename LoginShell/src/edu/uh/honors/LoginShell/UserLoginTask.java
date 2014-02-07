package edu.uh.honors.LoginShell;

import edu.uh.honors.LoginShell.LoginController.State;
import android.os.AsyncTask;


/**
 * A class that extends android's AsyncTask and allows the device to attempt user 
 * authentication on a background thread.
 * 
 * Android Specific
 * 
 * @author steven
 *
 */

public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
	
	//Instance Variables
	private String mEmail, mPassword;
	private LoginController loginController;
	
	//Temporary data to be checked against
	private static final String[] DUMMY_CREDENTIALS = new String[] {
		"user@example.com:password", "bar@example.com:world" };
	private static final String DUMMY_ACCESS_ID= new String("55555");
	private static final int DUMMY_TOKEN=12345;
	
	
	public UserLoginTask(LoginController inLoginController, String email, String password){
		super();
		mEmail=email;
		mPassword=password;
		loginController=inLoginController;
	}
	
	@Override
	protected Boolean doInBackground(Void... params) {
		// TODO: attempt authentication against a network service.
		// TODO: retrieve access token from server
		try {
			// Simulate network access.
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			return false;
		}

		for (String credential : DUMMY_CREDENTIALS) {
			String[] pieces = credential.split(":");
			if (pieces[0].equals(mEmail)) {
				// Account exists, return true if the password matches.
				return pieces[1].equals(mPassword);
			}
		}

		// TODO: register the new account here. Change below return to true when
		//registration protocol has been implemented.
		return false;
	}
	//TODO: Replace DUMMY ID/TOKEN with actual id received from server.
	@Override
	protected void onPostExecute(final Boolean success) {
		if (success){
			loginController.changeState(State.COMPLETE);
		}
		else
		loginController.changeState(State.FAILED);
	}

	@Override
	protected void onCancelled() {
		loginController.changeState(State.CANCELED);
	}
}
	
	
	
