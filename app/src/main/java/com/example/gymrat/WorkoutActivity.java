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

import java.util.Queue;
import java.io.Serializable;
/*
• Page for setting up a Workout.*/
public class WorkoutActivity extends AppCompatActivity {

    Intent i;
    Queue<ExercisePointer> exerciseQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        i = getIntent();
        exerciseQueue = (Queue<ExercisePointer>) i.getSerializableExtra("QUEUE");

        //displays list of exercises currently in the workout
        String currExercises = "";
        for (ExercisePointer exPointer: exerciseQueue) {
            currExercises += (exPointer.getExercise().getName() + "\n");
        }
        ((TextView) findViewById(R.id.currWorkoutList)).setText(currExercises);
    }

    /*
    • Launches the ExercisesActivity page.
    • This method runs when "Add" button is pressed. */
    public void launchExercisesPage(View v) {
        Intent i = new Intent(this, ExercisesActivity.class);
        i.putExtra("IN_WORKOUT", true);
        i.putExtra("QUEUE", (Serializable) exerciseQueue);
        WorkoutActivity.this.startActivity(i);
    }

    /*
    • Launches the WorkoutPlayActivity page.
    • This method runs when "Start" button is pressed. */
    public void launchWorkoutPlayPage(View v) {
        if (exerciseQueue.isEmpty()){ //must have at least one Exercise in workout to start workout
            Toast.makeText(
                    this,
                    "Must have at least one exercise.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        //validating inputs:
        String restTime = getValidatedInput(
                this,
                R.id.restTimeInput,
                "rest time",
                "^[0-9]:[0-5][0-9]$"
        );
        if (restTime == null) return;

        String sets = getValidatedInput(
                this,
                R.id.setsPerExInput,
                "sets per exercise",
                "^[1-9][0-9]?$"
        );
        if (sets == null) return;

        Intent i = new Intent(this, WorkoutPlayActivity.class);
        Workout workout = new Workout(exerciseQueue, Integer.valueOf(sets), minToMilliSec(restTime));
        i.putExtra("WORKOUT", (Serializable) workout);
        i.putExtra("IN_WORKOUT", true);
        WorkoutActivity.this.startActivity(i);
    }

    /*
    • Launches the MainActivity page.
    • This method runs when the "Previous" button is pressed. */
    public void launchMainPage(View v){
        Intent i = new Intent (this, MainActivity.class);
        WorkoutActivity.this.startActivity(i);
    }


}