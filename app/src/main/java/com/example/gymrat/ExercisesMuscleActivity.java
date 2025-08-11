package com.example.gymrat;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import java.util.ArrayList;

/*
• Page for selecting a part of a particular muscle
  to view its associated Exercises.*/
public class ExercisesMuscleActivity extends AppCompatActivity {
    /*
    • Setting up the ExercisesMuscleActivity
        (the page that shows all parts of a muscle). */
    Intent i;
    boolean inWorkout;
    String muscle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercises_muscle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Putting the names of the parts of the muscle on the buttons:
        i = getIntent();
        inWorkout = i.getBooleanExtra("IN_WORKOUT", false);
        muscle = i.getStringExtra("CLICKED_MUSCLE");

        Button muscle1Button = findViewById(R.id.muscle1);
        Button muscle2Button = findViewById(R.id.muscle2);
        Button muscle3Button = findViewById(R.id.muscle3);

        for (int j = 0; j < Exercise.MUSCLE_NAMES.length; j++){
            if (Exercise.MUSCLE_NAMES[j].contains(muscle)) {
                muscle1Button.setText(Exercise.MUSCLE_NAMES[j]);
                muscle2Button.setText(Exercise.MUSCLE_NAMES[j + 1]);
                muscle3Button.setText(Exercise.MUSCLE_NAMES[j + 2]);
                break;
            }
        }
    }

    /*
    • Launches the page for showing each exercise for a specific part of a muscle.
    • This method runs when a button with a name of part of a muscle is pressed. */
    public void launchExercisesListPage(View v){
        Intent newI = new Intent (this, ExercisesListActivity.class);
        String musclePart = ((Button)v).getText().toString();
        newI.putExtra("MUSCLE", muscle);
        newI.putExtra("MUSCLE_PART", musclePart);
        newI.putExtra("IN_WORKOUT", i.getBooleanExtra("IN_WORKOUT", false));
        if (inWorkout){
            newI.putExtra("QUEUE", i.getSerializableExtra("QUEUE"));
        }
        ExercisesMuscleActivity.this.startActivity(newI);
    }

    /*
    • Launches the ExercisesActivity page.
    • This method runs when the "Previous" button is pressed. */
    public void launchExercisesPage(View v){
        Intent newI = new Intent (this, ExercisesActivity.class);
        newI.putExtra("IN_WORKOUT", i.getBooleanExtra("IN_WORKOUT", false));
        if (inWorkout){ //only adds in workout queue if we are creating a new workout
            newI.putExtra("QUEUE", i.getSerializableExtra("QUEUE"));
        }
        ExercisesMuscleActivity.this.startActivity(newI);
    }

}