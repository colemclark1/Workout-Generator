package com.example.android.workoutgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);


        /**
         * This creates the string that the user selected in the first equipment drop down menu
         */
        // The string is saved to selection1 variable
        Intent intent = getIntent();
        String equipSelection1 = intent.getStringExtra("EquipSelection1");
        String movementSelection1 = intent.getStringExtra("MovementSelection1");



        /**
         * This creates a full array of all the workouts
         */
        final ArrayList<Workout> workouts = new ArrayList<>();
        workouts.add(new Workout("5 rounds for time of:\n" + "Run 400 meters\n" + "15 thrusters", "Men: 95 lb.\nWomen: 65 lb.", "FT"));
        workouts.add(new Workout("15 min Thrusters AMRAP:\n10 Burpees\n10 Sit ups\10 Hand Release Push ups", "AMRAP"));
        workouts.add(new Workout (" 3 rounds for time of:\n" + "50 GHD sit-ups\n" + "25 Dumbbell curls and Thrusters", "FT"));
        workouts.add(new Workout ("3 Rounds For Time:\nRun 800m\n50 Air Squats", "FT"));
        workouts.add(new Workout ("10 Rounds For Time:\n10 Pushups\n10 Sit ups\n10 Squats ", "FT"));
        workouts.add(new Workout("For Time:\n200 Air Squats", "FT"));
        workouts.add(new Workout("5 Rounds For Time:\nRun 200m\n10 Squats\n10 Push Ups", "FT"));
        workouts.add(new Workout("AMRAP in 20 minutes:\n5 Pushups\n10 Situps\n15 Squats", "FT"));
        workouts.add(new Workout("10 min EMOM\nEven Minutes: 20 KB swings\nOdd minutes: 12 burpees", "EMOM"));


        /**
         * This removes any workout that does not contain the equipment in equipSelection1
         * and also removes any workouts that does not contain the movement in movementSelection1
         * If both variables are set to "Any", then the arraylist will not be filtered
         */
        if (!equipSelection1.equals("Any") && !movementSelection1.equals("Any")) {
            int i = 0;
            while (i < workouts.size()) {
                String currentWorkout = workouts.get(i).getWorkoutDesc();
                if (!currentWorkout.contains(equipSelection1) ||
                        !currentWorkout.contains(movementSelection1)) {
                    workouts.remove(i);
                } else {
                    ++i;
                }
            }
        }
        else if (equipSelection1.equals("Any") && !movementSelection1.equals("Any")){
            int i = 0;
            while (i < workouts.size()) {
                String currentWorkout = workouts.get(i).getWorkoutDesc();
                if (!currentWorkout.contains(movementSelection1)) {
                    workouts.remove(i);
                } else {
                    ++i;
                }
            }
        }
        else if (!equipSelection1.equals("Any") && movementSelection1.equals("Any")){
            int i = 0;
            while (i < workouts.size()) {
                String currentWorkout = workouts.get(i).getWorkoutDesc();
                if (!currentWorkout.contains(equipSelection1)) {
                    workouts.remove(i);
                } else {
                    ++i;
                }
            }
        }

        Collections.shuffle(workouts);


        WorkoutAdapter adapter = new WorkoutAdapter(this, workouts);

        GridView gridView = (GridView) findViewById(R.id.list);


        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(WorkoutActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WorkoutActivity.this, DetailsActivity.class);
                Workout detailWorkout = workouts.get(position);
                intent.putExtra("Workout", detailWorkout);
                startActivity(intent);
            }
        });

    }


}


