package com.example.collegeattendance.studentpart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.collegeattendance.R;
import com.example.collegeattendance.StreamActivity;
import com.example.collegeattendance.notification.ViewMessage;

public class StudentSubjectListToGetAttPer extends AppCompatActivity {
   EditText getRollNo;
   TextView txt;
   String rollno ,stream,year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_subject_list_to_get_att_per);

        Intent intent = getIntent();
        rollno = intent.getStringExtra("rollno");
        stream = intent.getStringExtra("stream");
        year = intent.getStringExtra("year");

    }

    public void gotoViewAttBySubjects(View view) {
        Intent intent = new Intent(StudentSubjectListToGetAttPer.this, AttSubjectlist.class);
        intent.putExtra("rollNo",rollno);
        intent.putExtra("stream",stream);
        intent.putExtra("year",year);
        startActivity(intent);
    }

    public void gotoDownloadPdf1(View view) {
        Intent intent =  new Intent(this,PdfViewer.class);
        startActivity(intent);

    }

    public void gotomsg(View view) {
        Intent intent =  new Intent(this, ViewMessage.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,StudentPartLogin.class);
        startActivity(intent);
    }

}