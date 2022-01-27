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

    public ArrayList<String> getRecipeCode(String mat, int num){
        String type;
        if (num==1){
            c = db.rawQuery("select recipe_code from materials where mat_name='"+mat+"' order by recipe_code", new String[]{});
        } else if (num==2){
            type = "멍키친";
            c = db.rawQuery("select A.recipe_code from (select recipe_code from materials where mat_name='"+mat
                            +"') as A inner join (select recipe_code from recipe where type='"+type+"') as B on A.recipe_code = B.recipe_code",
                    new String[]{});
        } else if (num==3){
            type = "냥키친";
            c = db.rawQuery("select A.recipe_code from (select recipe_code from materials where mat_name='"+mat
                            +"') as A inner join (select recipe_code from recipe where type='"+type+"') as B on A.recipe_code = B.recipe_code",
                    new String[]{});
        }

        ArrayList<String> code_list = new ArrayList<String>();
        while(c.moveToNext()){
            Integer recipeCode = c.getInt(0);
            code_list.add(recipeCode.toString());
        }
        return code_list;
    }



    public Integer getResultSum(String mat, int num){
        String type;
        if (num==1){
            c = db.rawQuery("select recipe_code from materials where mat_name='"+mat+"'", new String[]{});
        } else if (num==2){
            type = "멍키친";
            c = db.rawQuery("select A.recipe_code from (select recipe_code from materials where mat_name='"+mat
                    +"') as A inner join (select recipe_code from recipe where type='"+type+"') as B on A.recipe_code = B.recipe_code",
                    new String[]{});
        } else if (num==3){
            type = "냥키친";
            c = db.rawQuery("select A.recipe_code from (select recipe_code from materials where mat_name='"+mat
                            +"') as A inner join (select recipe_code from recipe where type='"+type+"') as B on A.recipe_code = B.recipe_code",
                    new String[]{});
        }
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

    public String getLikeSum(String code){
        Integer likesum=0;
        c = db.rawQuery("select like_sum from recipe where recipe_code = '"+code, new String[]{});
        while(c.moveToNext()) {
            likesum = c.getInt(0);
        }
        return likesum.toString();
    }

    public ArrayList<ArrayList<String>> getSteplist(String code){
        ArrayList<ArrayList<String>> Step_list = new ArrayList<ArrayList<String>>();
        c = db.rawQuery("select step_txt, img_step from step where recipe_code = '"
                +code+"' order by step_num", new String[]{});
        Integer i = 0;
        while(c.moveToNext()){
            i++;
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add(i.toString());                  //step번호 저장
            tmp.add(c.getString(0));    //step_txt 저장
            tmp.add(c.getString(1));    //img_step 저장
            Step_list.add(tmp);
        }
        return Step_list;
    }

    public ArrayList<ArrayList<String>> getRecipelist(ArrayList<String> codes){
        ArrayList<ArrayList<String>> Recipe_list = new ArrayList<ArrayList<String>>();

        for (int i=0; i<codes.size(); i++){
            String code = codes.get(i);
            c = db.rawQuery("select img_main, recipe_name, recipe_sum, type, cook_time, tip, effect, like_sum"
                    + " from recipe where recipe_code = '"+code+ "'order by recipe_code", new String[]{});
            while(c.moveToNext()){
                ArrayList<String> one_rcp = new ArrayList<String>();
                one_rcp.add(c.getString(0));    //배열에 이미지 문자열 저장
                one_rcp.add(c.getString(1));    //배열에 레시피명 문자열 저장
                one_rcp.add(c.getString(2));    //배열에 레시피 요약문 저장
                one_rcp.add(c.getString(3));    //배열에 레시피 종류 저장
                one_rcp.add(c.getString(4));    //배열에 조리시간 저장
                one_rcp.add(c.getString(5));    //배열에 팁 저장
                one_rcp.add(c.getString(6));    //배열에 효과 저장
                Integer tmp = c.getInt(7);
                one_rcp.add(tmp.toString());                //배열에 좋아요수 저장

                StringBuilder rcp_mat = new StringBuilder();
                c2 = db.rawQuery("select mat_name from materials where recipe_code = '"+code+
                        "'order by mat_num", new String[]{});
                while(c2.moveToNext()){
                    rcp_mat.append(c2.getString(0));
                    rcp_mat.append(", ");
                }
                one_rcp.add(rcp_mat.toString());            //배열에 재료목록 저장

                Recipe_list.add(one_rcp);
            }
        }
        return Recipe_list;
    }

    public ArrayList<ArrayList<String>> getCodeDate(String id){
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        c = db.rawQuery("select recipe_code, cook_date from cook where user_code='"+id+"' order by recipe_code", new String[]{});
        while(c.moveToNext()){
            ArrayList<String> tmp = new ArrayList<>();
            tmp.add(c.getString(0));
            tmp.add(c.getString(1));
            list.add(tmp);
        }
        return list;
    }
    public Integer getRefResultSum(String id){
        c = db.rawQuery("select recipe_code from cook where user_code='"+id+ "'", new String[]{});
        Integer count = c.getCount();
        return count;
    }
    public ArrayList<ArrayList<String>> getRefrigList(ArrayList<ArrayList<String>> lists){
        ArrayList<ArrayList<String>> Refrig_list = new ArrayList<ArrayList<String>>();
        String code;
        for (int i=0; i<lists.size(); i++){
            for (int j=0; j<lists.get(i).size(); j++){
                ArrayList<String> one_rcp = new ArrayList<String>();
                code = lists.get(i).get(0);
                c = db.rawQuery("select img_main, recipe_name, type, tip from recipe where recipe_code='"
                +code+"'", new String[]{});
                while(c.moveToNext()){
                    one_rcp.add(c.getString(0));
                    one_rcp.add(c.getString(1));
                    one_rcp.add(c.getString(2));
                    one_rcp.add(c.getString(3));
                }
                Refrig_list.add(one_rcp);
            }
        }
        return Refrig_list;
    }

    public Integer getUserLikeSum(String id){
        c = db.rawQuery("select recipe_code from visit where user_code='"+id+"' and like=1", new String[]{});
        Integer count = c.getCount();
        return count;
    }

    public ArrayList<String> getUserLikeRecipe(String id){
        c = db.rawQuery("select recipe_code from visit where user_code='"+id+"' and like=1", new String[]{});

        ArrayList<String> code_list = new ArrayList<String>();
        while(c.moveToNext()){
            Integer recipeCode = c.getInt(0);
            code_list.add(recipeCode.toString());
        }
        return code_list;
    }
}
