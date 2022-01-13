package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MaterialItemAdapter extends RecyclerView.Adapter<MaterialItemAdapter.ViewHolder>{
    ArrayList<MaterialItem> items = new ArrayList<MaterialItem>();

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mat_name;
        TextView mat_qtt;

        public ViewHolder(View itemView){
            super(itemView);

            mat_name = itemView.findViewById(R.id.txt_mat);
            mat_qtt = itemView.findViewById(R.id.txt_unit);
        }

        public void setItem(MaterialItem item){
            mat_name.setText(item.getMat_name());
            mat_qtt.setText(item.getMat_qtt());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.mat_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MaterialItem item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(MaterialItem item){
        items.add(item);
    }
    public void setItems(ArrayList<MaterialItem> items){
        this.items = items;
    }
    public MaterialItem getItem(int position){
        return items.get(position);
    }
    public void setITem(int position, MaterialItem item){
        items.set(position, item);
    }

}
