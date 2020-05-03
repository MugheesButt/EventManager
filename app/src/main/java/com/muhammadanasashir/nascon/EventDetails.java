package com.muhammadanasashir.nascon;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EventDetails extends AppCompatActivity {
    DrawerLayout drawr;
    ImageButton menu;
    Button home;
    Button events;
    Button register;
    Button notifications;
    Button memories;
    Button contactus;
    Button adminLogin;
    Button btn_register;

    DatabaseReference dbRef ;

    TextView event_name , details , date , start_time , end_time , fee , first , second ;
    String name ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        event_name = findViewById(R.id.name);
        details = findViewById(R.id.EventDetails);
        date = findViewById(R.id.Date);
        start_time = findViewById(R.id.StartTime);
        end_time = findViewById(R.id.EndTime);
        fee = findViewById(R.id.Fee);
        first = findViewById(R.id.First);
        second = findViewById(R.id.Second);

        name = getIntent().getStringExtra("EventName");
        event_name.setText(name);

        showEvent();

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
                startActivity(new Intent(EventDetails.this, MainActivity.class));
                finish();
            }
        });

        events = findViewById(R.id.id_btn_events);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventDetails.this, Events.class));
                finish();
            }
        });

        register = findViewById(R.id.id_btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventDetails.this, RegisterStepOne.class));
                finish();
            }
        });

        memories = findViewById(R.id.id_btn_memories);
        memories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventDetails.this, Memories.class));
                finish();
            }
        });

        notifications = findViewById(R.id.id_btn_notifications);
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventDetails.this, Notifications.class));
                finish();
            }
        });

        contactus = findViewById(R.id.id_btn_contactus);
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventDetails.this, ContactUs.class));
                finish();
            }
        });

        adminLogin = findViewById(R.id.id_btn_adminlogin);
        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventDetails.this, AdminLogin.class));
                finish();
            }
        });

        btn_register = findViewById(R.id.id_btn_register_page);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventDetails.this, RegisterStepOne.class));
                finish();
            }
        });
    }

    private void showEvent() {

        dbRef = FirebaseDatabase.getInstance().getReference("Events").child(name);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                EventNode eventNode = dataSnapshot.getValue(EventNode.class);
                assert eventNode != null;
                details.setText(eventNode.getDetails());
                date.setText(eventNode.getDate());
                start_time.setText(eventNode.getStart_time());
                end_time.setText(eventNode.getEnd_time());
                fee.setText("Rs." + String.valueOf(eventNode.getFee()));
                first.setText("Rs." + String.valueOf(eventNode.getFirst()));
                second.setText("Rs." + String.valueOf(eventNode.getSecond()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}