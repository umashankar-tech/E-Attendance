package com.example.collegeattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SharedMemory;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.Multimap;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListStudent extends AppCompatActivity {
    Date currentTime =Calendar.getInstance().getTime();
    TextView date;
    TextView subjectName,subjectCodeView;
    TextView totalPresentCount;
    String stream;
    String year;
    String section;
    String subject;
    String semester;
    String currentDate;
    String subjectCode;
    int lastPosition;
    int a = 0;

    RecyclerView recyclerView;
    ArrayList<StudentModel> datalist;
    ArrayList<String> dbBaseList;
    StudentAdapter   studentAdapter;
    FirebaseFirestore  firebaseFirestore;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.list_student);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        
        recyclerView = findViewById(R.id.stuRecyclerView);
        subjectName= findViewById(R.id.subjectview12);
        subjectCodeView = findViewById(R.id.subjectCode);
        date = findViewById(R.id.date);
        totalPresentCount= findViewById(R.id.attTotalCount);

        Calendar calendar = Calendar.getInstance();
        currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        date.setText(currentDate);

        Intent intent = getIntent();
        stream = intent.getStringExtra("stream");
        year = intent.getStringExtra("year");
        section = intent.getStringExtra("section");
        subject = intent.getStringExtra("subject");
        subjectCode = intent.getStringExtra("subjectCode");
        semester = intent.getStringExtra("semester");

        subjectName.setText(subject);
        subjectCodeView.setText(subjectCode);


        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        datalist = new ArrayList<>();
        dbBaseList = new ArrayList<>();
        studentAdapter = new StudentAdapter(  this,dbBaseList,datalist);

        //retrive lastposition onstart()
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        sharedPreferences.getInt("lastposition",0);
        recyclerView.scrollToPosition(lastPosition);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                lastPosition = linearLayoutManager.findFirstVisibleItemPosition();
            }
        });

        firebaseFirestore  = FirebaseFirestore.getInstance();
        Query query= firebaseFirestore.collection("student").whereEqualTo("stream",stream).whereEqualTo("section",section).whereEqualTo("year",year);

        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot d:list)
                {
                    StudentModel model = d.toObject(StudentModel.class);
                    datalist.add(model);
                }
                studentAdapter.notifyDataSetChanged();
            }
        });
        dbBaseList.add(stream);
        dbBaseList.add(section);
        dbBaseList.add(year);
        dbBaseList.add(subject);
        recyclerView.setAdapter(studentAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //save position on destroy
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putInt("lastposition",lastPosition);
        e.apply();

    }

    public void attendanceSubmitted(View view) {
        Map<String,String> attlist1 = new HashMap<>();
        ArrayList<String> studentname  = studentAdapter.studenname();
        ArrayList<String> studentrollno = studentAdapter.studerollno();
        ArrayList<String> presentStudentName = studentAdapter.presentstudentname();
        ArrayList<String> presentStudentRollno = studentAdapter.presentstudentrollno();
        int count = presentStudentRollno.size();
        firebaseFirestore = FirebaseFirestore.getInstance();
        for (int i = 0; i < studentname.size(); i++) {
                 attlist1.put("Name", studentname.get(i));
                 attlist1.put("RollNo", studentrollno.get(i));
                 attlist1.put("Stream", stream);
                 attlist1.put("section",section);
                 attlist1.put("semester",semester);
                 attlist1.put("Year", year);
                 attlist1.put("Subject", subject);
                 attlist1.put("subjectCode",subjectCode);
                 attlist1.put("Date", currentDate);
                 attlist1.put("Time", currentTime.toString());
                 attlist1.put("AttStatus", "absent");
                 firebaseFirestore.collection("AttendanceRegister").document(studentrollno.get(i)+"-"+currentTime.toString()).set(attlist1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ListStudent.this, "Pleae check Again Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }
        for (int i = 0; i < presentStudentRollno.size(); i++) {

            firebaseFirestore.collection("AttendanceRegister").document(presentStudentRollno.get(i) + "-" + currentTime.toString()).update("AttStatus", "present").addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ListStudent.this, "Pleae check Again Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            });

        }
        Intent intent = new Intent(ListStudent.this,PageToTeacherLogin.class);
        startActivity(intent);

    }


    public void setTotalPresentCount(){
            a +=1;
            String count =  String.valueOf(a);
        totalPresentCount.setText(count);
    }
    public void setTotalPresentCountsub(){
        a -=1;
        String count =  String.valueOf(a);
        totalPresentCount.setText(count);
    }
}