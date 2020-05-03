package com.muhammadanasashir.nascon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

public class AdminNotifications extends AppCompatActivity {

    DrawerLayout drawr;
    ImageButton menu;
    Button events;
    Button register;
    Button home;
    Button memories;
    Button notifications;
    Button post;
    EditText Notif_box ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhome);

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

        Notif_box = findViewById(R.id.notification_box);

        post = findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Notification").push();
                if (!Notif_box.getText().toString().equals(""))
                {
                    dbRef.setValue(Notif_box.getText().toString());
                    //Toast.makeText(AdminNotifications.this , "Notification posted successfully!" , Toast.LENGTH_LONG).show();

                    // OneSignal Push Notification

                    try {
                        OneSignal.postNotification(new JSONObject("{'contents': {'en':'" +
                                        Notif_box.getText().toString() +
                                        "'}, 'headings':{'en':'" + "New Announcement" + "'}, 'include_external_user_ids': ['Nascon_App']}"),
                                new OneSignal.PostNotificationResponseHandler() {
                                    @Override
                                    public void onSuccess(JSONObject response) {
                                        Log.d("response", response.toString());
                                        Toast.makeText(AdminNotifications.this, "Notification Sent", Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onFailure(JSONObject response) {
                                        Log.d("response", response.toString());
                                        //Toast.makeText(AdminNotifications.this, "Notification not Sent", Toast.LENGTH_LONG).show();
                                    }
                                });
                    }
                    catch (JSONException e)
                    {
                        Log.e("NaSCon", "unexpected JSON exception", e);
                    }

                    Notif_box.setText("");

                }
                else
                {
                    Toast.makeText(AdminNotifications.this , "Text field should not be empty" , Toast.LENGTH_LONG).show();
                }
            }
        });

        Button logout = findViewById(R.id.id_btn_adminlogin);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(AdminNotifications.this , AdminLogin.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });

        events = findViewById(R.id.id_btn_events);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminNotifications.this, AdminEvents.class));
                finish();
            }
        });

        register = findViewById(R.id.id_btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminNotifications.this, AdminRegistrations.class));
                finish();
            }
        });

        home = findViewById(R.id.id_btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminNotifications.this, AdminHome.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        memories = findViewById(R.id.id_btn_memories);
        memories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminNotifications.this, AdminMemories.class));
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

    }

}
