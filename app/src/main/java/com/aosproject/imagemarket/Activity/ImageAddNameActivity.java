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

public class ImageAddNameActivity extends Activity {

    String filepath = null;
    String urlAddr = null;
    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_add_name);

        Intent intent = getIntent();
        filepath = intent.getStringExtra("filepath");

        button = findViewById(R.id.add_name_btn_next);
        editText = findViewById(R.id.add_name_edit);

        button.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(editText.getText().toString().isEmpty()){
                Snackbar.make(v, "이미지 이름을 입력해주세요!", Snackbar.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(ImageAddNameActivity.this, ImageAddContentActivity.class);
                intent.putExtra("filepath", filepath);
                intent.putExtra("title", editText.getText().toString());
                startActivity(intent);
            }
        }
    };

}