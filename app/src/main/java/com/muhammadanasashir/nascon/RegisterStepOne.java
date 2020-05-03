package com.muhammadanasashir.nascon;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class RegisterStepOne extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DrawerLayout drawr;
    ImageButton menu;
    Button home;
    Button events;
    Button register ;
    Button notifications;
    Button memories;
    Button contactus;
    Button adminLogin;
    Button btn_next;
    Button developers;

    private Spinner spinner_events;
    private Spinner spinner_members;

    ArrayList<String> eventsNames ;
    ArrayList<Integer> eventsPrices ;
    ArrayList<Integer> minMembers;
    ArrayList<Integer> maxMembers ;

    ArrayAdapter<String> adapter_events;
    ArrayAdapter<String> adapter_members;

    EditText Team_Name;
    EditText Team_Institute;

    TextView Total_Price;
    int price , members ;
    String tname , ename , institute ;
    TeamNode teamNode ;
    ArrayList<String> teams ;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_step1);

        Total_Price = findViewById(R.id.Fee);
        Team_Name = findViewById(R.id.Team_Name);
        Team_Institute = findViewById(R.id.Institute_Name);


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
                startActivity(new Intent(RegisterStepOne.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                drawr.closeDrawer(Gravity.LEFT);
                //finish();
            }
        });

        events = findViewById(R.id.id_btn_events);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterStepOne.this, Events.class));
                drawr.closeDrawer(Gravity.LEFT);
                finish();
            }
        });

        register = findViewById(R.id.id_btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawr.closeDrawer(Gravity.LEFT);
            }
        });

        memories = findViewById(R.id.id_btn_memories);
        memories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterStepOne.this, Memories.class));
                drawr.closeDrawer(Gravity.LEFT);
                finish();
            }
        });

        notifications = findViewById(R.id.id_btn_notifications);
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterStepOne.this, Notifications.class));
                drawr.closeDrawer(Gravity.LEFT);
                finish();
            }
        });

        contactus = findViewById(R.id.id_btn_contactus);
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterStepOne.this, ContactUs.class));
                drawr.closeDrawer(Gravity.LEFT);
                finish();
            }
        });

        adminLogin = findViewById(R.id.id_btn_adminlogin);
        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterStepOne.this, AdminLogin.class));
                drawr.closeDrawer(Gravity.LEFT);
                finish();
            }
        });

        developers = findViewById(R.id.id_btn_developers);
        developers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterStepOne.this, Developers.class));
                drawr.closeDrawer(Gravity.LEFT);
            }
        });


        readTeams();
        btn_next = findViewById(R.id.id_btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(RegisterStepOne.this, RegisterStepTwo.class));

                ////////////////////////  New Code Added Start  ///////////////////////////////////////////

                // Adding Team to FireBase

                /*                                                        HUZAIFA'S CODE

                DatabaseReference dbreference = FirebaseDatabase.getInstance().getReference("Registration");
                String Team_Id = dbreference.child("Teams").push().getKey();

                dbreference.child("Teams").child(Team_Id).child("Event_Name").setValue(spinner_events.getSelectedItem().toString()); // Event
                dbreference.child("Teams").child(Team_Id).child("Team_Name").setValue(Team_Name.getText().toString()); // Team Name
                dbreference.child("Teams").child(Team_Id).child("Institute").setValue(Team_Institute.getText().toString()); // Institute
                dbreference.child("Teams").child(Team_Id).child("No_of_Members").setValue(spinner_members.getSelectedItem().toString()); // Total Members
                dbreference.child("Teams").child(Team_Id).child("Registration_Id").setValue(Team_Id); // Registration No.

                // End

                Intent step_2 = new Intent(RegisterStepOne.this, RegisterStepTwo.class);
                step_2.putExtra("Team_Id",Team_Id);
                step_2.putExtra("Total",price);
                step_2.putExtra("Max",Integer.parseInt(spinner_members.getSelectedItem().toString()));
                step_2.putExtra("No",1);
                startActivity(step_2);

                 */
                tname = Team_Name.getText().toString().trim();
                institute = Team_Institute.getText().toString().trim();
                members = Integer.valueOf(spinner_members.getSelectedItem().toString());
                ename = spinner_events.getSelectedItem().toString() ;

                if (TextUtils.isEmpty(tname) || TextUtils.isEmpty(institute))
                {
                    Toast.makeText(RegisterStepOne.this , "Please fill all fields", Toast.LENGTH_LONG).show();
                }

                else if (teams.contains(tname))
                {
                    Toast.makeText(RegisterStepOne.this , "Team name already exists", Toast.LENGTH_LONG).show();
                }

                else
                {
                    /*
                    teamNode = new TeamNode(ename , institute , members , tname, price);

                    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Registration").child("Teams").child(tname);
                    dbRef.setValue(teamNode);

                     */

                    Intent step_2 = new Intent(RegisterStepOne.this, RegisterStepTwo.class);
                    step_2.putExtra("Team_Name" , tname);
                    step_2.putExtra("Total",price);
                    step_2.putExtra("Max",members);
                    step_2.putExtra("Event",ename);
                    step_2.putExtra("Inst",institute);
                    step_2.putExtra("No",1);
                    //step_2.putExtra("Team_Node",teamNode);
                    startActivity(step_2);
                }

                ////////////////////////  New Code Added End  ///////////////////////////////////////////
            }
        });

        teams = new ArrayList<>();
        eventsNames = new ArrayList<>();
        minMembers = new ArrayList<>();
        maxMembers = new ArrayList<>();
        eventsPrices = new ArrayList<>();
        readEvents();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        ////////////////////////  New Code Added Start  ///////////////////////////////////////////

        ArrayList<String> membersList = new ArrayList<>();
        for (int i = minMembers.get(position) ; i <= maxMembers.get(position) ; i++)
        {
            membersList.add(String.valueOf(i));
        }

        spinner_members = findViewById(R.id.id_spinner_members);

        adapter_members = new ArrayAdapter<String>(RegisterStepOne.this,
                android.R.layout.simple_spinner_item,membersList);

        adapter_members.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_members.setAdapter(adapter_members);
        price = eventsPrices.get(position);

        Total_Price.setText("Rs." + price);

        ////////////////////////  New Code Added End  ///////////////////////////////////////////
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }


    private void readEvents() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Events");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<String> eventsNames  = new ArrayList<>();
                //eventsNames.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    EventNode event = snapshot.getValue(EventNode.class);

                    assert event != null ;
                    eventsNames.add(event.getName());
                    minMembers.add(event.getMin_members());
                    maxMembers.add(event.getMax_members());
                    eventsPrices.add(event.getFee());  // New Line Added
                }

                ////////////////////////  New Code Added Start  ///////////////////////////////////////////

                spinner_events = findViewById(R.id.id_spinner_event);

                adapter_events = new ArrayAdapter<String>(RegisterStepOne.this,
                        android.R.layout.simple_spinner_item,eventsNames);

                adapter_events.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_events.setAdapter(adapter_events);
                spinner_events.setOnItemSelectedListener(RegisterStepOne.this);

                ////////////////////////  New Code Added End  ///////////////////////////////////////////
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void readTeams()
    {
        DatabaseReference dbTeams = FirebaseDatabase.getInstance().getReference("Registration").child("Teams");

        dbTeams.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                teams.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    teams.add(snapshot.getValue(TeamNode.class).getTname());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}