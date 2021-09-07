package com.example.twojo_a;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twojo_a.retrofit.dto.Cheting;
import com.example.twojo_a.retrofit.dto.Code;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Cheting> mChetData = null;

    public MessageAdapter(List<Cheting> dataList){

        this.mChetData = dataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (viewType == Code.ViewTpye.RIGHT_CONTENT) {
            view = inflater.inflate(R.layout.right_message, parent, false);
            return new LeftViewHolder(view);


        }else {
            view = inflater.inflate(R.layout.left_message, parent, false);
            return new RightViewHolder(view);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof LeftViewHolder){
            ((LeftViewHolder)viewHolder).textv_msg.setText(mChetData.get(position).getMessage());
        }else{
            ((RightViewHolder)viewHolder).textv_msg.setText(mChetData.get(position).getMessage());
        }
    }


    @Override
    public int getItemCount() {
//        if (mChetData == null){
//            return 0;
//        }else

            return mChetData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mChetData.get(position).getViewType();
    }

    public class LeftViewHolder extends RecyclerView.ViewHolder{

        TextView textv_nicname;
        TextView textv_msg;
        TextView textv_time;

        public LeftViewHolder(@NonNull View itemView) {
            super(itemView);
            textv_nicname = (TextView)itemView.findViewById(R.id.textv_nicname);
            textv_msg = (TextView)itemView.findViewById(R.id.textv_msg);
            textv_time = (TextView)itemView.findViewById(R.id.textv_time);

        }
    }

    public class RightViewHolder extends RecyclerView.ViewHolder{
        TextView textv_msg;
        TextView textv_time;

        public RightViewHolder(@NonNull View itemView) {
            super(itemView);
            textv_msg = (TextView)itemView.findViewById(R.id.textv_msg);
            textv_time = (TextView)itemView.findViewById(R.id.textv_time);
        }
    }






}
