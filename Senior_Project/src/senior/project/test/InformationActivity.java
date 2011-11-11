package senior.project.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class InformationActivity extends Activity{
	private FileOutputStream fos, user;
	String name, email, gender,birthday,password,checked,user_info,FILENAME  = "test_info";
	EditText username, pass;
	boolean bRequiresResponse;
	File filename = new File("test_info"), users;
	CheckBox c1;
	byte[] readIn;
	
	String USER_INFO;
	
	SharedPreferences settings;
	
	private TextView mDateDisplay;
    private Button mPickDate;
    private int mYear;
    private int mMonth;
    private int mDay;

    static final int DATE_DIALOG_ID = 0;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		
		postData();
		
		username = (EditText) findViewById(R.id.EditTextName);
		pass = (EditText) findViewById(R.id.pass);
		c1 = (CheckBox) findViewById(R.id.user_pass);
		
		settings = getSharedPreferences(USER_INFO,
                Activity.MODE_PRIVATE);
        String uname = settings.getString("username", null);
        
        String passw = settings.getString("password", null);
        boolean check = settings.getBoolean(checked, false);

        if(uname != null){
        	username.setText(uname);
        	pass.setText(passw);
        	c1.setChecked(check);
        }
		
		/*File file = getFileStreamPath("userinfo");
	    if (file != null) {
	    
		readIn = readInternalStoragePrivate("userinfo");
		String myString = new String(readIn); 
		//String userStuff = convertByteArrayToString(readIn);
		String[] userinfoFromFile= myString.split(" ");
	    
		if(userinfoFromFile != null){
			checked = userinfoFromFile[2];
			/*if(checked.equals(true)){
				username.setText(userinfoFromFile[0]);
				pass.setText(userinfoFromFile[1]);
				c1.setChecked(true);
			}
		}
	}*/

		
		
		
		
		View mlayout = findViewById(R.id.laidout);
		//mlayout.setBackgroundResource(R.drawable.lifter);

		
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

		
        final Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	final EditText nameField = (EditText) findViewById(R.id.EditTextName);  
            	name = nameField.getText().toString()+ " ";  
          
            	final EditText emailField = (EditText) findViewById(R.id.EditTextWeight);  
            	email = emailField.getText().toString() + " ";  
        
            	birthday = mYear + mMonth + mDay+ " ";        
            	
            	final RadioButton gender0 = (RadioButton) findViewById(R.id.radio0);
            	
            	if(gender0.isChecked()){
            		gender = "Female ";
            	}else{
            		gender = "Male ";
            	}
            	
        		if(c1.isChecked()){
        			checked = "true ";
        		}else{
        			checked = "false ";
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
            	
            	
            	settings = getSharedPreferences(
                        USER_INFO, MODE_WORLD_WRITEABLE);
                SharedPreferences.Editor editor = settings.edit();

               
                if(c1.isChecked()){
                	editor.putString("username", nameField.getText().toString());
                    editor.putString("paswword",plainPass.toString());
                	editor.putBoolean("checked", true);
        		}else{
        			editor.putBoolean("checked",false);
        		}
                
                editor.commit();
            	
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
        /**
         * Reads a file from internal storage
         * @param filename the file to read from
         * @return the file content
         */
        public byte[] readInternalStoragePrivate(String filename) {
            int len = 1024;
            byte[] buffer = new byte[len];
            try {
                FileInputStream fis = openFileInput(filename);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int nrb = fis.read(buffer, 0, len); // read up to len bytes
                while (nrb != -1) {
                    baos.write(buffer, 0, nrb);
                    nrb = fis.read(buffer, 0, len);
                }
                buffer = baos.toByteArray();
                fis.close();
            } catch (FileNotFoundException e) {
            	e.printStackTrace();
        	} catch (IOException e) {
                e.printStackTrace();
            }
            return buffer;
        }
        public String convertByteArrayToString(byte[] input) {                
        	String value = new String(input);                
        	return value;
        }
            
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
