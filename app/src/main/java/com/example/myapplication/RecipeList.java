package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecipeList extends LinearLayout {
    ImageView img_rcp;
    TextView txt_rcp;
    TextView txt_mat;

    public RecipeList(Context context) {
        super(context);
        init(context);
    }
    public RecipeList(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.recipe_list, this, true);

        img_rcp = findViewById(R.id.img_rcp);
        txt_rcp = findViewById(R.id.txt_rcp);
        txt_mat = findViewById(R.id.txt_mat);
    }
    public void setImg_rcp(int resId){
        img_rcp.setImageResource(resId);
    }
    public void setTxt_rcp(String rcp){
        txt_rcp.setText(rcp);
    }
    public void setTxt_mat(String mat){
        txt_mat.setText(mat);
    }

}
