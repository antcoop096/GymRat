package com.example.gymrat;

import java.io.Serializable;
import java.util.Queue;

/*
• This class represents a Workout containing ExercisePointers (the exercises). */
public class Workout implements Serializable {

    Queue<ExercisePointer> exerciseQueue;
    ExercisePointer currExercise;
    int currSet = 0;
    int setsPerEx;
    int restTime;

    public Workout(Queue<ExercisePointer> exerciseQueue, int setsPerEx, int restTime){
        this.exerciseQueue = exerciseQueue;
        this.currExercise = this.exerciseQueue.peek();
        this.setsPerEx = setsPerEx;
        this.restTime = restTime;
    }

    public Queue<ExercisePointer> getExerciseQueue() {
        return exerciseQueue;
    }

    public ExercisePointer getCurrExercise() {
        return currExercise;
    }

    public int getCurrSet() {
        return currSet;
    }

    public int getSetsPerEx() {
        return setsPerEx;
    }

    public int getRestTime() {
        return restTime;
    }

    public void incSet() {
        this.currSet++;
    }

    /*
    • Removes current Exercise from exerciseQueue and makes
      next Exercise currExercise (if not queue is not empty). */
    public void nextExercise() {
        this.currSet = 0;
        this.exerciseQueue.poll();
        if (!this.exerciseQueue.isEmpty()){
            this.currExercise = this.exerciseQueue.peek();
        }
    }


}
