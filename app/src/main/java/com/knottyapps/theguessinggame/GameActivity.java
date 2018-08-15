package com.knottyapps.theguessinggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private TextView clueTextview;
    private Button guessButton;
    private EditText guess;
    private int generatedNumber;
    private int numberOfGuesses = 0;
    private int MAX_GUESS_COUNT = 4;
    Intent resultsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        clueTextview = findViewById(R.id.clue_textview);
        guessButton = findViewById(R.id.submit_guess_button);
        guess = findViewById(R.id.user_guess_editText);

        setListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        generatedNumber = (int) (Math.ceil(Math.random() * 100));
        numberOfGuesses = 0;
        clueTextview.setVisibility(View.INVISIBLE);
        guess.setText("");
        guess.requestFocus();
    }

    @Override
    public void onBackPressed() {
    }

    private void setListener() {
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (guess.getText().toString().isEmpty()) {
                    Toast.makeText(GameActivity.this, "I can't read your mind.", Toast.LENGTH_SHORT).show();
                    guess.requestFocus();
                } else {
                    int userGuess = Integer.parseInt(guess.getText().toString()); //we have to parse everything to integer from string input
                    checkGuess(userGuess);
                }
            }
        });
    }

    private void checkGuess(int userGuess) { //parameter prevents method from running without any input from user

        if (userGuess == generatedNumber) {
            Intent winner = new Intent(this, ResultsActivity.class);
            startActivity(winner);
        } else if (numberOfGuesses == MAX_GUESS_COUNT) {
            Intent loser = new Intent(this, ResultsActivity.class);
            loser.putExtra("ACTUAL_NUMBER", generatedNumber);
            startActivity(loser);
        } else if (userGuess < generatedNumber) {
            clueTextview.setText(R.string.too_low_message);
            clueTextview.setVisibility(View.VISIBLE);
            guess.setText("");
            guess.requestFocus();
            numberOfGuesses++;
        } else if (userGuess > 100) {
            clueTextview.setText(R.string.outside_range_message);
            clueTextview.setVisibility(View.VISIBLE);
            guess.setText("");
            guess.requestFocus();
        } else if (userGuess > generatedNumber) {
            clueTextview.setText(R.string.too_high_message);
            clueTextview.setVisibility(View.VISIBLE);
            guess.setText("");
            guess.requestFocus();
            numberOfGuesses++;
        }
    }
}
