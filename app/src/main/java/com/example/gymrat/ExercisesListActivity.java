package com.example.gymrat;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.List;
import java.util.Queue;
import java.util.ArrayDeque;

/*
• Page for browsing existing Exercises for a part of a particular muscle group
  or selecting existing Exercises for a part of a particular muscle group to add in workout.*/
public class ExercisesListActivity extends AppCompatActivity {
    /*
    • Setting up the ExercisesListActivity
        (the page for showing each exercise for a specific part of a muscle). */

    Intent i;
    boolean inWorkout;
    String muscle;
    int musclePartIdx;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercises_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Displaying all exercise names for a specific muscle part to screen:
        //Also getting the name of muscle (not muscle part) and storing it in muscle
        //That is for when we return to ExercisesMuscleActivity page
        i = getIntent();
        inWorkout = i.getBooleanExtra("IN_WORKOUT", false);
        muscle = i.getStringExtra("MUSCLE");

        String musclePart = i.getStringExtra("MUSCLE_PART");
        TextView musclePartView = findViewById(R.id.muscleName);
        musclePartView.setText(musclePart);

        musclePartIdx = Exercise.MUSCLE_INDICES.get(musclePart);
        List<Exercise> musclePartExercises = Exercise.exerciseList[musclePartIdx];

        if (inWorkout) { //if in workout, make button for each exercise as a way to add it to workout
            //load XML layout, find linearLayout inside the ScrollView, and add buttons
            setContentView(R.layout.activity_exercises_list);
            LinearLayout buttonContainer = findViewById(R.id.button_container);
            for (int j = 0; j < musclePartExercises.size(); j++) {
                Button button = new Button(this);
                button.setText(musclePartExercises.get(j).getName());
                button.setTag(new ExercisePointer(musclePartIdx, j));
                button.setOnClickListener(this::launchWorkoutPage);
                buttonContainer.addView(button);
            }
        }
        else { //otherwise print simple string displaying name and info for each exercise
            String musclePartExercisesString = "";
            for (Exercise exercise : musclePartExercises) {
                musclePartExercisesString += exercise.getName() +
                                             " (" + exercise.getCurrWeight() +
                                             "->" + exercise.getGoalWeight() +
                                              "(" + exercise.getGoalReps() + "))\n";
            }
            ((TextView) findViewById(R.id.textText)).setText(musclePartExercisesString);
        }
    }

    /*
    • Launches the ExercisesMuscleActivity page.
    • This method runs when the "Previous" button is pressed. */
    public void launchExercisesMusclePage(View v){
        Intent newI = new Intent (this, ExercisesMuscleActivity.class);
        newI.putExtra("CLICKED_MUSCLE", muscle);
        newI.putExtra("IN_WORKOUT", i.getBooleanExtra("IN_WORKOUT", false));
        if (inWorkout){
            newI.putExtra("QUEUE", i.getSerializableExtra("QUEUE"));
        }
        ExercisesListActivity.this.startActivity(newI);
    }

    /*
    • Launches the WorkoutActivity page.
    • This method runs when an Exercise is selected to be added
      to a workout; ONLY happens when we are creating workout.*/
    public void launchWorkoutPage(View v){
        Intent newI = new Intent(this, WorkoutActivity.class);
        Queue<ExercisePointer> q = (ArrayDeque<ExercisePointer>) i.getSerializableExtra("QUEUE");
        q.add((ExercisePointer) v.getTag());
        newI.putExtra("QUEUE", (Serializable) q);
        ExercisesListActivity.this.startActivity(newI);
    }
}
