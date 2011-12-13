package senior.project.test;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.json.JSONException;

import senior.project.test.server.Server;
import senior.project.test.server.ServerConstants;
import senior.project.test.server.errors.ServerConnectionException;
import senior.project.test.server.errors.ServerInvalidAmountException;
import senior.project.test.server.errors.ServerInvalidDateException;
import senior.project.test.server.errors.ServerInvalidItemException;
import senior.project.test.server.errors.ServerInvalidKeyException;
import senior.project.test.server.errors.ServerInvalidMealException;
import senior.project.test.server.errors.ServerInvalidUserException;
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
            	Server s = new Server();
            	
            	final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                
                
            	Calendar cal = new GregorianCalendar();
            	cal.set(mYear, mMonth, mDay);
            	Date d = cal.getTime();
            	try {
					s.updateDiet(d, 1, 1.0, ServerConstants.MealType.BREAKFAST);
				} catch (ServerConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServerInvalidUserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServerInvalidItemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServerInvalidDateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServerInvalidMealException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServerInvalidAmountException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServerInvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	//Once the information is stored, close the activity
            	finish();
            }
        
        });
	}
}