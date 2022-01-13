package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MatList_2 extends LinearLayout {
    private String text1;
    private String text2;
    TextView txt_mat;
    TextView txt_unit;

    public MatList_2(Context context) {
        super(context);
        init(context);
    }
    public MatList_2(Context context, AttributeSet attrs) {
        super(context,attrs);
        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.mat_list, this, true);

        txt_mat = findViewById(R.id.txt_mat);
        txt_unit = findViewById(R.id.txt_unit);
    }
    public String getTxt_mat() {
        return text1;
    }
    public String getTxt_unit() {
        return text2;
    }
    public void setTxt_mat(String mat){
        txt_mat.setText(mat);
    }
    public void setTxt_unit(String unit){
        txt_unit.setText(unit);
    }
}
