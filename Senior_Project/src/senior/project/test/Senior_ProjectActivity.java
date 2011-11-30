package senior.project.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Senior_ProjectActivity extends Activity {
    /** Called when the activity is first created. */
	Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        
        View mlayout= findViewById(R.id.mainmenuLayout);
        mlayout.setBackgroundResource(R.drawable.fit);
      
    final Button exitApp = (Button) findViewById(R.id.exit);
    exitApp.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
    exitApp.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	finish();
        }
    });
    
    //If login page button is clicked, start login activity
    final Button startInfo = (Button) findViewById(R.id.loginButton);
    startInfo.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
    startInfo.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	Intent myIntent = new Intent(Senior_ProjectActivity.this, LoginActivity.class);
        	Senior_ProjectActivity.this.startActivity(myIntent);
        }
    });

    //If information page button is clicked, start information activity
    final Button startLogin = (Button) findViewById(R.id.infoButton);
    startLogin.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
    startLogin.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	Intent loginIntent = new Intent(Senior_ProjectActivity.this, InformationActivity.class);
        	Senior_ProjectActivity.this.startActivity(loginIntent);
        }
    });
    
    
    }
}