package com.hire.foxes.bottom_navigation.home_fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
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


public class home extends Fragment implements View.OnClickListener {

    RecyclerView services_recyclerView,top_services_recyclerView,popular_services_recyclerView;
    TextView home_searchView,all_services_view_all,home_signIn;
    DatabaseReference databaseReference;
    FirebaseAuth auth;

    public home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_home,null);

        //Firebase
        auth = FirebaseAuth.getInstance();
        //RecyclerView
        services_recyclerView = layout.findViewById(R.id.services_recyclerView);
        top_services_recyclerView = layout.findViewById(R.id.top_services_recyclerView);
        popular_services_recyclerView = layout.findViewById(R.id.popular_services_recyclerView);
        //TextView
        all_services_view_all = layout.findViewById(R.id.all_services_view_all);
        home_searchView = layout.findViewById(R.id.home_searchView);
        home_signIn = layout.findViewById(R.id.home_signIn);

        // functions
        get_all_services();
        get_popular_services();

        //OnClick
        all_services_view_all.setOnClickListener(this);
        home_searchView.setOnClickListener(this);
        home_signIn.setOnClickListener(this);


        if(auth.getCurrentUser() != null){
            home_signIn.setVisibility(View.GONE);
        }

        return layout;
    }

    public void get_all_services(){
        List<home_services_data> services_list = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("services");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count=0;
                for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    services_list.add(new home_services_data(dataSnapshot.child("work").getValue().toString(),dataSnapshot.child("image").getValue().toString()));
                    count++;
                    if(count == 12){
                        break;
                    }
                }
                home_services_adapter adapter = new home_services_adapter(getContext(),services_list);
                services_recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void get_popular_services(){
        List<home_services_data> services_list = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("services");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count=0;
                for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    services_list.add(new home_services_data(dataSnapshot.child("work").getValue().toString(),dataSnapshot.child("image").getValue().toString()));
                    count++;
                    if(count == 6){
                        break;
                    }
                }
                home_services_adapter adapter = new home_services_adapter(getContext(),services_list);
                popular_services_recyclerView.setAdapter(adapter);
                top_services_recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.all_services_view_all){
            Intent intent = new Intent(getActivity(),ViewAll.class);
            intent.putExtra("service_name","All Services");
            startActivity(intent);
        }else if(view.getId() == R.id.new_services_view_all){
            Intent intent = new Intent(getActivity(),ViewAll.class);
            intent.putExtra("service_name","New Services");
            startActivity(intent);
        }else if(view.getId() == R.id.popular_services_view_all){
            Intent intent = new Intent(getActivity(),ViewAll.class);
            intent.putExtra("service_name","Popular Services");
            startActivity(intent);
        }else if(view.getId() == R.id.home_searchView){
            startActivity(new Intent(getActivity(),searchView.class));
        }
    }
}
