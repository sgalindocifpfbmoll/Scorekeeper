package com.example.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView scoreText1, scoreText2;
    private int score1, score2;
    private ImageButton score1Decrease, score1Increase, score2Decrease, score2Increase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreText1 = findViewById(R.id.score1);
        scoreText2 = findViewById(R.id.score2);
        score1 = 0; score2 = 0;

        //
        score1Decrease = findViewById(R.id.score1Decrease);
        score1Increase = findViewById(R.id.score1Increase);
        score2Decrease = findViewById(R.id.score2Decrease);
        score2Increase = findViewById(R.id.score2Increase);
        if (savedInstanceState != null) {
            score1 = savedInstanceState.getInt(scoreText1.getText().toString());
            score2 = savedInstanceState.getInt(scoreText2.getText().toString());
            //Set the score text views
            scoreText1.setText(String.valueOf(score1));
            scoreText2.setText(String.valueOf(score2));
        }
    }

    public void decreaseScore(View view){
        if (view.equals(scoreText1)){
            score1--;
            updateText(view, String.valueOf(score1));
        }else{
            score2--;
            updateText(view, String.valueOf(score2));
        }
    }

    public void increaseScore(View view){
        if (view.equals(scoreText1)){
            score1++;
            updateText(view, String.valueOf(score1));
        }else{
            score2++;
            updateText(view, String.valueOf(score2));
        }
    }

    private void updateText(View view, String score){
        TextView textView = (TextView)view;
        textView.setText(score);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Check if the correct item was clicked
        if(item.getItemId()==R.id.night_mode){
            AppCompatDelegate.getDefaultNightMode();
            //Get the night mode state of the app
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            //Set the theme mode for the restarted activity
            if(nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu from XML
        getMenuInflater().inflate(R.menu.main_menu, menu);
        //Change the label of the menu based on the state of the app
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//Save the scores
        outState.putInt(scoreText1.getText().toString(), score1);
        outState.putInt(scoreText2.getText().toString(), score2);
        super.onSaveInstanceState(outState);
    }
}