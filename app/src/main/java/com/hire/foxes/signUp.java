package com.hire.foxes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hire.foxes.bottom_navigation.pojo_class.registration_data;

public class signUp extends AppCompatActivity implements View.OnClickListener {

    EditText signup_username,signup_email,signup_phone_number,signup_password;
    Button signup_button;
    String username,email,password;
    int phone_number;
    FirebaseAuth auth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Firebase
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user_info");
        //EditText
        signup_username = findViewById(R.id.signup_username);
        signup_email = findViewById(R.id.signup_email);
        signup_phone_number = findViewById(R.id.signup_phone_number);
        signup_password = findViewById(R.id.signup_password);
        //Button
        signup_button = findViewById(R.id.signup_button);
        signup_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.signup_button){
            register();
        }
    }

    public void register(){
        username = signup_username.getText().toString();
        email = signup_email.getText().toString();
        phone_number = Integer.parseInt(signup_phone_number.getText().toString());
        password = signup_password.getText().toString();

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    save_user_info();
                }else{
                    Toast.makeText(signUp.this, "User Not Sign Up Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void save_user_info(){
        registration_data registration_data = new registration_data(username,email,phone_number);
        databaseReference.child(auth.getCurrentUser().getUid()).setValue(registration_data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(signUp.this, "User Sign Up Successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(signUp.this, "User Not Sign Up Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}