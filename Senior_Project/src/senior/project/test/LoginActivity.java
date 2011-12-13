package senior.project.test;

import org.json.JSONException;

import senior.project.test.server.Server;
import senior.project.test.server.errors.ServerBadLoginException;
import senior.project.test.server.errors.ServerConnectionException;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		View mlayout= findViewById(R.id.loginLayout);
        mlayout.setBackgroundResource(R.drawable.fit);
        
        final Context ctx = this.getBaseContext();
		
		final Button submit = (Button) findViewById(R.id.submitLogin);
		submit.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	String username =  findViewById(R.id.logUser).toString();
            	String password = findViewById(R.id.logPass).toString(); 
            	
            	
            	Server s = new Server();
            	try{
            		s.login(username, password);
            	}catch(JSONException je){
            		
            	}catch(ServerBadLoginException sble){
            		Toast.makeText(ctx, "Invalid login", Toast.LENGTH_LONG);
            	}catch(ServerConnectionException sce){
            		Toast.makeText(ctx, "Unable to connect to server", Toast.LENGTH_LONG);
            	}
            
            	Intent myIntent = new Intent(LoginActivity.this, EnterInfoActivity.class);
            	LoginActivity.this.startActivity(myIntent);
            	finish();
            }
        
        });
	}
	
	}
