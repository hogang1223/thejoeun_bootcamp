package com.aoslec.editabletest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit);
        Button btn1 = findViewById(R.id.insert);
        btn1.setOnClickListener(mClickListener);

        findViewById(R.id.delete).setOnClickListener(mClickListener);
        findViewById(R.id.append).setOnClickListener(mClickListener);
        findViewById(R.id.replace).setOnClickListener(mClickListener);
        findViewById(R.id.clear).setOnClickListener(mClickListener);
    }// onCreate

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Editable edit = editText.getText();
            switch(v.getId()){
                case R.id.insert:
                    edit.insert(0,"INS");
                    break;
                case R.id.delete:
                    edit.delete(2,5);
                    break;
                case R.id.append:
                    edit.append("APP");
                    break;
                case R.id.replace:
                    edit.replace(2,5,"REP");
                    break;
                case R.id.clear:
                    edit.clear();
                    break;
            }
        }
    }; // onClickListener
}// end line