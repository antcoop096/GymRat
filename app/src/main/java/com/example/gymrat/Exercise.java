package com.example.gymrat;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/*
• This class represents an Exercise.
• Exercise objects can be in a Workout, and also stores date regarding
  the user's weight progress on that Exercise across all workouts. */
public class Exercise implements Serializable{

    /*
    • Utilizing a static array of Arraylists to store all the possible
      exercises a user can select from. */
    static List<Exercise>[] exerciseList;
    static{
        exerciseList = new ArrayList[18];
        for (int i = 0; i < 18; i++) {
            exerciseList[i] = new ArrayList<Exercise>();
        }
    }

    /*
    • Utilizing a static HashMap to quickly identify the index of a specific list
      of exercises for a specific muscle group. */
    static final String[] MUSCLE_NAMES = {"Biceps - Long", "Biceps - Short", "Biceps - Brachialis",
                                          "Back - Upper", "Back - Mid"," Back - Lower",
                                          "Triceps - Long", "Triceps - Medial", "Triceps - Lateral",
                                          "Chest - Upper", "Chest - Mid", "Chest - Lower",
                                          "Shoulders - Front", "Shoulders - Lateral", "Shoulders - Rear",
                                          "Abs - Upper", "Abs - Lower", "Abs - Obliques"};

    static final Map<String, Integer> MUSCLE_INDICES = new HashMap<>(){{
        for (int i = 0; i < MUSCLE_NAMES.length; i++){
            put(MUSCLE_NAMES[i], i);
        }
    }};



    /*
    • Fields for an Exercise instance. */
    String name;
    String muscle;
    String equipment;
    float currWeight;
    float goalWeight;
    float goalReps;

    /*
    • Constructor for an Exercise instance. */
    public Exercise(String name, String muscle, String equipment,
                    float currWeight, float goalWeight, float goalReps){
        this.name = name;
        this.muscle = muscle;
        this.equipment = equipment;
        this.currWeight = currWeight;
        this.goalWeight = goalWeight;
        this.goalReps = goalReps;
    }

    /*
    • Getters and setters for each attribute in Exercise class. */

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getMuscle(){
        return this.muscle;
    }

    public void setMuscle(String muscle){
        this.muscle = muscle;
    }

    public String getEquipment(){
        return this.equipment;
    }

    public void setEquipment(String equipment){
        this.equipment = equipment;
    }

    public float getCurrWeight(){
        return this.currWeight;
    }

    public void setCurrWeight(float currWeight){
        this.currWeight = currWeight;
    }

    public float getGoalWeight(){
        return this.goalWeight;
    }

    public void setGoalWeight(float goalWeight){
        this.goalWeight = goalWeight;
    }

    public float getGoalReps(){
        return this.goalReps;
    }

    public void setGoalReps(float goalReps){
        this.goalReps = goalReps;
    }

}
