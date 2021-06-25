package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.aosproject.imagemarket.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ImageAddContentActivity extends Activity {

    String filepath, title = null;
    Button button;
    ImageView imageView;
    TextInputLayout layout;
    TextInputEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_add_content);

        Intent intent = getIntent();
        filepath = intent.getStringExtra("filepath");
        title = intent.getStringExtra("title");

        button = findViewById(R.id.add_content_btn_next);
        imageView = findViewById(R.id.add_content_ivbtn_back);
        layout = findViewById(R.id.add_content_layout);
        editText = findViewById(R.id.add_content_edit);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(150)});

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