package com.example.gymrat;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

/*
• Page for either browsing or adding Exercises.*/
public class ExercisesActivity extends AppCompatActivity {
    Intent i;
    boolean inWorkout;

    /*
    • Setting up the ExercisesActivity (the exercises page). */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercises);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        i = getIntent();
        inWorkout = i.getBooleanExtra("IN_WORKOUT", false);

        if (inWorkout){ //sets page up in unique way if we are in workout
            View addExView = findViewById(R.id.addExercise);
            ViewGroup parent = (ViewGroup)addExView.getParent();

            if (parent != null){ //must get rid of "Add Exercise" button
                parent.removeView(addExView);
            }

            //must make previous button take user back to WorkoutActivity
            View previousView = findViewById(R.id.previousView);
            previousView.setOnClickListener(v -> launchWorkoutPage(v));
        }

    }

    /*
    • Launches the page for creating exercises.
    • This method runs when "Create Exercise" button is pressed. */
    public void launchExerciseCreatePage(View v){
        Intent i = new Intent (this, ExercisesCreateActivity.class);
        ExercisesActivity.this.startActivity(i);
    }

    /*
    • Launches the page for showing each part of a selected muscle.
    • This method runs when a button with a muscle name is pressed. */
    public void launchExerciseMusclePage(View v){
        Intent newI = new Intent (this, ExercisesMuscleActivity.class);

        String muscle = ((Button)v).getText().toString();
        newI.putExtra("CLICKED_MUSCLE", muscle);
        newI.putExtra("IN_WORKOUT", inWorkout);
        if (inWorkout){ //only adds in workout queue if we are creating a new workout
            newI.putExtra("QUEUE", i.getSerializableExtra("QUEUE"));
        }

        ExercisesActivity.this.startActivity(newI);
    }

    /*
    • Launches the MainActivity page.
    • This method runs when the "Previous" button is pressed. */
    public void launchMainPage(View v){
        Intent i = new Intent (this, MainActivity.class);
        ExercisesActivity.this.startActivity(i);
    }

    /*
    • Launches the WorkoutActivity page.
    • This method runs when the "Previous" button is pressed
      ONLY when creating a new workout. */
    public void launchWorkoutPage(View v){
        Intent newI = new Intent (this, WorkoutActivity.class);
        newI.putExtra("QUEUE", i.getSerializableExtra("QUEUE"));
        ExercisesActivity.this.startActivity(newI);
    }

}

