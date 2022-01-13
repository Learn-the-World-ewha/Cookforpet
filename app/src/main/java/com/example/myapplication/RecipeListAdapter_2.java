package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeListAdapter_2 extends BaseAdapter {
    private Context mContext;
    private ArrayList<RecipeList_2> recipeList2s = new ArrayList<RecipeList_2>();

    public RecipeListAdapter_2(Context context){
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return recipeList2s.size();
    }

    @Override
    public Object getItem(int i) {
        return recipeList2s.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // recipe_list.xml 레이아웃을 inflate해서 참조획득
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.recipe_list, parent, false);
        }

        // recipe_list.xml 의 참조 획득
        ImageView img_rcp=(ImageView)convertView.findViewById(R.id.img_rcp);
        TextView txt_mat = (TextView)convertView.findViewById(R.id.txt_rcp);
        TextView txt_unit = (TextView)convertView.findViewById(R.id.txt_mat);
        RecipeList_2 recipeList2 = recipeList2s.get(position);

        // 가져온 데이터를 텍스트뷰에 입력
        img_rcp.setImageBitmap(recipeList2.getImg_rcp());
        txt_mat.setText(recipeList2.getTxt_rcp());
        txt_unit.setText(recipeList2.getTxt_mat());

        return convertView;
    }
    public void addItem(Bitmap image, String text1, String text2){
        RecipeList_2 recipeList2 = new RecipeList_2(mContext);
        recipeList2.setImg_rcp(image);
        recipeList2.setTxt_rcp(text1);
        recipeList2.setTxt_mat(text2);

        recipeList2s.add(recipeList2);
    }

}
