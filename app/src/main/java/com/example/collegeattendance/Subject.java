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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subject extends AppCompatActivity
{
    FirebaseFirestore firebaseFirestore;
    EditText subjectCode;
    EditText name;
    Button addsub;
    ArrayList<String> datalist;
    Spinner spinner , spinnerStream ,spinnerBatch,spinnerSubject ;
    String [] array = new String[]{"3 Year Stream","5 Year Stream"};
    String [] threeYrStream = new String[]{"First Year","Second Year","Third Year"};
    String [] batch = new String[]{"LL.B","B.A,LL.B"};
    String [] fiveYrStream = new String[]{"First Year","Second Year","Third Year" ,"Fourth Year","Fifth Year"};
    String [] threeyearSemList = new String []{"SEM-I","SEM-II","SEM-III","SEM-IV","SEM-V","SEM-VI"};
    String [] fiveyearSemList = new String []{"SEM-I","SEM-II","SEM-III","SEM-IV","SEM-V","SEM-VI","SEM-VII","SEM-VIII","SEM-IX","SEM-X"};

    String [] batchid = new String []{};
    Map<String,String> batchMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject);
        firebaseFirestore = FirebaseFirestore.getInstance();

        spinnerBatch = findViewById(R.id.spinner2);
        spinnerSubject= findViewById(R.id.spinnerbatch);
        name = findViewById(R.id.subname);
        subjectCode = findViewById(R.id.subcode);
        addsub = findViewById(R.id.subAddBtn);
        spinner= findViewById(R.id.spinner);
        spinnerStream = findViewById(R.id.streamspinner);


        ArrayAdapter<String>  adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<String> adapterbatch = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, batch);
                adapterbatch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSubject.setAdapter(adapterbatch);
                spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String spinnerValue = parent.getItemAtPosition(position).toString();
                        Toast.makeText(Subject.this, "ItemSelected"+spinnerValue, Toast.LENGTH_SHORT).show();
                        batchMap.put("batch",spinnerValue);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                String spinnerValue = parent.getItemAtPosition(position).toString();
                Toast.makeText(Subject.this, "ItemSelected"+spinnerValue, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Subject.this, "Please select Stream", Toast.LENGTH_SHORT).show();

            }
        });


    }
    public void threeYearStream()
    {

        ArrayAdapter<String> adapterbatch = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, threeyearSemList);
        adapterbatch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBatch.setAdapter(adapterbatch);
        spinnerBatch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = parent.getItemAtPosition(position).toString();
                Toast.makeText(Subject.this, "ItemSelected"+spinnerValue, Toast.LENGTH_SHORT).show();
                batchMap.put("semester",spinnerValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String>  adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,threeYrStream);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStream.setAdapter(adapter);
        spinnerStream.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = parent.getItemAtPosition(position).toString();
                Toast.makeText(Subject.this, "ItemSelected"+spinnerValue, Toast.LENGTH_SHORT).show();
                batchMap.put("year",spinnerValue);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void fiveYearStream()
    {
        ArrayAdapter<String> adapterbatch = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,fiveyearSemList);
        adapterbatch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBatch.setAdapter(adapterbatch);

        spinnerBatch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = parent.getItemAtPosition(position).toString();
                Toast.makeText(Subject.this, "ItemSelected"+spinnerValue, Toast.LENGTH_SHORT).show();
                batchMap.put("semester",spinnerValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<String>  adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,fiveYrStream);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStream.setAdapter(adapter);
        spinnerStream.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = parent.getItemAtPosition(position).toString();
                Toast.makeText(Subject.this, "ItemSelected"+spinnerValue, Toast.LENGTH_SHORT).show();
                batchMap.put("year",spinnerValue);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    public void addSubject(View view)
    {

        String Name = name.getText().toString();
        String SubjectCode = subjectCode.getText().toString();
        batchMap.put("subjectname",Name);
        batchMap.put("subjectcode",SubjectCode);
        firebaseFirestore.collection("subject").document(Name).set(batchMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                StyleableToast.makeText(Subject.this, "Subject Added Successfully",R.style.customSuccessToast).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                StyleableToast.makeText(Subject.this, "Unable To Add\nSomething went Wrong",R.style.customFailureToast).show();


            }
        });
        Intent intent = new Intent(this,AdminMangement.class);
        startActivity(intent);
    }
    public void bactToManagement(View view) {
        Intent intent = new Intent(this,AdminMangement.class);
        startActivity(intent);
    }


}