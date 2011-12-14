package senior.project.test;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.json.JSONException;
import org.json.JSONObject;

import senior.project.test.server.Server;
import senior.project.test.server.ServerConstants;
import senior.project.test.server.errors.ServerBadRetrievalException;
import senior.project.test.server.errors.ServerConnectionException;
import senior.project.test.server.errors.ServerInvalidAmountException;
import senior.project.test.server.errors.ServerInvalidDateException;
import senior.project.test.server.errors.ServerInvalidItemException;
import senior.project.test.server.errors.ServerInvalidKeyException;
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
	ArrayList<String> exercises = new ArrayList<String>();
	JSONObject data = null, fitness1=null, fitness2=null;
	Server s = new Server();
	int numExer, numReps, duration;
	String name;
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

		numReps = Integer.parseInt(findViewById(R.id.numReps).toString());
		duration = Integer.parseInt(findViewById(R.id.length).toString());
		
		try {
			data = s.listFitness();
		} catch (ServerConnectionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ServerBadRetrievalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		//When the sumbit button is clicked, user info is stored and 
		//the activity is ended, returning to the menu
		final Button submit = (Button) findViewById(R.id.closefit);
		submit.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
		//submit.
		submit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int selected = choices.getSelectedItemPosition() + 1;
				int intensity = category.getSelectedItemPosition();

				Server s = new Server();

				final Calendar c = Calendar.getInstance();
				int mYear = c.get(Calendar.YEAR);
				int mMonth = c.get(Calendar.MONTH);
				int mDay = c.get(Calendar.DAY_OF_MONTH);


				Calendar cal = new GregorianCalendar();
				cal.set(mYear, mMonth, mDay);
				Date d = cal.getTime();
				try {
					switch(intensity){
					case 0:
						s.updateFitness(d, selected, duration, numReps, ServerConstants.ExerciseIntensity.LIGHT);
					case 1:
						s.updateFitness(d, selected, duration, numReps, ServerConstants.ExerciseIntensity.MODERATE);
					case 2:
						s.updateFitness(d, selected, duration, numReps, ServerConstants.ExerciseIntensity.INTENSE);
					}
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
