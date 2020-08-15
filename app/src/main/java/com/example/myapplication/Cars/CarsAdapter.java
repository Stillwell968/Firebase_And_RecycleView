package com.example.myapplication.Cars;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarsViewHolder> {
    private List<CarsObject> carsObjectList;
    private Context context;

    public CarsAdapter(List<CarsObject> carsObjectList, Context context){
        this.carsObjectList = carsObjectList;
        this.context = context;
    }
    @NonNull
    @Override
    public CarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cars, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutView.setLayoutParams(layoutParams);
        CarsViewHolder carsViewHolder = new CarsViewHolder(layoutView);

        return carsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarsViewHolder holder, int position) {
        holder.carMake.setText(carsObjectList.get(position).getCarmake());
        holder.carModel.setText(carsObjectList.get(position).getCarModel());
        holder.carRegId.setText(carsObjectList.get(position).getRegNumber());

    }

    @Override
    public int getItemCount() {
        return this.carsObjectList.size();
    }
}
