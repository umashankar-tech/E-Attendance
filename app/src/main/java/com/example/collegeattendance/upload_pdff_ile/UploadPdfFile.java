package com.example.collegeattendance.upload_pdff_ile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeattendance.AdminMangement;
import com.example.collegeattendance.Batches;
import com.example.collegeattendance.PageToTeacherLogin;
import com.example.collegeattendance.R;
import com.example.collegeattendance.Subject;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UploadPdfFile extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    EditText fileName;
    EditText filePath;
    Map<String, String> batchMap = new HashMap<>();

    ArrayList<String> datalist;
    Spinner spinnerYear, spinnerSemester, spinnerBatch, spinnerSubject;

    String[] threeYr = new String[]{"First Year", "Second Year", "Third Year"};
    String[] batch = new String[]{"LL.B", "B.A.LL.B"};
    String[] fiveYr = new String[]{"First Year", "Second Year", "Third Year", "Fourth Year", "Fifth Year"};
    String[] threeyearSemList = new String[]{"SEM-I", "SEM-II", "SEM-III", "SEM-IV", "SEM-V", "SEM-VI"};
    String[] fiveyearSemList = new String[]{"SEM-I", "SEM-II", "SEM-III", "SEM-IV", "SEM-V", "SEM-VI", "SEM-VII", "SEM-VIII", "SEM-IX", "SEM-X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_pdf_file);
        firebaseFirestore = FirebaseFirestore.getInstance();

        spinnerBatch = findViewById(R.id.spinnerbatchfile);
        spinnerSemester = findViewById(R.id.semesterfile);
        spinnerYear = findViewById(R.id.yearFile);

        fileName = findViewById(R.id.filePathfile);
        filePath = findViewById(R.id.filePathfile);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, batch);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBatch.setAdapter(adapter);

        spinnerBatch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String spinnerValue = parent.getItemAtPosition(position).toString();
                batchMap.put("batch", spinnerValue);
                if (spinnerValue == "LL.B") {
                    batchMap.put("stream", "3 Year Stream");
                    Toast.makeText(UploadPdfFile.this, spinnerValue + " " + "3 Year Stream", Toast.LENGTH_SHORT).show();

                    threeYearStream();
                } else if (spinnerValue == "B.A.LL.B") {
                    batchMap.put("stream", "5 Year Stream");
                    Toast.makeText(UploadPdfFile.this, spinnerValue + " " + "5 Year Stream", Toast.LENGTH_SHORT).show();

                    fiveYearStream();
                } else {
                    Toast.makeText(UploadPdfFile.this, "Please Select A batch", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void threeYearStream() {

        ArrayAdapter<String> adapterbatch = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, threeYr);
        adapterbatch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(adapterbatch);
        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = parent.getItemAtPosition(position).toString();
                Toast.makeText(UploadPdfFile.this, "ItemSelected" + spinnerValue, Toast.LENGTH_SHORT).show();
                batchMap.put("Year", spinnerValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, threeyearSemList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);        spinnerSemester.setAdapter(adapter);
        spinnerSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = parent.getItemAtPosition(position).toString();
                Toast.makeText(UploadPdfFile.this, "ItemSelected" + spinnerValue, Toast.LENGTH_SHORT).show();
                batchMap.put("semester", spinnerValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void fiveYearStream() {
        ArrayAdapter<String> adapterbatch = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, fiveYr);
        adapterbatch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(adapterbatch);
        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = parent.getItemAtPosition(position).toString();
                Toast.makeText(UploadPdfFile.this, "ItemSelected" + spinnerValue, Toast.LENGTH_SHORT).show();
                batchMap.put("Year", spinnerValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fiveyearSemList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemester.setAdapter(adapter);
        spinnerSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = parent.getItemAtPosition(position).toString();
                Toast.makeText(UploadPdfFile.this, "ItemSelected" + spinnerValue, Toast.LENGTH_SHORT).show();
                batchMap.put("semester", spinnerValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void submit(View view) {
        String FileName = fileName.getText().toString();
        String FilePath = filePath.getText().toString();
        Calendar calendar = Calendar.getInstance();
         String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
         batchMap.put("date",currentDate);
        batchMap.put("fileName", FileName);
        batchMap.put("filePath",FilePath);
        firebaseFirestore.collection("Files").add(batchMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(UploadPdfFile.this, "Sucessfully Uploaded a File", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UploadPdfFile.this, PageToTeacherLogin.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadPdfFile.this, "Try Again", Toast.LENGTH_SHORT).show();

            }
        });

    }


}
