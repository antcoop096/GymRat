package com.example.gymrat;

import static com.example.gymrat.Utils.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.regex.Pattern;

/*
• Page for asking user if they have progressed in a particular Exercise during a workout. */
public class WorkoutProgCheckActivity extends AppCompatActivity {
    Intent i;
    Workout workout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout_prog_check);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        i = getIntent();
        workout = (Workout)i.getSerializableExtra("WORKOUT");
    }

    /*
    • Launches the WorkoutRest page.
    • This method runs when the "Continue" button is pressed. */
    public void LaunchWorkoutRestActivity(View v) {

        //validated input:
        String newGoalWeight = getValidatedInput(
                this,
                R.id.newGoalWeight,
                "new goal weight",
                "^$|^\\d+(\\.\\d+)?$"
        );
        if (newGoalWeight == null) return;

        String newRepGoal = getValidatedInput(
                this,
                R.id.newRepGoal,
                "new rep goal",
                "^$|^\\d+(\\.\\d+)?$"
        );
        if (newRepGoal == null) return;

        //updating fields of Exercise depending if user progressed
        Exercise currEx = workout.getCurrExercise().getExercise();
        if (!newGoalWeight.isEmpty()){ //if new goal weight input is not empty
            currEx.setCurrWeight(Float.valueOf(currEx.getGoalWeight())); //make the 8-10 rep weight the curr goal weight
            currEx.setGoalWeight(Float.valueOf(newGoalWeight)); //make the goal weight the new inputted goal weight
            if (!newRepGoal.isEmpty()){ //if new rep goal input is not empty
                currEx.setGoalReps(Float.valueOf(newRepGoal)); //change repGoal to new inputted rep goal
            }
            else{ //if new rep goal input is empty
                currEx.setGoalReps(0); //reset rep goal to 0
            }
        }
        else if (!newRepGoal.isEmpty()){ //if new goal weight input is empty and if new rep goal input is not empty
            currEx.setGoalReps(Float.valueOf(newRepGoal)); //change repGoal to new inputted rep goal
        }
        //if both new goal weight and new rep goal inputs are empty, no changes happen to data of Exercise

        workout.nextExercise();
        Intent i = new Intent(this, WorkoutRestActivity.class);
        i.putExtra("WORKOUT", (Serializable) workout);
        WorkoutProgCheckActivity.this.startActivity(i);

        }
    }
