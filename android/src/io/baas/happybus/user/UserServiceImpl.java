package io.baas.happybus.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.kth.baasio.Baas;
import com.kth.baasio.callback.BaasioAsyncTask;
import com.kth.baasio.callback.BaasioCallback;
import com.kth.baasio.callback.BaasioSignInCallback;
import com.kth.baasio.entity.user.BaasioUser;
import com.kth.baasio.exception.BaasioError;
import com.kth.baasio.exception.BaasioException;
import com.kth.baasio.response.BaasioResponse;

import io.baas.happybus.domain.User;
import io.baas.happybus.service.IUserService;

public class UserServiceImpl implements IUserService {
	static UserServiceImpl instance;
	User user;
	Context mContext;
	boolean isPasswordChanged;
	
	public synchronized static UserServiceImpl getInstance(){
		

			if(instance == null){
				instance = new UserServiceImpl();
			}
		
		return instance;
	}
	
	@Override
	public User login(Context context, String userName, String password) {
		// TODO Auto-generated method stub
        Log.d("happbus", "login start username : "+userName);
        this.mContext = context;
        
        BaasioUser.signInInBackground(
                mContext
                , userName
                , password
                , new BaasioSignInCallback() {

                    @Override
                    public void onException(BaasioException e) {
                    	Log.d("happybus", "login fail");
                    	user = null;
                        if (e.getStatusCode() != null) {
                            if (e.getErrorCode() == 201) {
                            	//username(ID) 또는 비밀번호 오류                            	
                                return;
                            }
                        }

                        //기타 오류
                    }

                    @Override
                    public void onResponse(BaasioUser response) {
                    	Log.d("happybus", "login success");
                        if (response != null) {                        	
                            // 로그인 성공                        	
                        user =  new User(Baas.io().getSignedInUser());
                        }
                    }
                });
        

        return user;
	}
	@Override
	public User signUp(Context mContext, String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void exit(Context mContext, User user) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void increaseShareCount(Context mContext) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void increaseHelpCount(Context mContext) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean resetPassword(Context mContext) {
		// TODO Auto-generated method stub
		return true;
	}
	@SuppressLint("NewApi")
	@Override
	public boolean changePassword(String oldPassword,
			String newPassword, String confirmPassword) throws Exception{
		
		if( oldPassword == null || newPassword == null || confirmPassword == null)
			throw new Exception("입력이 완료되지 않았습니다.");
		if( oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty())
			throw new Exception("입력이 완료되지 않았습니다.");
		
		if( !newPassword.equals(confirmPassword))
			throw new Exception("새로운 비밀번호를 확인하세요");
		
		changePasswordInBackground(
				oldPassword,
				newPassword,
				new BaasioCallback<Boolean>() {

			        @Override
			        public void onException(BaasioException e) {
			            // 실패
			        	isPasswordChanged = false;
			        	
			        }

			        @Override
			        public void onResponse(Boolean response) {
			            if (response != null) {
			                //성공
			            	isPasswordChanged = true;
			            }
			        }
			    });
		
		// TODO Auto-generated method stub
		return isPasswordChanged;
		
	}
	
	public boolean changePassword(String oldPassword, String newPassword) throws BaasioException {
        BaasioUser user = Baas.io().getSignedInUser();
 
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("oldpassword", oldPassword);
        params.put("newpassword", newPassword);
        BaasioResponse response = Baas.io().apiRequest(HttpMethod.POST, null, params,
                BaasioUser.ENTITY_TYPE, user.getUuid().toString(), "password");
 
        if (response != null) {
            return true;
        }
 
        throw new BaasioException(BaasioError.ERROR_UNKNOWN_NO_RESPONSE_DATA);
    }
 
    public void changePasswordInBackground(final String oldPassword, final String newPassword,
            final BaasioCallback<Boolean> callback) {
        (new BaasioAsyncTask<Boolean>(callback) {
            @Override
            public Boolean doTask() throws BaasioException {
                return changePassword(oldPassword, newPassword);
            }
        }).execute();
    }
	
    public boolean isLogin(){
    	if( user != null )
    		return this.user.isLoggedIn();
    	else
    		return false;
    }
    
    public User getCurrentUser(){
    	return user;
    }

}
