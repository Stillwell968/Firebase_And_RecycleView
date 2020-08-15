package com.example.myapplication.Cars;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class CarsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView carModel, carMake, carRegId;

    public CarsViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        carModel = itemView.findViewById(R.id.carModel_txt);
        carMake = itemView.findViewById(R.id.carMake_txt);
        carRegId = itemView.findViewById(R.id.carReg_num);
    }

    @Override
    public void onClick(View view) {

    }
}
