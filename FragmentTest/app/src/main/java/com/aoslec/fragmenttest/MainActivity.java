package com.aoslec.fragmenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements ToolBarFragment.ToolbarListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("message", "main_onCreate");
    }


    @Override
    public void onButtonClick(int position, String text) {
        Log.v("message", "main_onButtonClick_Start");
        TextFragment textFragment = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_2);
        textFragment.changeTextProperties(position, text);
        Log.v("message", "main_onButtonClick_End");
    }
}