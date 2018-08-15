package com.knottyapps.theguessinggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    private Button playAgainButton;
    private TextView correctNumberTextview;
    private TextView winOrLoseResultTextview;
    private ImageView winOrLoseResultImageview;
    protected Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        playAgainButton = findViewById(R.id.play_again_button);
        correctNumberTextview = findViewById(R.id.correct_number_textview);
        winOrLoseResultTextview = findViewById(R.id.results_output_textview);
        winOrLoseResultImageview = findViewById(R.id.winner_imageview);

        setListener();

        intent = getIntent();
        if(intent.hasExtra("ACTUAL_NUMBER")){
            setLosingData();
        }
        else{
            winner();
        }
    }

    private void setLosingData() {
        int actualNumber = intent.getIntExtra("ACTUAL_NUMBER", 0);
        winOrLoseResultTextview.setText(R.string.lost_message);
        correctNumberTextview.setText(getString(R.string.winning_number, actualNumber));
        correctNumberTextview.setVisibility(View.VISIBLE);
        winOrLoseResultTextview.setVisibility(View.VISIBLE);
        winOrLoseResultImageview.setImageDrawable(getDrawable(R.drawable.losing_sad_face));
    }

    private void setListener() {
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void winner(){
        winOrLoseResultTextview.setText(R.string.you_win_message);
        winOrLoseResultTextview.setVisibility(View.VISIBLE);
    }
}
