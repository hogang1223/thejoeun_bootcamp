package com.aoslec.networkjson_student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    String urlAddr = "http://192.168.2.3:8080/test/students.json";
    Button button;
    ListView listView;
    ArrayList<Student> students;
    StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn_main_network);
        listView = findViewById(R.id.lv_main_listView);

        button.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_main_network:
                    try {
                        NetworkTask networkTask = new NetworkTask(MainActivity.this, urlAddr);
                        Object obj = networkTask.execute().get();
                        students = (ArrayList<Student>) obj;

                        adapter = new StudentAdapter(MainActivity.this, R.layout.custom_layout, students);
                        listView.setAdapter(adapter);

                        button.setText("Results");

                    }catch (Exception e){
                        e.printStackTrace();
                    }
            }
        }
    };

}