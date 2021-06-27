package com.example.collegeattendance.studentpart;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeattendance.ListStudent;
import com.example.collegeattendance.R;
import com.example.collegeattendance.SubejctAdapter;
import com.example.collegeattendance.SubjectModel;

import java.util.ArrayList;

public class AttSubjectAdapter extends RecyclerView.Adapter<AttSubjectAdapter.ViewHolder>{
    ArrayList<SubjectModel> titles;
    Context context;
    ArrayList<String> getStreambatchYearValue;
    LayoutInflater inflater ;
    String subject;
    public AttSubjectAdapter(Context context,ArrayList<String> getStreambatchYearValue,ArrayList<SubjectModel> titles) {

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
        String Rollno = getStreambatchYearValue.get(2);

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject= titles.get(position).getSubjectname();
                Intent intent = new Intent(context, StudentAttPageViewer.class);
                intent.putExtra("stream",stream);
                intent.putExtra("year",year);
                intent.putExtra("subject",subject);
                intent.putExtra("rollNo",Rollno);
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
