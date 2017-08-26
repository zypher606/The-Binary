package me.ashimrajkonwar.thebinary;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import static me.ashimrajkonwar.thebinary.R.id.buttonPlay;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton buttonPlay;
    private String playerScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set orientation to landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // get the button
        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);

        buttonPlay.setOnClickListener(this);
        playerScore = getIntent().getStringExtra("PLAYER_SCORE");
        if (!(playerScore==null)) {
            TextView textView = (TextView) findViewById(R.id.score);
            textView.setText("Score: " + playerScore);
        }
    }

    @Override
    public void onClick(View v) {

        // Start game activity
        startActivity(new Intent(this, GameActivity.class));
    }
}
