#### Project Name: GymRat
#### Author: Anthony Evan Cooper
#### GitHub: https://github.com/antcoop096<br><br>
## Purpose of App:

The purpose of this app is to help streamline workouts according to my
personal workout style in a way that makes the process of working out and
tracking progress smooth and efficient. This app can also show all saved
exercises and information regarding user progress for each particular exercise.

## Understanding My Workout Style:

Usually, I'd lift 2-3 times per week. Each week would contain at least one
push day and one pull day:<br>
* Push: Chest, Triceps, Shoulders
* Pull: Back, Biceps, Abs<br>

Each muscle group (e.g. Biceps, Shoulders, etc.) has 3 different parts. Every
workout, I would make sure to do one exercise for each of the three parts of
each muscle group I focus on that day, comprising of 9 total exercises per workout. For example, a push day would look like this:<br>
* Chest
  * 1 exercise for upper chest
  * 1 exercise for mid chest
  * 1 exercise for lower chest
* Triceps
  * 1 exercise for long head of triceps
  * 1 exercise for medial head of triceps
  * 1 exercise for lateral head of triceps
* Shoulders
  * 1 exercise for front delt of shoulders
  * 1 exercise for lateral delt of shoulders
  * 1 exercise for rear delt of shoulders<br><br>
    (9 total exercises for that workout)

For example, a pull day would look like this:
* Back
  * 1 exercise for upper back
  * 1 exercise for mid back
  * 1 exercise for lats (lower-ish back)
* Biceps
  * 1 exercise for long head of bicep
  * 1 exercise for short head of bicep
  * 1 exercise for brachialis of bicep
* Abs
  * 1 exercise for upper abs
  * 1 exercise for lower abs
  * 1 exercise for obliques (side of abs)<br><br>
    (9 total exercises for that workout)

For each individual exercise, I would do 3 total sets:
* set 1: use weight I can do 8-10 reps with
* set 2: use weight I can do 8-10 reps with
* set 3: use the next possible heavier weight and
  try to beat the number of reps I could
  previously do with that weight

For example, if for the cable chest press exercise I can do 8-10 reps with 30lbs
each side and I can do 5 reps with 32.5lbs each side (the next heavier weight), then my sets would look like:
* set 1: 30lbs for 8-10 reps
* set 2: 30lbs for 8-10 reps
* set 3: 32.5lbs, trying to get over 5 reps

After I can get to (usually) 10 reps using the heavier weight in the third set,
that heavier weight becomes the weight I use for sets 1 and 2 for 8-10 reps (for next time I do the exercise in a future workout, assuming the exercise isn't done again later in the current workout).
Then the next possible heavier weight above *that* would become the new weight for the third set
(in the example above, the next weight above 32.5 lbs would be 35 lbs).

To track my progress, I would keep a database of all the exercises I do (using just a
google doc), and next to the name of each exercise would be a certain notation used for tracking the weight and rep progress of that exercise:
a->b(c), where:
* a = weight I can do that exercise for 8-10 reps
* b = the next possible heavier weight above 'a'
* c = the amount of reps I'm able to do the exercise
  with 'b' lbs, and is thus the rep goal to beat<br><br>

I would use this notation as a reference to follow during my workout so I know what weights to use
  and how many reps to do for every exercise I do.

## How To Use The App (Tour Guide)

**1.** After opening the app, you will be shown the main menu. Click on "Exercises." Here you will see all the possible muscle groups that the app
can store exercises for. Clicking on a muscle group (e.g. "Biceps") will open another
page showing the three different parts of that muscle group (e.g. the long head, short
head, and brachialis of the biceps). Clicking on a muscle part will take you to your saved
exercises that target that specific part of the muscle, along with information regarding
your progress.

**2.** Pressing "previous" twice to go back to the "Exercises" page, you will see an "Add Exercise"
button. Clicking on it will take you to a form to fill out regarding information for the new
exercise you want to the exercise database as shown in **(1.)**. The fields include:
* name of the exercise
* type of equipment to use for exercise (e.g. dumbbells, cables, etc.)
* the muscle part (e.g. "long head" of biceps) the exercise targets
* weight it can be done in 8-10 reps
* next possible heavier weight (or other goal) for final set of exercise
* number of reps that can be performed with that heavier weight for final set<br>

Click "Create" to add the exercise to the database. You will be able to see the name
and info of the exercise just created by repeating **(1.)**, i.e. by going to the "Exercise" page
and clicking to the targeted muscle group and part.<br><br>
**3.** After adding a preferred number of exercises in the database, a workout can be started by clicking "Workout" in the main menu. It will take you to a form that will ask for:

* what exercises you want in your workout (the order added is the order they will be done in the workout)
* number of sets per exercise (the last set will always be the set in which you use the next heavier weight)
* rest time in between sets (in the format of 00:00)<br>

Clicking "Start" will start the workout. Information will always be displayed on the screen such as:<br>

* the exercise you are currently doing
* the number of sets you have left for that exercise
* the weight you should be doing in the current set
* the number of reps you should aim for in the current set<br>

After completing your set click on "Set Completed" and, if not having just completed the last set
of the exercise, the timer for your rest period before the next set will start. If you have completed
the last set of an exercise, you will be taken to a form that asks for info regarding your weight and/or
rep progression of the exercise. Some tips are:<br>

* if you have not progressed in the last set, leave both inputs blank
* if you have progressed in the last set in reps but not weight, fill in the reps
  input and just leave the weight input blank
* if you were able to get to 10 reps on the last set (or any other rep goal),
  fill in the weight input and just leave the rep input blank (this will reset goal reps of the last set of exercise to 0,
  since a new weight for doing the exercise for 8-10 reps has been reached, and that new weight will start to be used next time you do the exercise in the future)
* while uncommon, it is completely acceptable to fill in both inputs<br>

After filling out this form (if necessary), the next exercise will begin (if any).<br>

**4.** After completing all the exercises and after the final rest period, a screen will pop up
saying that you finished the workout. Click on the button that says "Home Page," to go
back to the main menu. You can repeat step **(1.)** again to see your updated progress on the
exercises you had just did in the workout.



   