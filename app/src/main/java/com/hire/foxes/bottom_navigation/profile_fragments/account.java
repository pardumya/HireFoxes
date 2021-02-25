package com.hire.foxes.bottom_navigation.profile_fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hire.foxes.R;
import com.hire.foxes.bottom_navigation.profile_fragments.bookmarks.bookmarks;
import com.hire.foxes.bottom_navigation.profile_fragments.notification.notification;
import com.hire.foxes.bottom_navigation.profile_fragments.payment.payment;
import com.hire.foxes.bottom_navigation.profile_fragments.profile.edit_profile;
import com.hire.foxes.bottom_navigation.profile_fragments.profile.profile;
import com.hire.foxes.bottom_navigation.profile_fragments.settings.settings;
import com.hire.foxes.login;

public class account extends Fragment implements View.OnClickListener {

    TextView account_viewProfile,account_about,account_signOut,account_username;
    LinearLayout account_bookmark_layout,account_notification_layout,account_settings_layout,account_payments_layout;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    ImageView account_profile_pic;

    public account() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_account, container, false);

        //Firebase
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user_info").child(auth.getCurrentUser().getUid());

        //TextView
        account_viewProfile = layout.findViewById(R.id.account_viewProfile);
        account_about = layout.findViewById(R.id.account_about);
        account_signOut = layout.findViewById(R.id.account_signOut);
        account_username = layout.findViewById(R.id.account_username);
        //Linear Layout
        account_bookmark_layout = layout.findViewById(R.id.account_bookmark_layout);
        account_notification_layout = layout.findViewById(R.id.account_notification_layout);
        account_settings_layout = layout.findViewById(R.id.account_settings_layout);
        account_payments_layout = layout.findViewById(R.id.account_payments_layout);

        //ImageView
        account_profile_pic = layout.findViewById(R.id.account_profile_pic);

        //OnClick
        account_viewProfile.setOnClickListener(this);
        account_about.setOnClickListener(this);
        account_signOut.setOnClickListener(this);
        account_bookmark_layout.setOnClickListener(this);
        account_notification_layout.setOnClickListener(this);
        account_settings_layout.setOnClickListener(this);
        account_payments_layout.setOnClickListener(this);

        //function
        fetch_info();

        return layout;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.account_viewProfile){
            startActivity(new Intent(getActivity(), profile.class));
        }else if(view.getId() == R.id.account_about){
            startActivity(new Intent(getActivity(),about.class));
        }else if(view.getId() == R.id.account_signOut){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getActivity(), login.class));
        }else if(view.getId() == R.id.account_bookmark_layout){
            startActivity(new Intent(getActivity(), bookmarks.class));
        }else if(view.getId() == R.id.account_notification_layout){
            startActivity(new Intent(getActivity(), notification.class));
        }else if(view.getId() == R.id.account_settings_layout){
            startActivity(new Intent(getActivity(), settings.class));
        }else if(view.getId() == R.id.account_payments_layout){
            startActivity(new Intent(getActivity(), payment.class));
        }
    }

    private void fetch_info() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("information").exists()){
                    Glide.with(getContext()).load(snapshot.child("information").child("profile_pic").getValue().toString()).into(account_profile_pic);
                }
                account_username.setText(snapshot.child("username").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}