package com.muhammadanasashir.nascon;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminRegisteredTeams extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<String> myDataset ;
    TextView Event_Name;
    ImageButton menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminregisteredteams);

        Event_Name = findViewById(R.id.Event_Teams);
        Event_Name.setText(getIntent().getExtras().getString("EventName"));

        menu = findViewById(R.id.menu);
        menu.setVisibility(View.INVISIBLE);

        recyclerView = findViewById(R.id.id_rv_notifications);
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

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Registration").child("Teams");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myDataset.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {

                    TeamNode Tnode = snapshot.getValue(TeamNode.class);

                    if (Tnode.getEname().equals(getIntent().getExtras().getString("EventName")))
                    {
                        String Data = "Team Name : " + Tnode.getTname() + "\n";
                        Data += "Team Institute : " + Tnode.getInstitute() + "\n";
                        Data += "Total Members : " + Tnode.getMembers() + "\n";
                        Data += "Total Fee : " + Tnode.getFee();

                        myDataset.add(Data);
                    }
                }

                mAdapter = new RVAdapter(myDataset, 2);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}