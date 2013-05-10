package io.baas.happybus.domain;

import java.util.ArrayList;

import org.codehaus.jackson.JsonNode;

import com.kth.baasio.Baas;
import com.kth.baasio.entity.user.BaasioUser;

public class User {

	private String 	userName;
	private String 	email;
	private long 		created;
	private long 		modified;
	private int		shareCount;
	private int		helpCount;
	private String	pictureUrl;
	
	private ArrayList	activities;
	
	
	private boolean isLoggedIn;
	
	public User(BaasioUser baasioUser){
		setBaasioUser(baasioUser);
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getCreated() {
		return created;
	}


	public long getModified() {
		return modified;
	}

	public boolean isLoggedIn() {
		BaasioUser savedUser = Baas.io().getSignedInUser();
		if( savedUser != null )
			return true;
		else 
			return false;	
	}

	
	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public int getHelpCount() {
		return helpCount;
	}

	public void setHelpCount(int helpCount) {
		this.helpCount = helpCount;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public void setBaasioUser(BaasioUser baasioUser) {
		this.userName = baasioUser.getUsername();
		this.email = baasioUser.getEmail();
		this.created = baasioUser.getCreated();
		this.modified = baasioUser.getModified();		
		JsonNode jnShareCount = baasioUser.getProperty("shareCount");
		if( jnShareCount != null )
			this.shareCount = jnShareCount.getIntValue();
		else
			this.shareCount = 0;
		
		JsonNode jnHelpCount = baasioUser.getProperty("helpCount");
		if( jnHelpCount != null )
			this.helpCount = jnHelpCount.getIntValue();
		else
			this.helpCount = 0;
		
				
		this.pictureUrl = baasioUser.getPicture();
		this.activities = new ArrayList();
	}
	
	public Activity[] buildActivities(){
		/**
		 * TO-DO
		 */
		return null;
	}
	
}
