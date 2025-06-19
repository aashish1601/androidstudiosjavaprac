package com.example.uibasics2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtHello;
    private EditText edtTextName;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnButton) {
            Toast.makeText(this, "Button Pressed", Toast.LENGTH_SHORT).show();
            txtHello.setText("Hello " + edtTextName.getText().toString());
        } else if (v.getId() == R.id.edtTxtName) {
            Toast.makeText(this, "Type Your Name", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnButton = findViewById(R.id.btnButton);
        btnButton.setOnClickListener(this);

        txtHello = findViewById(R.id.txtHello);

        edtTextName = findViewById(R.id.edtTxtName);
        edtTextName.setOnClickListener(this);

    }

}