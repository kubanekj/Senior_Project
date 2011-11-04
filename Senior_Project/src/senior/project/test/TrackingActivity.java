package senior.project.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TrackingActivity extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nutrition);
		
		final Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	finish();
            }
        });
	}
}
