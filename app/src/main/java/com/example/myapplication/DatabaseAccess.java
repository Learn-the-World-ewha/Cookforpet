package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;
    Cursor c2 = null;

    //private constructor so that object creation from outside the class is avoided
    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    //to return the single instance of database
    public static DatabaseAccess getInstance(Context context){
        if (instance==null){
            instance=new DatabaseAccess(context);
        }
        return instance;
    }

    //to open the database
    public void open(){
        this.db = openHelper.getWritableDatabase();
    }

    //closing the database connection
    public void close(){
        if (db!=null){
            this.db.close();
        }
    }

    //now lets create a method to query and return the result from database

    public String getRecipeName(String code){
        c = db.rawQuery("select recipe_name from recipe where recipe_code = '"+code+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String recipeCode = c.getString(0);
            buffer.append(""+recipeCode);
        }
        return buffer.toString();
    }

    public String getRecipeImg(String code){
        c = db.rawQuery("select img_main from recipe where recipe_code = '"+code+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String recipeCode = c.getString(0);
            buffer.append(""+recipeCode);
        }
        return buffer.toString();
    }

    public ArrayList<String> getRecipeCode(String mat){
        c = db.rawQuery("SELECT recipe_code" + " FROM materials" +
                " WHERE mat_name='"+mat+"'order by recipe_code", new String[]{});
        ArrayList<String> code_list = new ArrayList<String>();
        while(c.moveToNext()){
            Integer recipeCode = c.getInt(0);
            code_list.add(recipeCode.toString());
        }
        return code_list;
    }

    public Integer getResultSum(String mat){
        c = db.rawQuery("select recipe_code from materials where mat_name='"+mat+"'", new String[]{});
        Integer count = c.getCount();

        return count;
    }

    public ArrayList<ArrayList<String>> getMatlist(String code){
        ArrayList<ArrayList<String>> Mat_list = new ArrayList<ArrayList<String>>();
        c = db.rawQuery("select mat_name, mat_qtt from materials where recipe_code = '"
                +code+"' order by mat_num", new String[]{});
        while(c.moveToNext()){
            ArrayList<String> tmp = new ArrayList<>();
            tmp.add(c.getString(0));
            tmp.add(c.getString(1));
            Mat_list.add(tmp);
        }
        return Mat_list;
    }

    public Integer getLikeSum(String code){
        Integer likesum;
        c = db.rawQuery("select like_sum from recipe where recipe_code = '"+code, new String[]{});
        likesum = c.getInt(0);
        return likesum;
    }
    public ArrayList<String> getRecipeInfo(String code){
        ArrayList<String> Recipe_Info = new ArrayList<String>();
        c = db.rawQuery("select recipe_name, recipe_sum, type, cook_time, tip, img_main, effect"+
                "from recipe where recipe_code = '"+code, new String[]{});
        while(c.moveToNext()) {
            Recipe_Info.add(c.getString(0));
            Recipe_Info.add(c.getString(1));
            Recipe_Info.add(c.getString(2));
            Recipe_Info.add(c.getString(3));
            Recipe_Info.add(c.getString(4));
            Recipe_Info.add(c.getString(5));
            Recipe_Info.add(c.getString(6));
        }
        return Recipe_Info;
    }

    public ArrayList<ArrayList<String>> getSteplist(String code){
        ArrayList<ArrayList<String>> Step_list = new ArrayList<ArrayList<String>>();
        c = db.rawQuery("select step_num, step_txt, img_step from step where recipe_code = '"
                +code+"' order by step_num", new String[]{});
        while(c.moveToNext()){
            ArrayList<String> tmp = new ArrayList<>();
            tmp.add(c.getString(0));
            tmp.add(c.getString(1));
            tmp.add(c.getString(2));
            Step_list.add(tmp);
        }
        return Step_list;
    }

    public ArrayList<ArrayList<String>> getRecipelist(ArrayList<String> codes){
        ArrayList<ArrayList<String>> Recipe_list = new ArrayList<ArrayList<String>>();

        for (int i=0; i<codes.size(); i++){
            String code = codes.get(i);
            c = db.rawQuery("select img_main, recipe_name from recipe where recipe_code = '"+code+
                    "'order by recipe_code", new String[]{});
            while(c.moveToNext()){
                ArrayList<String> one_rcp = new ArrayList<String>();
                one_rcp.add(c.getString(0));    //배열에 이미지 문자열 저장
                one_rcp.add(c.getString(1));    //배열에 레시피명 문자열 저장
                Recipe_list.add(one_rcp);
            }
//            String rcp_mat="";
//            c2 = db.rawQuery("select mat_name from materials where recipe_code = '"+code+
//                    "'order by mat_num", new String[]{});
//            while(c2.moveToNext()){
//                rcp_mat.concat(c2.getString(0));
//            }
//            one_rcp.add(rcp_mat);
        }
        return Recipe_list;
    }
}
