package com.muhammadanasashir.nascon;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class Events extends AppCompatActivity {
    DrawerLayout drawr;
    ImageButton menu;
    Button home;
    Button events ;
    Button register;
    Button notifications;
    Button memories;
    Button contactus;
    Button adminLogin;
    Button developers;

    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

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
                Intent intent = new Intent(Events.this, MainActivity.class) ;
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        events = findViewById(R.id.id_btn_events);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawr.closeDrawer(Gravity.LEFT);
            }
        });

        register = findViewById(R.id.id_btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Events.this, RegisterStepOne.class));
                finish();
            }
        });

        notifications = findViewById(R.id.id_btn_notifications);
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Events.this, Notifications.class));
                finish();
            }
        });

        memories = findViewById(R.id.id_btn_memories);
        memories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Events.this, Memories.class));
                finish();
            }
        });

        contactus = findViewById(R.id.id_btn_contactus);
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Events.this, ContactUs.class));
                finish();
            }
        });

        adminLogin = findViewById(R.id.id_btn_adminlogin);
        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Events.this, AdminLogin.class));
                finish();
            }
        });

        developers = findViewById(R.id.id_btn_developers);
        developers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Events.this, Developers.class));
                drawr.closeDrawer(Gravity.LEFT);
            }
        });

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.pager);

        tabLayout.addTab(tabLayout.newTab().setText("CS"));
        tabLayout.addTab(tabLayout.newTab().setText("EE"));
        tabLayout.addTab(tabLayout.newTab().setText("Business"));
        tabLayout.addTab(tabLayout.newTab().setText("Sports"));
        tabLayout.addTab(tabLayout.newTab().setText("Socials"));


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());
                    }
                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                    }
                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                });
    }
}