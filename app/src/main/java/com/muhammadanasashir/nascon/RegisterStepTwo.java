package com.muhammadanasashir.nascon;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterStepTwo extends AppCompatActivity {

    DrawerLayout drawr;
    ImageButton menu;
    Button home;
    Button events;
    Button register ;
    Button notifications;
    Button memories;
    Button contactus;
    Button adminLogin;
    Button btn_save;

    RadioButton Lodging_Yes;
    RadioButton Lodging_No;
    RadioButton Food_Yes;
    RadioButton Food_No;
    Boolean Lodging = false;
    Boolean Food = false;

    TextView Member_No;
    TextView Price_Total;

    EditText Member_Name;
    EditText Member_CNIC;
    EditText Member_Phone_No;

    int price , cur_member ,max_member ;
    String tname , mem_name , mem_cnic , mem_phone , ename , institute ;
    Boolean mem_lodge , mem_food ;
    MemberNode memberNode;
    TeamNode teamNode;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_step2);

        price = getIntent().getExtras().getInt("Total");
        tname = getIntent().getExtras().getString("Team_Name");
        cur_member = getIntent().getExtras().getInt("No");
        max_member = getIntent().getExtras().getInt("Max");
        ename = getIntent().getExtras().getString("Event");
        institute = getIntent().getExtras().getString("Inst");

        Member_Name = findViewById(R.id.Name);
        Member_CNIC= findViewById(R.id.CNIC);
        Member_Phone_No = findViewById(R.id.Ph_No);



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
                startActivity(new Intent(RegisterStepTwo.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                drawr.closeDrawer(Gravity.LEFT);
                //finish();
            }
        });

        events = findViewById(R.id.id_btn_events);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterStepTwo.this, Events.class));
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
                startActivity(new Intent(RegisterStepTwo.this, Memories.class));
                drawr.closeDrawer(Gravity.LEFT);
                finish();
            }
        });

        notifications = findViewById(R.id.id_btn_notifications);
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterStepTwo.this, Notifications.class));
                drawr.closeDrawer(Gravity.LEFT);
                finish();
            }
        });

        contactus = findViewById(R.id.id_btn_contactus);
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterStepTwo.this, ContactUs.class));
                drawr.closeDrawer(Gravity.LEFT);
                finish();
            }
        });

        adminLogin = findViewById(R.id.id_btn_adminlogin);
        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterStepTwo.this, AdminLogin.class));
                drawr.closeDrawer(Gravity.LEFT);
                finish();
            }
        });

        btn_save = findViewById(R.id.id_btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Save Member Details

                /*                                          HUZAIFA'S CODE

                DatabaseReference dbreference = FirebaseDatabase.getInstance().getReference("Registration");

                String Team_Id = getIntent().getExtras().getString("Team_Id");

                dbreference.child("Team_Members").child(Team_Id).child(""+getIntent().getExtras().getInt("No")).child("Name").setValue(Member_Name.getText().toString());//  Name
                dbreference.child("Team_Members").child(Team_Id).child(""+getIntent().getExtras().getInt("No")).child("CNIC").setValue(Member_CNIC.getText().toString());// CNIC
                dbreference.child("Team_Members").child(Team_Id).child(""+getIntent().getExtras().getInt("No")).child("Phone_No").setValue(Member_Phone_No.getText().toString());// Phone Number
                dbreference.child("Team_Members").child(Team_Id).child(""+getIntent().getExtras().getInt("No")).child("Lodging").setValue(Lodging);// Lodging
                dbreference.child("Team_Members").child(Team_Id).child(""+getIntent().getExtras().getInt("No")).child("Food").setValue(Food);// Food

                // End

                if (getIntent().getExtras().getInt("No")==getIntent().getExtras().getInt("Max"))
                {
                    // Save Total Price

                    dbreference.child("Teams").child(Team_Id).child("Total_Price").setValue(price);

                    // End

                    startActivity(new Intent(RegisterStepTwo.this, MainActivity.class));
                }
                else
                {

                    Intent step_2 = new Intent(RegisterStepTwo.this, RegisterStepTwo.class);
                    step_2.putExtra("Team_Id",getIntent().getExtras().getString("Team_Id"));
                    step_2.putExtra("Total",price);
                    step_2.putExtra("Max",getIntent().getExtras().getInt("Max"));
                    step_2.putExtra("No",getIntent().getExtras().getInt("No")+1);
                    startActivity(step_2);
                }


                 */

                DatabaseReference oldRef = FirebaseDatabase.getInstance().getReference("Registration").child("Teams").child(tname);


                mem_name = Member_Name.getText().toString().trim();
                mem_cnic = Member_CNIC.getText().toString().trim();
                mem_phone = Member_Phone_No.getText().toString().trim();
                mem_lodge = Lodging ;
                mem_food = Food;

                if (TextUtils.isEmpty(mem_name) || TextUtils.isEmpty(mem_cnic) || TextUtils.isEmpty(mem_phone))
                {
                    Toast.makeText(RegisterStepTwo.this , "Please fill all fields" , Toast.LENGTH_LONG).show();
                }

                else
                {
                    teamNode = new TeamNode(ename , institute , max_member , tname, price);
                    DatabaseReference TeamRef = FirebaseDatabase.getInstance().getReference("Registration").child("Teams").child(tname);
                    TeamRef.setValue(teamNode);

                    memberNode = new MemberNode(mem_name, mem_cnic, mem_phone , mem_lodge , mem_food);
                    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Registration").child("Team Members").child(tname).
                            child(String.valueOf(cur_member));
                    dbRef.setValue(memberNode);

                    HashMap<String , Object> map = new HashMap<>();
                    map.put("fee" , price);
                    oldRef.updateChildren(map);


                    if (cur_member == max_member)
                    {
                        //TeamNode
                        //DatabaseReference dbN = FirebaseDatabase.getInstance().getReference("Reg.").child("Teams").child()
                        Toast.makeText(RegisterStepTwo.this , "Successfully Registered!" , Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterStepTwo.this, MainActivity.class));
                        finish();
                    }

                    else
                    {
                        cur_member++ ;

                        Intent step_2 = new Intent(RegisterStepTwo.this, RegisterStepTwo.class);
                        step_2.putExtra("Team_Name",tname);
                        step_2.putExtra("Total",price);
                        step_2.putExtra("Max",max_member);
                        step_2.putExtra("No",cur_member);
                        step_2.putExtra("Event",ename);
                        step_2.putExtra("Inst",institute);
                        //step_2.putExtra("Member"+(cur_member-1),memberNode);
                        //step_2.putExtra("Team_Node",getIntent().getExtras().getSerializable("Team_Node"));
                        //for (int i=1;i<cur_member-1;i++)
                        {
                            //step_2.putExtra("Member"+i,getIntent().getExtras().getSerializable("Member"+i));

                        }
                        startActivity(step_2);
                        finish();
                    }

                }

            }
        });

        Lodging_Yes = findViewById(R.id.id_radio_lodging_yes);
        Lodging_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Lodging)
                {
                    price += 1500 ;
                    Price_Total.setText("Rs."+ price);
                    Lodging = true;
                }
            }
        });

        Lodging_No = findViewById(R.id.id_radio_lodging_no);
        Lodging_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Lodging)
                {
                    price -= 1500 ;
                    Price_Total.setText("Rs."+ price);
                    Lodging = false;
                }
            }
        });

        Food_Yes = findViewById(R.id.id_radio_food_yes);
        Food_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Food)
                {
                    price += 1000 ;
                    Price_Total.setText("Rs."+ price);
                    Food = true;
                }
            }
        });

        Food_No = findViewById(R.id.id_radio_food_no);
        Food_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Food)
                {
                    price -= 1000 ;
                    Price_Total.setText("Rs."+ price);
                    Food = false;
                }
            }
        });

        Member_No = findViewById(R.id.Member_No);
        Member_No.setText("Member # "+ cur_member);

        Price_Total = findViewById(R.id.Price_Total);
        Price_Total.setText("Rs."+ price);

    }
}