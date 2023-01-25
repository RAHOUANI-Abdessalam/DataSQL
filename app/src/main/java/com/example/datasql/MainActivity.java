package com.example.datasql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeUtils;

public class MainActivity extends AppCompatActivity {
    TextView texttitle;
    EditText database,id,modele,marque,prix;
    Button insert,update,delete,view,deleteDB,filtre;
    DBHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texttitle = findViewById(R.id.texttitle);
        texttitle.setText(getIntent().getStringExtra("DBNAME")+" Database");

        id=findViewById(R.id.id);
        modele=findViewById(R.id.modele);
        marque=findViewById(R.id.marque);
        prix=findViewById(R.id.prix);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        deleteDB = findViewById(R.id.btnDeleteDB);
        filtre=findViewById(R.id.btnFiltre);

        DB = new DBHelper(this);
        //DB = new DBHelper(this,database.getText().toString());

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt = id.getText().toString();
                String contacttxt = modele.getText().toString();
                String dobtxt = marque.getText().toString();
                String prixe = prix.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTxt,contacttxt,dobtxt,prixe);
                if (checkinsertdata==true){
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt = id.getText().toString();
                String contacttxt = modele.getText().toString();
                String dobtxt = marque.getText().toString();
                String prixe = prix.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTxt,contacttxt,dobtxt,prixe);
                if (checkupdatedata==true){
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Entry Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt = id.getText().toString();

                Boolean checkdeletedata = DB.deletedata(nameTxt);
                if (checkdeletedata==true){
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("id :"+res.getString(0)+"\n");
                    buffer.append("modele :"+res.getString(1)+"\n");
                    buffer.append("marque :"+res.getString(2)+"\n");
                    buffer.append("prix :"+res.getString(3)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        deleteDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.onDestroy();
                Toast.makeText(MainActivity.this, "DB Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        filtre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this,Selectfiltre.class);
                startActivity(intent2);
            }
        });
    }
}