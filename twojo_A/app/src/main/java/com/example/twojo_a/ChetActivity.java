package com.example.twojo_a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.twojo_a.retrofit.ApiService;
import com.example.twojo_a.retrofit.NetworkHelper;
import com.example.twojo_a.retrofit.dto.Cheting;
import com.example.twojo_a.retrofit.dto.ChetingInsert;
import com.example.twojo_a.retrofit.dto.Code;
import com.example.twojo_a.retrofit.dto.MatchingRoom;
import com.example.twojo_a.retrofit.dto.Result;
import com.example.twojo_a.retrofit.dto.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChetActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    List<Cheting> dataList;
    ImageButton insertButton;
    EditText insertText;
    private NetworkHelper networkHelper;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chet);


        insertText = findViewById(R.id.sendMessage);
        insertButton = findViewById(R.id.sendMessageButton);
        recyclerView = findViewById(R.id.recycleview2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChetActivity.this, RecyclerView.VERTICAL, true);
        recyclerView.setLayoutManager(linearLayoutManager);
        ExampleThread thread;
        Timer timer = new Timer();

        TimerTask TT = new TimerTask() {
            @Override
            public void run() {
                // 반복실행할 구문
                ChethingMessage();
            }

        };



        timer.schedule(TT, 0, 1000); //Timer 실행




        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getIntent();
                int room_idx = intent.getExtras().getInt("room_idx");
                String Message = insertText.getText().toString().trim();
                String token = getPreferenceString("token");
                ChetingInsert chetingInsert = new ChetingInsert(Message, room_idx);

                networkHelper = NetworkHelper.getInstance();
                apiService = NetworkHelper.getApiService();


                apiService.chetingInsert(token, chetingInsert).enqueue(new Callback<Status>() {
                    @Override
                    public void onResponse(Call<Status> call, Response<Status> response) {
                        Toast.makeText(ChetActivity.this,
                                "보내기완료", Toast.LENGTH_LONG).show();
                        ChethingMessage();
                        insertText.setText(null);
                    }

                    @Override
                    public void onFailure(Call<Status> call, Throwable t) {

                    }
                });


            }
        });


    }

    public void ChethingMessage() {


        Intent intent = getIntent();
        int room_idx = intent.getExtras().getInt("room_idx");

        MatchingRoom matchingRoom = new MatchingRoom(room_idx);

        networkHelper = NetworkHelper.getInstance();
        apiService = NetworkHelper.getApiService();
        SharedPreferences sharedPreferences = getSharedPreferences("mem_idx", 0);
        int member_idx = Integer.parseInt(sharedPreferences.getString("mem_idx", ""));
        Cheting cheting = null;
        apiService.chetinglist(matchingRoom).enqueue(new Callback<List<Cheting>>() {
            @Override
            public void onResponse(Call<List<Cheting>> call, Response<List<Cheting>> response) {

                dataList = response.body();

//                dataList = new ArrayList<>();

                for (int i = 0; i < dataList.size(); i++) {

                    if (member_idx == dataList.get(i).getMem_idx()) {
                        dataList.get(i).setViewType(Code.ViewTpye.RIGHT_CONTENT);
                    }

                }

                mAdapter = new MessageAdapter(dataList);
                recyclerView.setAdapter(mAdapter);


            }

            @Override
            public void onFailure(Call<List<Cheting>> call, Throwable t) {

                Log.d("MainActivity", "zzzzzzzzzzzzz");

            }
        });

    }

    public String getPreferenceString(String key) {
        SharedPreferences pref = getSharedPreferences(key, MODE_PRIVATE);
        return pref.getString(key, "");
    }

    public class ExampleThread extends Thread {

        private int threadNum;

        public ExampleThread(int threadNum) {
            this.threadNum = threadNum;

        }
    }

    public void run() {
        ChethingMessage();
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}