package senior.project.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		View mlayout= findViewById(R.id.loginLayout);
        mlayout.setBackgroundResource(R.drawable.fit);
		
		final Button submit = (Button) findViewById(R.id.submitLogin);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	String username =  findViewById(R.id.logUser).toString();
            	String password = findViewById(R.id.logPass).toString(); 
            	
            	
            	/*EditText editText = (EditText) findViewById(R.id.logPassword);
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD); 
                editText.setTransformationMethod(new PasswordTransformationMethod());*/ 
            	Intent myIntent = new Intent(LoginActivity.this, EnterInfoActivity.class);
            	LoginActivity.this.startActivity(myIntent);
            	finish();
            }
        
        });
	}
	
	}
