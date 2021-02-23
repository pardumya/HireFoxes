package com.hire.foxes.bottom_navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.hire.foxes.R;
import com.hire.foxes.bottom_navigation.adapters.tablayout_adapter;

public class provider_profile extends AppCompatActivity {

    ImageView provider_profile_pic;
    TextView provider_profile_name;
    String provider_name,provider_image;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_profile);
        //ImageView
        provider_profile_pic = findViewById(R.id.provider_profile_pic);
        //Intent
        provider_name = getIntent().getStringExtra("developer_name");
        provider_image = getIntent().getStringExtra("developer_image");
        //TextView
        provider_profile_name = findViewById(R.id.provider_profile_name);
        //Tab or ViewPager
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Projects"));
        tabLayout.addTab(tabLayout.newTab().setText("About"));
        tabLayout.addTab(tabLayout.newTab().setText("Review"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

//        tablayout_adapter tablayout_adapter = new tablayout_adapter(provider_profile.this,getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPager.setAdapter(tablayout_adapter);

        provider_profile_name.setText(provider_name);
        Glide.with(provider_profile.this).load(provider_image).into(provider_profile_pic);
    }
}