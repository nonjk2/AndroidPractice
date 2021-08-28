package com.example.ex_0812;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class LotActivity extends AppCompatActivity {

    Button btn1, btn2, btn3 ,btn4, btn_re;
    TextView txt_result;
    int n;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lot);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn_re = findViewById(R.id.btn_re);
        txt_result = findViewById(R.id.txt_result);

        setRandom();

        btn1.setOnClickListener(click);
        btn2.setOnClickListener(click);
        btn3.setOnClickListener(click);
        btn4.setOnClickListener(click);


        btn_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRandom();
                txt_result.setText("결과");
            }
        });
    }


    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //view 객체는 현재 이벤트를 발생시킨 버튼객체
            String str = ((Button)view).getText().toString();//클릭한 버튼에 쓰여진 글자
            int res = Integer.parseInt(str);

            if (res == n){
                txt_result.setText("당첨!!");
            }else {
                txt_result.setText("꽝");
            }
        }
    };

    public void setRandom() {
        n= new Random().nextInt(4)+1;
    }
}
