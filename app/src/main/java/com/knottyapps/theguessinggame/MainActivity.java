package com.knottyapps.theguessinggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private TextView directions;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //every class that is accessed by a user has an onCreate
        super.onCreate(savedInstanceState); //these 2 lines should ALWAYS be the first 2 lines ever.
        setContentView(R.layout.activity_main); //NEVER mess with these 2 lines

        startButton = findViewById(R.id.start_button); //R stands for resource.
        directions = findViewById(R.id.directions_textview);

        startListener();
    }

    private void startListener() {
        startButton.setOnClickListener(new View.OnClickListener() { //OnClickListener is a class built in to Android
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
    }

    private void startGame() {
        Intent playGame = new Intent(this, GameActivity.class);
        startActivity(playGame);
        GameActivity gameActivity = new GameActivity();
    }
}
