package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MatList extends LinearLayout{
    ImageView img_mat;
    TextView txt_mat;
    TextView txt_unit;

    public MatList(Context context) {
        super(context);
        init(context);
    }
    public MatList(Context context, AttributeSet attrs) {
        super(context,attrs);
        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.mat_list, this, true);

        img_mat = findViewById(R.id.img_mat);
        txt_mat = findViewById(R.id.txt_mat);
        txt_unit = findViewById(R.id.txt_unit);
    }
    public void setImg_mat(int resId){
        img_mat.setImageResource(resId);
    }
    public void setTxt_mat(String mat){
        txt_mat.setText(mat);
    }
    public void setTxt_unit(String unit){
        txt_unit.setText(unit);
    }
}
