package com.example.twojo_a;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.twojo_a.retrofit.ApiService;
import com.example.twojo_a.retrofit.NetworkHelper;
import com.example.twojo_a.retrofit.dto.Resister;
import com.example.twojo_a.retrofit.dto.Status;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResisterActivity extends AppCompatActivity {
    private NetworkHelper networkHelper;
    private ApiService apiService;
    Button resisterEnter, resisterBack;
    EditText resi_Id, resi_pw, resi_name, resi_addr, resi_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resister);

        resisterEnter = findViewById(R.id.resisterEnter);
        resisterBack = findViewById(R.id.backResister);

        resi_Id = findViewById(R.id.resisterId);
        resi_pw = findViewById(R.id.resisterpw);
        resi_name = findViewById(R.id.resisterName);
        resi_addr = findViewById(R.id.resisterAddr);
        resi_age = findViewById(R.id.resisterAge);

        resisterEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = resi_Id.getText().toString();
                String pw = resi_pw.getText().toString();
                String name = resi_name.getText().toString();
                String addr = resi_addr.getText().toString();
                String age = resi_age.getText().toString();

                //로그인 정보 미입력 시
                if (id.trim().length() == 0 || id == null ||
                        pw.trim().length() == 0 || pw == null ||
                        name.trim().length() == 0 || name == null ||
                        addr.trim().length() == 0 || addr == null ||
                        age.trim().length() == 0 || age == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ResisterActivity.this);
                    builder.setTitle("알림")
                            .setMessage("회원가입정보가 비어있습니다.")
                            .setPositiveButton("확인", null)
                            .create()
                            .show();
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                } else {

                    ResisterResponse();
                }


            }
        });

    }

    public void ResisterResponse() {

        String ID = resi_Id.getText().toString().trim();
        String PW = resi_pw.getText().toString().trim();
        String NAME = resi_name.getText().toString().trim();
        String ADDR = resi_addr.getText().toString().trim();
        int age = Integer.parseInt(resi_age.getText().toString().trim());

        Resister resister = new Resister(ID, PW, NAME, ADDR, age);

        networkHelper = NetworkHelper.getInstance();
        apiService = NetworkHelper.getApiService();

        apiService.resisterUser(resister)
                .enqueue(new Callback<Status>() {

                    @Override
                    public void onResponse(Call<Status> call, Response<Status> response) {

                        Toast.makeText(ResisterActivity.this,
                                "회원가입 성공", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ResisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        ResisterActivity.this.finish();
                    }

                    @Override
                    public void onFailure(Call<Status> call, Throwable t) {
                        String s = t.getMessage();
                        Toast.makeText(ResisterActivity.this, s, Toast.LENGTH_LONG).show();

                    }
                });


    }
}