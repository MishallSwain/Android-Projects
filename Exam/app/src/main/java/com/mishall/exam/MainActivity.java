package com.mishall.exam;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyDb db;
    EditText name,cno,Rollno;
    Button insert,update,delete,display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new MyDb(this);

        name=(EditText) findViewById(R.id.name);
        cno=(EditText) findViewById(R.id.number);
        Rollno=(EditText) findViewById(R.id.rollno);
        insert=(Button) findViewById(R.id.save);
        update=(Button) findViewById(R.id.update);
        delete=(Button) findViewById(R.id.delete);
        display=(Button) findViewById(R.id.display);

        addData();
        updateData();
        deleteData();
        selectData();
    }
    public void addData(){
        insert.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                boolean isInserted = db.insertData(name.getText().toString(),cno.getText().toString(),Rollno.getText().toString());
                if (isInserted == true)
                    Toast.makeText(MainActivity.this,"Contact Saved", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Contact not saved", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void updateData(){
        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = db.updateData(name.getText().toString(), cno.getText().toString(), Rollno.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(MainActivity.this,"Contact Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Contact not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void deleteData(){
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = db.deleteData(name.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(MainActivity.this,"Contact Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Contact not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );


    }
    public void selectData(){
        display.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = db.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Name :"+ res.getString(0)+"\n");
                            buffer.append("Contact Number :"+ res.getString(1)+"\n");
                            buffer.append("Rollno :"+ res.getString(2)+"\n\n");
                        }

                        // Show all data
                        showMessage("Contacts",buffer.toString());
                    }
                }
        );
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}