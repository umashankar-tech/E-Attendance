package com.example.collegeattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.collegeattendance.notification.PushNotification;
import com.example.collegeattendance.notification.SentMessage;
import com.example.collegeattendance.upload_pdff_ile.CreateExcelSheet;
import com.google.firebase.auth.FirebaseAuth;

public class AdminMangement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_mangement);
    }

    public void gotoAddFaculty(View view) {
        Intent intent = new Intent(AdminMangement.this,Batches.class);
        startActivity(intent);
    }

    public void goToTeacherCreation(View view) {
        Intent intent = new Intent(AdminMangement.this,TeacherCreation.class);
        startActivity(intent);
    }

    public void goToAddStudent(View view) {
        Intent intent = new  Intent(AdminMangement.this,Student.class);
        startActivity(intent);
    }

    public void goToAddSubjects(View view) {
        Intent intent = new Intent(AdminMangement.this,Subject.class);
        startActivity(intent);
    }

    public void logoutFromAdminManegement(View view) {
        FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(AdminMangement.this,WelcomeScreen.class);
        startActivity(intent);

    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Want to go back?Please Do LOGOUT", Toast.LENGTH_SHORT).show();
        return;
    }

    public void goToNotification(View view) {
        Intent intent = new Intent(AdminMangement.this, SentMessage.class);
        startActivity(intent);

    }

    public void goToCreateReport(View view) {
        Intent intent = new Intent(AdminMangement.this, CreateExcelSheet.class);
        startActivity(intent);
    }
}