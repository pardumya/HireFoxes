package com.hire.foxes.bottom_navigation.profile_fragments.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hire.foxes.R;
import com.hire.foxes.bottom_navigation.profile_fragments.pojo_class.user_information;
import com.hire.foxes.constants.Constants;


import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class edit_profile extends AppCompatActivity implements View.OnClickListener {

    ImageView edit_profile_back_button,edit_user_profile_pic;
    EditText edit_profile_username,edit_profile_location,edit_profile_phoneNumber,edit_profile_description;
    TextView edit_profile_addPhoto;
    Uri imageUri;
    FirebaseStorage storage;
    FirebaseAuth auth;
    StorageReference storageReference,mountainsRef;
    DatabaseReference databaseReference;
    Button edit_profile_saveInfo;
    String name,location,phoneNumber,description,randomKey;
    RadioGroup edit_profile_radioGroup;
    int workingStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //Firebase
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        auth = FirebaseAuth.getInstance();
        randomKey = UUID.randomUUID().toString();
        mountainsRef = storageReference.child("images").child("User Profile Pic").child(auth.getCurrentUser().getUid()+"/"  + randomKey);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user_info").child(auth.getCurrentUser().getUid());

        //EditText
        edit_profile_username = findViewById(R.id.edit_profile_username);
        edit_profile_location = findViewById(R.id.edit_profile_location);
        edit_profile_phoneNumber = findViewById(R.id.edit_profile_phoneNumber);
        edit_profile_description = findViewById(R.id.edit_profile_description);

        //ImageView
        edit_profile_back_button = findViewById(R.id.edit_profile_back_button);
        edit_user_profile_pic = findViewById(R.id.edit_user_profile_pic);

        //TextView
        edit_profile_addPhoto = findViewById(R.id.edit_profile_addPhoto);

        //Button
        edit_profile_saveInfo = findViewById(R.id.edit_profile_saveInfo);

        //RadioGroup
        edit_profile_radioGroup = findViewById(R.id.edit_profile_radioGroup);

        //OnClick
        edit_profile_back_button.setOnClickListener(this);
        edit_user_profile_pic.setOnClickListener(this);
        edit_profile_addPhoto.setOnClickListener(this);
        edit_profile_saveInfo.setOnClickListener(this);

        fetch_info();

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.edit_profile_back_button){
            finish();
        }else if(view.getId() == R.id.edit_profile_addPhoto){
            Select_image();
        }else if(view.getId() == R.id.edit_user_profile_pic){
            Select_image();
        }else if(view.getId() == R.id.edit_profile_saveInfo){
            validation();
        }
    }

    private void validation() {

        name = edit_profile_username.getText().toString();
        location = edit_profile_location.getText().toString();
        phoneNumber = edit_profile_phoneNumber.getText().toString();
        description = edit_profile_description.getText().toString();

        if(name.isEmpty()){
            edit_profile_username.setError(Constants.error);
        }else{
            Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
            Matcher matcher = pattern.matcher(name);
            boolean tf = matcher.matches();
            if(tf){
                if(name.length() >= 3){
                    edit_profile_username.setError(null);
                    if(location.isEmpty()){
                        edit_profile_location.setError(Constants.error);
                    }else{
                        if(phoneNumber.isEmpty()){
                            edit_profile_phoneNumber.setError(Constants.error);
                        }else{
                            if(phoneNumber.length() != 10){
                                edit_profile_phoneNumber.setError(Constants.invalid_contact);
                            }else{
                                if(description.isEmpty()){
                                    edit_profile_description.setError(Constants.error);
                                }else{
                                    saveInfo();
                                }
                            }
                        }
                    }
                }else{
                    edit_profile_username.setError(Constants.name_length);
                }
            }else {
                edit_profile_username.setError(Constants.invalid_name);
            }
        }
    }

    private void saveInfo() {
        workingStatus = edit_profile_radioGroup.getCheckedRadioButtonId();

        user_information user_information = new user_information(name,location,phoneNumber,description,workingStatus);
        databaseReference.child("information").setValue(user_information).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Snackbar.make(findViewById(android.R.id.content),"Information Uploaded Successfully.", Snackbar.LENGTH_LONG).show();
                }else{

                }
            }
        });

    }

    public void Select_image(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            edit_user_profile_pic.setImageURI(imageUri);
            uploadPicture();
        }

    }

    private void uploadPicture() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading Image....");
        progressDialog.show();

        mountainsRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                mountainsRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        databaseReference.child("profile_pic").setValue(uri.toString());
                        progressDialog.dismiss();
                        Snackbar.make(findViewById(android.R.id.content),"Image Uploaded Successfully.", Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(edit_profile.this, "Failed To Upload", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                progressDialog.setMessage("Progress: "+(int) progressPercent + "%");
            }
        });

    }

    private void fetch_info() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("information").exists()){
                    edit_profile_location.setText(snapshot.child("information").child("location").getValue().toString());
                    edit_profile_phoneNumber.setText(snapshot.child("phone_number").getValue().toString());
                    edit_profile_description.setText(snapshot.child("information").child("description").getValue().toString());
                    Glide.with(edit_profile.this).load(snapshot.child("information").child("profile_pic").getValue().toString()).into(edit_user_profile_pic);
                }
                edit_profile_username.setText(snapshot.child("username").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}