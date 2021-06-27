package com.example.collegeattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.collegeattendance.notification.ViewMessage;
import com.example.collegeattendance.upload_pdff_ile.CreateExcelSheet;
import com.example.collegeattendance.upload_pdff_ile.UploadPdfFile;
import com.google.firebase.auth.FirebaseAuth;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class PageToTeacherLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_to_teacher_login);
    }
    public void goToStream(View view) {
        Intent intent = new Intent(this,StreamActivity.class);
        startActivity(intent);
    }

    public void gotoUploadPdfPage(View view) {
        Intent intent = new Intent(this, UploadPdfFile.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        StyleableToast.makeText(PageToTeacherLogin.this, "Want to go Back? Please Do Logout",R.style.customFailureToast).show();

        return;
    }

    public void teacheLogout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(PageToTeacherLogin.this,WelcomeScreen.class);
        startActivity(intent);
    }

    public void viewMessage(View view) {
        Intent intent = new Intent(PageToTeacherLogin.this, ViewMessage.class);
        startActivity(intent);

    }

    public void CreateExcel(View view) {
        Intent intent = new Intent(PageToTeacherLogin.this, CreateExcelSheet.class);
        startActivity(intent);
    }
}