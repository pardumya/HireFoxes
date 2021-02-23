package com.hire.foxes.bottom_navigation.home_fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.hire.foxes.R;

public class searchView extends AppCompatActivity implements View.OnClickListener {

    TextView search_view_cancel_button;
    AutoCompleteTextView search_HireServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        //TextView
        search_view_cancel_button = findViewById(R.id.search_view_cancel_button);
        //AutoCompleteTextView
        search_HireServices = findViewById(R.id.search_HireServices);

        //AutoCompleteTextView Focus
        search_HireServices.requestFocus();
        InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.RESULT_HIDDEN);
        if(!search_HireServices.isFocused()) {
            imm.hideSoftInputFromWindow(search_HireServices.getWindowToken(),0);
        }

        //AutoCompleteTextView Array
        String[] countries = getResources().getStringArray(R.array.services);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        search_HireServices.setAdapter(adapter);



        //OnClick
        search_view_cancel_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() ==R.id.search_view_cancel_button){
            InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);

            finish();
        }
    }
}