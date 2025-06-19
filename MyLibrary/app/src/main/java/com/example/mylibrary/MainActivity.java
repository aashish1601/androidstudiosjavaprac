package com.example.mylibrary;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnAllBooks, btnCurrentlyReading, btnAlreadyRead, btnWantToRead, btnFavorite, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.parentActivityBook), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnAllBooks = findViewById(R.id.btnSeeAll);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnWantToRead = findViewById(R.id.btnWantToRead);
        btnFavorite = findViewById(R.id.btnSeeFavorites);
        btnAbout = findViewById(R.id.btnAbout);

        btnAllBooks.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
            startActivity(intent);
        });

        btnAlreadyRead.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AlreadyReadBooksActivity.class);
            startActivity(intent);
        });

        btnWantToRead.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WantToReadBooksActivity.class);
            startActivity(intent);
        });

        btnFavorite.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FavoriteBooksActivity.class);
            startActivity(intent);
        });

        btnCurrentlyReading.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CurrentlyReadingBooksActivity.class);
            startActivity(intent);
        });

        btnAbout.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("About MyLibrary")
                    .setMessage("MyLibrary is a simple helper utility to manage your books collection. Want to learn more?")
                    .setPositiveButton("Visit Website", (dialog, which) -> {
                        // Launch WebsiteActivity
                        Intent intent = new Intent(this, WebsiteActivity.class);
                        startActivity(intent);
                    })
                    .setNegativeButton("Dismiss", (dialog, which) -> dialog.dismiss())
                    .show();
        });

        Utils.getInstance();
    }
}