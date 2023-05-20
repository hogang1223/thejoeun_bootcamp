package com.aoslec.frametest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView sibaImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        sibaImg = findViewById(R.id.sibaImg);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sibaImg.getVisibility() == v.VISIBLE){
                    sibaImg.setVisibility(v.INVISIBLE);
                }else{
                    sibaImg.setVisibility(v.VISIBLE);
                }
            }
        });
    }
}