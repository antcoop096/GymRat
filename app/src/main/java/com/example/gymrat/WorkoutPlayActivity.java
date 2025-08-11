package com.example.gymrat;

import static com.example.gymrat.Utils.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;

/*
• Page for doing a rep of an Exercise in a Workout.
• Also shows info regarding the weight and reps of that Exercise.*/
public class WorkoutPlayActivity extends AppCompatActivity {

    Intent i;
    Workout workout;
    int currSet;
    int setsPerEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout_play);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        i = getIntent();
        workout = (Workout)i.getSerializableExtra("WORKOUT");
        currSet = workout.getCurrSet();
        setsPerEx = workout.getSetsPerEx();

        displayExerciseInfo(this, workout); //display info about curr Exercise
    }

    /*
    • Function that runs after user finishes a rep for an Exercise.
    */
    public void repDone(View v) {
        Intent newI;
        if (currSet < setsPerEx - 1){ //if didn't finish last set
            workout.incSet();         //increment the number of sets done for exercise
            newI = new Intent(this, WorkoutRestActivity.class); //go to the WorkoutRest page
        }
        else{ //otherwise go to the WorkoutProgCheck page (to see if user progressed in that exercise)
            newI = new Intent(this, WorkoutProgCheckActivity.class);
        }
        newI.putExtra("WORKOUT", (Serializable) workout);
        WorkoutPlayActivity.this.startActivity(newI);
    }

}