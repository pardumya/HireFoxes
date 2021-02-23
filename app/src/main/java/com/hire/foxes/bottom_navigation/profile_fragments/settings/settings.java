package com.hire.foxes.bottom_navigation.profile_fragments.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hire.foxes.R;
import com.hire.foxes.bottom_navigation.profile_fragments.profile.edit_profile;

public class settings extends AppCompatActivity implements View.OnClickListener {

    ImageView settings_back_button;
    LinearLayout add_a_place_layout,edit_profile_layout,notification_settings_layout,account_settings_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //ImageView
        settings_back_button = findViewById(R.id.settings_back_button);
        //LinearLayout
        add_a_place_layout = findViewById(R.id.add_a_place_layout);
        edit_profile_layout = findViewById(R.id.edit_profile_layout);
        notification_settings_layout = findViewById(R.id.notification_settings_layout);
        account_settings_layout = findViewById(R.id.account_settings_layout);


        //OnClick
        settings_back_button.setOnClickListener(this);
        add_a_place_layout.setOnClickListener(this);
        edit_profile_layout.setOnClickListener(this);
        notification_settings_layout.setOnClickListener(this);
        account_settings_layout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.settings_back_button){
            finish();
        }else if(view.getId() == R.id.add_a_place_layout){
            startActivity(new Intent(settings.this,add_your_place.class));
        }else if(view.getId() == R.id.edit_profile_layout){
            startActivity(new Intent(settings.this, edit_profile.class));
        }else if(view.getId() == R.id.notification_settings_layout){

        }else if(view.getId() == R.id.account_settings_layout){

        }
    }
}