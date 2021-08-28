package com.example.ex_0813;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class PopupMenuActivity extends AppCompatActivity {
    Button btn_show;
    Button anchor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu);

        anchor = findViewById(R.id.anchor);

        btn_show = findViewById(R.id.btn_show);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup = new PopupMenu(PopupMenuActivity.this, anchor);//context : 화면 제어권자);


                //팝업 메뉴에 my_menu.xml리소스를 참조시킨다
                getMenuInflater().inflate(R.menu.my_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(itemClick);

                popup.show();

            }
        });

    }

    PopupMenu.OnMenuItemClickListener itemClick = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()) {

                case R.id.menu1:

                    Toast.makeText(PopupMenuActivity.this, "menu1", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu2:
                    Toast.makeText(PopupMenuActivity.this, "menu2", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu3:
                    break;


            }

            return false;
        }
    };

    //뒤로가기버튼 클릭 감지
    @Override
    public void onBackPressed() {
        //super.onBackPressed(); 이걸없애면 뒤로가기버튼 두번눌러서 finish(); 사용불가
    }
}