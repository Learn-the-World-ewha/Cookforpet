package com.example.myapplication;

import static android.view.View.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class DogsMainActivity extends AppCompatActivity {
    String val = "";
    private ImageButton imgBtn_search;
    private EditText editTxt_search;
    private String search;
    private ImageButton imgBtn_home;
    private ImageButton imgBtn_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs_main);

        editTxt_search = findViewById(R.id.editTxt_search);

        imgBtn_search = findViewById(R.id.imgBtn_search);
        imgBtn_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                search = editTxt_search.getText().toString();
                Intent intent = new Intent(DogsMainActivity.this, DogSearchResultActivity.class);
                intent.putExtra("search",search);
                startActivity(intent);  // activity 이동
            }
        });

        imgBtn_home = findViewById(R.id.imgBtn_home);
        imgBtn_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(DogsMainActivity.this, TypeActivity.class);
                startActivity(intent);  // activity 이동
            }
        });
        imgBtn_user = findViewById(R.id.imgBtn_user);
        imgBtn_user.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(DogsMainActivity.this, UserActivity.class);
                startActivity(intent);  // activity 이동
            }
        });

//    25~마지막줄은 수정해야 함. 미완성 레시피 추천 함수임.
//            public void getVal(); {
//
//                DatabaseHelper dbHelper = new DatabaseHelper(this);
//                SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//                Cursor cursor = db.rawQuery("SELECT * FROM recipe_ingredient where RECIPE_ID = 181",null);
//                //" and name = ?",new String[]{"홍길동"});
//                while (cursor.moveToNext())
//                {
//                    val += cursor.getString(2)+", ";
//
//                }
//                sqlresult.setText("재료: "+val);
//                cursor.close();
//                dbHelper.close();
//            }
    }
}