package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class LikesActivity extends AppCompatActivity {

    private ImageButton imgBtn_rcp1;
    Intent intent;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch(curId){
            case R.id.menu_home:
                intent = new Intent(LikesActivity.this, SubMainActivity.class);
                startActivity(intent);  // activity 이동
                break;
            case R.id.menu_user:
                intent = new Intent(LikesActivity.this, UserActivity.class);
                startActivity(intent);  // activity 이동
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likes);

        imgBtn_rcp1 = findViewById(R.id.imgBtn_rcp1);
        imgBtn_rcp1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LikesActivity.this, RecipeActivity.class);
                startActivity(intent);  // activity 이동
            }
        });
    }
}