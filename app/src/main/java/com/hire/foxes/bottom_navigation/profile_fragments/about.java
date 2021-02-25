package com.hire.foxes.bottom_navigation.profile_fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hire.foxes.R;

public class about extends AppCompatActivity implements View.OnClickListener {

    ImageView about_back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //ImageView
        about_back_button = findViewById(R.id.about_back_button);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.about_back_button){
            finish();
        }
    }
}