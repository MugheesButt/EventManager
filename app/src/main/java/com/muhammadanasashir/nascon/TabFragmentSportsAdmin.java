package com.muhammadanasashir.nascon;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TabFragmentSportsAdmin extends Fragment {

    RecyclerView Sports ;
    FloatingActionButton fbtn;
    AdminEventAdapter eventAdapter;
    ArrayList<EventNode> eventsList ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tab_sports_admin, container, false);


        Sports = rootView.findViewById(R.id.id_rv_Sports);

        Sports.setHasFixedSize(true);
        Sports.setLayoutManager(new LinearLayoutManager(getContext()));

        eventsList = new ArrayList<>();
        readEvents();

        fbtn = rootView.findViewById(R.id.id_sports_floatingActionButton);
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdminEvent_Add.class);
                intent.putExtra("category","Sports");
                intent.putExtra("Edit" , 0);
                startActivity(intent);
            }
        });

        return rootView;
    }


    private void readEvents() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Events");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                eventsList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    EventNode event = snapshot.getValue(EventNode.class);

                    assert event != null ;

                    if (event.getCategory().equals("Sports"))
                    {
                        eventsList.add(event);
                    }


                }

                eventAdapter = new AdminEventAdapter(getContext() , eventsList);
                Sports.setAdapter(eventAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}