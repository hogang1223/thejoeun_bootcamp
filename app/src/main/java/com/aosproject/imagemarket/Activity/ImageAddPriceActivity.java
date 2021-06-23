package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.aosproject.imagemarket.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ImageAddPriceActivity extends Activity {

    String filepath, title, detail, fileformat, tag = null;
    int category = 0;
    Button button;
    ImageView imageView;
    TextInputLayout layout;
    TextInputEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_add_price);

        Intent intent = getIntent();
        filepath = intent.getStringExtra("filepath");
        title = intent.getStringExtra("title");
        detail = intent.getStringExtra("detail");
        fileformat = intent.getStringExtra("fileformat");
        category = intent.getIntExtra("category", 0);
        tag = intent.getStringExtra("tag");

        Log.v("Message", "파일 형식 확인!!!" + fileformat);


        button = findViewById(R.id.add_price_btn_next);
        editText = findViewById(R.id.add_price_edit);
        layout = findViewById(R.id.add_price_layout);
        imageView = findViewById(R.id.add_price_ivbtn_back);

        button.setOnClickListener(onClickListener);
        imageView.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String[] parserData = editText.getText().toString().split(",");

            switch (v.getId()){
                case R.id.add_price_btn_next:
                    if(editText.getText().toString().isEmpty()){
                        layout.setError("이미지의 가격을 입력해주세요!");
                    }else {
                        Intent intent = new Intent(ImageAddPriceActivity.this, ImageAddLocationActivity.class);
                        intent.putExtra("filepath", filepath);
                        intent.putExtra("title", title);
                        intent.putExtra("detail", detail);
                        intent.putExtra("fileformat", fileformat);
                        intent.putExtra("category", category);
                        intent.putExtra("tag", tag);
                        intent.putExtra("price", Integer.parseInt(editText.getText().toString()));
                        startActivity(intent);
                    }
                    break;
                case R.id.add_price_ivbtn_back:
                    finish();
                    break;
            }

        }
    };
}