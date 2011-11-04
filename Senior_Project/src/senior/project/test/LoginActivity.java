package senior.project.test;

import android.app.Activity;
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
		
		final Button submit = (Button) findViewById(R.id.submitLogin);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	/*EditText editText = (EditText) findViewById(R.id.logPassword);
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD); 
                editText.setTransformationMethod(new PasswordTransformationMethod()); 
                */
            	finish();
            }
        
        });
	}
}
