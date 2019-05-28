package com.example.languagequiz;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timeTextView;
    Button playButton;
    TextView finishTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        timeTextView = findViewById(R.id.timeTextView);
        finishTextView = findViewById(R.id.finishTextView);
        TextView scoreTextView = findViewById(R.id.scoreTextView);


    }
    public void play(View view){
        playButton.setVisibility(View.INVISIBLE);

        CountDownTimer countDownTimer = new CountDownTimer(30000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTime((int) millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
            playButton.setVisibility(View.VISIBLE);
            finishTextView.setVisibility(View.VISIBLE);
            }
        }.start();

    }
    public void updateTime(int timeLeft){
        timeTextView.setText(checkDigit(timeLeft)+"s");


    }
    public String checkDigit(int digit){
        return digit<=9 ? "0"+digit: String.valueOf(digit);
    }
}
