package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aosproject.imagemarket.R;
import com.google.android.material.snackbar.Snackbar;

public class ImageAddContentActivity extends Activity {

    String filepath, title = null;
    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_add_content);

        Intent intent = getIntent();
        filepath = intent.getStringExtra("filepath");
        title = intent.getStringExtra("title");

        button = findViewById(R.id.add_content_btn_next);
        editText = findViewById(R.id.add_content_edit);

        button.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(editText.getText().toString().isEmpty()){
                Snackbar.make(v, "이미지 설명을 입력해주세요!", Snackbar.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(ImageAddContentActivity.this, ImageAddFormatActivity.class);
                intent.putExtra("filepath", filepath);
                intent.putExtra("title", title);
                intent.putExtra("detail", editText.getText().toString());
                startActivity(intent);
            }
        }
    };
}