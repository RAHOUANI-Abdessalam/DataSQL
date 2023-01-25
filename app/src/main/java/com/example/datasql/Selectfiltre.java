package com.example.datasql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Selectfiltre extends AppCompatActivity {

    EditText marque;
    Button view;
    DBHelper DB;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectfiltre);
        marque=findViewById(R.id.marquefiltre);
        view=findViewById(R.id.btnViewfiltre);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mrq =marque.getText().toString();
                Cursor res = DB.getdata2(mrq);
                if (res.getCount()==0){
                    Toast.makeText(Selectfiltre.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("id :"+res.getString(0)+"\n");
                    buffer.append("modele :"+res.getString(1)+"\n");
                    buffer.append("marque :"+res.getString(2)+"\n");
                    buffer.append("prix :"+res.getString(3)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(Selectfiltre.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}