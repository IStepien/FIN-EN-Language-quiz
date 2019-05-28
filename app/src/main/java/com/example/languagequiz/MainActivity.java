package com.example.languagequiz;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String[] questionWords = {"\"night\"", "\"wine\"", "\"pig\"", "\"snake\"", "\"computer\""};


    TextView timeTextView;
    Button playButton;
    TextView finishTextView;
    TextView questionTextView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        timeTextView = findViewById(R.id.timeTextView);
        finishTextView = findViewById(R.id.finishTextView);
        questionTextView = findViewById(R.id.questionTextView);


        setQuestion();

    }

    public void play(View view) {
        playButton.setVisibility(View.INVISIBLE);

        CountDownTimer countDownTimer = new CountDownTimer(30000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTime((int) millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {
                playButton.setVisibility(View.VISIBLE);
                finishTextView.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    public void updateTime(int timeLeft) {
        timeTextView.setText(checkDigit(timeLeft) + "s");


    }

    public String checkDigit(int digit) {
        return digit <= 9 ? "0" + digit : String.valueOf(digit);
    }

    public void setQuestion() {
        int numberOfQuestions = questionWords.length;

        Random random = new Random();
        int nextQuestion = random.nextInt(numberOfQuestions);
        questionTextView.setText("Finnish word for\n" + questionWords[nextQuestion]);
    }
}
