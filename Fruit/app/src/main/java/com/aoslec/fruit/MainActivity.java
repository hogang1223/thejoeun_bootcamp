package com.aoslec.fruit;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text;
    Button btnApple, btnOrange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.textFruit);
        btnApple = findViewById(R.id.btnApple);
        btnOrange = findViewById(R.id.btnOrange);

        btnApple.setOnClickListener(btnClickListener);
        btnOrange.setOnClickListener(btnClickListener);

    }

    View.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnApple:
                    text.setText("Apple");
                    text.setTextColor(Color.RED);
                    btnApple.setBackgroundColor(Color.RED);
                    btnOrange.setBackgroundColor(0xFF6200EE);
                    break;
                case R.id.btnOrange:
                    text.setText("Orange");
                    text.setTextColor(0xFFFF9800);
                    btnOrange.setBackgroundColor(0xFFFF9800);
                    btnApple.setBackgroundColor(0xFF6200EE);
                    break;

            }
        }
    };
    

}