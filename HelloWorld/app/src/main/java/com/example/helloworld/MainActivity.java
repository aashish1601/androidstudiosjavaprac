package com.example.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onBtnClick (View view) {

        EditText edtTxtFirstName = findViewById(R.id.editTextFirstName);
        EditText edtTxtLastName = findViewById(R.id.editTextLastName);
        EditText edtTxtEmail = findViewById(R.id.editTextEMail);

        TextView txtFirstName = findViewById(R.id.textFirstName);
        TextView txtLastName = findViewById(R.id.textLastName);
        TextView txtEmail = findViewById(R.id.textEmail);

        String firstNamePlaceholder = "First Name: " + edtTxtFirstName.getText().toString();
        String lastNamePlaceholder = "Last Name: " + edtTxtLastName.getText().toString();
        String emailPlaceHolder = "Email: " + edtTxtEmail.getText().toString();

        txtFirstName.setText(firstNamePlaceholder);
        txtLastName.setText(lastNamePlaceholder);
        txtEmail.setText(emailPlaceHolder);
    }
}