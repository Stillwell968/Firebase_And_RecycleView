package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CreateCarActivity extends AppCompatActivity {
    private Button saveBtn;
    private EditText makeEddit, modelEddit, regEdit;

    private FirebaseAuth mAuth;
    private DatabaseReference userDb;
    private DatabaseReference adminDb;

    private String make, model, regNum, userId;

    private Uri resultUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_car);

        saveBtn = findViewById(R.id.save_btn);

        makeEddit = findViewById(R.id.makeName_txt);
        modelEddit = findViewById(R.id.model_txt);
        regEdit = findViewById(R.id.regNo_txt);

        //get user is reference from database
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInfo();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    private void saveUserInfo() {
        make = makeEddit.getText().toString();
        model = modelEddit.getText().toString();
        regNum = regEdit.getText().toString();

        userDb = FirebaseDatabase.getInstance().getReference().child("Cars").child(userId).child(regNum);
        adminDb = FirebaseDatabase.getInstance().getReference().child("Car List").child(regNum);

        Map userInfo = new HashMap<>();

        userInfo.put("Make", make);
        userInfo.put("Model", model);
        userInfo.put("Registration Mumber", regNum);

        userDb.updateChildren(userInfo);
        adminDb.updateChildren(userInfo);
    }
}