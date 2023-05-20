package com.aoslec.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.v("Message", "onCreate_main");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.call);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        Log.v("Message", "onStart_main");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.v("Message", "onResume_main");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.v("Message", "onPause_main");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.v("Message", "onStop_main");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.v("Message", "onDestroy_main");
        super.onDestroy();
    }
}