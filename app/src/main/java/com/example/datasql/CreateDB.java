package com.example.datasql;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateDB extends AppCompatActivity {

    EditText nameDB;
    Button createDB,moveCrud;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_db);

        nameDB = findViewById(R.id.nametxtDB);
        createDB = findViewById(R.id.btnCreateDB);

        createDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper DB = new DBHelper(CreateDB.this,nameDB.getText().toString());
                Toast.makeText(CreateDB.this, "Database "+nameDB.getText().toString()+" created succesfully", Toast.LENGTH_SHORT).show();
            }
        });

        moveCrud = findViewById(R.id.btnCrud);
        moveCrud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateDB.this,MainActivity.class);
                intent.putExtra("DBNAME",nameDB.getText().toString());
                startActivity(intent);
            }
        });
    }
}