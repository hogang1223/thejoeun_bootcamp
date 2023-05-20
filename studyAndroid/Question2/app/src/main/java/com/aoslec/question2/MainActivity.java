package com.aoslec.question2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int a, b, result;
    Button button ;
    TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("질문")
                        .setMessage("좌변을 선택하십시오")
                        .setPositiveButton("4", lClick)
                        .setNegativeButton("5", lClick)
                        .show();
            }
        });
    } // onCreate

    DialogInterface.OnClickListener lClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            if(which == DialogInterface.BUTTON_POSITIVE){
                a = 4;
            }else{
                a = 5;
            }
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("질문2")
                    .setMessage("변을 선택하십시오")
                    .setPositiveButton("6", rClick)
                    .setNegativeButton("7", rClick)
                    .show();
        }
    };
    DialogInterface.OnClickListener rClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            if(which == DialogInterface.BUTTON_POSITIVE){
                b = 6;
            }else{
                b = 7;
            }
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("질문3")
                    .setMessage("어떤 연산을 하시겠습니까?")
                    .setPositiveButton("곱셈", resultClick)
                    .setNegativeButton("덧셈", resultClick)
                    .show();
        }
    };
    DialogInterface.OnClickListener resultClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            if(which == DialogInterface.BUTTON_POSITIVE){
                result = a * b;
            }else{
                result = a + b;
            }
            textView = findViewById(R.id.text);
            textView.setText("연산결과 : " + result);

        }
    };
}