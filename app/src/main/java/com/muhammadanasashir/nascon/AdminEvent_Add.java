package com.muhammadanasashir.nascon;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminEvent_Add extends AppCompatActivity {

    EditText Event_Name;
    EditText Event_Desc;
    EditText Event_Date;
    EditText Event_Start;
    EditText Event_End;
    EditText Event_Fee;
    EditText Event_First;
    EditText Event_Second;
    EditText Event_Min;
    EditText Event_Max;

    ArrayList<String> eventsList;

    Button Save_Event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminevent_add);

        Event_Name = findViewById(R.id.name);
        Event_Desc = findViewById(R.id.EventDetails);
        Event_Date = findViewById(R.id.Date);
        Event_Start = findViewById(R.id.StartTime);
        Event_End = findViewById(R.id.EndTime);
        Event_Fee = findViewById(R.id.Fee);
        Event_First = findViewById(R.id.First);
        Event_Second = findViewById(R.id.Second);
        Event_Min = findViewById(R.id.minMembers);
        Event_Max = findViewById(R.id.maxMembers);

        eventsList = new ArrayList<>();
        readEvents();

        Save_Event = findViewById(R.id.id_btn_add_event);

        Save_Event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(Event_Name.getText().toString().trim()) || TextUtils.isEmpty(Event_Desc.getText().toString().trim())
                        || TextUtils.isEmpty(Event_Date.getText().toString().trim()) || TextUtils.isEmpty(Event_Start.getText().toString().trim())
                        || TextUtils.isEmpty(Event_End.getText().toString().trim()) || TextUtils.isEmpty(Event_Fee.getText().toString().trim())
                        || TextUtils.isEmpty(Event_First.getText().toString().trim()) || TextUtils.isEmpty(Event_Second.getText().toString().trim())
                        || TextUtils.isEmpty(Event_Min.getText().toString().trim()) || TextUtils.isEmpty(Event_Max.getText().toString().trim())) {
                    Toast.makeText(AdminEvent_Add.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                }
                else if (eventsList.contains(Event_Name.getText().toString()) && getIntent().getExtras().getInt("Edit") == 0)
                {
                    Toast.makeText(AdminEvent_Add.this, "Event already exists", Toast.LENGTH_LONG).show();
                }
                else if (eventsList.contains(Event_Name.getText().toString()) && getIntent().getExtras().getInt("Edit") == 1
                        && !Event_Name.getText().toString().equals(getIntent().getExtras().getString("EventName")))
                {
                    Toast.makeText(AdminEvent_Add.this, "Event already exists", Toast.LENGTH_LONG).show();
                }
                 else {
                    EventNode eventNode = new EventNode(Event_Name.getText().toString(),
                            Event_Desc.getText().toString(), getIntent().getExtras().getString("category"),
                            Event_Date.getText().toString(), Event_Start.getText().toString(),
                            Event_End.getText().toString(), Integer.parseInt(Event_Fee.getText().toString()),
                            Integer.parseInt(Event_First.getText().toString()), Integer.parseInt(Event_Second.getText().toString()),
                            Integer.parseInt(Event_Min.getText().toString()), Integer.parseInt(Event_Max.getText().toString()));
                    //String name,
                    // String details, String category,
                    // String date, String start_time,
                    // String end_time, int fee,
                    // int first, int second,
                    // int min_members, int max_members) {


                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Events");
                    reference.child(Event_Name.getText().toString()).setValue(eventNode);

                    if (getIntent().getExtras().getInt("Edit") == 1) {
                        if (!Event_Name.getText().toString().equals(getIntent().getExtras().getString("EventName"))) {
                            reference.child(getIntent().getExtras().getString("EventName")).removeValue();
                        }
                    }
                    finish();
                }
            }
        });

        if(getIntent().getExtras().getInt("Edit")==1)
        {
            String name = getIntent().getExtras().getString("EventName");
            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Events").child(name);

            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    EventNode eventNode = dataSnapshot.getValue(EventNode.class);
                    assert eventNode != null;

                    Event_Name.setText(eventNode.getName());
                    Event_Desc.setText(eventNode.getDetails());
                    Event_Date.setText(eventNode.getDate());
                    Event_Start.setText(eventNode.getStart_time());
                    Event_End.setText(eventNode.getEnd_time());
                    Event_Fee.setText(""+eventNode.getFee());
                    Event_First.setText(""+eventNode.getFirst());
                    Event_Second.setText(""+eventNode.getSecond());
                    Event_Min.setText(""+eventNode.getMin_members());
                    Event_Max.setText(""+eventNode.getMax_members());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
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

                    if (event.getCategory().equals(getIntent().getExtras().getString("category")))
                    {
                        eventsList.add(event.getName());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
