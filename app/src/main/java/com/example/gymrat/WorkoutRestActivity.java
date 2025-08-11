package com.example.gymrat;

import static com.example.gymrat.Utils.*;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;

/*
• Page for showing a timer for a rest period of a workout.
• Also shows info regarding the weight and reps of the next Exercise.*/
public class WorkoutRestActivity extends AppCompatActivity {

    Intent i;
    Workout workout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout_rest);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        i = getIntent();
        workout = (Workout)i.getSerializableExtra("WORKOUT");

        //displaying next exercise of workout after rest and info associated with it:
        if (workout.getExerciseQueue().isEmpty()){
            ((TextView)findViewById(R.id.exerciseInfo)).setText("Done after rest!");
        }
        else{
            displayExerciseInfo(this, workout);
        }

        //setting up rest timer
        TextView timerView = findViewById(R.id.timer);
        CountDownTimer timer = new CountDownTimer(workout.getRestTime(),1000) {
            int secRemaining = workout.getRestTime() / 1000;
            @Override
            public void onTick(long millisUntilFinished) {

                int minutes = secRemaining / 60;
                int seconds = secRemaining % 60;
                String time = minutes + ":";

                if (seconds < 10){
                    time += "0";
                }

                time += seconds;
                timerView.setText(time);
                secRemaining--;

            }

            @Override
            public void onFinish() {
                Intent i;
                if (workout.getExerciseQueue().isEmpty()) { //if workout is done go to WorkoutComplete page
                    i = new Intent(WorkoutRestActivity.this, WorkoutCompleteActivity.class);
                }
                else{ //if workout is done go to WorkoutPlay page
                    i = new Intent(WorkoutRestActivity.this, WorkoutPlayActivity.class);
                }
                i.putExtra("WORKOUT", (Serializable) workout);
                WorkoutRestActivity.this.startActivity(i);
            }
        };

        timer.start();

    }
}