package senior.project.test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.json.JSONException;

import senior.project.test.server.Server;
import senior.project.test.server.ServerConstants;
import senior.project.test.server.errors.ServerConnectionException;
import senior.project.test.server.errors.ServerInvalidDateException;
import senior.project.test.server.errors.ServerInvalidEmailException;
import senior.project.test.server.errors.ServerInvalidHeightException;
import senior.project.test.server.errors.ServerInvalidPasswordException;
import senior.project.test.server.errors.ServerInvalidUserException;
import senior.project.test.server.errors.ServerInvalidWeightException;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class InformationActivity extends Activity{
	private FileOutputStream fos, user;
	String name, gender,birthday,password, verifyPassword,checked,user_info,email,verifyEmail;
	EditText username, pass,verifyPass, weightAmt;
	boolean bRequiresResponse;
	File filename = new File("test_info"), users;
	double  weight;
	byte[] readIn;
	
	String USER_INFO;
	
	int genderChoice=0; 
	double height;
	static Button chooseGender;
	private TextView mDateDisplay;
	Spinner units;
    private Button mPickDate;
    private int mYear;
    private int mMonth;
    private int mDay,unitsSelected;

    static final int DATE_DIALOG_ID = 0;
    static final int PASS = 5;
    static final int ALPHANUM = 3;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		
		final Context ctx = this.getApplicationContext();
		
		username = (EditText) findViewById(R.id.EditTextName);
		pass = (EditText) findViewById(R.id.pass);
		verifyPass = (EditText) findViewById(R.id.verifyPass);
		units  = (Spinner) findViewById(R.id.units);
		units.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
		
		View mlayout = findViewById(R.id.laidout);
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
        
        
        
        
        chooseGender = (Button) findViewById(R.id.gender);
        chooseGender.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
        chooseGender.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	showDialog(10);
            	
            }
            });
        final Button submit = (Button) findViewById(R.id.submitRegisterPage);
        submit.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	final EditText nameField = (EditText) findViewById(R.id.EditTextName);  
            	name = nameField.getText().toString();  
          
            	final EditText weightField = (EditText) findViewById(R.id.StartWeight);
            	try{
            	weight = Double.parseDouble(weightField.getText().toString());
            	}catch(NumberFormatException nfe){
            		Toast.makeText(ctx, "Please Enter a height", Toast.LENGTH_LONG);
            	}
            	weight = weight / 2.2;
            	
            	Calendar cal = new GregorianCalendar();
            	cal.set(mYear, mMonth, mDay);
            	birthday = mYear +"-"+ mMonth +"-"+ mDay;        
         
            	
               	
               	password = pass.getText().toString();
               	verifyPassword = verifyPass.getText().toString();
               	email = findViewById(R.id.Email).toString();
               	verifyEmail = findViewById(R.id.verifyEmail).toString();
               	
               	final EditText heightfield = (EditText) findViewById(R.id.heightInput);
              	try{
               	double height = Double.parseDouble(heightfield.getText().toString());
               	}catch(NumberFormatException e){
               		Toast.makeText(ctx, "Please Enter your Height", Toast.LENGTH_LONG);
               	}
               	height = height*2.54;
               	String sendheight = height + "";
               	Spinner units = (Spinner)findViewById(R.id.units);
               	unitsSelected = units.getSelectedItemPosition();
               	
               	
               	
               	try {
               		if(unitsSelected == 0){
               			if(genderChoice == 0){
               				Server.register(name, password, verifyPassword, email, verifyEmail,ServerConstants.Gender.MALE , ServerConstants.MeasurementSystem.US, weight, sendheight, cal.getTime());
               			}else{
               				Server.register(name, password, verifyPassword, email, verifyEmail,ServerConstants.Gender.FEMALE , ServerConstants.MeasurementSystem.US, weight, sendheight, cal.getTime());
               			}
               		}else{
               			if(genderChoice == 0){
               				Server.register(name, password, verifyPassword, email, verifyEmail,ServerConstants.Gender.MALE , ServerConstants.MeasurementSystem.METRIC, weight, sendheight, cal.getTime());
               			}else{
               				Server.register(name, password, verifyPassword, email, verifyEmail,ServerConstants.Gender.FEMALE , ServerConstants.MeasurementSystem.METRIC, weight, sendheight, cal.getTime());
               			}
               		}
				} catch (ServerConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServerInvalidPasswordException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServerInvalidUserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServerInvalidEmailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServerInvalidWeightException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServerInvalidHeightException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServerInvalidDateException e) {
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
            case 10:
            	final CharSequence[] items = {"Male", "Female"};

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Choose Your Gender");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
                        genderChoice = item;
                        if(genderChoice == 0){
                    		chooseGender.setText("Male");
                    	}else{
                    		chooseGender.setText("Female");
                    	}
                       
                    }
                
                	
                });
                AlertDialog alert = builder.create();
                return alert;
            case PASS:
            	 AlertDialog.Builder invalid = new AlertDialog.Builder(this);
                 invalid.setTitle("Passwords Must Match!");
                 AlertDialog invalidPass = invalid.create();
                 return invalidPass;
                 
            case ALPHANUM:
            	AlertDialog.Builder alphanum = new AlertDialog.Builder(this);
                alphanum.setTitle("Passwords May Only Contain Letters And Numbers!");
                AlertDialog invalidAlphanum = alphanum.create();
                return invalidAlphanum;
            }
            
            return null;
        }
        /**
         * Reads a file from internal storage
         * @param filename the file to read from
         * @return the file content
         */ 
       /* public void writeToFile(String[] userInput, String storageFilename){
        	FileOutputStream fos = null ;
        	try{
        		fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
        	}catch(FileNotFoundException fnf){
        		fnf.printStackTrace();
        	}
        	for(int i=0;i<userInput.length;i++){
        		try{
        			fos.write(userInput[i].getBytes());
        		}catch(IOException ioe){
        			ioe.printStackTrace();
        		}
        	}
        }*/
       }
