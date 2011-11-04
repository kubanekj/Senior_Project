package senior.project.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class WeightHistory extends Activity {
	
	FileOutputStream fos;
	
	private TextView mDateDisplay;
    private Button mPickDate;
    private int mYear;
    private int mMonth;
    private int mDay;

    static final int DATE_DIALOG_ID = 2;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weight);
		
		
		// capture our View elements
        mDateDisplay = (TextView) findViewById(R.id.dateDisplay);
        mPickDate = (Button) findViewById(R.id.pickDate);

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
		
		/*if (Connection.getInstance(this).isOnline(this)) {
			online = true;
   	     Toast t = Toast.makeText(this,"You are online!!!!",8000);
   	     t.show();
		} else {  
            Toast t = Toast.makeText(this,"You are not online!!!!",8000);
            t.show();
      		Log.v("Home", "############################You are not online!!!!");    
		}*/	
   	    
		
		//When the sumbit button is clicked, user info is stored and 
		//the activity is ended, returning to the menu
		final Button submit = (Button) findViewById(R.id.submitWeight);
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
            	}catch(IOException ioe){
            		ioe.printStackTrace();
            	}*/
            	//Once the info is stored, exit the activity
            	//Return to the main menu
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
