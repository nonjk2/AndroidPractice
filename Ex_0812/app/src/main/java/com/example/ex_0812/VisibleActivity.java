package com.example.ex_0812;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class VisibleActivity extends AppCompatActivity {

    Button back1, back2 ,back3 ,btn_bottom;
    ImageView img1, img2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visible);

        back1 = findViewById(R.id.back1);
        back2 = findViewById(R.id.back2);
        back3 = findViewById(R.id.back3);
        btn_bottom = findViewById(R.id.btn_bottom);

        img1 = findViewById(R.id.back1_img);
        img2 = findViewById(R.id.back2_img);

        back1.setOnClickListener(click);
        back2.setOnClickListener(click);
        back3.setOnClickListener(click);
    }
        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                switch (view.getId()){

                    case  R.id.back1:
                        img1.setVisibility(View.VISIBLE);
                        img2.setVisibility(View.INVISIBLE);
                        break;

                    case R.id.back2:
                        img1.setVisibility(View.INVISIBLE);
                        img2.setVisibility(View.VISIBLE);
                        break;

                    case R.id.back3:

                        if( btn_bottom.getVisibility() != View.VISIBLE){
                            btn_bottom.setVisibility(View.VISIBLE);
                        }else{
                            btn_bottom.setVisibility(View.GONE);
                        }
                        break;


                }

            }


    };
}