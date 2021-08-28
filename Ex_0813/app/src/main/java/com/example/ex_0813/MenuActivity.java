package com.example.ex_0813;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }


    //메뉴 클릭 이벤트 감지자자

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu1:
                Toast.makeText(MenuActivity.this, "추가 기능", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu2:
                Toast.makeText(MenuActivity.this, "수정 기능", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu3:
                finish();
                break;

        }

        return true;
    }
}