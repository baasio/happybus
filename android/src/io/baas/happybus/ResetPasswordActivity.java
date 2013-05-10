
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

public class ResetPasswordActivity extends Activity {

    private TextView tvResult;
    private EditText etEmail;
    private User 	user;
    
    private Context mContext;

    private UUID savedUuid;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);
        
        mContext = this;

        tvResult = (TextView)findViewById(R.id.login);
        etEmail = (EditText)findViewById(R.id.email);
        
        Button login = (Button)findViewById(R.id.btn_reset_password);
        
         Log.d("happybus", "start");
         login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email =  etEmail.getText().toString();
                UserServiceImpl userService = new UserServiceImpl();                
//                userService.resetPassword(mContext);
                
//                	if(user == null){
//                		tvLoginResult.setText("id 또는 비밀번호가 일치하지 않습니다.");
//                		//Toast.makeText(mContext, "id 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG);
//                	}
//                	else{
//                		tvLoginResult.setText("로그인 되었습니다.");
//                		try {
//							Thread.sleep(100);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//                		Intent it_main = new Intent( "android.intent.action.MAIN" );
//                		startActivity(it_main);
//                	}
              }
        });

        TextView forgotPassword = (TextView)findViewById(R.id.forget_password);
          
        forgotPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	Intent it_forgotpw = new Intent( "android.intent.action.forgotpassword" );
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
