package com.example.gymrat;

import java.io.Serializable;

/*
• This class acts as a pointer to an Exercise object.
• This class exists because when transferring a Workout object
  from activity to activity, a deep copy of the Workout's Exercises
  are made, which are not stored in Exercise.exerciseList, so we
  wouldn't be modifying the correct Exercises.
• This allows us to store the location of an Exercise in the
  Exercise.exerciseList, so we modify the Exercise in Exercise.exerciseList
  rather than just a copy.*/
public class ExercisePointer implements Serializable {
    /*
    • Fields for an ExercisePointer instance.
    • These are the indexes of the Exercise in Exercise.exerciseList. */
    public int muscleIndex;
    public int exerciseIndex;

    public ExercisePointer(int muscleIndex, int exerciseIndex) {
        this.muscleIndex = muscleIndex;
        this.exerciseIndex = exerciseIndex;

    }

    /*
    • Accesses the Exercise object in Exercise.exerciseList
      pointed to by this ExercisePointer object. */
    public Exercise getExercise() {
        return Exercise.exerciseList[muscleIndex] //List<Exercise> of all exercises for a muscle
               .get(exerciseIndex);               //the specific Exercise in that list
    }
}