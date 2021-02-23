package com.hire.foxes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login extends AppCompatActivity implements View.OnClickListener {

    TextView register_account;
    Button login_button;
    EditText login_email,login_password;
    String email_string,password_string;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Firebase
        auth = FirebaseAuth.getInstance();
        //TextView
        register_account = findViewById(R.id.register_account);
        //Button
        login_button = findViewById(R.id.login_button);
        //EditText
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);

        //On Click
        login_button.setOnClickListener(this);
        register_account.setOnClickListener(this);

        //functions

    }

    public void validation_check(){
        email_string = login_email.getText().toString();
        password_string = login_password.getText().toString();

        if(email_string.isEmpty()){
            login_email.setError("Email");
        }else{
            login_email.setError(null);
            Pattern pattern1 = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
            Matcher matcher1 = pattern1.matcher(email_string);
            if(matcher1.matches()){
                if(password_string.isEmpty()){
                    login_password.setError("Password");
                }else{
                    login_password.setError(null);
                    if(password_string.length() > 6){
                        //call sign-in method
                        login_email.setEnabled(false);
                        login_password.setEnabled(false);
                        login_button.setEnabled(false);
//                        register.setEnabled(false);
//                        progressBar.setVisibility(View.VISIBLE);
                        sign_in(email_string,password_string);
                    }else{
                        login_password.setError("Minimum password length should be(6)");
                    }
                }
            }else{
                login_email.setError("Plz enter valid email");
            }
        }
    }

    private void sign_in(String email, String password) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    login_email.setEnabled(true);
                    login_password.setEnabled(true);
                    login_button.setEnabled(true);
//                    register.setEnabled(true);
//                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(login.this, index.class));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                login_email.setEnabled(true);
                login_password.setEnabled(true);
                login_button.setEnabled(true);
//                register.setEnabled(true);
//                progressBar.setVisibility(View.GONE);
                Toast.makeText(login.this, "error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.login_button){
            validation_check();
        }else if(view.getId() == R.id.register_account){
            startActivity(new Intent(login.this,signUp.class));
        }
    }
}