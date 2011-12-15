package senior.project.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnterInfoActivity extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infoentry);
        
        View mlayout= findViewById(R.id.info);
        mlayout.setBackgroundResource(R.drawable.fit);

      //If nutrition page button is clicked, start nutrition activity
        final Button startNutrition = (Button) findViewById(R.id.nutr);
        startNutrition.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent nutrIntent = new Intent(EnterInfoActivity.this, NutritionActivity.class);
            	EnterInfoActivity.this.startActivity(nutrIntent);
            }
        });
        startNutrition.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
      //If exercise page button is clicked, start exercise activity
        final Button startExercise = (Button) findViewById(R.id.exer);
        startExercise.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent exerIntent = new Intent(EnterInfoActivity.this, ExerciseActivity.class);
            	EnterInfoActivity.this.startActivity(exerIntent);
            }
        });
        startExercise.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
       
      //If goal page button is clicked, start goal activity
        final Button startGoal = (Button) findViewById(R.id.Goal);
        startGoal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(EnterInfoActivity.this, GoalActivity.class);
            	EnterInfoActivity.this.startActivity(myIntent);
            }
        });  
        startGoal.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
      //If weight page button is clicked, start weight activity
        final Button startWeight = (Button) findViewById(R.id.WeightAct);
        startWeight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(EnterInfoActivity.this, WeightHistory.class);
            	EnterInfoActivity.this.startActivity(myIntent);
            }
        }); 
        startWeight.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
      //If exercise tracking page button is clicked, start weight activity
        final Button startExerTrack = (Button) findViewById(R.id.exerTrack);
        startExerTrack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(EnterInfoActivity.this, ExerciseTrackingActivity.class);
            	EnterInfoActivity.this.startActivity(myIntent);
            }
        }); 
        startExerTrack.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
      //If nutrition tracking page button is clicked, start weight activity
        final Button startNutrTrack = (Button) findViewById(R.id.nutrTrack);
        startNutrTrack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(EnterInfoActivity.this, NutritionTrackingActivity.class);
            	EnterInfoActivity.this.startActivity(myIntent);
            }
        }); 
        startNutrTrack.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
        
        //If "back to home screen" button is clicked, close activity
        final Button goHome = (Button) findViewById(R.id.returnHome);
        goHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	finish();
            }
        });  
        goHome.getBackground().setColorFilter(0xFFFFDD22, PorterDuff.Mode.MULTIPLY);
	}
}
