package edu.uh.honors.LoginShell;



public class LoginController {
	//Instance Variables
	private UserCredentials loginData= new UserCredentials();
	private LoginView loginView;
	private LoginActivity loginActivity;
	public enum State{UNINITIALIZED, INITIALIZED, SUBMIT_CLICK, COMPLETE, CANCELED, FAILED};
	private State mState=State.UNINITIALIZED;
	

	
	
	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;
	
	public LoginController(LoginActivity inActivity){
		super();
		loginActivity=inActivity;
		
		
		
	}
	
	/**
	 * The main activity loop
	 * 
	 * @Params given enum State
	 */
	public void changeState(State newState){
		mState=newState;

		switch(mState){
		case UNINITIALIZED: return;
		case INITIALIZED: 
		{
			loginView=new LoginView(loginActivity, this, loginData);
			//TODO: Import userdata from cache here.
			break;
		}
		case SUBMIT_CLICK:
		{
			attemptLogin();break;
		}
		case COMPLETE:
		{
			proccessResult(true);break;
		}
		case FAILED:
		{
			proccessResult(false);break;
		}
		case CANCELED:
		{
			mAuthTask = null;
			loginView.showProgress(false);
			break;
		}
		}
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
		loginData.setEmail(loginView.getEmail());
		loginData.setPassword(loginView.getPassword());

		boolean cancel = false;
		

		// Check for a valid password.
		if (loginData.getPassword().length()==0) {
			loginView.passwordReqError();
			cancel = true;
		} else if (loginData.getPassword().length() < 4) {
			loginView.passwordInvalidError();
			cancel = true;
		}

		// Check for a valid email address.
		if (loginData.getEmail().length()==0) {
			loginView.emailReqError();
			cancel = true;
		} else if (!loginData.getEmail().contains("@")) {
			loginView.emailInvalidError();
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			loginView.resetFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user authentication attempt.
			loginView.loginAnimationBegin();
			mAuthTask = new UserLoginTask(this,  loginActivity, loginData);
			mAuthTask.execute((Void) null);
		}

	}
	
	/**
	 * If the login was successful the controller attempts to finish the login.
	 * If login failed then set incorrectPasswordError
	 * @param success
	 * Contains Android Specifics
	 */
	 void proccessResult(boolean success){
		mAuthTask = null;
		loginView.showProgress(false);
		
		if (success) {
			loginActivity.finishLogin(loginData);
		} else {
			loginView.passwordIncorrectError();
		
		}
		
	}
	

}
