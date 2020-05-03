package com.muhammadanasashir.nascon;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onesignal.OneSignal;

public class AdminHome extends AppCompatActivity {

    EditText date,description;
    Button logout;
    DrawerLayout drawr;
    ImageButton menu;
    Button home ;
    Button events;
    Button register;
    Button notifications;
    Button memories;
    Button Save;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_adminhome_edit);

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

        logout = findViewById(R.id.id_btn_adminlogin);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(AdminHome.this , AdminLogin.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }
        });

        home = findViewById(R.id.id_btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawr.closeDrawer(Gravity.LEFT);
            }
        });

        events = findViewById(R.id.id_btn_events);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, AdminEvents.class));
                drawr.closeDrawer(Gravity.LEFT);
            }
        });

        register = findViewById(R.id.id_btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, AdminRegistrations.class));
                drawr.closeDrawer(Gravity.LEFT);
            }
        });

        notifications = findViewById(R.id.id_btn_notifications);
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, AdminNotifications.class));
                drawr.closeDrawer(Gravity.LEFT);
            }
        });

        memories = findViewById(R.id.id_btn_memories);
        memories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, AdminMemories.class));
                drawr.closeDrawer(Gravity.LEFT);
            }
        });

        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("MainEvent");

        date=findViewById(R.id.dateNaSCon);
        description=findViewById(R.id.DescriptionNascon);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                MainEvent mainEvent = dataSnapshot.getValue(MainEvent.class);
                date.setText(mainEvent.getDate());
                description.setText(mainEvent.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Save = findViewById(R.id.SaveHomePage);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainEvent mainEvent = new MainEvent(date.getText().toString(),description.getText().toString());
                dbRef.setValue(mainEvent);
                Toast.makeText(AdminHome.this , "Updated" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    boolean doubleBackToExitPressedOnce = false;
    private static long back_pressed;
    @Override
    public void onBackPressed(){

        if (back_pressed + 2000 > System.currentTimeMillis())
        {
            super.onBackPressed();
            moveTaskToBack(true);
            finish();
        }
        else
        {
            Toast.makeText(getBaseContext(), "Press back again to exit!", Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
}
