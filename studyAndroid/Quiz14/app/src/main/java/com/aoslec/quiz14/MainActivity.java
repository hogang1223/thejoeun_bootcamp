package com.aoslec.quiz14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    final static int Rvalue = 0;
    EditText editId = null;
    EditText editPw = null;
    Button button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editId = findViewById(R.id.editID_main);
        editPw = findViewById(R.id.editPW_main);
        button = findViewById(R.id.btnLogin_Main);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;


            }
        });

    }
}