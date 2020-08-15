package com.example.myapplication.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminLoginActivity extends AppCompatActivity {
    private Button mRegBtn, mLoginBtn;
    private EditText emailInput, passInput;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        mRegBtn = findViewById(R.id.reg_btn);
        mLoginBtn = findViewById(R.id.login_btn);

        emailInput = findViewById(R.id.email_txt);
        passInput = findViewById(R.id.password_txt);

        mAuth = FirebaseAuth.getInstance();

        //when there is a current user already loged in
        //go to main activity and logout first
        firebaseAuth = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user !=null){
                    startActivity(new Intent(getApplicationContext(), MainAdminActivity.class));
                    finish();
                    return;
                }
            }
        };
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = emailInput.getText().toString();
                final String password = passInput.getText().toString();

                if (email.isEmpty() && password.isEmpty()){
                    Toast.makeText(AdminLoginActivity.this,"email or pass empty",
                            Toast.LENGTH_LONG).show();
                }else{
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                startActivity(new Intent(getApplicationContext(), MainAdminActivity.class));
                            }else{
                                Toast.makeText(AdminLoginActivity.this,"error loging in",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuth);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuth);
    }


    public void reg(View view) {
        startActivity(new Intent(getApplicationContext(), AdminRegActivity.class));
    }
}