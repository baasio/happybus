package io.baas.happybus;

import io.baas.happybus.domain.User;
import io.baas.happybus.user.UserServiceImpl;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.Menu;
import android.view.View;

public class ChangePasswordActivity extends Activity {
	
	static int intentResultChangePasswordSuccess = 3;
	static int intentResultChangePasswordFail = 4;

	    private TextView tvResult;
	    private EditText etOldPassword;
	    private EditText etNewPassword;
	    private EditText etConfirmPassword;
	    private User 	user;    
	    private Context mContext;
	    private Intent intent;
	    

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
		  Log.d("happybuis", "chpw_1");
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.change_password);
	        Log.d("happybuis", "chpw_2");
	        mContext = this;
	        intent = getIntent();

	        tvResult = (TextView)findViewById(R.id.change_password_result);
	        etOldPassword = (EditText)findViewById(R.id.old_password);
	        etNewPassword = (EditText)findViewById(R.id.new_password);
	        etConfirmPassword = (EditText)findViewById(R.id.confirm_password);
	        Log.d("happybuis", "chpw_3");
	        Button btChangePassword = (Button)findViewById(R.id.btn_change_password);
	        
	        btChangePassword.setOnClickListener(new View.OnClickListener() {

	            @Override
	            public void onClick(View v) {
	                String oldPassword =  etOldPassword.getText().toString();
	                String newPassword =  etNewPassword.getText().toString();
	                String confirmPassword =  etConfirmPassword.getText().toString();
	                UserServiceImpl userService = UserServiceImpl.getInstance(); 
	                boolean success = false;
	                try {
	                		success = userService.changePassword(oldPassword, newPassword, confirmPassword);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							
							tvResult.setText(e.getMessage());
							e.printStackTrace();
							return;
						}
	                if(success){
	                
	                tvResult.setText("성공적으로 변경되었습니다.");
	                setResult(RESULT_OK, intent);
	                finish();
	                	}
	                else{
	                	tvResult.setText("비밀번호 변경에 실패하였습니다.");
	                    }
	                
	                

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
