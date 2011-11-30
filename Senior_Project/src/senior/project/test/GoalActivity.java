package senior.project.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GoalActivity extends Activity {
	
	private TextView mStartDateDisplay, mEndDateDisplay;
    private Button mPickStartDate, mPickEndDate;
    private int mStartYear, mEndYear;
    private int mStartMonth, mEndMonth;
    private int mStartDay, mEndDay;

    static final int START_DATE_DIALOG_ID = 3;
    static final int END_DATE_DIALOG_ID = 4;
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
		
		View mlayout= findViewById(R.id.goalLayout);
        mlayout.setBackgroundResource(R.drawable.fit);
		
		// capture our View elements
        mStartDateDisplay = (TextView) findViewById(R.id.goalStartDateDisplay);
        mPickStartDate = (Button) findViewById(R.id.pickGoalStartDate);
        mPickStartDate.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
        
        mEndDateDisplay = (TextView) findViewById(R.id.goalEndDateDisplay);
        mPickEndDate = (Button) findViewById(R.id.pickGoalEndDate);
        mPickEndDate.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
        
        // add a click listener to the button
        mPickStartDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(START_DATE_DIALOG_ID);
            }
        });
        mPickEndDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(END_DATE_DIALOG_ID);
            }
        });

        // get the current date
        final Calendar c = Calendar.getInstance();
        mStartYear = c.get(Calendar.YEAR);
        mStartMonth = c.get(Calendar.MONTH);
        mStartDay = c.get(Calendar.DAY_OF_MONTH);
        
        mEndYear = c.get(Calendar.YEAR);
        mEndMonth = c.get(Calendar.MONTH);
        mEndDay = c.get(Calendar.DAY_OF_MONTH);
        
        

        // display the current date (this method is below)
        updateStartDisplay();
        updateEndDisplay();
		
		//When the submit button is clicked, it will save user info
		final Button submit = (Button) findViewById(R.id.submitGoal);
		submit.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	//Once the information is stored, close the activity
            	finish();
            }
        });
	}
	 // updates the date in the TextView
    private void updateStartDisplay() {
        mStartDateDisplay.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mStartMonth + 1).append("-")
                    .append(mStartDay).append("-")
                    .append(mStartYear).append(" "));
    }
    private void updateEndDisplay() {
        mEndDateDisplay.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mEndMonth + 1).append("-")
                    .append(mEndDay).append("-")
                    .append(mEndYear).append(" "));
    }
    // the callback received when the user "sets" the date in the dialog
    private final DatePickerDialog.OnDateSetListener mStartDateSetListener =
        new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, 
                                  int monthOfYear, int dayOfMonth) {
                mStartYear = year;
                mStartMonth = monthOfYear;
                mStartDay = dayOfMonth;
                updateStartDisplay();
            }
        };
        private final DatePickerDialog.OnDateSetListener mEndDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, 
                                          int monthOfYear, int dayOfMonth) {
                        mEndYear = year;
                        mEndMonth = monthOfYear;
                        mEndDay = dayOfMonth;
                        updateEndDisplay();
                    }
                };
        @Override
        protected Dialog onCreateDialog(int id) {
            switch (id) {
            case START_DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                            mStartDateSetListener,
                            mStartYear, mStartMonth, mStartDay);
            case END_DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                            mEndDateSetListener,
                            mStartYear, mStartMonth, mStartDay);
            }
            return null;
        
	
        }
}