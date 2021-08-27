package com.jyh.ex_0811;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ButtonEvent2Activity extends AppCompatActivity {


    Button btn1, btn2, btn3;
    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_event2);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        txt = findViewById(R.id.txt2);

        btn1.setOnClickListener(click);
        btn2.setOnClickListener(click);
        btn3.setOnClickListener(click);
    }//oncreate()

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(ButtonEvent2Activity.this, "버튼누름", Toast.LENGTH_SHORT).show();
            String str = "";
            switch (view.getId()) {
                case R.id.btn1:
                    str = "나는 버튼 1";
                    break;
                case R.id.btn2:
                    str = "나는 버튼 2";
                    break;
                case R.id.btn3:
                    str = "나는 버튼 3";
                    break;


            }//SWITCH
            txt.setText(str);
        }
    };
}