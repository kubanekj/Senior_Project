package senior.project.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GoalActivity extends Activity {
	private static final int MY_DATE_DIALOG_ID = 3;
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
		
		//When the submit button is clicked, it will save user info
		final Button submit = (Button) findViewById(R.id.submitGoal);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	//Once the information is stored, close the activity
            	finish();
            }
        });
	}
	
}