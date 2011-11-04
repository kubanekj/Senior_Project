package senior.project.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExerciseActivity extends Activity{
	//Output stream to save user input
	FileOutputStream fos;
	boolean online = false;
	@Override
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 * This onCreate method starts a new activity with a layout
	 * to takes the users exercise information
	 */
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercise);
		

		if (isNetworkAvailable(this.getApplicationContext())) {
			online = true;
   	     Toast t = Toast.makeText(this,"You are online!!!!",8000);
   	     t.show();
		}/* else {  
            Toast t = Toast.makeText(this,"You are not online!!!!",8000);
            t.show();
      		Log.v("Home", "############################You are not online!!!!");    
		}*/
   	    
		
		//When the sumbit button is clicked, user info is stored and 
		//the activity is ended, returning to the menu
		final Button submit = (Button) findViewById(R.id.submitNu);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	final EditText calories = (EditText) findViewById(R.id.calories);  
            	String cals = calories.getText().toString();  
		        
            	final EditText fat = (EditText) findViewById(R.id.fat);  
            	String fatAmount = fat.getText().toString();  
		        
            	final EditText satFat = (EditText) findViewById(R.id.satFat);  
            	String satFatAmount = satFat.getText().toString();
		      
            	final EditText transFat = (EditText) findViewById(R.id.transFat);  
            	String transFatAmount = transFat.getText().toString();
            	
            	final EditText unsatFat = (EditText) findViewById(R.id.unsatFat);  
            	String unsatFatAmount = unsatFat.getText().toString();
		       
            	final EditText carbs = (EditText) findViewById(R.id.carbs);  
            	String carbAmount = carbs.getText().toString();
            	
            	
            	

            	//Store information
            	String FILENAME = "test_ex";
            	try{
            		fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            	}catch(FileNotFoundException fnf){
            		fnf.printStackTrace();
            	}
            	try{
            		fos.write(cals.getBytes());
            		fos.write(fatAmount.getBytes());
            		fos.write(satFatAmount.getBytes());
            		fos.write(transFatAmount.getBytes());
            		fos.write(unsatFatAmount.getBytes());
            		fos.write(carbAmount.getBytes());
            		fos.close();
            	}catch(IOException ioe){
            		ioe.printStackTrace();
            	}
            	//Once the info is stored, exit the activity
            	//Return to the main menu
            	finish();
            }
        
        });
		 
	}
	private boolean isNetworkAvailable(Context ctx) {
		ConnectivityManager cm = (ConnectivityManager) ctx .getSystemService(Context.CONNECTIVITY_SERVICE);

		// Check if we are connected to an active data network.
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

		boolean isConnected = ((activeNetwork != null )&& activeNetwork.isConnectedOrConnecting());
		return isConnected;

	}

}
