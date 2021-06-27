package com.example.collegeattendance.studentpart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.collegeattendance.FiveYearStream;
import com.example.collegeattendance.R;
import com.example.collegeattendance.ThreeYearStream;

public class ViewAttBySubjects extends AppCompatActivity {
    String Rollno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_att_by_subjects);
        Intent intent = getIntent();
        Rollno = intent.getStringExtra("rollNo");

    }
    public void goToThreeYearStream(View view) {


        Intent intent = new Intent(this, AttThreeYearAcrivity.class);
        intent.putExtra("stream","3 Year Stream");
        intent .putExtra("rollno",Rollno);
        startActivity(intent);


    }

    public void goToFiveYearStream(View view) {

        Intent intent = new Intent(this, AttFiveYearActivityStream.class);
        intent.putExtra("stream","5 Year Stream");
        intent .putExtra("rollno",Rollno);
        startActivity(intent);


    }
}