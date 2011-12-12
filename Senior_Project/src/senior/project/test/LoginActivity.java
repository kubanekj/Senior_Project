package senior.project.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		View mlayout= findViewById(R.id.loginLayout);
        mlayout.setBackgroundResource(R.drawable.fit);
		
		final Button submit = (Button) findViewById(R.id.submitLogin);
		submit.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	String username =  findViewById(R.id.logUser).toString();
            	String password = findViewById(R.id.logPass).toString(); 
            	
            	
            	/*EditText editText = (EditText) findViewById(R.id.logPassword);
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                editText.setTransformationMethod(new PasswordTransformationMethod()); */
            	Intent myIntent = new Intent(LoginActivity.this, EnterInfoActivity.class);
            	LoginActivity.this.startActivity(myIntent);
            	finish();
            }
        
        });
	}
	
	}
