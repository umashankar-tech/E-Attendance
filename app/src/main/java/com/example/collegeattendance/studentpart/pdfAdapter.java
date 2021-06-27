package com.example.collegeattendance.studentpart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeattendance.R;
import com.example.collegeattendance.StudentAdapter;
import com.example.collegeattendance.StudentModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class pdfAdapter  extends  RecyclerView.Adapter<pdfAdapter.ViewHolder> {
    Context context;
    ArrayList<pdfModel> titles;


    public pdfAdapter(Context context, ArrayList<pdfModel> titles) {
        this.context = context;
        this.titles = titles;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pdf_viewer_recycle,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pdfTitle.setText(titles.get(position).getFileName());
        holder.pdfPath.setText(titles.get(position).getFilePath());

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView pdfTitle,pdfPath,pdfClassName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pdfTitle = itemView.findViewById(R.id.pdffileName1);
            pdfPath = itemView.findViewById(R.id.pdfpath2);

        }
    }

}
