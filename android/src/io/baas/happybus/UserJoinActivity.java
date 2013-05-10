
package io.baas.happybus;

import com.kth.baasio.callback.BaasioCallback;
import com.kth.baasio.callback.BaasioSignInCallback;
import com.kth.baasio.entity.entity.BaasioEntity;
import com.kth.baasio.entity.user.BaasioUser;
import com.kth.baasio.exception.BaasioException;
import io.baas.happybus.R;
import io.baas.happybus.domain.User;
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

public class UserJoinActivity extends Activity {
	static int intentResultJoinSuccess = 5;
	static int intentResultJoinFail = 6;

    private TextView tvBaasio;
    private TextView tvLoginResult;
    private EditText userNameEditText;
    private EditText passwordEditText;
    private User 	user;
    
    private Context mContext;

    private UUID savedUuid;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);
        
        mContext = this;

        tvBaasio = (TextView)findViewById(R.id.login);
        tvLoginResult = (TextView)findViewById(R.id.login_result);
        userNameEditText = (EditText)findViewById(R.id.username);
        passwordEditText = (EditText)findViewById(R.id.password);
        
        Button login = (Button)findViewById(R.id.btn_login);
        
         Log.d("happybus", "start");
         login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userName =  userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                UserServiceImpl userService = UserServiceImpl.getInstance();                
                user = userService.login(mContext, userName, password);
                
                	if(user == null){
                		tvLoginResult.setText("id 또는 비밀번호가 일치하지 않습니다.");
                		//Toast.makeText(mContext, "id 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG);
                	}
                	else{
                		tvLoginResult.setText("로그인 되었습니다.");
                		try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                		finish();
                		//Intent it_main = new Intent( UserLoginActivity.this, io.baas.happybus.MainActivity.class);
                		
                		//startActivity(it_main);
                	}
              }
        });

        TextView forgotPassword = (TextView)findViewById(R.id.forget_password);
          
        forgotPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	Intent it_forgotpw = new Intent( "android.intent.action.reset_password" );
            	startActivity( it_forgotpw );
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
