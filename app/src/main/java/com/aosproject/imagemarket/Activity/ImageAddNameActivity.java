package com.aosproject.imagemarket.Activity;

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

public class ImageAddNameActivity extends Activity {

    String filepath = null;
    Button button;
    ImageView imageView;
    TextInputLayout layout;
    TextInputEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_add_name);

        Intent intent = getIntent();
        filepath = intent.getStringExtra("filepath");

        button = findViewById(R.id.add_name_btn_next);
        imageView = findViewById(R.id.add_name_ivbtn_back);
        layout = findViewById(R.id.add_name_layout);
        editText = findViewById(R.id.add_name_edit);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});

//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                if(s.toString().trim().length()==0){
//                    button.setEnabled(false);
//                }else {
//                    button.setEnabled(true);
//                }
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(s.toString().trim().length()==0){
//                    button.setEnabled(false);
//                }else {
//                    button.setEnabled(true);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if(s.toString().trim().length()==0){
//                    button.setEnabled(false);
//                }else {
//                    button.setEnabled(true);
//                }
//            }
//        });

        button.setOnClickListener(onClickListener);
        imageView.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.add_name_btn_next:
                    if(editText.getText().toString().isEmpty()){
                        layout.setError("이미지 이름을 입력해주세요!");
                    }else {
                        Intent intent = new Intent(ImageAddNameActivity.this, ImageAddContentActivity.class);
                        intent.putExtra("filepath", filepath);
                        intent.putExtra("title", editText.getText().toString());
                        startActivity(intent);
                    }
                    break;
                case R.id.add_name_ivbtn_back:
                    finish();
                    break;
            }
        }
    };

}