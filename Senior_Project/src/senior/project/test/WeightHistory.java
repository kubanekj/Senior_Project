package senior.project.test;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.json.JSONException;

import senior.project.test.server.Server;
import senior.project.test.server.errors.ServerConnectionException;
import senior.project.test.server.errors.ServerInvalidDateException;
import senior.project.test.server.errors.ServerInvalidKeyException;
import senior.project.test.server.errors.ServerInvalidUserException;
import senior.project.test.server.errors.ServerInvalidWeightException;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class WeightHistory extends Activity {
	
	FileOutputStream fos;
	
	private TextView mDateDisplay;
    private Button mPickDate;
    private int mYear;
    private int mMonth;
    private int mDay;
    private String weight, date, units;
    private EditText weightAmt;
    private Spinner weightunits;

    static final int DATE_DIALOG_ID = 2;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weight);
		
		View mlayout= findViewById(R.id.weightLayout);
        mlayout.setBackgroundResource(R.drawable.fit);
		
		
		// capture our View elements
        mDateDisplay = (TextView) findViewById(R.id.dateDisplay);
        mPickDate = (Button) findViewById(R.id.pickDate);
        mPickDate.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);

        // add a click listener to the button
        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // display the current date (this method is below)
        updateDisplay();
		
        weightAmt = (EditText) findViewById(R.id.weightEntry);
		weightunits = (Spinner) findViewById(R.id.Weightunits);
		weightunits.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
		
		//When the submit button is clicked, user info is stored and 
		//the activity is ended, returning to the menu
		final Button submit = (Button) findViewById(R.id.submitWeight);
		submit.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	//Once the info is stored, exit the activity
            	//Return to the main menu
            	int weight = 0;
            	try{
            		weight = Integer.parseInt(weightAmt.getText().toString());
            	}catch(NumberFormatException nfe){
            		
            	}
            	units = weightunits.getSelectedItem().toString();
				date = mYear + "-" + mMonth + "-" + mDay;
				
				
				Calendar cal  = new GregorianCalendar();
				cal.set(mYear, mMonth, mDay);
				Date d = cal.getTime();
				
				try {
					Server.updateWeight(d, weight);
				} catch (ServerConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServerInvalidUserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServerInvalidDateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServerInvalidWeightException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServerInvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
            	finish();
            }
        });
	}
	
	 // updates the date in the TextView
    private void updateDisplay() {
        mDateDisplay.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mMonth + 1).append("-")
                    .append(mDay).append("-")
                    .append(mYear).append(" "));
    }
    // the callback received when the user "sets" the date in the dialog
    private final DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, 
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };
            @Override
            protected Dialog onCreateDialog(int id) {
                switch (id) {
                case DATE_DIALOG_ID:
                    return new DatePickerDialog(this,
                                mDateSetListener,
                                mYear, mMonth, mDay);
                }
                return null;
            }
}
