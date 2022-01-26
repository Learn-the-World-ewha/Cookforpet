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

public class RefrigItemAdapter extends RecyclerView.Adapter<RefrigItemAdapter.ViewHolder>{
    ArrayList<RefrigItem> items = new ArrayList<RefrigItem>();
    OnRefrigItemClickListener listener;


    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_url;
        TextView rcp_txt;
        TextView date_txt;

        public ViewHolder(View itemView, final OnRefrigItemClickListener listener){
            super(itemView);

            img_url = itemView.findViewById(R.id.img_rcp);
            rcp_txt = itemView.findViewById(R.id.txt_rcp);
            date_txt = itemView.findViewById(R.id.txt_date);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    int position = getAdapterPosition();
                    if (listener!=null){
                        listener.onItemClick(RefrigItemAdapter.ViewHolder.this, view, position);
                    }
                }
            });
        }
    }
    @NonNull
    @Override
    public RefrigItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.refrig_list, parent, false);

        return new RefrigItemAdapter.ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RefrigItemAdapter.ViewHolder holder, int position) {
        RefrigItem item = items.get(position);

        Glide.with(holder.itemView.getContext())
                .load(item.getImg_url())
                .into(holder.img_url);

        holder.rcp_txt.setText(item.getRcp_txt());
        holder.date_txt.setText(item.getDate_txt());
    }

    public void setOnItemClickListener(OnRefrigItemClickListener listener){
        this.listener = listener;
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(RefrigItem item){
        items.add(item);
    }
    public void setItems(ArrayList<RefrigItem> items){
        this.items = items;
    }
    public RefrigItem getItem(int position){
        return items.get(position);
    }
    public void setITem(int position, RefrigItem item){
        items.set(position, item);
    }
}
