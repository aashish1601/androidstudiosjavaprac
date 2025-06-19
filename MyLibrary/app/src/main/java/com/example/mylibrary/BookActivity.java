package com.example.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    private TextView txtBookName, txtAuthor, txtPages, txtLongDesc;
    private Button btnAddToAlreadyRead, btnAddToWantToRead, btnAddToCurrentlyReading, btnAddToFavorites;

    private ImageView imgBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.parentActivityBook), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();

        Intent intent = getIntent();
        if (null != intent) {
            int bookId = intent.getIntExtra("bookId", -1);
            if (bookId != -1) {
                Book incommingBook = Utils.getInstance().getBookById(bookId);
                if (incommingBook != null) {
                    setData(incommingBook);

                    handleAlreadyRead(incommingBook);
                    handleWantToRead(incommingBook);
                    handleCurrentlyReading(incommingBook);
                    handleFavorite(incommingBook);

                }
            }
        }
    }

    private void handleFavorite(Book book) {
        ArrayList<Book> favoriteBooks = Utils.getAlreadyReadBooks();

        boolean alreadyfavorite = false;

        for (Book b: favoriteBooks) {
            if (b.getId() == book.getId()) {
                alreadyfavorite = true;
                break;
            }
        }

        if (alreadyfavorite) {
            btnAddToFavorites.setEnabled(false);
        } else {
            btnAddToFavorites.setOnClickListener(v -> {
                if (Utils.getInstance().AddToFavoriteBooks(book)) {
                    Toast.makeText(this, "Book Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookActivity.this, FavoriteBooksActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(this, "Something wrong Happened, try again", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void handleCurrentlyReading(Book book) {
        ArrayList<Book> currentlyReadingBooks = Utils.getAlreadyReadBooks();

        boolean alreadyCurrentlyReading = false;

        for (Book b: currentlyReadingBooks) {
            if (b.getId() == book.getId()) {
                alreadyCurrentlyReading = true;
                break;
            }
        }

        if (alreadyCurrentlyReading) {
            btnAddToCurrentlyReading.setEnabled(false);
        } else {
            btnAddToCurrentlyReading.setOnClickListener(v -> {
                if (Utils.getInstance().AddToCurrentlyReadingBooks(book)) {
                    Toast.makeText(this, "Book Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookActivity.this, CurrentlyReadingBooksActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(this, "Something wrong Happened, try again", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void handleWantToRead(Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getAlreadyReadBooks();

        boolean alreadyWantToRead = false;

        for (Book b: wantToReadBooks) {
            if (b.getId() == book.getId()) {
                alreadyWantToRead = true;
                break;
            }
        }

        if (alreadyWantToRead) {
            btnAddToWantToRead.setEnabled(false);
        } else {
            btnAddToWantToRead.setOnClickListener(v -> {
                if (Utils.getInstance().AddToWantToReadBooks(book)) {
                    Toast.makeText(this, "Book Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookActivity.this, WantToReadBooksActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(this, "Something wrong Happened, try again", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void handleAlreadyRead(final Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getAlreadyReadBooks();

        boolean alreadyRead = false;

        for (Book b: alreadyReadBooks) {
            if (b.getId() == book.getId()) {
                alreadyRead = true;
                break;
            }
        }

        if (alreadyRead) {
            btnAddToAlreadyRead.setEnabled(false);
        } else {
            btnAddToAlreadyRead.setOnClickListener(v -> {
                if (Utils.getInstance().AddToAlreadyReadBooks(book)) {
                    Toast.makeText(this, "Book Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookActivity.this, AlreadyReadBooksActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(this, "Something wrong Happened, try again", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void initViews() {
        txtBookName = findViewById(R.id.txtBookName);
        txtAuthor = findViewById(R.id.txtAuthorActivityBook);
        txtPages = findViewById(R.id.txtPages);
        txtLongDesc = findViewById(R.id.txtLongDesc);

        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToWantToRead = findViewById(R.id.btnAddToWantToRead);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToFavorites = findViewById(R.id.btnAddToFavorites);

        imgBook = findViewById(R.id.imageBookActivityBook);
    }

    private void setData(@NonNull Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtLongDesc.setText(book.getLongDesc());

        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(imgBook);
    }
}