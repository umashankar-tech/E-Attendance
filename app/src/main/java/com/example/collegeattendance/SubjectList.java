package com.example.collegeattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SubjectList extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<SubjectModel> datalist;
    SubejctAdapter   subejctAdapter;

    ArrayList<String> streamBatchYearValue;
    FirebaseFirestore  firebaseFirestore ;

    String stream;
    String year;
    String semester;
    String section;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_batch_to_teacher);
        recyclerView = findViewById(R.id.batchesRecylerView);

        datalist = new ArrayList<>();
        streamBatchYearValue = new ArrayList<>();
        subejctAdapter = new SubejctAdapter(this, streamBatchYearValue,datalist);

        firebaseFirestore = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        stream= intent.getStringExtra("stream");
        semester = intent.getStringExtra("semester");
        year = intent.getStringExtra("year");
        section= intent.getStringExtra("section");

         Query query = firebaseFirestore.collection("subject").whereEqualTo("stream",stream).whereEqualTo("year",year);


               query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list)
                        {
                           SubjectModel model = d.toObject(SubjectModel.class);
                            datalist.add(model);
                        }
                        subejctAdapter.notifyDataSetChanged();

                    }
                });
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);

        streamBatchYearValue.add(stream);
        streamBatchYearValue.add(year);
        streamBatchYearValue.add(semester);
        streamBatchYearValue.add(section);
        recyclerView.setAdapter(subejctAdapter);
    }



}