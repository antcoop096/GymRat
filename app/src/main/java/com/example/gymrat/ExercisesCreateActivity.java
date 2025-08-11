package com.example.gymrat;

import static com.example.gymrat.Utils.*;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Toast;

/*
• Page for creating new Exercises.*/
public class ExercisesCreateActivity extends AppCompatActivity {
    /*
    • Setting up the ExercisesCreateActivity
      (the page for creating new exercises). */

    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercises_create);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //creating spinner (aka drop-down menu) for selecting muscle group targeted by exercise
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                Exercise.MUSCLE_NAMES
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),
                "Selected: " + selectedItem,
                Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle no selection
            }
        });
    }


    /*
    • Adds a new exercise to the static exerciseList in Exercise class.
    • This method runs when the "Create" button is pressed. */
    public void createExercise(View v){
        //getting and validating exercise fields:
        TextView nameView = findViewById(R.id.nameInput);
        String name = nameView.getText().toString();

        String muscle = spinner.getSelectedItem().toString();

        TextView equipmentView = findViewById(R.id.equipmentInput);
        String equipment = equipmentView.getText().toString();

        String currWeight = getValidatedInput(
                this,
                R.id.currWeight,
                "current weight",
                "^\\d+(\\.\\d+)?$"
        );
        if (currWeight == null) return; //would stop function here if input is not valid

        String goalWeight = getValidatedInput(
                this,
                R.id.goalWeight,
                "goal weight",
                "^\\d+(\\.\\d+)?$"
        );
        if (goalWeight == null) return;

        String repGoal = getValidatedInput(
                this,
                R.id.repGoal,
                "rep goal",
                "^\\d+(\\.\\d+)?$"
        );
        if (repGoal == null) return;

        //Creating new Exercise object with captured fields and adding to exerciseList:
        Exercise exercise = new Exercise(
                name, muscle, equipment,
                Float.valueOf(currWeight),
                Float.valueOf(goalWeight),
                Float.valueOf(repGoal)
        );
        int muscleIndex = Exercise.MUSCLE_INDICES.get(exercise.getMuscle());
        Exercise.exerciseList[muscleIndex].add(exercise);

        //Returning to previous page:
        Intent i = new Intent (this, ExercisesActivity.class);
        i.putExtra("IN_WORKOUT", false);
        ExercisesCreateActivity.this.startActivity(i);
    }

    /*
    • Launches the ExercisesActivity page.
    • This method runs when the "Previous" button is pressed. */
    public void launchExercisesPage(View v){
        Intent i = new Intent (this, ExercisesActivity.class);
        i.putExtra("IN_WORKOUT", false);
        ExercisesCreateActivity.this.startActivity(i);
    }


}

