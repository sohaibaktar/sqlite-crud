package com.food_court;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    DBHelper DB;
    EditText name , age;
    Button create,  update,delete,view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        create = findViewById(R.id.create_btn);
        update = findViewById(R.id.update_btn);
        delete= findViewById(R.id.delete_btn);
        view = findViewById(R.id.view_btn);
        
        DB = new DBHelper(this);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt = name.getText().toString();
                String agetxt = age.getText().toString();

                Boolean checkinsertdata = DB.createData(nametxt,agetxt);
                if(checkinsertdata == true){
                    Toast.makeText(MainActivity.this, "New entry Created", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt = name.getText().toString();
                String agetxt = age.getText().toString();

                Boolean checkupdatedata = DB.updateData(nametxt,agetxt);
                if(checkupdatedata == true){
                    Toast.makeText(MainActivity.this, "New data Update", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt = name.getText().toString();

                Boolean check_data_delete = DB.deleteData(nametxt);
                if(check_data_delete == true){
                    Toast.makeText(MainActivity.this, "Deleted!!!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Not Deleted.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount() == 0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append("Name: "+ res.getString(0)+"\n");
                        buffer.append("Age: "+ res.getString(1)+"\n");
                    }
                    Log.d("TAG", "onClick: "+buffer.toString());
                    Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}