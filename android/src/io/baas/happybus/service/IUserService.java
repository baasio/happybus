package io.baas.happybus.service;

import android.content.Context;
import io.baas.happybus.domain.User;

public interface IUserService {
	
	public User 	login(Context mContext,  String userName, String password );	
	public User 	signUp(Context mContext,  String username, String password);
	public void 	exit(Context mContext, User user);
	
	public void	increaseShareCount(Context mContext);
	public void	increaseHelpCount( Context mContext);
	
	public boolean	resetPassword( Context mContext );
	public boolean	changePassword(String oldPassword, String newPassword, String confirmPassword) throws Exception;
	
	public boolean isLogin();
	
	public User getCurrentUser();

}
