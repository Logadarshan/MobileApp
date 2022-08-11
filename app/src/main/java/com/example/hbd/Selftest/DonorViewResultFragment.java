package com.example.hbd.Selftest;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.hbd.Model.TestModel;
import com.example.hbd.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DonorViewResultFragment extends Fragment {

    TextView resultq1,resultq2,resultq3,resultq4,resultq5,resultq6,resultq7,resultq8,resultq9,resultq10,overallresult;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    FirebaseAuth fAuth;
    Activity context;
    TestModel testModel;
    long tid =0 ;

    String dquestion1, dquestion2,  dquestion3, dquestion4, dquestion5,
            dquestion6,  dquestion7,  dquestion8, dquestion9,  dquestion10, resultans,usertestid;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_donor_view_result, container, false);


        context = getActivity();
        resultq1 = v.findViewById(R.id.resultansquestion11);
        resultq2 = v.findViewById(R.id.resultansquestion21);
        resultq3 = v.findViewById(R.id.resultansquestion31);
        resultq4 = v.findViewById(R.id.resultansquestion41);
        resultq5 = v.findViewById(R.id.resultansquestion51);
        resultq6 = v.findViewById(R.id.resultansquestion61);
        resultq7 = v.findViewById(R.id.resultansquestion71);
        resultq8 = v.findViewById(R.id.resultansquestion81);
        resultq9 = v.findViewById(R.id.resultansquestion91);
        resultq10 = v.findViewById(R.id.resultansquestion101);
        overallresult = v.findViewById(R.id.overallresult1);

        fAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        showresult(firebaseUser);



        return v;
    }

    // display donor's result
    private void showresult(FirebaseUser firebaseUser) {


        String userID = firebaseUser.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Test").child(userID);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TestModel testModel = snapshot.getValue(TestModel.class);

                if(testModel != null){

                    dquestion1 = testModel.getDquestion1();
                    dquestion2 = testModel.getDquestion2();
                    dquestion3 = testModel.getDquestion3();
                    dquestion4 = testModel.getDquestion4();
                    dquestion5 = testModel.getDquestion5();
                    dquestion6 = testModel.getDquestion6();
                    dquestion7 = testModel.getDquestion7();
                    dquestion8 = testModel.getDquestion8();
                    dquestion9 = testModel.getDquestion9();
                    dquestion10 = testModel.getDquestion10();
                    resultans = testModel.getResultans();

                    resultq1.setText(dquestion1);
                    resultq2.setText(dquestion2);
                    resultq3.setText(dquestion3);
                    resultq4.setText(dquestion4);
                    resultq5.setText(dquestion5);
                    resultq6.setText(dquestion6);
                    resultq7.setText(dquestion7);
                    resultq8.setText(dquestion8);
                    resultq9.setText(dquestion9);
                    resultq10.setText(dquestion10);
                    overallresult.setText(resultans);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}