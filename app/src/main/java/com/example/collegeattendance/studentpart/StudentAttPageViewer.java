package com.example.collegeattendance.studentpart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeattendance.R;
import com.example.collegeattendance.StudentModel;
import com.example.collegeattendance.WelcomeScreen;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class StudentAttPageViewer extends AppCompatActivity {
    TextView totalPresent,totalAbsent,totalCounductedClass,percentage ,subjectName;

    String rollno;
    String subject;
    String totalPre;
    String totalAb;

    int size1 = 0;
    int size2 = 0;
    int percent;


    FirebaseFirestore firebaseFirestore ;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(StudentAttPageViewer.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setTitle("Attendance Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        setContentView(R.layout.student_att_page_viewer);
        totalPresent= findViewById(R.id.totalpresentcount);
        totalAbsent= findViewById(R.id.totalAbsentCount);
        totalCounductedClass =findViewById(R.id.totalCounductedClassesCount);
        subjectName = findViewById(R.id.subjectName);
        percentage= findViewById(R.id.totalpercentageInNo);
        firebaseFirestore = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        subject = intent.getStringExtra("subject");
        rollno= intent.getStringExtra("rollNo");
        subjectName.setText(subject);
        getAttCalC();

    }
    private void getAttCalC() {
        Query query = firebaseFirestore.collection("AttendanceRegister").whereEqualTo("RollNo",rollno).whereEqualTo("Subject",subject).whereEqualTo("AttStatus","present");
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

               if(queryDocumentSnapshots.isEmpty()){
                   totalPresent.setText("");

               }
               else{
                   size1 = queryDocumentSnapshots.size();
                   percent = size1;
                   totalPre = String.valueOf(size1);
                   totalPresent.setText(totalPre);
               }

            }


        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(StudentAttPageViewer.this, "unble to find", Toast.LENGTH_SHORT).show();
            }
        });


        Query query1 = firebaseFirestore.collection("AttendanceRegister").whereEqualTo("RollNo",rollno).whereEqualTo("Subject",subject).whereEqualTo("AttStatus","absent");
        query1.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(queryDocumentSnapshots.isEmpty()){
                    totalAbsent.setText("");
                    totalCounductedClass.setText("");

                }else{
                    size2 = queryDocumentSnapshots.size();
                    totalAb = String.valueOf(size2);
                    totalAbsent.setText(totalAb);
                    int total = size1+size2;
                    String conductedclasses = String.valueOf(total);
                    totalCounductedClass.setText(conductedclasses);
                }


            }
        }).addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                progressDialog.dismiss();
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,AttSubjectlist.class);
        startActivity(intent);
    }

    public void logout(View view) {
        FirebaseAuth firebaseAuth;
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        Intent intent = new Intent(StudentAttPageViewer.this, WelcomeScreen.class);
        startActivity(intent);
    }



    public void goback(View view) {
        Intent intent = new Intent(StudentAttPageViewer.this, AttSubjectlist.class);
        startActivity(intent);

    }
}