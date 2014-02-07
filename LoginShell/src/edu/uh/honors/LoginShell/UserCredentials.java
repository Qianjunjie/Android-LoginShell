package edu.uh.honors.LoginShell;

import android.text.format.Time;




public class UserCredentials {
	private String mEmail=new String("email@example.com");
	private String mPassword=new String("******");
	private String mToken=new String("00000000");
	private Time mTokenExp=new Time();
	
	Boolean isTokenValid(){
		Time currentTime=new Time();
		currentTime.setToNow();
		return mTokenExp.after(currentTime);
		
	}
	
	
	
	
	/**
	 * Set gets follow
	 * 
	 */
	String getEmail(){
		return mEmail;
	}
	void setEmail(String inEmail){
		mEmail=inEmail;
	}
	String getPassword(){
		return mPassword;
	}
	void setPassword(String inPassword){
		mPassword=inPassword;
	}
	String getToken(){
		return mToken;
	}
	void setToken(String inToken){
		mToken=inToken;
	}
	Time getTokenExp(){
		return mTokenExp;
	}
	void setTokenExp(Time inTime){
		mTokenExp=new Time(inTime);
	}
}
