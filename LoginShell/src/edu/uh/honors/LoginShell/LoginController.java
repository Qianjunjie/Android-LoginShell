package edu.uh.honors.LoginShell;



public class LoginController {
	//Instance Variables
	private LoginActivity loginActivity;
	private UserCredentials loginData= new UserCredentials();
	private LoginView loginView;
	private String mEmail;
	private String mPassword;
	
	//Keys
	public static final String EXTRA_ID=new String("edu.uh.honors.id");
	public static final String EXTRA_TOKEN=new String("edu.uh.honors.token"); 
	
	//Constants
	private static final String[] DUMMY_CREDENTIALS = new String[] {
		"user@example.com:password", "bar@example.com:world" };
	private static final String DUMMY_ACCESS_ID= new String("55555");
	private static final int DUMMY_TOKEN=12345;
	
	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;
	
	public LoginController(LoginActivity inActivity){
		loginActivity=inActivity;
		loginView=new LoginView(inActivity, this, loginData);
		
		
	}
	
	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		loginView.resetErrors();


		// Store values at the time of the login attempt.
		mEmail=loginView.getEmail();
		mPassword=loginView.getPassword();

		boolean cancel = false;
		

		// Check for a valid password.
		if (mPassword.isEmpty()) {
			loginView.passwordReqError();
			cancel = true;
		} else if (mPassword.length() < 4) {
			loginView.passwordInvalidError();
			cancel = true;
		}

		// Check for a valid email address.
		if (mEmail.isEmpty()) {
			loginView.emailReqError();
			cancel = true;
		} else if (!mEmail.contains("@")) {
			loginView.emailInvalidError();
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
			showProgress(true);
			mAuthTask = new UserLoginTask();
			mAuthTask.execute((Void) null);
		}

	

}
