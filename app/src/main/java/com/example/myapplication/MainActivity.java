package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.Cars.MyCarsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createCar(View view) {
        startActivity(new Intent(getApplicationContext(), CreateCarActivity.class));
    }

    public void viewCar(View view) {
        startActivity(new Intent(getApplicationContext(), MyCarsActivity.class));
    }
}