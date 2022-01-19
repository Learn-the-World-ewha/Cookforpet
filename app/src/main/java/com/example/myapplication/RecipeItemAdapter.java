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

public class RecipeItemAdapter extends RecyclerView.Adapter<RecipeItemAdapter.ViewHolder> {
    ArrayList<RecipeItem> items = new ArrayList<RecipeItem>();
    OnRecipeItemClickListener listener;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_url;
        TextView rcp_txt;
        TextView mat_txt;

        public ViewHolder(View itemView, final OnRecipeItemClickListener listener){
            super(itemView);

            img_url = itemView.findViewById(R.id.img_rcp);
            rcp_txt = itemView.findViewById(R.id.txt_rcp);
            mat_txt = itemView.findViewById(R.id.mat_txt);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    int position = getAdapterPosition();
                        if (listener!=null){
                            listener.onItemClick(ViewHolder.this, view, position);
                        }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recipe_list, parent, false);

        return new RecipeItemAdapter.ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecipeItem item = items.get(position);

        Glide.with(holder.itemView.getContext())
                .load(item.getImg_url())
                .into(holder.img_url);

        holder.rcp_txt.setText(item.getRcp_txt());
        holder.mat_txt.setText(item.getMat_txt());
    }

    public void setOnItemClickListener(OnRecipeItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(RecipeItem item){
        items.add(item);
    }
    public void setItems(ArrayList<RecipeItem> items){
        this.items = items;
    }
    public RecipeItem getItem(int position){
        return items.get(position);
    }
    public void setITem(int position, RecipeItem item){
        items.set(position, item);
    }
}
