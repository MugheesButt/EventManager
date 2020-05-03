package com.muhammadanasashir.nascon;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawr;
    ImageButton menu;
    Button home ;
    Button events;
    Button register;
    Button notifications;
    Button memories;
    Button contactus;
    Button adminLogin;
    Button developers ;


    TextView description, date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        OneSignal.startInit(this)
                .inFocusDisplaying
                        (
                                OneSignal.OSInFocusDisplayOption.Notification
                        )
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(new MyNotificationOpenedHandler(this))
                .init();
        OneSignal.setExternalUserId("Nascon_App");

        drawr=findViewById(R.id.drwr);
        description = findViewById(R.id.Desc);
        date = findViewById(R.id.dateNaSCon);

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("MainEvent");

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

                drawr.closeDrawer(Gravity.LEFT);
            }
        });

        events = findViewById(R.id.id_btn_events);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Events.class));
                drawr.closeDrawer(Gravity.LEFT);
            }
        });

        register = findViewById(R.id.id_btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterStepOne.class));
                drawr.closeDrawer(Gravity.LEFT);
            }
        });

        notifications = findViewById(R.id.id_btn_notifications);
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Notifications.class));
                drawr.closeDrawer(Gravity.LEFT);
            }
        });

        memories = findViewById(R.id.id_btn_memories);
        memories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Memories.class));
                drawr.closeDrawer(Gravity.LEFT);
            }
        });

        contactus = findViewById(R.id.id_btn_contactus);
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ContactUs.class));
                drawr.closeDrawer(Gravity.LEFT);
            }
        });

        adminLogin = findViewById(R.id.id_btn_adminlogin);
        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this , AdminLogin.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                drawr.closeDrawer(Gravity.LEFT);
            }
        });

        developers = findViewById(R.id.id_btn_developers);
        developers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Developers.class));
                drawr.closeDrawer(Gravity.LEFT);
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