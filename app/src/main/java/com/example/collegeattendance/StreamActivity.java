package com.example.collegeattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StreamActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stream);

    }

    public void goToThreeYearStream(View view) {

            Intent intent = new Intent(this,ThreeYearStream.class);
            intent.putExtra("stream","3 Year Stream");
            startActivity(intent);


    }

    public void goToFiveYearStream(View view) {

            Intent intent = new Intent(this,FiveYearStream.class);
            intent.putExtra("stream","5 Year Stream");
            startActivity(intent);


    }

    public void goBack(View view) {
        Intent intent = new Intent(this,WelcomeScreen.class);
        startActivity(intent);
    }
}