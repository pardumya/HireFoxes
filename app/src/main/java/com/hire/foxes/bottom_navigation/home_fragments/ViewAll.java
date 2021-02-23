package com.hire.foxes.bottom_navigation.home_fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hire.foxes.R;
import com.hire.foxes.bottom_navigation.adapters.home_services_adapter;
import com.hire.foxes.bottom_navigation.pojo_class.home_services_data;

import java.util.ArrayList;
import java.util.List;

public class ViewAll extends AppCompatActivity implements View.OnClickListener {

    RecyclerView viewAll_recyclerView;
    TextView viewAll_service_name;
    DatabaseReference databaseReference;
    List<home_services_data> services_list = new ArrayList<>();
    ImageView viewAll_back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        //RecyclerView
        viewAll_recyclerView = findViewById(R.id.viewAll_recyclerView);
        //TextView
        viewAll_service_name = findViewById(R.id.viewAll_service_name);
        viewAll_service_name.setText(getIntent().getStringExtra("service_name"));
        //ImageView
        viewAll_back_button = findViewById(R.id.viewAll_back_button);

        //OnCLick
        viewAll_back_button.setOnClickListener(this);

        //Functions
        get_services();

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.viewAll_back_button){
            finish();
        }
    }

    public void get_services(){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("services");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    services_list.add(new home_services_data(dataSnapshot.child("work").getValue().toString(),dataSnapshot.child("image").getValue().toString()));
                }
                home_services_adapter adapter = new home_services_adapter(ViewAll.this,services_list);
                viewAll_recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}