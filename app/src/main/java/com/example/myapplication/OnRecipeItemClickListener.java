package com.example.myapplication;

import android.view.View;

public interface OnRecipeItemClickListener {
    public void onItemClick(RecipeItemAdapter.ViewHolder holder, View view, int position);
}
