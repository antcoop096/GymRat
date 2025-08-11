package com.example.gymrat;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.content.Intent;

import java.io.Serializable;
import java.util.ArrayDeque;

/*
• Page for main menu.*/
public class MainActivity extends AppCompatActivity {

    /*
    • Setting up the main activity (the home page). */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /*
    • Launches the workout page.
    • This method runs when "Workout" button is pressed. */
    public void launchWorkoutPage(View v){
        Intent i = new Intent (this, WorkoutActivity.class);
        i.putExtra("QUEUE", (Serializable)(new ArrayDeque<ExercisePointer>()));
        MainActivity.this.startActivity(i);
    }

    /*
    • Launches the exercises page.
    • This method runs when "Exercises" button is pressed. */
    public void launchExercisesPage(View v){
        Intent i = new Intent (this, ExercisesActivity.class);
        i.putExtra("IN_WORKOUT", false);
        MainActivity.this.startActivity(i);
    }
}

