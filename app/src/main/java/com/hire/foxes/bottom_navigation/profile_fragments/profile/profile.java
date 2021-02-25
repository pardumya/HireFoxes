package com.hire.foxes.bottom_navigation.profile_fragments.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hire.foxes.R;

public class profile extends AppCompatActivity implements View.OnClickListener {

    ImageView provider_back_button,profile_pic;
    TextView profile_username,profile_email,profile_phone_number;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    String username,email,phone_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Firebase
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user_info").child(auth.getCurrentUser().getUid());
        //TextView
        profile_username = findViewById(R.id.profile_username);
        profile_email = findViewById(R.id.profile_email);
        profile_phone_number = findViewById(R.id.profile_phone_number);
        //ImageView
        provider_back_button = findViewById(R.id.provider_back_button);
        profile_pic = findViewById(R.id.profile_pic);

        //OnClick
        provider_back_button.setOnClickListener(this);

        //functions
        get_user_info();

    }

    public void get_user_info(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    username = snapshot.child("username").getValue().toString();
                    email = snapshot.child("email").getValue().toString();
                    phone_number = snapshot.child("phone_number").getValue().toString();
                    Glide.with(profile.this).load(snapshot.child("information").child("profile_pic").getValue().toString()).into(profile_pic);
                    profile_username.setText(username);
                    profile_email.setText(email);
                    profile_phone_number.setText(phone_number);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.provider_back_button){
            finish();
        }
    }
}