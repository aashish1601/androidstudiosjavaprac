package com.example.uibasics3;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.slider.RangeSlider;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private CheckBox checkboxIronman, checkboxLoki, checkboxThor;
    private RadioGroup rgMaritalStatus;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkboxIronman = findViewById(R.id.checkboxIronman);
        checkboxLoki = findViewById(R.id.checkboxLoki);
        checkboxThor = findViewById(R.id.checkboxThor);

        checkboxIronman.setOnCheckedChangeListener(this);
        checkboxLoki.setOnCheckedChangeListener(this);
        checkboxThor.setOnCheckedChangeListener(this);

        rgMaritalStatus = findViewById(R.id.rgMaritalStatus);

        progressBar = findViewById(R.id.progressBar);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    progressBar.incrementProgressBy(10);
                    SystemClock.sleep(500);
                }
            }
        });
        thread.start();

        rgMaritalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String strMaritalStatus;
                if (checkedId == R.id.rbMarried) {
                    strMaritalStatus = "Married";
                } else if (checkedId == R.id.rbSingle) {
                    strMaritalStatus = "Single";
                } else {
                    strMaritalStatus = "In A Relationship";
                }
                Toast.makeText(MainActivity.this, strMaritalStatus, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String movieName = buttonView.getText().toString();
        String strFinal = (isChecked ? "You Have Watched" : "You Have To Watch") + movieName;
        Toast.makeText(this, strFinal, Toast.LENGTH_SHORT).show();
    }
}