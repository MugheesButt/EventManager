package com.muhammadanasashir.nascon;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Notifications extends AppCompatActivity {

    DrawerLayout drawr;
    ImageButton menu;
    Button home;
    Button events;
    Button register;
    Button memories;
    Button notifications;
    Button contactus;
    Button adminLogin;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<String> myDataset ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        recyclerView = findViewById(R.id.id_rv_notifications);
        drawr=findViewById(R.id.drwr);
        menu=findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawr.isDrawerOpen(Gravity.LEFT))
                {
                    drawr.closeDrawer(Gravity.LEFT);
                }
                else
                {
                    drawr.openDrawer(Gravity.LEFT);
                }
            }
        });

        home = findViewById(R.id.id_btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Notifications.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                drawr.closeDrawer(Gravity.LEFT);
                //finish();
            }
        });

        events = findViewById(R.id.id_btn_events);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Notifications.this, Events.class));
                drawr.closeDrawer(Gravity.LEFT);
                finish();
            }
        });

        register = findViewById(R.id.id_btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Notifications.this, RegisterStepOne.class));
                drawr.closeDrawer(Gravity.LEFT);
                finish();
            }
        });

        notifications = findViewById(R.id.id_btn_notifications);
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawr.closeDrawer(Gravity.LEFT);
            }
        });

        memories = findViewById(R.id.id_btn_memories);
        memories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Notifications.this, Memories.class));
                drawr.closeDrawer(Gravity.LEFT);
                finish();
            }
        });

        contactus = findViewById(R.id.id_btn_contactus);
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Notifications.this, ContactUs.class));
                drawr.closeDrawer(Gravity.LEFT);
                finish();
            }
        });

        adminLogin = findViewById(R.id.id_btn_adminlogin);
        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Notifications.this, AdminLogin.class));
                drawr.closeDrawer(Gravity.LEFT);
                finish();
            }
        });

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        myDataset = new ArrayList<>();
        readNotifications();

    }

    private void readNotifications()
    {

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Notification");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myDataset.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    String notif = snapshot.getValue(String.class);
                    myDataset.add(0,notif);
                }

                mAdapter = new RVAdapter(myDataset, 0);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}