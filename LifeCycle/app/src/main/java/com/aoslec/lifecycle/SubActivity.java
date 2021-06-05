package com.aoslec.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.v("Message", "onCreate_Sub");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Button button = findViewById(R.id.close);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    @Override
    protected void onStart() {
        Log.v("Message", "onStart_sub");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.v("Message", "onResume_sub");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.v("Message", "onPause_sub");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.v("Message", "onStop_sub");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.v("Message", "onDestroy_sub");
        super.onDestroy();
    }
}