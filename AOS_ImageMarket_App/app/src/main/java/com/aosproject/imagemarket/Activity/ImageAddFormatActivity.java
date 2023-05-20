package com.aosproject.imagemarket.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aosproject.imagemarket.R;
import com.google.android.material.snackbar.Snackbar;

public class ImageAddFormatActivity extends Activity {

    ArrayAdapter<CharSequence> adapter = null;
    Spinner spinner = null;
    Button button;
    String filepath, title, detail, fileformat, img_path = null;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_add_format);

        Intent intent = getIntent();
        filepath = intent.getStringExtra("filepath");
        title = intent.getStringExtra("title");
        detail = intent.getStringExtra("detail");
        img_path = intent.getStringExtra("img_path");

        adapter = ArrayAdapter.createFromResource(this, R.array.format, android.R.layout.simple_spinner_dropdown_item);

        spinner = findViewById(R.id.add_spinner_format);
        button = findViewById(R.id.add_format_btn_next);
        imageView = findViewById(R.id.add_format_ivbtn_back);

        spinner.setAdapter(adapter);
        button.setOnClickListener(onClickListener);
        imageView.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.add_format_btn_next:
                    if(spinner.getSelectedItem().toString().equals("이미지 파일 형식 선택")){
                        Snackbar.make(v, "이미지 파일 형식을 선택해주세요!", Snackbar.LENGTH_SHORT).show();
                    }else {
                        button.setEnabled(true);
                        Intent intent = new Intent(ImageAddFormatActivity.this, ImageAddCategoryActivity.class);
                        intent.putExtra("filepath", filepath);
                        intent.putExtra("title", title);
                        intent.putExtra("detail", detail);
                        fileformat = spinner.getSelectedItem().toString();
                        intent.putExtra("fileformat", fileformat);
                        intent.putExtra("img_path", img_path);
                        Log.v("Message", "파일 형식 확인!!! format " + spinner.getSelectedItem().toString());
                        startActivity(intent);
                    }
                    break;
                case R.id.add_format_ivbtn_back:
                    finish();
                    break;
            }
        }
    };
}