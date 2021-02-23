package com.hire.foxes.bottom_navigation.profile_fragments.bookmarks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hire.foxes.R;

public class bookmarks extends AppCompatActivity implements View.OnClickListener {

    ImageView bookmarks_back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        //ImageView
        bookmarks_back_button = findViewById(R.id.bookmarks_back_button);


        //OnClick
        bookmarks_back_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.bookmarks_back_button){
            finish();
        }
    }
}