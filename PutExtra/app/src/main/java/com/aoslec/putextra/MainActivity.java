package com.aoslec.putextra;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final  static  int Rvalue = 0;
    TextView textView = null;
    Button button1 = null;
    Button button2 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv_main);
        button1 = findViewById(R.id.btn_main1);
        button2 = findViewById(R.id.btn_main2);

        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);

    } // onCreate

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.btn_main1:
                    intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("userid", "root");
                    intent.putExtra("password", 1111);
                    startActivity(intent);
                    break;

                case R.id.btn_main2:
                    intent = new Intent(MainActivity.this, ThirdActivity.class);
                    intent.putExtra("userid", "admin");
                    intent.putExtra("password", 2222);
                    startActivityForResult(intent, Rvalue);
                default:
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(resultCode){
            case MainActivity.Rvalue:
                textView.setText("Return Value : " + data.getStringExtra("result"));
                break;
            default:
                break;
        }
    }
}