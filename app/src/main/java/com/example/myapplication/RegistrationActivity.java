package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private Button mRegBtn, mLoginBtn;
    private EditText emailImput, passInput, usernameInput;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mRegBtn = findViewById(R.id.reg_btn);
        mLoginBtn = findViewById(R.id.login_btn);

        usernameInput = findViewById(R.id.userName_txt);
        emailImput = findViewById(R.id.email_txt);
        passInput = findViewById(R.id.password_txt);

        mAuth = FirebaseAuth.getInstance();

        //when there is a current user already loged in
        //go to main activity and logout first
        firebaseAuth = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user !=null){
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    finish();
                    return;
                }
            }
        };


        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = emailImput.getText().toString();
                final String password = passInput.getText().toString();
                final String name = usernameInput.getText().toString();

                if (email.isEmpty() && password.isEmpty()){
                    Toast.makeText(RegistrationActivity.this,"email or pass empty",
                            Toast.LENGTH_LONG).show();
                }else{
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(RegistrationActivity.this, "error sighning up", Toast.LENGTH_LONG).show();
                            }else{
                                String userId= mAuth.getCurrentUser().getUid();

                                DatabaseReference currentUid = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
                                Map userInfo = new HashMap<>();
                                userInfo.put("Name", name);

                                currentUid.updateChildren(userInfo);

                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));

                            }
                        }
                    });
                }
            }
        });
    }

    public void logIn(View view) {
        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}