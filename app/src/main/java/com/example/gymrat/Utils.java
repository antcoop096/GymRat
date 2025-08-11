package com.example.gymrat;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

/*
• This class has utility functions that are used
  multiple times in other files.
• This class exists to avoid code duplication and clutter. */
public class Utils {

    /*
    • Returns true if string satisfies the regular expression regex
      and false otherwise. */
    public static boolean isValid(String regex, String string){
        return Pattern.matches(regex, string);
    }

    /*
    • Returns the string input typed in view of viewId if it satisfies regex
      and null otherwise. */
    public static String getValidatedInput(Activity activity, int viewId, String fieldName, String regex){
        TextView view = activity.findViewById(viewId);
        String input = view.getText().toString(); //get the input from the view
        if (!isValid(regex, input)){ //check if valid
            Toast.makeText(activity, "Invalid input for " + fieldName + ".", Toast.LENGTH_SHORT).show();
            return null; //if not valid let user know and return null
        }
        return input; //return inputted string
    }

    /*
    • Converts minute to milliseconds.
    • Return int representing the result. */
    public static int minToMilliSec(String min){
        //first convert min to sec
        int seconds = (Integer.valueOf(min.substring(0, 1)) * 60) //minutes (left) side of :
                     + Integer.valueOf(min.substring(2));         //seconds (right) side of:
        return seconds * 1000;
        //then convert sec to millisec
    }
    /*
      • Displays the current Exercise, or the upcoming one after rest period.*/
    public static void displayExerciseInfo(Activity activity, Workout workout){
        Exercise currEx = workout.getCurrExercise().getExercise();
        int currSet = workout.getCurrSet();
        int setsPerEx = workout.getSetsPerEx();

        String exerciseInfo = "Exercise: " + currEx.getName() +
                              "\nWeight: ";
        if (currSet < setsPerEx - 1){ //if not on last set of Exercise, rep goal is 8-10
            exerciseInfo += currEx.getCurrWeight() +
                            "\nReps: 8-10";
        }
        else{ //otherwise rep goal is the goal rep of the goal weight
            exerciseInfo += currEx.getGoalWeight() +
                            "\nReps To Beat: " + currEx.getGoalReps();
        }
        exerciseInfo += "\nSets Completed: " + currSet + "/" + setsPerEx;

        TextView exerciseInfoView = activity.findViewById(R.id.exerciseInfo);
        exerciseInfoView.setText(exerciseInfo);
    }
}
