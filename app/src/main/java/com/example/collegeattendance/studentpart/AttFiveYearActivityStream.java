package com.example.collegeattendance.studentpart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.collegeattendance.R;
import com.example.collegeattendance.SubjectList;

public class AttFiveYearActivityStream extends AppCompatActivity {
    String stream;
    String Rollno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.att_five_year);
        Intent intent = getIntent();
        stream = intent.getStringExtra("stream");
        Rollno = intent.getStringExtra("rollno");

    }
    public void goToSubjectFirstYear(View view) {
        Intent intent = new Intent(this, AttSubjectlist.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","FirstYear");
        intent.putExtra("semester","SEM-II");
        intent.putExtra("section","Section-A");
        intent .putExtra("rollno",Rollno);

        startActivity(intent);
    }

    public void goToSubjectSecondYear(View view) {
        Intent intent = new Intent(this,AttSubjectlist.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","Second Year");
        intent.putExtra("semester","SEM-IV");
        intent.putExtra("section","Section-A");
        intent .putExtra("rollno",Rollno);

        startActivity(intent);
    }

    public void goToSubjectThirdYear(View view) {
        Intent intent = new Intent(this,AttSubjectlist.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","Third Year");
        intent.putExtra("semester","SEM-VI");
        intent.putExtra("section","Section-A");
        intent .putExtra("rollno",Rollno);

        startActivity(intent);
    }

    public void goToSubjectFourthYear(View view) {
        Intent intent = new Intent(this,AttSubjectlist.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","Fourth Year");
        intent.putExtra("semester","SEM-VIII");
        intent.putExtra("section","Section-A");
        intent .putExtra("rollno",Rollno);

        startActivity(intent);
    }

    public void goToSubjectFifthYear(View view) {
        Intent intent = new Intent(this,AttSubjectlist.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","Fifth Year");
        intent.putExtra("semester","SEM-X");
        intent.putExtra("section","Section-A");
        intent .putExtra("rollno",Rollno);

        startActivity(intent);
    }
}