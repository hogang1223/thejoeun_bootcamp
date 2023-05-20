package com.aoslec.frametest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2;
//            , btnGray1, btnGray2, btnRed1, btnRed2;
    LinearLayout linear1 = findViewById(R.id.linear1);
    LinearLayout linear2 = findViewById(R.id.linear2);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);


//        btnGray1 = findViewById(R.id.btnGray1);
//        btnGray2 = findViewById(R.id.btnGray2);
//        btnRed1 = findViewById(R.id.btnRed1);
//        btnRed2 = findViewById(R.id.btnRed2);

        btn1.setOnClickListener(onClickListener);
        btn2.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1:
                    linear1.setVisibility(v.VISIBLE);
                    linear2.setVisibility(v.INVISIBLE);
                    break;

                case R.id.btn2:
                    linear2.setVisibility(v.VISIBLE);
                    linear1.setVisibility(v.INVISIBLE);
                    break;
            }
        }
    };
}