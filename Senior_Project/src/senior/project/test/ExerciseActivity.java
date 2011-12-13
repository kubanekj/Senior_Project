package senior.project.test;

import java.io.FileOutputStream;
import java.util.ArrayList;
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
import senior.project.test.server.errors.ServerInvalidTimeException;
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
		category.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
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
		submit.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
		//submit.
		submit.setOnClickListener(new View.OnClickListener() {
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
					s.updateFitness(d, 1, 60, 1, ServerConstants.ExerciseIntensity.INTENSE);
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
				} catch (ServerInvalidAmountException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServerInvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServerInvalidTimeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//Once the info is stored, exit the activity
				//Return to the main menu
				finish();
			}

		});

	}
}
