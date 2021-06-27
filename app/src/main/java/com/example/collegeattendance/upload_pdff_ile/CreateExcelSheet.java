package com.example.collegeattendance.upload_pdff_ile;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.collegeattendance.PageToTeacherLogin;
import com.example.collegeattendance.R;
import com.example.collegeattendance.StudentModel;
import com.example.collegeattendance.Subject;
import com.example.collegeattendance.studentpart.StudentPartLogin;
import com.example.collegeattendance.studentpart.StudentSubjectListToGetAttPer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateExcelSheet extends AppCompatActivity {
    String StreamValue,YearValue,SemesterValue,SectionValue,SubjectValue,DateVaLue;

    // private File filePath = new File(Environment.getExternalStorageDirectory() + "/Demo.xls");
    FirebaseFirestore firebaseFirestore;
    ArrayList<AttendanceModel> attlist;
    Integer RowTot = 62, ColTot = 10, row=0,col,rowController=9;
    Spinner spinneryear , spinnerStream ,spinnerSubject,spinnerSemester ,spinnerDate,spinnerSection;
    String [] Stream = new String[]{"3 Year Stream","5 Year Stream"};
    String [] Section = new String[]{"Section-A","Section-B"};
    String [] threeYrStream = new String[]{"First Year","Second Year","Third Year"};
    String [] fiveYrStream = new String[]{"First Year","Second Year","Third Year" ,"Fourth Year","Fifth Year"};
    String [] threeyearSemList = new String []{"SEM-I","SEM-II","SEM-III","SEM-IV","SEM-V","SEM-VI"};
    String [] fiveyearSemList = new String []{"SEM-I","SEM-II","SEM-III","SEM-IV","SEM-V","SEM-VI","SEM-VII","SEM-VIII","SEM-IX","SEM-X"};
    String [] date = new String[]{"Apr 20, 2021","Apr 17, 2021","Apr 16, 2021"};

    ArrayList<String> colunValue = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_excel_sheet);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        spinnerStream = findViewById(R.id.selectStream);
        spinneryear = findViewById(R.id.selectYear);
        spinnerSemester = findViewById(R.id.selectSemester);
        spinnerSection= findViewById(R.id.selectSection);
        spinnerSubject = findViewById(R.id.selectSubject);
        spinnerDate= findViewById(R.id.selectDate);

        ArrayAdapter<String> adapterbatch1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,date);
        adapterbatch1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDate.setAdapter(adapterbatch1);
        spinnerDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = parent.getItemAtPosition(position).toString();
                DateVaLue= spinnerValue;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, fiveyearSemList);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemester.setAdapter(adapter1);
        spinnerSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = parent.getItemAtPosition(position).toString();
                SemesterValue = spinnerValue;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, Section);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSection.setAdapter(adapter2);
        spinnerSection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = parent.getItemAtPosition(position).toString();
                SectionValue = spinnerValue;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<String> adapterbatch = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,Stream);
        adapterbatch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStream.setAdapter(adapterbatch);
        spinnerStream.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = parent.getItemAtPosition(position).toString();
                StreamValue = spinnerValue;
                Toast.makeText(CreateExcelSheet.this, "ItemSelected"+spinnerValue, Toast.LENGTH_SHORT).show();
                if (spinnerValue=="3 Year Stream"){
                    ArrayAdapter<String> adapterbatch = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,threeYrStream);
                    adapterbatch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinneryear.setAdapter(adapterbatch);
                    spinneryear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String spinnerValue = parent.getItemAtPosition(position).toString();
                            YearValue = spinnerValue;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, threeyearSemList);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSemester.setAdapter(adapter1);
                    spinnerSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String spinnerValue = parent.getItemAtPosition(position).toString();
                            SemesterValue = spinnerValue;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, Section);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSection.setAdapter(adapter2);
                    spinnerSection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String spinnerValue = parent.getItemAtPosition(position).toString();
                            SectionValue = spinnerValue;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    threeYearStream();
                }
                else{
                    ArrayAdapter<String> adapterbatch = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,fiveYrStream);
                    adapterbatch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinneryear.setAdapter(adapterbatch);
                    spinneryear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String spinnerValue = parent.getItemAtPosition(position).toString();
                            YearValue = spinnerValue;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, fiveyearSemList);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSemester.setAdapter(adapter1);
                    spinnerSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String spinnerValue = parent.getItemAtPosition(position).toString();
                            SemesterValue = spinnerValue;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, Section);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSection.setAdapter(adapter2);
                    spinnerSection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String spinnerValue = parent.getItemAtPosition(position).toString();
                            SectionValue = spinnerValue;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    fiveYearStream();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        firebaseFirestore  = FirebaseFirestore.getInstance();
        Query query= firebaseFirestore.collection("AttendanceRegister").whereEqualTo("Stream","3 Year Stream").whereEqualTo("Year","First Year");

        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                Integer row1 =1;

                for(DocumentSnapshot d:list)
                {
                    String date= d.getString("Date");
                    colunValue.add(date);
                    String Name = d.getString("Name");
                    colunValue.add(Name);
                    String rollNo = d.getString("RollNo");
                    colunValue.add(rollNo);
                    String stream = d.getString("Stream");
                    colunValue.add(stream);
                    String year = d.getString("Year");
                    colunValue.add(year);
                    String  semester = d.getString("semester");
                    colunValue.add(semester);
                    String subject = d.getString("Subject");
                    colunValue.add(subject);
                    String subjectCode = d.getString("subjectCode");
                    colunValue.add(subjectCode);
                    String section= d.getString("section");
                    colunValue.add(section);
                    String attStatus = d.getString("AttStatus");
                    colunValue.add(attStatus);
                    row1 = row1+1;

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateExcelSheet.this, "Unable to fetch", Toast.LENGTH_SHORT).show();
            }
        });  //Header cr


    }


    // Three Yearspinner Declarartion
    private void threeYearStream() {

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        Query subjectsRef = rootRef.collection("subject").whereEqualTo("stream",StreamValue);

        List<String> subjects = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, subjects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubject.setAdapter(adapter);
        subjectsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String subject = document.getString("subjectname");
                        subjects.add(subject);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
    // Five Yearspinner Declarartion
    private void fiveYearStream() {

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        Query subjectsRef = rootRef.collection("subject").whereEqualTo("stream",StreamValue);

        List<String> subjects = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, subjects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubject.setAdapter(adapter);
        subjectsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String subject = document.getString("subjectname");
                        subjects.add(subject);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }



    //onCliclButtonListner
    public void createExcelSheet(View view) {


        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        Workbook wb = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Custom Sheet");
        //   HSSFRow hssfRow = hssfSheet.createRow(0);
        //   HSSFCell hssfCell1 = hssfRow.createCell(0);
        //  HSSFCell hssfCell2 = hssfRow.createCell(1);

        //  hssfCell1.setCellValue(data1.getText().toString());
        //  hssfCell2.setCellValue(data2.getText().toString());
        String header[] = { "Date", "name","RollNo","Stream","Year","Semester","Subject","SubjectCode","Section","AttStatus"};

        for(row = 0; row < 1 ; row++) {
            HSSFRow hssfRow = hssfSheet.createRow(row);// new createCell(col);
            for(col = 0; col < ColTot ; col++) {
                HSSFCell hssfCell = hssfRow.createCell(col);
                hssfCell.setCellValue(header[col]);
            }
        }


        for(row = 1; row <=RowTot ; row++) {
            HSSFRow hssfRow = hssfSheet.createRow(row);// new createCell(col);
            for(col = 0; col < colunValue.size() ; col++) {

                if(col==rowController){
                    col=rowController;
                    rowController = rowController+10;
                    break;
                }
                HSSFCell hssfCell = hssfRow.createCell(col);
                hssfCell.setCellValue(colunValue.get(col));
            }

        }
        File file = new File(getExternalFilesDir(null), "demo1.xls");
        FileOutputStream outputStream = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                hssfWorkbook.write(fileOutputStream);
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();

            }


        } catch (java.io.IOException e) {
            e.printStackTrace();

            Toast.makeText(getApplicationContext(), "NO OK", Toast.LENGTH_LONG).show();
            try {
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        Intent intent = new Intent(CreateExcelSheet.this, PageToTeacherLogin.class);
        startActivity(intent);
    }

}