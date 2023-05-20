package com.aoslec.test01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // imageView
        img = findViewById(R.id.imgSport);

        // button
        btn1 = findViewById(R.id.btnSport1);
        btn2 = findViewById(R.id.btnSport2);

        btn1.setOnClickListener(btnClick);
        btn2.setOnClickListener(btnClick);

    }// onCreate

    // button 클릭시 이미지 띄우기
    View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.btnSport1:
                    img.setImageResource(R.drawable.sport1);
                    break;
                case R.id.btnSport2:
                    img.setImageResource(R.drawable.sport2);
                    break;
            }
        }
    };

}