package com.kerelos.recyclerviewsample;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kerelos.recyclerviewsample.model.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyDBHelper helper;
    private SQLiteDatabase db;
    private EditText etAfName, etAge;
    private Button btnAddDb;
    // to get all data
    ArrayList<Student> all = new ArrayList<>();

    // RVadapter  and RecyclerView
    MyRVAdapter adapter;
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        all.clear();

        helper = new MyDBHelper(this);
        db = helper.getWritableDatabase();

        btnAddDb = findViewById(R.id.btnAddDb);
        etAfName = findViewById(R.id.etAfName);
        etAge = findViewById(R.id.etAge);
        Button btnVA = findViewById(R.id.btnSeeAll);

        addDate();

        // RecyclerView  // link xml with Java
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Button view all records in database
        btnVA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                all = helper.getData();

                if (all.isEmpty()){
                    showmessage("Error", "No Data found");
                } else {
                    // to put all data in the adapter
                    //                  adapter = new MyRVAdapter(all);
                    adapter = new MyRVAdapter(all);
                    recyclerView.setAdapter(adapter);

                    // get All Data
                    //                 for(int i=0; i<all.size();i++)
                    // see here each row
                    //                   showmessage("Data", all.toString());

                }
            }
        });


    }


    public void addDate (){

        btnAddDb.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ContentValues row = new ContentValues();
                                            row.put("name",etAfName.getText().toString());
                                            row.put("age",etAge.getText().toString());

                                            long isInserted = db.insert("kid",null,row);

                                            if (isInserted != -1 ) {
                                                Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_LONG).show();
                                                etAfName.setText("");
                                                etAge.setText("");
                                            }else
                                                Toast.makeText(MainActivity.this, "Data not \n saved", Toast.LENGTH_LONG).show();
                                        }
                                    }
        );
    }


    // customized alart dialog ...just give it tiltle and message :)
    public void showmessage ( String tiltle, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(tiltle);
        builder.setMessage(message);
        builder.show();
    }


}
