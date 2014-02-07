package edu.uh.honors.LoginShell;

import edu.uh.honors.LoginShell.LoginController.State;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;


//Class implementation is specific to OS.  Public method names are 
//consistent regardless of OS.
public class LoginView {
	
	//Default email to populate field with
	public static final String EXTRA_EMAIL = "edu.uh.honors.mEmail";
	
	//instance variables
	private String mEmail;
	private String mPassword;
	private LoginController loginController;
	private LoginActivity loginActivity;
	private UserCredentials loginData;
 
	// UI references.
	private EditText mEmailView;
	private EditText mPasswordView;
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;


	public LoginView(LoginActivity inActivity, LoginController inController,  UserCredentials inUser){
		super();
		loginActivity=inActivity;
		loginData=inUser;
		loginController=inController;


		mEmail = loginActivity.getIntent().getStringExtra(EXTRA_EMAIL);
		mEmailView = (EditText) loginActivity.findViewById(R.id.email);
		mEmailView.setText(mEmail);

		mPasswordView = (EditText) loginActivity.findViewById(R.id.password);
		mPasswordView
		.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int id,
					KeyEvent keyEvent) {
				if (id == R.id.login || id == EditorInfo.IME_NULL) {
					loginController.attemptLogin();
					return true;
				}
				return false;
			}
		});

		mLoginFormView = loginActivity.findViewById(R.id.login_form);
		mLoginStatusView = loginActivity.findViewById(R.id.login_status);
		mLoginStatusMessageView = (TextView) loginActivity.findViewById(R.id.login_status_message);


		loginActivity.findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						loginController.changeState(State.SUBMIT_CLICK);
					}
				});
	}
	
	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	protected void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = loginActivity.getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}
	
	public void resetErrors(){
		mEmailView.setError(null);
		mPasswordView.setError(null);
	}
	public String getEmail(){
		return mEmailView.getText().toString();	
	}
	public String getPassword(){
		return mPasswordView.getText().toString();
	}
	
	//Displays field required error in mPasswordView
	public void passwordReqError(){
		mPasswordView.setError(loginActivity.getString(R.string.error_field_required));
		loginActivity.setFocus(mPasswordView);
	}
	//Displays password invalid error in mPasswordView
	public void passwordInvalidError(){
		mPasswordView.setError(loginActivity.getString(R.string.error_invalid_email));
		loginActivity.setFocus(mPasswordView);
	}
	//Displays field required error in mEmailView
	public void emailReqError(){
		mEmailView.setError(loginActivity.getString(R.string.error_field_required));
		loginActivity.setFocus(mEmailView);
	}
	//Displays email invalid error in mEmailView
	public void emailInvalidError(){
		mEmailView.setError(loginActivity.getString(R.string.error_invalid_email));
	}
	//Displays password incorrect error
	public void passwordIncorrectError(){
		mPasswordView.setError(loginActivity.getString(R.string.error_incorrect_password));
		mPasswordView.requestFocus();
	}
	//Possibly android specific, brings focus to whichever view is currently assigned to viewFocus
	public void resetFocus(){
		loginActivity.focusView.requestFocus();
	}
	//Displays progress spinner
	public void loginAnimationBegin(){
		mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
		showProgress(true);
	}
	
	
	

	
}
