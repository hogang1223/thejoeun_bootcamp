package com.aoslec.quiz0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnRed1, btnGreen1, btnBlue1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRed1 = findViewById(R.id.btnRed);
        btnGreen1 = findViewById(R.id.btnGreen);
        btnBlue1 = findViewById(R.id.btnBlue);

        btnRed1.setOnClickListener(onClickListener); // 준비 대기 상태
        btnGreen1.setOnClickListener(onClickListener);
        btnBlue1.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String colorString = "";
            switch (v.getId()) {
                case R.id.btnRed:
//                        Toast.makeText(MainActivity.this, "red button is clicked", Toast.LENGTH_SHORT).show();
                    colorString = "Red";
                    break;

                case R.id.btnGreen:
                    colorString = "Green";
                    break;

                case R.id.btnBlue:
                    colorString = "Blue";
                    break;
            }
            Toast.makeText(MainActivity.this, colorString + " button is clicked", Toast.LENGTH_SHORT).show();
        }
    };


//        btnRed.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                Toast.makeText( MainActivity.this,"Red button is pushed", Toast.LENGTH_SHORT).show();
//            }
//
//        });
//
//        btnGreen.setOnClickListener(new View.OnClickListener(){
//
//            public void onClick(View v){
//                Toast.makeText( MainActivity.this,"Green button is pushed", Toast.LENGTH_SHORT).show();
//            }
//
//        });
//
//        btnBlue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Blue button is pushed", Toast.LENGTH_SHORT).show();
//            }
//        });


}