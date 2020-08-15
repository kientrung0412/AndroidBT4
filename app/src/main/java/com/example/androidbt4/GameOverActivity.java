package com.example.androidbt4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnRePlay;
    private TextView tvScore;
    private int userScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        initViews();
    }

    private void initViews() {
        btnRePlay = findViewById(R.id.btn_re_play);
        tvScore = findViewById(R.id.tv_show_score);
        userScore = getIntent().getIntExtra(PlayActivity.EXTRA_SCORE, 0);
        btnRePlay.setOnClickListener(this);
        showScore();
    }

    private void showScore() {
        tvScore.setText(String.format("Điểm của bạn là: %s", userScore));
    }

    @Override
    public void onClick(View view) {
        this.finish();
        Intent intent = new Intent(this, PlayActivity.class);
        this.startActivity(intent);
    }
}