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

public class Memories extends AppCompatActivity {
    DrawerLayout drawr;
    ImageButton menu;
    Button home;
    Button events;
    Button register;
    Button memories;
    Button notifications;
    Button contactus;
    Button adminLogin;
    Button developers;

    RecyclerView memories_view ;
    ArrayList<String> memories_links ;
    DatabaseReference dbRef ;
    RecyclerView.Adapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memories);

        memories_view = findViewById(R.id.rv_memories);
        memories_view.setHasFixedSize(true);
        memories_view.setLayoutManager(new LinearLayoutManager(this));

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
                startActivity(new Intent(Memories.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                drawr.closeDrawer(Gravity.LEFT);
            }
        });

        events = findViewById(R.id.id_btn_events);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Memories.this, Events.class));
                drawr.closeDrawer(Gravity.LEFT);
                finish();
            }
        });

        register = findViewById(R.id.id_btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Memories.this, RegisterStepOne.class));
                drawr.closeDrawer(Gravity.LEFT);
                finish();
            }
        });

        notifications = findViewById(R.id.id_btn_notifications);
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Memories.this, Notifications.class));
                drawr.closeDrawer(Gravity.LEFT);
                finish();
            }
        });

        memories = findViewById(R.id.id_btn_memories);
        memories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawr.closeDrawer(Gravity.LEFT);
            }
        });

        contactus = findViewById(R.id.id_btn_contactus);
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Memories.this, ContactUs.class));
                drawr.closeDrawer(Gravity.LEFT);
                finish();
            }
        });

        adminLogin = findViewById(R.id.id_btn_adminlogin);
        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Memories.this, AdminLogin.class));
                drawr.closeDrawer(Gravity.LEFT);
                finish();
            }
        });

        developers = findViewById(R.id.id_btn_developers);
        developers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Memories.this, Developers.class));
                drawr.closeDrawer(Gravity.LEFT);
            }
        });

        memories_links = new ArrayList<>();
        readMemories();
    }


    private void readMemories() {

        dbRef = FirebaseDatabase.getInstance().getReference("Memories");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                memories_links.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    String link = snapshot.getValue(String.class);
                    memories_links.add(link);
                }

                adapter = new RVAdapter(memories_links, 1);
                memories_view.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}