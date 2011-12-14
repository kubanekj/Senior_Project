package senior.project.test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.json.JSONException;
import org.json.JSONObject;

import senior.project.test.server.Server;
import senior.project.test.server.errors.ServerBadDateRangeException;
import senior.project.test.server.errors.ServerConnectionException;
import senior.project.test.server.errors.ServerInvalidDateException;
import senior.project.test.server.errors.ServerInvalidKeyException;
import senior.project.test.server.errors.ServerInvalidUserException;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class ExerciseTrackingActivity extends Activity {
	
	private TextView mStartDateDisplay, mEndDateDisplay;
    private Button mPickStartDate, mPickEndDate;
    private int mStartYear, mEndYear;
    private int mStartMonth, mEndMonth;
    private int mStartDay, mEndDay;
    
    private String calsBurned, calsBurnedPerDay;
    
    private String startDate, endDate;
    
    Server s = new Server();

    static final int START_DATE_DIALOG_ID = 3;
    static final int END_DATE_DIALOG_ID = 4;
    static final int CALS_BURNED = 10;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercisetracking);
		
		mStartDateDisplay = (TextView) findViewById(R.id.exerStartDate);
        mPickStartDate = (Button) findViewById(R.id.pickExerStartDate);
        mPickStartDate.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
        
        mEndDateDisplay = (TextView) findViewById(R.id.exerEndDate);
        mPickEndDate = (Button) findViewById(R.id.pickExerEndDate);
        mPickEndDate.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
        
        /****************************************************
         * Creates a listener for the set start date button
         ****************************************************/
        mPickStartDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(START_DATE_DIALOG_ID);
            }
        });
        /****************************************************
         * Creates a listener for the set end date button
         ****************************************************/
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
        
        /***************************************************************
         * When the Get Exercise Info button is clicked, adialog box 
         * is displayed showing total calories consumed and average
         * calories consumed per day
         ***************************************************************/
        final Button getExer = (Button) findViewById(R.id.getExer);
  		getExer.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
  		getExer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	JSONObject data = null;
            	
            	Calendar cal1 = new GregorianCalendar();
            	Calendar cal2 = new GregorianCalendar();
            	
            	cal1.set(mStartYear, mStartMonth, mStartDay); 
            	cal2.set(mEndYear, mEndMonth, mEndDay);

            	
            	try {
					data = s.trackFitness(cal1.getTime(), cal2.getTime());
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
				} catch (ServerBadDateRangeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServerInvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            	try {
					calsBurned = data.get("name") + "";
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	calsBurnedPerDay = Integer.parseInt(calsBurned)/getNumDays()+"";
            	showDialog(CALS_BURNED);
            	startDate = mStartYear +  "-" + mStartMonth + "-" + mStartDay;
            	endDate = mEndYear +  "-" + mEndMonth + "-" + mEndDay;
            }
        });
        
        /********************************************************************
         * When the submit button is clicked, it will save user info
         ********************************************************************/
      		final Button submit = (Button) findViewById(R.id.exit);
      		submit.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
              submit.setOnClickListener(new View.OnClickListener() {
                  public void onClick(View v) {
                  	//Once the information is stored, close the activity
                  	finish();
                  }
              });
	}
	/**************************************************
	 * This method updates the TextView to the chosen
	 * start date 
	 ***************************************************/
	private void updateStartDisplay() {
        mStartDateDisplay.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mStartMonth + 1).append("-")
                    .append(mStartDay).append("-")
                    .append(mStartYear).append(" "));
    }
	/**************************************************
	 * This method updates the TextView to the chosen
	 * end date 
	 ***************************************************/
    private void updateEndDisplay() {
        mEndDateDisplay.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mEndMonth + 1).append("-")
                    .append(mEndDay).append("-")
                    .append(mEndYear).append(" "));
    }
    /*************************************************************************
     * This method generates the dialog box allowing a user to pick a start 
     * date for reporting calorie consumption
     ************************************************************************/
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
        /*************************************************************************
         * This method generates the dialog box allowing a user to pick an end 
         * date for reporting calorie consumption
         ************************************************************************/
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
	    /********************************************************************
	     * This method determines which dialog box needs to be created and 
	     * returns the appropriate dialog box
	     * (non-Javadoc)
	     * @see android.app.Activity#onCreateDialog(int)
	     ********************************************************************/
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
            case CALS_BURNED:
            	final CharSequence[] items = { calsBurned + " calories burned", calsBurnedPerDay + " Avg. calories burned per day"};

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Your Calorie Progession");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        //Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alert = builder.create();
                return alert;
            }
            return null;
        }
        
        public int getNumDays(){
        	int numDays;
        	Calendar cal1 = new GregorianCalendar();
        	Calendar cal2 = new GregorianCalendar();
        	
        	cal1.set(mStartYear, mStartMonth, mStartDay); 
        	cal2.set(mEndYear, mEndMonth, mEndDay);

        	numDays = daysBetween(cal1.getTime(),cal2.getTime());
        	return numDays;
        }
        
        
        public int daysBetween(Date d1, Date d2){
        	int numDays;
        	numDays = (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
        	if(numDays == 0){
        		numDays = 1;
        	}
        	 return numDays;
    	 }
}
