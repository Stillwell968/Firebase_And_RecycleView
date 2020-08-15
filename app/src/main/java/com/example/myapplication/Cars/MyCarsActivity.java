package com.example.myapplication.Cars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyCarsActivity extends AppCompatActivity {
    private RecyclerView mRecicleView;
    private RecyclerView.Adapter mCarsdapter;
    private RecyclerView.LayoutManager mCarsLayout;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cars);

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mRecicleView = findViewById(R.id.recyclerView);

        mRecicleView.setNestedScrollingEnabled(false);
        mRecicleView.setHasFixedSize(true);
        mCarsLayout =new LinearLayoutManager(MyCarsActivity.this);
        mRecicleView.setLayoutManager(mCarsLayout);
        mCarsdapter = new CarsAdapter(getDatasetCars(), MyCarsActivity.this);
        mRecicleView.setAdapter(mCarsdapter);

        getRegNuber();
    }

    private void getRegNuber() {
        DatabaseReference regId = FirebaseDatabase.getInstance().getReference().child("Cars").child(userId);
        regId.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot id : snapshot.getChildren()){
                        FetchInformation(id.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void FetchInformation(String key) {
        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("Cars").child(userId).child(key);
        userDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String carId = snapshot.getKey();
                    String model = "";
                    String make = "";

                    if (snapshot.child("Make").getValue().toString() != null){
                        make = snapshot.child("Make").getValue().toString();
                    }
                    if (snapshot.child("Model").getValue().toString() != null){
                        model = snapshot.child("Model").getValue().toString();
                    }
                    if (snapshot.child("Registration Mumber").getValue().toString() != null){
                        carId = snapshot.child("Registration Mumber").getValue().toString();
                    }

                    CarsObject obj = new CarsObject(carId,make, model);
                    resultCars.add(obj);

                    mCarsdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private ArrayList<CarsObject> resultCars = new ArrayList<CarsObject>();
    private List<CarsObject> getDatasetCars() {
        return resultCars;
    }
}