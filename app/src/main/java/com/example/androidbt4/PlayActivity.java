package com.example.androidbt4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class PlayActivity extends AppCompatActivity implements Runnable, View.OnClickListener {

    public static final String EXTRA_SCORE = "com.example.androidbt4.EXTRA_SCORE";

    private ImageButton btnTrue;
    private ImageButton btnFalse;
    private ImageButton btnRePlay;
    private TextView tvScore;
    private TextView tvTime;
    private TextView tvMatch;
    private int score = 0;
    private int time = 5;
    private int numberA;
    private int numberB;
    private int result;
    private boolean isTrue;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initViews();
        showAll();
    }

    private void initViews() {
        btnRePlay = findViewById(R.id.btn_re_play);
        btnFalse = findViewById(R.id.btn_false);
        btnTrue = findViewById(R.id.btn_true);
        tvTime = findViewById(R.id.tv_time);
        tvScore = findViewById(R.id.tv_score);
        tvMatch = findViewById(R.id.tv_match);
        btnTrue.setOnClickListener(this);
        btnFalse.setOnClickListener(this);
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (time > 0) {
            try {
                Thread.sleep(1000);
                time--;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showTime();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gameOver();
    }

    @Override
    public void onClick(View view) {
        int tag = Integer.parseInt(view.getTag().toString());
        if (isTrue == (tag > 0)) {
            score++;
            showAll();
        } else {
            time = 0;
        }
    }

    private void createMatch() {
        time = 5;
        Random random = new Random();
        numberA = random.nextInt(100);
        numberB = random.nextInt(100);
        result = numberA + numberB + random.nextInt(4);
        isTrue = (result == numberA + numberB);
    }

    private void showAll() {
        createMatch();
        showTime();
        tvMatch.setText(String.format("%s + %s = %s", numberA, numberB, result));
        tvScore.setText(String.format("Điểm số của bạn: %s", score));
    }

    private void showTime() {
        tvTime.setText(String.format("Thời gian còn lại: %s", time));
    }

    private void gameOver() {
        this.finish();
        Intent intent = new Intent(this, GameOverActivity.class);
        intent.putExtra(EXTRA_SCORE, score);
        this.startActivity(intent);

    }
}