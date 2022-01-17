package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class StepItemAdapter extends RecyclerView.Adapter<StepItemAdapter.ViewHolder>{
    ArrayList<StepItem> items = new ArrayList<>();
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView step_num;
        TextView step_txt;
        ImageView step_img;

        public ViewHolder(View itemView){
            super(itemView);

            step_num = itemView.findViewById(R.id.txt_stepNum);
            step_txt = itemView.findViewById(R.id.txt_step);
            step_img = itemView.findViewById(R.id.img_step);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.step_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StepItemAdapter.ViewHolder holder, int position) {
        StepItem item = items.get(position);

        Glide.with(holder.itemView.getContext())
                .load(item.getStep_img())
                .into(holder.step_img);

        holder.step_num.setText(item.getStep_num());
        holder.step_txt.setText(item.getStep_txt());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(StepItem item){
        items.add(item);
    }
    public void setItems(ArrayList<StepItem> items){
        this.items = items;
    }
    public StepItem getItem(int position){
        return items.get(position);
    }
    public void setITem(int position, StepItem item){
        items.set(position, item);
    }
}
