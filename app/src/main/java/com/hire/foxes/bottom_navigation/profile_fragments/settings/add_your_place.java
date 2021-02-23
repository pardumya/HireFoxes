package com.hire.foxes.bottom_navigation.profile_fragments.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hire.foxes.R;

public class add_your_place extends AppCompatActivity implements View.OnClickListener {

    ImageView add_your_place_back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_your_place);

        //ImageView
        add_your_place_back_button = findViewById(R.id.add_your_place_back_button);

        //OnClick
        add_your_place_back_button.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.add_your_place_back_button){
            finish();
        }
    }
}