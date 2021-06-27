package com.example.collegeattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    EditText sturollno;
    EditText stuname;
    EditText studob;
    EditText stuMobileNo;
    EditText stuAlternateMobileNo;
    EditText stuEmail;
    EditText stupassword;
    Spinner stuyear;
    Spinner stustream;
    Spinner section;
    Button stuaddBatch;

    String [] array = new String[]{"3 Year Stream","5 Year Stream"};
    String [] threeYrStream = new String[]{"First Year","Second Year","Third Year"};
    String [] sectionlist = new String[]{"Section-A","Section-B"};
    String [] fiveYrStream = new String[]{"First Year","Second Year","Third Year" ,"Fourth Year","Fifth Year"};

    Map<String,String> batchMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        stuname     =  findViewById(R.id.stuname);
        studob      =  findViewById(R.id.studentdob);
        sturollno   =  findViewById(R.id.studentrolno);

        stuyear     =  findViewById(R.id.stuspinneryear);
        stustream   =  findViewById(R.id.stuspinnerstream);
        section     =  findViewById(R.id.stuspinnersection);

        stuMobileNo = findViewById(R.id.studentMobileNo);
        stuEmail    =  findViewById(R.id.studentEmailId);
        stupassword = findViewById(R.id.studentpasword);
        stuAlternateMobileNo= findViewById(R.id.studentalterrnateMobileNo);

        stuaddBatch =  findViewById(R.id.stuAddBtn);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        stustream.setAdapter(adapter);
        stustream.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = parent.getItemAtPosition(position).toString();
                Toast.makeText(Student.this, "ItemSelected"+spinnerValue, Toast.LENGTH_SHORT).show();
                batchMap.put("stream",spinnerValue);

                if (spinnerValue=="3 Year Stream")
                {

                    threeYearStream();
                }
                else
                {

                    fiveYearStream();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                Toast.makeText(Student.this, "Please select Stream", Toast.LENGTH_SHORT).show();

            }
        });
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,sectionlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        section.setAdapter(adapter2);
        section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = parent.getItemAtPosition(position).toString();
                Toast.makeText(Student.this, "ItemSelected"+spinnerValue, Toast.LENGTH_SHORT).show();
             batchMap.put("section",spinnerValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void threeYearStream() {

        ArrayAdapter<String>  adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,threeYrStream);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stuyear.setAdapter(adapter);
        stuyear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = parent.getItemAtPosition(position).toString();
                Toast.makeText(Student.this, "ItemSelected"+spinnerValue, Toast.LENGTH_SHORT).show();
                batchMap.put("year",spinnerValue);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void fiveYearStream() {



        ArrayAdapter<String>  adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,fiveYrStream);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stuyear.setAdapter(adapter);
        stuyear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = parent.getItemAtPosition(position).toString();
                Toast.makeText(Student.this, "ItemSelected"+spinnerValue, Toast.LENGTH_SHORT).show();
                batchMap.put("year",spinnerValue);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void addStudent(View view) {

        String Name =stuname.getText().toString();
        String RollNo = sturollno.getText().toString();
        String Dob = studob.getText().toString();
        String Mobile = stuMobileNo.getText().toString();
        String Email = stuEmail.getText().toString();
        String Password = stupassword.getText().toString();
        String AlternateMobileNo = stuAlternateMobileNo.getText().toString();

        batchMap.put("name",Name);
        batchMap.put("rollno",RollNo);
        batchMap.put("dob",Dob);
        batchMap.put("mobileno",Mobile);
        batchMap.put("alternateMobileno",AlternateMobileNo);
               batchMap.put("email",Email);
        batchMap.put("password",Password);
        firebaseFirestore.collection("student").document(RollNo).set(batchMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                StyleableToast.makeText(Student.this, "Student Added Successfully",R.style.customSuccessToast).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                StyleableToast.makeText(Student.this, "Somthing Went wrong",R.style.customFailureToast).show();


            }
        });

        firebaseAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            StyleableToast.makeText(Student.this, "Student UserId Created Successfully",R.style.customSuccessToast).show();

                        }
                        else {
                            StyleableToast.makeText(Student.this, "Unale To Create Student User \n May be already a User",R.style.customFailureToast).show();

                        }
                    }
                });

    }
    public void bactToManagement(View view) {
        Intent intent = new Intent(this,AdminMangement.class);
        startActivity(intent);
    }


}