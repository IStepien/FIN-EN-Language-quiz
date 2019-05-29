package com.example.languagequiz;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String[] questionWords = {"\"night\"", "\"wine\"", "\"pig\"", "\"snake\"", "\"computer\""};
    String[][] answers = {{"kuu", "öy", "silta"}, {"viini", "viina", "vesi"}, {"ihminen", "sika", "lautanen"}, {"käärme", "poika", "kahdeksantoista"}, {"kampa", "tiekokone", "ohje"}};
    String[] correctAnswers = {"öy", "viini", "sika", "käärme", "tiekokone"};

    TextView timeTextView;
    Button playButton;
    TextView finishTextView;
    TextView questionTextView;
    TextView scoreTextView;
    RadioGroup radioGroup;
    int questionNumber;
    int score;
    CountDownTimer countDownTimer;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        timeTextView = findViewById(R.id.timeTextView);
        finishTextView = findViewById(R.id.finishTextView);
        questionTextView = findViewById(R.id.questionTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        radioGroup = findViewById(R.id.radioGroup);


    }

    public void play(View view) {
        playButton.setVisibility(View.INVISIBLE);
        radioGroup.setVisibility(View.VISIBLE);
        finishTextView.setVisibility(View.INVISIBLE);
        questionNumber=0;
        score=0;
        setCountDownTimer();
        updateScore(0);
        setQuestion(questionNumber);
        setAnswers(questionNumber);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Button checked = findViewById(checkedId);


                if (checked.getText().equals(correctAnswers[questionNumber])) {

                    score++;
                    updateScore(score);

                    if (score == questionWords.length || questionNumber == questionWords.length - 1) {
                        reset();

                    }
                }

                if (questionNumber < questionWords.length - 1) {
                    questionNumber++;
                }

                setQuestion(questionNumber);
                setAnswers(questionNumber);


            }

        });


    }

    public void updateTime(int timeLeft) {
        timeTextView.setText(checkDigit(timeLeft) + "s");

    }

    public String checkDigit(int digit) {
        return digit <= 9 ? "0" + digit : String.valueOf(digit);
    }

    public void setQuestion(int questionNumber) {

        questionTextView.setText("Finnish word for\n" + questionWords[questionNumber]);


    }

    public void updateScore(int value) {
        scoreTextView.setText(String.valueOf(value) + "/" + questionWords.length);

    }

    public void setAnswers(int questionNumber) {

        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            String nextAnswer = answers[questionNumber][i];
            ((RadioButton) radioGroup.getChildAt(i)).setText(nextAnswer);
        }

    }

    public void setCountDownTimer() {
        countDownTimer = new CountDownTimer(60000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTime((int) millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {
                reset();

            }
        }.start();

    }

    public void reset() {
        countDownTimer.cancel();
        finishTextView.setText("your score:\n" + score + "/" + questionWords.length);
        updateScore(0);
        playButton.setVisibility(View.VISIBLE);
        finishTextView.setVisibility(View.VISIBLE);

    }


}
