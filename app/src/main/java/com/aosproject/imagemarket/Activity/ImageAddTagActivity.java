package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.aosproject.imagemarket.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ImageAddTagActivity extends AppCompatActivity {

    String filepath, title, detail, fileformat, img_path = null;
    int category = 0;
    Button button;
    ImageView imageView, plus;
    EditText editText;
    ChipGroup chipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_add_tag);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        filepath = intent.getStringExtra("filepath");
        title = intent.getStringExtra("title");
        detail = intent.getStringExtra("detail");
        fileformat = intent.getStringExtra("fileformat");
        category = intent.getIntExtra("category", 0);
        img_path = intent.getStringExtra("img_path");

        Log.v("Message", "파일 형식 확인!!!" + fileformat);


        button = findViewById(R.id.add_tag_btn_next);
        editText = findViewById(R.id.add_tag_edit);
        plus = findViewById(R.id.add_tag_plus);
        imageView = findViewById(R.id.add_tag_ivbtn_back);
        chipGroup = findViewById(R.id.add_tag_chip_group);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(100)});

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                plus.setVisibility(View.VISIBLE);
            }
        });

        button.setOnClickListener(onClickListener);
        imageView.setOnClickListener(onClickListener);
        plus.setOnClickListener(onClickListener);

//        if(chipGroup.getChildCount()==0){
//            Toast.makeText(ImageAddTagActivity.this, "태그는 1개 이상 입력해주세요!", Toast.LENGTH_SHORT).show();
//            button.setEnabled(false);
//        }else if (chipGroup.getChildCount()>9){
//            button.setEnabled(false);
//            Toast.makeText(ImageAddTagActivity.this, "태그는 9개 이하로 입력해주세요!", Toast.LENGTH_SHORT).show();
////            Snackbar.make(v, "태그는 9개 이하로 입력해주세요!", Snackbar.LENGTH_SHORT).show();
//        } else {
//            button.setEnabled(true);
//        }

        if(chipGroup.getChildCount()>=1){
            button.setEnabled(true);
        }

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String[] tags = editText.getText().toString().split(" ");
            String[] parserData = editText.getText().toString().split(",");

            switch (v.getId()){
                case R.id.add_tag_btn_next:
                    if(chipGroup.getChildCount()==0){
                        Snackbar.make(v, "태그는 1개 이상 입력해주세요!", Snackbar.LENGTH_SHORT).show();
//                        button.setEnabled(false);
                    }else if (chipGroup.getChildCount()>9){
//                        button.setEnabled(false);
                        Snackbar.make(v, "태그는 9개 이하로 입력해주세요!", Snackbar.LENGTH_SHORT).show();
                    } else {
//                        button.setEnabled(true);
                        StringBuffer result = new StringBuffer("");
                        for(int i=0; i<chipGroup.getChildCount(); i++) {
                            Chip chip = (Chip) chipGroup.getChildAt(i);
                            if (chip.isChecked())
                                if (i < chipGroup.getChildCount() - 1)
                                    result.append(chip.getText()).append(",");
                                else result.append(chip.getText());
                        }
                        Intent intent = new Intent(ImageAddTagActivity.this, ImageAddPriceActivity.class);
                        intent.putExtra("filepath", filepath);
                        intent.putExtra("title", title);
                        intent.putExtra("detail", detail);
                        intent.putExtra("fileformat", fileformat);
                        intent.putExtra("category", category);
                        intent.putExtra("tag", result.toString());
                        intent.putExtra("img_path", img_path);
                        startActivity(intent);
                    }
                    break;
                case R.id.add_tag_ivbtn_back:
                    finish();
                    break;
                case R.id.add_tag_plus:
                    button.setEnabled(true);
                    LayoutInflater inflater = LayoutInflater.from(ImageAddTagActivity.this);
                    for(String text : tags){
                        Chip chip = (Chip) inflater.inflate(R.layout.chip_item, null, false);
                        chip.setText(text);
                        chip.setOnCloseIconClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                chipGroup.removeView(v);
                            }
                        });
                        chipGroup.addView(chip);
                        editText.setText("");
                    }
                    break;
            }

        }
    };
}