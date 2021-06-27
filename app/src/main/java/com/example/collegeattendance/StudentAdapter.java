
package com.example.collegeattendance;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.Multimap;
import com.google.common.io.LittleEndianDataInputStream;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>{
    ArrayList<StudentModel> titles;
    ArrayList<String> presentstudentname = new ArrayList<>();
    ArrayList<String> presentstudentrollno = new ArrayList<>();
    ArrayList<String>studentrollno = new ArrayList<>();
    ArrayList<String >studentname   = new ArrayList<>();

    ArrayList<String> dbBaseList;
    Context context;
    int a =0;


    FirebaseFirestore firebaseFirestore;
    Map<String,String> addAttendance = new HashMap<>();
    public StudentAdapter(  Context context,  ArrayList<String> dbBaseList,ArrayList<StudentModel> titles) {
        this.titles = titles;
        this.dbBaseList= dbBaseList;
        this.context = context;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_name_checkboxview,parent,false);
        return  new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        final StudentModel student =  titles.get(position);
        holder.checkBox.setText(student.getName());
        holder.stuRegNo.setText(student.getRollno());
        String rollno = student.getRollno();
        String name = student.getName();
        studentrollno.add(rollno);
        studentname.add(name);
        holder.checkBox.setChecked(student.getIsSelected());
        holder.checkBox.setTag(titles.get(position));
         holder.checkBox.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

              StudentModel model1 = (StudentModel) holder.checkBox.getTag();
                 model1.setIsSelected(holder.checkBox.isChecked());
                 titles.get(position).setIsSelected(holder.checkBox.isChecked());

                     if (holder.checkBox.isChecked() == true) {
                         ((ListStudent) context).setTotalPresentCount();
                         presentstudentname.add(student.getName());
                         presentstudentrollno.add(student.getRollno());

                     } else {
                         a -= 1;
                         ((ListStudent) context).setTotalPresentCountsub();
                         presentstudentname.remove(student.getName());
                         presentstudentrollno.remove(student.getRollno());
                     }

             }
         });
    }
    @Override
    public int getItemCount() {
        return titles.size();
    }

    public    ArrayList<String> studenname() {
        return studentname ;
    }
    public    ArrayList<String> studerollno() {
        return studentrollno ;
    }

    ////////////////
    public    ArrayList<String>  presentstudentname() {
        return  presentstudentname ;
    }
    public    ArrayList<String> presentstudentrollno () {
        return presentstudentrollno  ;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox checkBox;
        TextView stuRegNo ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.newcheckBox);
            stuRegNo = itemView.findViewById(R.id.sturegno);

        }
    }

}
