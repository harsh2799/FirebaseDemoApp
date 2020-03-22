package com.e.firebasedemoapp;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DataDisplayActivity extends AppCompatActivity {

    ListView listView;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);
        listView = findViewById(R.id.listview);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("People");
final ArrayList<PeopleModel> peopleModelArrayList = new ArrayList<PeopleModel>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {


                    PeopleModel peopleModel = dataSnapshot1.getValue(PeopleModel.class);
                    peopleModelArrayList.add(peopleModel);

                }
                MyBaseAdapter myBaseAdapter = new MyBaseAdapter(DataDisplayActivity.this, peopleModelArrayList);
                listView.setAdapter(myBaseAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
