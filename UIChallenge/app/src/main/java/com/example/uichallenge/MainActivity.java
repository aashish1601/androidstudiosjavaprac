package com.example.uichallenge;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtTxtName, edtTxtEmail, edtTxtPassword, edtTxtRePassword;
    private CheckBox checkBox;
    private RadioGroup radGrpGender;
    private Spinner sprCountry;
    private Button btnRegister;

    private final ArrayList<String> countries = new ArrayList<>();

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

        findViews();
        setupSpinner();
        setupRegisterButton();
    }

    // region View Binding
    private void findViews() {
        edtTxtName = findViewById(R.id.edtTxtName);
        edtTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTxtPassword = findViewById(R.id.edtTxtPassword);
        edtTxtRePassword = findViewById(R.id.edtTxtNameRePassword);
        checkBox = findViewById(R.id.checkbox);
        radGrpGender = findViewById(R.id.radGrpGender);
        sprCountry = findViewById(R.id.spnrCountry);
        btnRegister = findViewById(R.id.btnRegister);
    }
    // endregion

    // region Spinner Setup
    private void setupSpinner() {
        countries.add("India");
        countries.add("Pakistan");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                countries
        );
        sprCountry.setAdapter(adapter);
    }
    // endregion

    // region Register Logic
    private void setupRegisterButton() {
        btnRegister.setOnClickListener(v -> {
            if (!isFormValid()) return;

            Toast.makeText(MainActivity.this, "Registration Complete!!", Toast.LENGTH_SHORT).show();
            printUserInfo();
        });
    }

    private boolean isFormValid() {
        if (isEmpty(edtTxtName) || isEmpty(edtTxtEmail) || isEmpty(edtTxtPassword) || isEmpty(edtTxtRePassword)) {
            showToast("Please Fill All the Fields");
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(edtTxtEmail.getText().toString()).matches()) {
            showToast("Please enter a valid email");
            return false;
        }

        if (!edtTxtPassword.getText().toString().equals(edtTxtRePassword.getText().toString())) {
            showToast("Passwords Don't Match");
            return false;
        }

        if (isRadioGroupEmpty(radGrpGender)) {
            showToast("Please Select Gender");
            return false;
        }

        if (!checkBox.isChecked()) {
            showToast("Please Agree to Agreement");
            return false;
        }

        return true;
    }

    private void printUserInfo() {
        RadioButton selectedGender = findViewById(radGrpGender.getCheckedRadioButtonId());

        System.out.println("User Info Successfully Captured");
        System.out.println("Name: " + edtTxtName.getText().toString());
        System.out.println("Email: " + edtTxtEmail.getText().toString());
        System.out.println("Password: ******"); // For safety
        System.out.println("Gender: " + selectedGender.getText().toString());
        System.out.println("Country: " + sprCountry.getSelectedItem().toString());
    }
    // endregion

    // region Helpers
    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private boolean isRadioGroupEmpty(RadioGroup group) {
        return group.getCheckedRadioButtonId() == -1;
    }
    // endregion
}