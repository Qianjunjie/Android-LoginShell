package edu.uh.honors.LoginShell;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;


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
						loginController.changeState("SUBMIT_CLICK");
					}
				});
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
}
