package senior.project.test;

import java.io.FileOutputStream;

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

public class NutritionActivity extends Activity {
	 //An output stream to save user nutrition info
	FileOutputStream fos;
	Spinner category;
	Spinner choices;

	@Override
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 * The onCreate method creates anew activity with the layout to accept the user's
	 * nutrition information
	 */
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food);
		
		View mlayout= findViewById(R.id.foodLayout);
        mlayout.setBackgroundResource(R.drawable.fit);
		
		category = (Spinner) findViewById(R.id.Categories);
		category.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
		choices = (Spinner) findViewById(R.id.foodOptions);
		choices.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
		
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
	                    	 ArrayAdapter adapter1 = ArrayAdapter.createFromResource(category.getContext(), R.array.breakfast, android.R.layout.simple_spinner_dropdown_item);
	                    	 //adapter1.setDropDownViewResource(android.R.drawable.spinner_background);
	                    	 
	                    	 choices.setAdapter(adapter1);
	                    	 
	                    	 break;
	                     case 1:
	                    	 ArrayAdapter adapter2 = ArrayAdapter.createFromResource(ctx, R.array.lunch, android.R.layout.simple_spinner_dropdown_item);
	                    	 choices.setAdapter(adapter2);
	                    	 break;
	                     case 2:
	                    	 ArrayAdapter adapter3 = ArrayAdapter.createFromResource(ctx, R.array.dinner, android.R.layout.simple_spinner_dropdown_item);
	                    	 choices.setAdapter(adapter3);
	                    	 break;
	                }
	                }
	                public void onNothingSelected(AdapterView<?> parents) {

	                }
	                }
	            );

		
		//When the submit button is clicked, it will save user info
		final Button submitNutr = (Button) findViewById(R.id.closenutr);
		submitNutr.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
        submitNutr.setOnClickListener(new View.OnClickListener() {
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
            	 FileOutputStream fos;
            	String FILENAME = "test_nutr";
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