package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.aosproject.imagemarket.R;
import com.google.android.material.snackbar.Snackbar;

public class ImageAddCategoryActivity extends Activity {

    ArrayAdapter<CharSequence> adapter = null;
    Spinner spinner = null;
    Button button;
    String filepath, title, detail, fileformat = null;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_add_category);

        Intent intent = getIntent();
        filepath = intent.getStringExtra("filepath");
        title = intent.getStringExtra("title");
        detail = intent.getStringExtra("detail");
        fileformat = intent.getStringExtra("fileformat");

        adapter = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_dropdown_item);

        spinner = findViewById(R.id.add_spinner_category);
        button = findViewById(R.id.add_category_btn_next);
        imageView = findViewById(R.id.add_category_ivbtn_back);

        spinner.setAdapter(adapter);
        button.setOnClickListener(onClickListener);
        imageView.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.add_category_btn_next:
                    if(spinner.getSelectedItem().toString().equals("이미지 카테고리 선택")){
                        Snackbar.make(v, "이미지 카테고리를 선택해주세요!", Snackbar.LENGTH_SHORT).show();
                    }else if (spinner.getSelectedItem().toString().equals("사진")){
                        Intent intent = new Intent(ImageAddCategoryActivity.this, ImageAddTagActivity.class);
                        intent.putExtra("filepath", filepath);
                        intent.putExtra("title", title);
                        intent.putExtra("detail", detail);
                        intent.putExtra("fileformat", fileformat);
                        intent.putExtra("category", 1);
                        startActivity(intent);
                    }else if (spinner.getSelectedItem().toString().equals("일러스트")){
                        Intent intent = new Intent(ImageAddCategoryActivity.this, ImageAddTagActivity.class);
                        intent.putExtra("filepath", filepath);
                        intent.putExtra("title", title);
                        intent.putExtra("detail", detail);
                        intent.putExtra("fileformat", fileformat);
                        intent.putExtra("category", 2);
                        startActivity(intent);
                    }else if (spinner.getSelectedItem().toString().equals("캘리그라피")){
                        Intent intent = new Intent(ImageAddCategoryActivity.this, ImageAddTagActivity.class);
                        intent.putExtra("filepath", filepath);
                        intent.putExtra("title", title);
                        intent.putExtra("detail", detail);
                        intent.putExtra("fileformat", fileformat);
                        intent.putExtra("category", 3);
                        startActivity(intent);
                    }
                    break;
                case R.id.add_category_ivbtn_back:
                    finish();
                    break;
            }
        }
    };
}