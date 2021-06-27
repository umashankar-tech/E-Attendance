package com.example.collegeattendance.studentpart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.collegeattendance.R;
import com.example.collegeattendance.SubjectList;

public class AttThreeYearAcrivity extends AppCompatActivity {
    String stream;
    String Rollno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.att_three_year_acrivity);
        Intent intent = getIntent();
        stream = intent.getStringExtra("stream");
        Rollno = intent.getStringExtra("rollno");
    }
    public void goTo2ndSemSecA(View view) {


        Intent intent = new Intent(this, AttSubjectlist.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","First Year");
        intent.putExtra("semester","SEM-II");
        intent.putExtra("section","Section-A");
        intent .putExtra("rollno",Rollno);

        startActivity(intent);



    }

    public void goTo2ndSemSecB(View view) {

        Intent intent = new Intent(this,AttSubjectlist.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","First Year");
        intent.putExtra("semester","SEM-II");
        intent.putExtra("section","Section-B");
        intent .putExtra("rollno",Rollno);

        startActivity(intent);
    }
    public void goToThirdYear6thSemSecA(View view) {
        Intent intent = new Intent(this,AttSubjectlist.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","Third Year");
        intent.putExtra("semester","SEM-VI");
        intent.putExtra("section","Section-A");
        intent .putExtra("rollno",Rollno);


        startActivity(intent);

    }

    public void goToThirdYear6thSemSecB(View view) {
        Intent intent = new Intent(this,AttSubjectlist.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","Third Year");
        intent.putExtra("semester","SEM-VI");
        intent.putExtra("section","Section-B");
        intent .putExtra("rollno",Rollno);

        startActivity(intent);

    }

    public void goToSecYear4thSemSecB(View view) {
        Intent intent = new Intent(this,AttSubjectlist.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","Second Year");
        intent.putExtra("semester","SEM-IV");
        intent.putExtra("section","Section-B");
        intent .putExtra("rollno",Rollno);

        startActivity(intent);
    }

    public void goToSecYear4thSemSecA(View view) {
        Intent intent = new Intent(this,AttSubjectlist.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","Second Year");
        intent.putExtra("semester","SEM-IV");
        intent.putExtra("section","Section-A");
        intent .putExtra("rollno",Rollno);
        startActivity(intent);
    }

}