package me.ashimrajkonwar.thebinary;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import static me.ashimrajkonwar.thebinary.R.id.buttonPlay;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton buttonPlay;

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
    }

    @Override
    public void onClick(View v) {

        // Start game activity
        startActivity(new Intent(this, GameActivity.class));
    }
}
