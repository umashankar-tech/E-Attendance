package com.example.collegeattendance.notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeattendance.R;

import java.util.ArrayList;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder>{
    ArrayList<MsgModel> titles;
    Context context;

    public MsgAdapter( Context context,ArrayList<MsgModel> titles) {
        this.titles = titles;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_content_layout,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.msgContent.setText(titles.get(position).getMsgContent());

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView msgDate,msgContent;
       public ViewHolder(@NonNull View itemView) {
           super(itemView);
              msgDate = 
              msgContent = itemView.findViewById(R.id.msgContentrcv1);

       }
   }
}
