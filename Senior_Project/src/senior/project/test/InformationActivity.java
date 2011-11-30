package senior.project.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InformationActivity extends Activity{
	private FileOutputStream fos, user;
	String name, email, gender,birthday,password,checked,user_info,FILENAME  = "test_info";
	EditText username, pass,verifyPass;
	boolean bRequiresResponse;
	File filename = new File("test_info"), users;
	byte[] readIn;
	
	String USER_INFO;
	
	int genderChoice=0;
	static Button chooseGender;
	private TextView mDateDisplay;
    private Button mPickDate;
    private int mYear;
    private int mMonth;
    private int mDay;

    static final int DATE_DIALOG_ID = 0;
    static final int PASS = 5;
    static final int ALPHANUM = 3;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		
		postData();
		
		username = (EditText) findViewById(R.id.EditTextName);
		pass = (EditText) findViewById(R.id.pass);
		verifyPass = (EditText) findViewById(R.id.verifyPass);
	
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
            	if (pass.getText().toString().matches("[a-zA-z0-9]*")) {
            	if(pass.getText().toString().equals(verifyPass.getText().toString())){
            		
            	final EditText nameField = (EditText) findViewById(R.id.EditTextName);  
            	name = nameField.getText().toString()+ " ";  
          
            	final EditText emailField = (EditText) findViewById(R.id.EditTextWeight);  
            	email = emailField.getText().toString() + " ";  
        
            	birthday = mYear +"-"+ mMonth +"-"+ mDay+ " ";        
         
            	
               	if(genderChoice ==0){
            		gender = " Male"; 
            	}else{
            		gender = "Female";
            	}
               	
        		
        		//Store information
        		String[] info = {name, email, birthday, gender};
        		writeToFile(info, FILENAME);
            	
            	//Mask password
            	EditText editText = (EditText) findViewById(R.id.pass);
            	String plainPass = editText.toString();
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD); 
                editText.setTransformationMethod(new PasswordTransformationMethod()); 
            	password = editText.toString() + " ";
            	
            	String[] storedInfo = {name, password, checked};
            	writeToFile(storedInfo, "userinfo");
            	
            	
            	
            	
            	finish();
            	}else{
                	showDialog(ALPHANUM);
                }
            	}else{
            		showDialog(PASS);
            	}
            
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
        public void writeToFile(String[] userInput, String storageFilename){
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
        }
        public void postData() {
            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://www.yoursite.com/script.php");

            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("id", "1"));
                //nameValuePairs.add(new BasicNameValuePair("stringdata", "AndDev is Cool!"));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                String temp = EntityUtils.toString(response.getEntity());
                if (temp.compareTo("SUCCESS")==0)
                {
                    Toast.makeText(this, "Sending complete!", Toast.LENGTH_LONG).show();
                }else{
                	Toast.makeText(this, "Sending complete!", Toast.LENGTH_LONG).show();
                }     
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
        } 
}
