package com.example.recyclerviewexample;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView contactsRecyclerView;

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

        contactsRecyclerView = findViewById(R.id.contactsRecView);

        ArrayList<Contact> contacts = new ArrayList<>();

        contacts.add(new Contact(
                "Aarav Sharma",
                "aarav.sharma@email.com",
                "https://randomuser.me/api/portraits/men/10.jpg"
        ));

        contacts.add(new Contact(
                "Priya Verma",
                "priya.verma@email.com",
                "https://randomuser.me/api/portraits/women/21.jpg"
        ));

        contacts.add(new Contact(
                "Rohan Kapoor",
                "rohan.kapoor@email.com",
                "https://randomuser.me/api/portraits/men/35.jpg"
        ));

        contacts.add(new Contact(
                "Simran Kaur",
                "simran.kaur@email.com",
                "https://randomuser.me/api/portraits/women/44.jpg"
        ));

        contacts.add(new Contact(
                "Kabir Mehta",
                "kabir.mehta@email.com",
                "https://randomuser.me/api/portraits/men/52.jpg"
        ));

        contacts.add(new Contact(
                "Isha Singh",
                "isha.singh@email.com",
                "https://randomuser.me/api/portraits/women/60.jpg"
        ));

        ContactsRecViewAdapter adapter = new ContactsRecViewAdapter(this);
        adapter.setContacts(contacts);

        contactsRecyclerView.setAdapter(adapter);
        contactsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}