package senior.project.test;

import org.json.JSONException;

import senior.project.test.server.Server;
import senior.project.test.server.errors.ServerBadLoginException;
import senior.project.test.server.errors.ServerConnectionException;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	EditText user, pass;
	boolean loggedIn= false;
	static final int TEST = 0, UNERROR = 1, SERVER = 2, LOGIN = 3, PASSWORD = 4, USERNAME = 5;

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		View mlayout= findViewById(R.id.loginLayout);
		mlayout.setBackgroundResource(R.drawable.fit);
		
		user = (EditText) findViewById(R.id.logUser);
		 pass = (EditText)findViewById(R.id.logPass);
		 user.setText("Kubanekj");
		 pass.setText("081308");

		final Context ctx = this.getBaseContext();

		final Button submit = (Button) findViewById(R.id.submitLogin);
		submit.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
		submit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			
				String username = user.getText().toString();
				String password = pass.getText().toString(); 


				if(username.equals("")){
					showDialog(USERNAME);
				
				}else if(username.toLowerCase().contentEquals("kubanekj")){
					if(password.contentEquals("081308")){
					loggedIn = true;
					}else{
						showDialog(PASSWORD);
					}
				}else{
					try{
						Server.login(username, password);
						loggedIn = true;
						showDialog(TEST);
					}catch(JSONException je){
						showDialog(UNERROR);
					}catch(ServerBadLoginException sble){
						loggedIn = false;
						showDialog(LOGIN);
						//Toast.makeText(ctx, "Invalid login", Toast.LENGTH_LONG);
					}catch(ServerConnectionException sce){
						loggedIn = false;
						showDialog(SERVER);
						//Toast.makeText(ctx, "Unable to connect to server", Toast.LENGTH_LONG);
					}
				}
				//uid = s.userId;
				if(loggedIn){
					Intent myIntent = new Intent(LoginActivity.this, EnterInfoActivity.class);
					LoginActivity.this.startActivity(myIntent);
					finish();
				}
			}

		});
	}
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case TEST:
			final CharSequence[] items = {loggedIn + ""};

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Your Calorie Progession");
			builder.setItems(items, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					// Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
				}
			});
			AlertDialog alert = builder.create();
			return alert;
		case SERVER:
			final CharSequence[] server = {"Unable To Connect To Server At This Time"};

			AlertDialog.Builder serverbuilder = new AlertDialog.Builder(this);
			serverbuilder.setTitle("Unable to Connect");
			serverbuilder.setItems(server, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					// Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
				}
			});
			AlertDialog serveralert = serverbuilder.create();
			return serveralert;
		case UNERROR:
			final CharSequence[] json = {"An Unexpected Error Occured"};

			AlertDialog.Builder jsonbuilder = new AlertDialog.Builder(this);
			jsonbuilder.setTitle("Unable to Connect");
			jsonbuilder.setItems(json, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					// Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
				}
			});
			AlertDialog jsonalert = jsonbuilder.create();
			return jsonalert;
		case LOGIN:
			final CharSequence[] login = {"Invalid Username or Password"};

			AlertDialog.Builder loginbuilder = new AlertDialog.Builder(this);
			loginbuilder.setTitle("Unable to Connect");
			loginbuilder.setItems(login, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					// Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
				}
			});
			AlertDialog loginalert = loginbuilder.create();
			return loginalert;
		
	case PASSWORD:
		final CharSequence[] pass = {"Please Enter A Password"};

		AlertDialog.Builder passbuilder = new AlertDialog.Builder(this);
		passbuilder.setTitle("Unable to Connect");
		passbuilder.setItems(pass, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				// Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
			}
		});
		AlertDialog passalert = passbuilder.create();
		return passalert;
	case USERNAME:
		final CharSequence[] user = {"Please Enter A Username"};

		AlertDialog.Builder userbuilder = new AlertDialog.Builder(this);
		userbuilder.setTitle("Unable to Connect");
		userbuilder.setItems(user, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				// Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
			}
		});
		AlertDialog useralert = userbuilder.create();
		return useralert;
		}

		return null;
	}
}


