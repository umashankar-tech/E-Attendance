package com.example.collegeattendance.studentpart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.collegeattendance.R;
import com.example.collegeattendance.StudentAdapter;
import com.example.collegeattendance.StudentModel;
import com.example.collegeattendance.SubejctAdapter;
import com.example.collegeattendance.SubjectModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PdfViewer extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<pdfModel> datalist;
    pdfAdapter pdfAdapter;
    int lastPosition;

    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_viewer);
        progressDialog = new ProgressDialog(PdfViewer.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setTitle("FilePath is On Progress");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        recyclerView = findViewById(R.id.pdfrecyler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        datalist = new ArrayList<>();
        pdfAdapter = new pdfAdapter(  this,datalist);

        recyclerView.setAdapter(pdfAdapter);

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
        Query query= firebaseFirestore.collection("Files");
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot d:list)
                {
                    pdfModel model = d.toObject(pdfModel.class);
                    datalist.add(model);
                }
                pdfAdapter.notifyDataSetChanged();
            }
        }).addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                progressDialog.dismiss();
            }
        });



    }
}