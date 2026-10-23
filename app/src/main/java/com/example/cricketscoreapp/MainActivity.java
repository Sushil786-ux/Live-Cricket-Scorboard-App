package com.example.cricketscoreapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView tvScore, tvOvers;
    private int runs = 0;
    private int wickets = 0;
    private int balls = 0;
    private int overs = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tvScore = findViewById(R.id.tvScore);
        tvOvers = findViewById(R.id.tvOvers);
        findViewById(R.id.btn1Run).setOnClickListener(v -> addRuns(1));
        findViewById(R.id.btn2Runs).setOnClickListener(v -> addRuns(2));
        findViewById(R.id.btn3Runs).setOnClickListener(v -> addRuns(3));
        findViewById(R.id.btn4Runs).setOnClickListener(v -> addRuns(4));
        findViewById(R.id.btn6Runs).setOnClickListener(v -> addRuns(6));

        // Wicket Button
        findViewById(R.id.btnWicket).setOnClickListener(v -> addWicket());

        // Wide / Extra Button (Adds 1 run without counting a legal delivery)
        findViewById(R.id.btnWide).setOnClickListener(v -> addExtraRun());

        // Reset Button
        findViewById(R.id.btnReset).setOnClickListener(v -> resetMatch());

    }

    private void resetMatch() {
        runs = 0;
        wickets = 0;
        balls = 0;
        overs = 0;
        updateScoreboard();
        Toast.makeText(this, "Scorecard Reset", Toast.LENGTH_SHORT).show();
    }

    private void addExtraRun() {
        runs += 1; // Extra run added, but legal ball count does not increment
        updateScoreboard();
        Toast.makeText(this, "1 Extra Run Added", Toast.LENGTH_SHORT).show();
    }

    private void addWicket() {
        if (wickets < 10) {
            wickets++;
            incrementBallCount();
            updateScoreboard();
        } else {
            Toast.makeText(this, "Innings Over - All Out!", Toast.LENGTH_SHORT).show();
        }
    }

    private void addRuns(int count) {
        runs += count;
        incrementBallCount();
        updateScoreboard();
    }

    private void incrementBallCount() {
        balls++;
        if (balls == 6) {
            overs++;
            balls = 0;
        }
    }

    private void updateScoreboard() {
        tvScore.setText(runs + "/" + wickets);
        tvOvers.setText("Overs: " + overs + "." + balls);
    }
}