package com.e.firebasedemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateActivity extends AppCompatActivity {

    Button update;
    Button delete;
    EditText firstname;
    EditText lastname;
    TextView  id;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        id =  findViewById(R.id.user_id_tv);
        update = findViewById(R.id.btn_update);
        delete = findViewById(R.id.btn_delete);


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("People");


        Intent intent = getIntent();
        String fname = intent.getStringExtra("FN");
        String lname = intent.getStringExtra("LN");
        final String uid = intent.getStringExtra("ID");


        firstname.setText(fname);
        lastname.setText(lname);
        id.setText(uid);
        Toast.makeText(this, uid, Toast.LENGTH_SHORT).show();


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strfn = firstname.getText().toString();
                String strLn = lastname.getText().toString();

                PeopleModel peopleModel = new PeopleModel();
                peopleModel.setPeople_id(uid);
                peopleModel.setPeople_fn(strfn);
                peopleModel.setPeople_ln(strLn);

                databaseReference.child(uid).setValue(peopleModel);
                Intent  i = new Intent(UpdateActivity.this,DataDisplayActivity.class);
                startActivity(i);
                finish();

            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(uid).removeValue();
                Intent  i = new Intent(UpdateActivity.this,DataDisplayActivity.class);
                startActivity(i);
                finish();


            }
        });

    }



}
