package edu.uh.honors.LoginShell;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;


public class MainActivity extends Activity {
	
	//Member Variables
	//TODO: Have system load default email/id from file
	private String mId=new String("00000");
	private String mDefaultEmail=new String("Initialized@example.com");
	private int mToken=00000;
	
	//Constants
	private static final int LOGIN_REQUEST_CODE=11111;
	
	//Keys
	public static final String EXTRA_DEFAULT_EMAIL=new String("edu.uh.honors.defaultEmail");
	
	//UI Elements
	private TextView loginResults;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);		
		//check for valid id
		if((mId.equals("00000")))
		{
			Intent intent=new Intent(this, LoginActivity.class);
			intent.putExtra(EXTRA_DEFAULT_EMAIL, mDefaultEmail);
			startActivityForResult(intent, LOGIN_REQUEST_CODE);
			
		}
		 
		updateTextView();
				
	}
	
	//check for LoginActivity return
	 
	 
	protected void onActivityResult(int requestCode, int returnCode, Intent intent){
		if(requestCode==LOGIN_REQUEST_CODE&&returnCode==RESULT_OK)
		{
			
			mDefaultEmail=intent.getStringExtra(LoginActivity.EXTRA_EMAIL);
			mId=intent.getStringExtra(LoginActivity.EXTRA_ID);
			mToken=intent.getIntExtra(LoginActivity.EXTRA_TOKEN, 00000);
			
			
		}
	}
	
	@Override
	public void onStart(){
		super.onStart();
		
		//TODO: verify token with server
		updateTextView();
	}
	
	public void updateTextView(){
		loginResults=(TextView) findViewById(R.id.textView1);
		loginResults.setText("Login Results:\n" +
				"Email: "+mDefaultEmail+"\n"+
				"ID: "+mId+"\n"+
				"Token: "+mToken);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
