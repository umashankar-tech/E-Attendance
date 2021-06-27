package com.example.collegeattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ThreeYearStream extends AppCompatActivity {
  String stream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three_year_stream);
        Intent intent = getIntent();
        stream = intent.getStringExtra("stream");
    }



    public void goTo2ndSemSecA(View view) {


            Intent intent = new Intent(this,SubjectList.class);
            intent.putExtra("stream",stream);
            intent.putExtra("year","First Year");
            intent.putExtra("semester","SEM-II");
            intent.putExtra("section","Section-A");
            startActivity(intent);



    }

    public void goTo2ndSemSecB(View view) {

        Intent intent = new Intent(this,SubjectList.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","First Year");
        intent.putExtra("semester","SEM-II");
        intent.putExtra("section","Section-B");

        startActivity(intent);
    }
    public void goToThirdYear6thSemSecA(View view) {
        Intent intent = new Intent(this,SubjectList.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","Third Year");
        intent.putExtra("semester","SEM-VI");
        intent.putExtra("section","Section-A");

        startActivity(intent);

    }

    public void goToThirdYear6thSemSecB(View view) {
        Intent intent = new Intent(this,SubjectList.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","Third Year");
        intent.putExtra("semester","SEM-VI");
        intent.putExtra("section","Section-B");
        startActivity(intent);

    }

    public void goToSecYear4thSemSecB(View view) {
        Intent intent = new Intent(this,SubjectList.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","Second Year");
        intent.putExtra("semester","SEM-IV");
        intent.putExtra("section","Section-B");
        startActivity(intent);
    }

    public void goToSecYear4thSemSecA(View view) {
        Intent intent = new Intent(this,SubjectList.class);
        intent.putExtra("stream",stream);
        intent.putExtra("year","Second Year");
        intent.putExtra("semester","SEM-IV");
        intent.putExtra("section","Section-A");
        startActivity(intent);
    }
}