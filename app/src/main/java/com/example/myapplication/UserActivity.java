package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class UserActivity extends AppCompatActivity {

    RefrigFragment rfragment;
    LikesFragment lfragment;

    Intent intent;
    DatabaseAccess dbAc;
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
                intent = new Intent(UserActivity.this, SubMainActivity.class);
                startActivity(intent);  // activity 이동
                break;
            case R.id.menu_user:
                intent = new Intent(UserActivity.this, UserActivity.class);
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
        setContentView(R.layout.activity_user);

        rfragment = new RefrigFragment();
        lfragment = new LikesFragment();

        dbAc = DatabaseAccess.getInstance(getApplicationContext());
        dbAc.open();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, rfragment).commit();

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("My Refrigerator"));
        tabs.addTab(tabs.newTab().setText("Likes"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                Log.d("UserActivity", "선택된 탭: "+pos);

                Fragment selected = null;
                if (pos==0){
                    selected = rfragment;
                } else if (pos==1){
                    selected = lfragment;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selected).commit();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}