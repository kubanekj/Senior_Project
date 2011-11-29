package senior.project.test;

import java.io.FileOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ExerciseActivity extends Activity{
	//Output stream to save user input
	FileOutputStream fos;
	boolean online = false;
	Spinner category,choices;
	ArrayList<String> exercises= new ArrayList<String>();
	@Override
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 * This onCreate method starts a new activity with a layout
	 * to takes the users exercise information
	 */
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fitness);   	    
		
		View mlayout= findViewById(R.id.fitnessLayout);
        mlayout.setBackgroundResource(R.drawable.fit);
	
		
		category = (Spinner) findViewById(R.id.exerciseCategories);
		choices = (Spinner) findViewById(R.id.exerciseOptions);
		
		/*if(category.getSelectedItem().toString() == "Breakfast"){
			//choices.setAdapter(adapter);
		}else if(category.getSelectedItem().toString() == "Lunch"){
			
		}else{
			
		}*/
		
	    category.setOnItemSelectedListener(
	            new OnItemSelectedListener() {
	                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
	                    // Here's what I need help with. I basically want it to say:
	                	Context ctx = getBaseContext();
	                     switch(position) {

	                     case 0:
	                    	 ArrayAdapter adapter1 = ArrayAdapter.createFromResource(category.getContext(), R.array.light, android.R.layout.simple_spinner_dropdown_item);
	                    	 //adapter1.setDropDownViewResource(android.R.drawable.spinner_background);
	                    	 
	                    	 choices.setAdapter(adapter1);
	                    	 
	                    	 break;
	                     case 1:
	                    	 ArrayAdapter adapter2 = ArrayAdapter.createFromResource(ctx, R.array.moderate, android.R.layout.simple_spinner_dropdown_item);
	                    	 choices.setAdapter(adapter2);
	                    	 break;
	                     case 2:
	                    	 ArrayAdapter adapter3 = ArrayAdapter.createFromResource(ctx, R.array.intense, android.R.layout.simple_spinner_dropdown_item);
	                    	 choices.setAdapter(adapter3);
	                    	 break;
	                }
	                }
	                public void onNothingSelected(AdapterView<?> parents) {

	                }
	                }
	            );
		
		//When the sumbit button is clicked, user info is stored and 
		//the activity is ended, returning to the menu
		final Button submit = (Button) findViewById(R.id.closefit);
		submit.getBackground().setColorFilter(0xFF00F0E0, PorterDuff.Mode.MULTIPLY);
		//submit.
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            /*	final EditText calories = (EditText) findViewById(R.id.calories);  
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
            	}*/
            	//Once the info is stored, exit the activity
            	//Return to the main menu
            	finish();
            }
        
        });
		 
	}
}
