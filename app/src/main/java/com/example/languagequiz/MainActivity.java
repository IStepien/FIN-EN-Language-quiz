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
    String[][] answers = {{"kuu", "öy", "silta"}, {"viini", "viina", "vesi"}, {"ihminen", "sika", "lautanen"}, {"käärme", "poika", "kahdeksantoista"}, {"tiekokone", "kampa", "ohje"}};

    TextView timeTextView;
    Button playButton;
    TextView finishTextView;
    TextView questionTextView;
    TextView scoreTextView;
    RadioGroup radioGroup;
    int questionNumber;

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

        questionTextView.setText("Finnish word for\n");
        updateScore(0);


    }

    public void play(View view) {
        playButton.setVisibility(View.INVISIBLE);
        setQuestion();
        setAnswers(questionNumber);
        setCountDownTimer();

    }

    public void updateTime(int timeLeft) {
        timeTextView.setText(checkDigit(timeLeft) + "s");


    }

    public String checkDigit(int digit) {
        return digit <= 9 ? "0" + digit : String.valueOf(digit);
    }

    public void setQuestion() {
        int numberOfQuestions = questionWords.length - 1;

        Random random = new Random();
        questionNumber = random.nextInt(numberOfQuestions);
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
}
