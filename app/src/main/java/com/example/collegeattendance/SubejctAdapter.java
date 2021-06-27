package com.example.collegeattendance;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubejctAdapter extends RecyclerView.Adapter<SubejctAdapter.ViewHolder>{

    ArrayList<SubjectModel> titles;
    Context context;
    ArrayList<String> getStreambatchYearValue;
    LayoutInflater inflater ;
    String subject ,subjectCode;

    public SubejctAdapter(Context context,ArrayList<String> getStreambatchYearValue,ArrayList<SubjectModel> titles) {
        this.titles = titles;
        this.context = context;
        this.getStreambatchYearValue = getStreambatchYearValue;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.subject_recylerview_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(titles.get(position).getSubjectname());
        String stream = getStreambatchYearValue.get(0);
        String year = getStreambatchYearValue.get(1);
        String semester = getStreambatchYearValue.get(2);
        String section = getStreambatchYearValue.get(3);



        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject= titles.get(position).getSubjectname();
                subjectCode= titles.get(position).getSubjectcode();
                Intent intent = new Intent(context,ListStudent.class);
                intent.putExtra("stream",stream);
                intent.putExtra("year",year);
                intent.putExtra("semester",semester);
                intent.putExtra("section",section);
                intent.putExtra("subject",subject);
                intent.putExtra("subjectCode",subjectCode);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.subjectTextView);

        }
    }
}
