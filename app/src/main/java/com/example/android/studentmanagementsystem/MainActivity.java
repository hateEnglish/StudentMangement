package com.example.android.studentmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.example.android.studentmanagementsystem.adapter.RecycleItems;
import com.example.android.studentmanagementsystem.Model.Studentinfo;

/**
 * create student detial card and do sorting
 */
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 2;
    private Button button, button1, button2;
    private Button buttonsortid;
    private Button buttonsortname;
    private String mname;
    private String mrollno;
    private ArrayList<Studentinfo> studentInfoArrayList = new ArrayList<>();
    private LinearLayoutManager managerlinear;
    private GridLayoutManager managergrid;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btcreateStudent);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        button1 = (Button) findViewById(R.id.btnsortid);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {

                Collections.sort(studentInfoArrayList, new Comparator<Studentinfo>() {
                    @Override
                    public int compare(final Studentinfo o1, final Studentinfo o2) {
                        return o1.getRollno().compareTo(o2.getRollno());
                    }
                });

            }
        });

        button2 = (Button) findViewById(R.id.btnsortname);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {

                Collections.sort(studentInfoArrayList, new Comparator<Studentinfo>() {
                    @Override
                    public int compare(final Studentinfo o1, final Studentinfo o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });

            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent intent) {
        if (resultCode == RESULT_OK) {
            Studentinfo obj = intent.getParcelableExtra("infoobject");
            studentInfoArrayList.add(obj);
            RecycleItems adapter = new RecycleItems(this, studentInfoArrayList);
            final RecyclerView rvinfostudent = (RecyclerView) findViewById(R.id.rvItems);
            rvinfostudent.setAdapter(adapter);
            rvinfostudent.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            Switch simpleSwitch = (Switch) findViewById(R.id.simpleSwitch);
            simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                    if (isChecked) {
                        rvinfostudent.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                    } else {
                        rvinfostudent.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }
                }
            });
            rvinfostudent.setHasFixedSize(true);
        } else {
            Toast.makeText(MainActivity.this, "Not valid Result Code", Toast.LENGTH_LONG).show();
        }

    }
}