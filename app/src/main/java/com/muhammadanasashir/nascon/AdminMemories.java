package com.muhammadanasashir.nascon;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.UUID;

public class AdminMemories extends AppCompatActivity {

    DrawerLayout drawr;
    ImageButton menu;
    Button events;
    Button register;
    Button home;
    Button memories;
    Button notifications;
    Button Add_Memories;

    RecyclerView memories_view ;
    ArrayList<String> memories_links ;
    DatabaseReference dbRef ;
    RecyclerView.Adapter adapter ;
    private static final int IMAGE_REQUEST = 1000 ;
    private static final int PERMISSION_CODE=1001;
    Uri ImageURI ;
    String photoStringLink ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminmemories);

        memories_view = findViewById(R.id.id_rv_memories);
        memories_view.setHasFixedSize(true);
        memories_view.setLayoutManager(new LinearLayoutManager(this));

        Add_Memories = findViewById(R.id.AddMemories);
        Add_Memories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {

                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    }

                    else {

                        pickimagefromgallery();

                    }
                }

                else {

                    pickimagefromgallery();
                }
            }
        });

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

        Button logout = findViewById(R.id.id_btn_adminlogin);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(AdminMemories.this , AdminLogin.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });

        events = findViewById(R.id.id_btn_events);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMemories.this, AdminEvents.class));
                finish();
            }
        });

        register = findViewById(R.id.id_btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMemories.this, AdminRegistrations.class));
                finish();
            }
        });

        home = findViewById(R.id.id_btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMemories.this, AdminHome.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        notifications = findViewById(R.id.id_btn_notifications);
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMemories.this, AdminNotifications.class));
                finish();
            }
        });

        memories = findViewById(R.id.id_btn_memories);
        memories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawr.closeDrawer(Gravity.LEFT);
            }
        });

        memories_links = new ArrayList<>();
        readMemories();
    }

    private void pickimagefromgallery() {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_REQUEST) {
            ImageURI = data.getData();
            sendImage();
            //img.setImageURI(ImageURI);
        }
    }

    private void sendImage()
    {

        final DatabaseReference mDb = FirebaseDatabase.getInstance().getReference("Memories").push();
        Toast.makeText(AdminMemories.this, "Uploading Picture...", Toast.LENGTH_SHORT).show();
        String uniqueId = UUID.randomUUID().toString();
        final StorageReference ur_firebase_reference = FirebaseStorage.getInstance().getReference().child("images/" + uniqueId);

        Uri file = ImageURI;
        UploadTask uploadTask = ur_firebase_reference.putFile(file);

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }


                // Continue with the task to get the download URL
                return ur_firebase_reference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    System.out.println("Send " + downloadUri);
                    Toast.makeText(AdminMemories.this, "Upload Successful!", Toast.LENGTH_SHORT).show();

                    if (downloadUri != null) {

                        photoStringLink = downloadUri.toString(); //YOU WILL GET THE DOWNLOAD URL HERE !!!!

                        // Get a URL to the uploaded content
                        System.out.println("Sent " + photoStringLink);
                        mDb.setValue(photoStringLink);
                    }

                } else {
                    Toast.makeText(AdminMemories.this , "Upload Failed!" , Toast.LENGTH_SHORT).show();
                    // Handle failures
                    // ...
                }
            }
        });

    }

    private void readMemories() {

        dbRef = FirebaseDatabase.getInstance().getReference("Memories");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                memories_links.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    String link = snapshot.getValue(String.class);
                    memories_links.add(link);
                }

                adapter = new RVAdapter(memories_links, 1);
                memories_view.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
