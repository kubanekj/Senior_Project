package senior.project.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GoalActivity extends Activity {
	private static final int MY_DATE_DIALOG_ID = 3;
	 //An output stream to save user nutrition info
	FileOutputStream fos;
	@Override
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 * The onCreate method creates anew activity with the layout to accept the user's
	 * nutrition information
	 */
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goal);
		
	
		
		//When the submit button is clicked, it will save user info
		final Button submit = (Button) findViewById(R.id.submitGoal);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	/*final EditText calories = (EditText) findViewById(R.id.calories);  
            	String cals = calories.getText().toString();  
		        
            	final EditText fat = (EditText) findViewById(R.id.fat);  
            	String fatAmount = fat.getText().toString();  
		        
            	final EditText satFat = (EditText) findViewById(R.id.satFat);  
            	String satFatAmount = satFat.getText().toString();
		      
            	final EditText transFat = (EditText) findViewById(R.id.transFat);  
            	String transFatAmount = transFat.getText().toString();
            	
            	final EditText unsatFat = (EditText) findViewById(R.id.unsatFat);  
            	String unsatFatAmount = unsatFat.getText().toString();
		       
            	final EditText cholesterol = (EditText) findViewById(R.id.cholesterol);  
            	String cholesAmount = cholesterol.getText().toString();
            	
            	final EditText sodium = (EditText) findViewById(R.id.sodium);  
            	String sodiumAmount = sodium.getText().toString();
            	
            	final EditText carbs = (EditText) findViewById(R.id.carbs);  
            	String carbAmount = carbs.getText().toString();
		        		        
            	final EditText protein = (EditText) findViewById(R.id.protein);  
            	String proteinAmount = protein.getText().toString();
            	
            	//Store information
            	String FILENAME = "test_goal";
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
            		fos.write(cholesAmount.getBytes());
            		fos.write(sodiumAmount.getBytes());
            		fos.write(carbAmount.getBytes());
            		fos.write(proteinAmount.getBytes());
            		fos.close();
            	}catch(IOException ioe){
            		ioe.printStackTrace();
            	}*/
            	//Once the information is stored, close the activity
            	finish();
            }
        
        });
	}
	
}