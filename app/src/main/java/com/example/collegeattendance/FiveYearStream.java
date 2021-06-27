package com.example.collegeattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.internal.InternalTokenProvider;

public class FiveYearStream extends AppCompatActivity {
   String stream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.five_year_stream);
        Intent intent = getIntent();
        stream = intent.getStringExtra("stream");
    }

    public void goToSubjectFirstYear(View view) {
        Intent intent = new Intent(this,SubjectList.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","First Year");
        intent.putExtra("semester","SEM-II");
        intent.putExtra("section","Section-A");
        startActivity(intent);
    }

    public void goToSubjectSecondYear(View view) {
        Intent intent = new Intent(this,SubjectList.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","Second Year");
        intent.putExtra("semester","SEM-IV");
        intent.putExtra("section","Section-A");
        startActivity(intent);
    }

    public void goToSubjectThirdYear(View view) {
        Intent intent = new Intent(this,SubjectList.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","Third Year");
        intent.putExtra("semester","SEM-VI");
        intent.putExtra("section","Section-A");
        startActivity(intent);
    }

    public void goToSubjectFourthYear(View view) {
        Intent intent = new Intent(this,SubjectList.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","Fourth Year");
        intent.putExtra("semester","SEM-VIII");
        intent.putExtra("section","Section-A");
        startActivity(intent);
    }

    public void goToSubjectFifthYear(View view) {
        Intent intent = new Intent(this,SubjectList.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","Fifth Year");
        intent.putExtra("semester","SEM-X");
        intent.putExtra("section","Section-A");
        startActivity(intent);
    }
}