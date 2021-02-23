package com.hire.foxes.bottom_navigation.profile_fragments.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hire.foxes.R;

public class notification extends AppCompatActivity implements View.OnClickListener {

    ImageView notification_back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        //ImageView
        notification_back_button = findViewById(R.id.notification_back_button);

        //OnClick
        notification_back_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.notification_back_button){
            finish();
        }
    }
}