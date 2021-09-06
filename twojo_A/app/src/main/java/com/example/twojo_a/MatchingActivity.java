package com.example.twojo_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.twojo_a.retrofit.ApiService;
import com.example.twojo_a.retrofit.NetworkHelper;
import com.example.twojo_a.retrofit.dto.Authorization;
import com.example.twojo_a.retrofit.dto.Cheting;
import com.example.twojo_a.retrofit.dto.MatchingRoom;
import com.example.twojo_a.retrofit.dto.Status;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchingActivity extends AppCompatActivity {
    Button matching_btn;
    private NetworkHelper networkHelper;
    private ApiService apiService;
    TextView textView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);
        progressBar = findViewById(R.id.progressbar);
        matching_btn = findViewById(R.id.matching);
//        textView.setText(getPreferenceString("token",);
        matching_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                matching_btn.setVisibility(matching_btn.INVISIBLE);
                progressBar.setVisibility(progressBar.VISIBLE);
                String token = getPreferenceString("token");

                Authorization authorization = new Authorization(token);

                networkHelper = NetworkHelper.getInstance();
                apiService = NetworkHelper.getApiService();

                apiService.matching(token)
                        .enqueue(new Callback<MatchingRoom>() {
                            @Override
                            public void onResponse(Call<MatchingRoom> call, Response<MatchingRoom> response) {
                                Toast.makeText(MatchingActivity.this,
                                        getPreferenceString("token"), Toast.LENGTH_LONG).show();

                                MatchingRoom result = response.body();
                                int room_idx = result.getRoom_idx();

                                Intent intent = new Intent(MatchingActivity.this, ChetActivity.class);
                                intent.putExtra("room_idx",room_idx);
                                startActivity(intent);
                                MatchingActivity.this.finish();
                            }

                            @Override
                            public void onFailure(Call<MatchingRoom> call, Throwable t) {

                            }
                        });
            }
        });


    }
    public String getPreferenceString(String key) {
        SharedPreferences pref = getSharedPreferences(key, MODE_PRIVATE);
        return pref.getString(key, "");
    }
}