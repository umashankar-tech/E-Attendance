package com.example.collegeattendance;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BactheAdapter  extends RecyclerView.Adapter<BactheAdapter.ViewHolder> {

   ArrayList<BatchModel> titles;
   ArrayList<String> getStream;
    String stream;
   Context context;

    LayoutInflater inflater ;
    String batchvalue;

    public BactheAdapter(Context context, ArrayList<String> getStream,ArrayList<BatchModel> titles) {
         this.context = context;
         this.getStream = getStream;
        this.titles = titles;
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.new_batch_recycler_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(titles.get(position).getBatch());
         stream = getStream.get(0);

        switch(stream){
            case "3 Year Stream":{
                holder.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        batchvalue = titles.get(position).getBatch();

                        Intent intent = new Intent(context, ThreeYearStream.class);
                        intent.putExtra("stream", stream);
                        intent.putExtra("batch",batchvalue);
                        context.startActivity(intent);

                    }
                });
            }
            break;
            case "5 Year Stream":{
                holder.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        batchvalue = titles.get(position).getBatch();
                        Intent intent = new Intent(context, FiveYearStream.class);
                        intent.putExtra("stream", stream);
                        intent.putExtra("batch",batchvalue);
                        context.startActivity(intent);

                    }
                });

            }
            break;
            default:{
                Toast.makeText(context, "Something Went Wrong ", Toast.LENGTH_SHORT).show();
            }


        }



    }

    @Override
    public int getItemCount() {
        return titles.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.batchesTextView2);

        }
    }
}
