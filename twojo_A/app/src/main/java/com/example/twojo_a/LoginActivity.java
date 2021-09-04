package com.example.twojo_a;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.twojo_a.retrofit.ApiService;
import com.example.twojo_a.retrofit.NetworkHelper;
import com.example.twojo_a.retrofit.dto.Authorization;
import com.example.twojo_a.retrofit.dto.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private NetworkHelper networkHelper;
    private ApiService apiService;
    Button loginButton , resisterButton;
    EditText id_text, pw_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resisterButton = findViewById(R.id.resister);
        loginButton = findViewById(R.id.loginApp1);
        id_text = findViewById(R.id.id);
        pw_text = findViewById(R.id.pw);



        resisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, ResisterActivity.class);
                startActivity(intent);

            }
        });



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = id_text.getText().toString();
                String pw = pw_text.getText().toString();


                //로그인 정보 미입력 시
                if (id.trim().length() == 0 || pw.trim().length() == 0 || id == null || pw == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("알림")
                            .setMessage("로그인 정보를 입력바랍니다.")
                            .setPositiveButton("확인", null)
                            .create()
                            .show();
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                } else {

                    LoginResponse();
                }
            }
        });
    }

    //함수생성
    public void LoginResponse() {

        //에딧텍스트안에 유저 아이디 비번저장
        String userID = id_text.getText().toString().trim();
        String userPassword = pw_text.getText().toString().trim();

        //login dto에 저장
        Login loginRequest = new Login(userID, userPassword);

        //retrofit 생성
        networkHelper = NetworkHelper.getInstance();
        apiService = NetworkHelper.getApiService();


        apiService.getLoginResponse(loginRequest)
                .enqueue(new Callback<Authorization>() {
                    @Override
                    public void onResponse(Call<Authorization> call, Response<Authorization> response) {
                        //통신 성공
                        if (response.isSuccessful() && response.body() != null) {

                            //response.body()를 result에 저장
                            Authorization result = response.body();

                            //받은 코드 저장
                            String resultCode = result.getResultCode();

                            //받은 토큰 저장
                            String token = response.headers().get("Authorization");
                            String success = "200"; //로그인 성공
                            String errorId = "300"; //아이디 일치x
                            String errorPw = "400"; //비밀번호 일치x

                            if (resultCode.equals(success)) {
                                String userID = id_text.getText().toString();
                                String userPassword = pw_text.getText().toString();

                                //다른 통신을 하기 위해 token 저장
                                setPreference("token", token);
                                Toast.makeText(LoginActivity.this, resultCode, Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(LoginActivity.this, MatchingActivity.class);
                                intent.putExtra("userId", userID);
                                startActivity(intent);
                                LoginActivity.this.finish();
                            } else if (resultCode.equals(errorId)) {

                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setTitle("알림")
                                        .setMessage("아이디가 존재하지 않습니다.\n")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();

                            } else if (resultCode.equals(errorPw)) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setTitle("알림")
                                        .setMessage("비밀번호가 일치하지 않습니다.\n")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();
                            } else {

                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setTitle("알림")
                                        .setMessage("예기치 못한 오류가 발생하였습니다.\n")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Authorization> call, Throwable t) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setTitle("알림")
                                .setMessage("아이디와 비밀번호를 확인해주세요.\n")
                                .setPositiveButton("확인", null)
                                .create()
                                .show();

                    }
                });
    }

    //데이터를 내부 저장소에 저장하기
    public void setPreference(String key, String value) {
        SharedPreferences pref = getSharedPreferences("token", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    //내부 저장소에 저장된 데이터 가져오기
    public String getPreferenceString(String key) {
        SharedPreferences pref = getSharedPreferences("token", MODE_PRIVATE);
        return pref.getString(key, "");
    }


}