package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.aosproject.imagemarket.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ImageAddContentActivity extends AppCompatActivity {

    String filepath, title, img_path = null;
    Button button;
    ImageView imageView;
    TextInputLayout layout;
    TextInputEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_add_content);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        filepath = intent.getStringExtra("filepath");
        title = intent.getStringExtra("title");
        img_path = intent.getStringExtra("img_path");

        button = findViewById(R.id.add_content_btn_next);
        imageView = findViewById(R.id.add_content_ivbtn_back);
        layout = findViewById(R.id.add_content_layout);
        editText = findViewById(R.id.add_content_edit);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(150)});

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.toString().trim().length()==0){
                    button.setEnabled(false);
                    layout.setError("이미지 설명을 입력해주세요!");

                }else {
                    button.setEnabled(true);
                    layout.setError(null);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length()==0){
                    button.setEnabled(false);
                    layout.setError("이미지 설명을 입력해주세요!");
                }else {
                    button.setEnabled(true);
                    layout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length()==0){
                    button.setEnabled(false);
                    layout.setError("이미지 설명을 입력해주세요!");
                }else {
                    button.setEnabled(true);
                    layout.setError(null);
                }
            }
        });

        button.setOnClickListener(onClickListener);
        imageView.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.add_content_btn_next:
                    if(editText.getText().toString().isEmpty()){
                        layout.setError("이미지 설명을 입력해주세요!");
                    }else {
                        Intent intent = new Intent(ImageAddContentActivity.this, ImageAddFormatActivity.class);
                        intent.putExtra("filepath", filepath);
                        intent.putExtra("title", title);
                        intent.putExtra("detail", editText.getText().toString());
                        intent.putExtra("img_path", img_path);
                        startActivity(intent);
                    }
                    break;
                case R.id.add_content_ivbtn_back:
                    finish();
                    break;
            }
        }
    };
}