package senior.project.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Senior_ProjectActivity extends Activity {
    /** Called when the activity is first created. */
	Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
      
    final Button exitApp = (Button) findViewById(R.id.exit);
    exitApp.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	finish();
        }
    });
    //If login page button is clicked, start login activity
    final Button startLogin = (Button) findViewById(R.id.loginPage);
    startLogin.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	Intent loginIntent = new Intent(Senior_ProjectActivity.this, LoginActivity.class);
        	Senior_ProjectActivity.this.startActivity(loginIntent);
        }
    });
    //If nutrition page button is clicked, start nutrition activity
    final Button startNutrition = (Button) findViewById(R.id.nutr);
    startNutrition.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	Intent nutrIntent = new Intent(Senior_ProjectActivity.this, NutritionActivity.class);
        	Senior_ProjectActivity.this.startActivity(nutrIntent);
        }
    });
  //If exercise page button is clicked, start exercise activity
    final Button startExercise = (Button) findViewById(R.id.exer);
    startExercise.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	Intent exerIntent = new Intent(Senior_ProjectActivity.this, ExerciseActivity.class);
        	Senior_ProjectActivity.this.startActivity(exerIntent);
        }
    });
  //If information page button is clicked, start information activity
    final Button startInfo = (Button) findViewById(R.id.info);
    startInfo.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	Intent myIntent = new Intent(Senior_ProjectActivity.this, InformationActivity.class);
        	Senior_ProjectActivity.this.startActivity(myIntent);
        }
    });
  //If tracking page button is clicked, start tracking activity
    final Button startTrack = (Button) findViewById(R.id.track);
    startTrack.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	Intent myIntent = new Intent(Senior_ProjectActivity.this, TrackingActivity.class);
        	Senior_ProjectActivity.this.startActivity(myIntent);
        }
    });  
  //If goal page button is clicked, start goal activity
    final Button startGoal = (Button) findViewById(R.id.Goal);
    startGoal.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	Intent myIntent = new Intent(Senior_ProjectActivity.this, GoalActivity.class);
        	Senior_ProjectActivity.this.startActivity(myIntent);
        }
    });  
  //If weight page button is clicked, start weight activity
    final Button startWeight = (Button) findViewById(R.id.WeightAct);
    startWeight.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	Intent myIntent = new Intent(Senior_ProjectActivity.this, WeightHistory.class);
        	Senior_ProjectActivity.this.startActivity(myIntent);
        }
    }); 
    
    }
}