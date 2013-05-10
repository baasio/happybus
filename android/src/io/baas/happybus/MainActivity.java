
package io.baas.happybus;

import com.kth.baasio.callback.BaasioCallback;
import com.kth.baasio.callback.BaasioSignInCallback;
import com.kth.baasio.entity.entity.BaasioEntity;
import com.kth.baasio.entity.user.BaasioUser;
import com.kth.baasio.exception.BaasioException;
import io.baas.happybus.R;
import io.baas.happybus.domain.User;
import io.baas.happybus.service.IUserService;
import io.baas.happybus.user.UserServiceImpl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.UUID;

public class MainActivity extends Activity {
	static int intentRequestLogin = 1;
	static int intentRequestChangePassword = 2;
	static int intentRequetJoin = 3;
	

    private TextView tvLogin;
    private TextView tvChangePassword;
    private TextView tvJoin;
    private TextView tvInformation;
    private Context mContext;

    private UUID savedUuid;
    
    private IUserService userService = UserServiceImpl.getInstance();
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mContext = this;

        tvLogin = (TextView)findViewById(R.id.login);
        tvChangePassword = (TextView)findViewById(R.id.tv_change_password);
        tvInformation = (TextView)findViewById(R.id.tv_information);
        tvJoin = (TextView)findViewById(R.id.tv_join);
        
    	if( userService.isLogin() ){
    		 showInformation(userService.getCurrentUser().getUserName() + " 님이 로그인 하셨습니다.");
    	}
        else{
        	showInformation(" 로그인 하세요." );
        }
        
       
        
        tvLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	Intent it_login = new Intent( "android.intent.action.login" );
            	startActivityForResult( it_login,  MainActivity.intentRequestLogin);
            }
        });
        
        tvChangePassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	if( UserServiceImpl.getInstance().isLogin() ){
            		Intent it_change_password = new Intent( MainActivity.this, io.baas.happybus.ChangePasswordActivity.class);
            		startActivityForResult(it_change_password, MainActivity.intentRequestChangePassword);
            	}
            	else{
            		Toast.makeText(mContext, "먼저 로그인하셔야 합니다.", Toast.LENGTH_LONG).show();
            	}
            }
        });
        
        tvJoin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	Intent it_login = new Intent( "android.intent.action.join" );
            	startActivityForResult( it_login,  MainActivity.intentRequetJoin);
            }
        });
        
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if(resultCode==RESULT_OK) // 액티비티가 정상적으로 종료되었을 경우
        {
          if(requestCode==MainActivity.intentRequestLogin) // requestCode==1 로 호출한 경우에만 처리합니다.
            {
        	  showInformation(userService.getCurrentUser().getUserName() + " 님이 로그인 하셨습니다.");
            }
        }
    }   
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    private void showInformation( String info){
    	if( userService.isLogin() ){
        	tvInformation.setText(info);
    	}
        else{
        	tvInformation.setText(" 로그인 하세요." );
        }
    }

}
