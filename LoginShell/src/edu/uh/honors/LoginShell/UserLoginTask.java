package edu.uh.honors.LoginShell;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import edu.uh.honors.LoginShell.LoginController.State;


/**
 * A class that extends android's AsyncTask and allows the device to attempt user 
 * authentication on a background thread.
 * 
 * Android Specific
 * 
 * @author steven
 *
 */

public class UserLoginTask extends AsyncTask<Void, Void, String> {
	
	//Instance Variables
	private UserCredentials user;
	private LoginController loginController;
	private LoginActivity loginActivity;
	private String url = "http://housuggest.org/appLogin/test/index.php";
	private String response;
	private ArrayList<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(2);
	
	/*
	//Temporary data to be checked against
	private static final String[] DUMMY_CREDENTIALS = new String[] {
		"user@example.com:password", "bar@example.com:world" };
	private static final String DUMMY_ACCESS_ID= new String("55555");
	private static final int DUMMY_TOKEN=12345;
	*/
	
	public UserLoginTask(LoginController inLoginController, LoginActivity loginActivity, UserCredentials user){
		super();
		this.loginActivity=loginActivity;
		loginController=inLoginController;
		this.user=user;
	}
	
	@Override
	protected String doInBackground(Void... params) {
		System.out.print(user.getPassword());
		// TODO: attempt authentication against a network service.
		HttpClient client = new DefaultHttpClient();
		HttpPost httppost;
		
		
		//HttpResponse response;
		
		try {
		  httppost=new HttpPost(url);
		  //load values into an arraylist and attach the arraylist to the httppost
		  nameValuePairs.add(new BasicNameValuePair("username", user.getEmail()));
		  nameValuePairs.add(new BasicNameValuePair("password", user.getPassword()));
		  httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		  
		  ResponseHandler<String> responseHandler = new BasicResponseHandler();
		  response = client.execute(httppost, responseHandler);
		  return response;
		} catch(Exception e) {
		  System.out.println("Exception : "+e.getMessage());
		  response=e.getMessage();
		  
		  
		}
		
		
		
		/*Contains code for validation against local data
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
		*/

		// TODO: register the new account here. Change below return to true when
		//registration protocol has been implemented.
		return response;
	}

	//TODO: Replace DUMMY ID/TOKEN with actual id received from server.
	@Override
	protected void onPostExecute(final String response) {
		loginActivity.showToast(response);
		if(response.equals("User Found"))
		loginController.changeState(State.COMPLETE);
		else if(response.equals("No Such User Found"))
				loginController.changeState(State.FAILED);
		else loginController.changeState(State.ERROR);
	
	}

	@Override
	protected void onCancelled() {
		loginController.changeState(State.CANCELED);
	}
}

	
	
	
