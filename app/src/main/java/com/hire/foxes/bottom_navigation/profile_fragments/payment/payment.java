package com.hire.foxes.bottom_navigation.profile_fragments.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hire.foxes.R;

public class payment extends AppCompatActivity implements View.OnClickListener {

    ImageView payment_back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //ImageView
        payment_back_button = findViewById(R.id.payment_back_button);

        //OnClick
        payment_back_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.payment_back_button){
            finish();
        }
    }
}