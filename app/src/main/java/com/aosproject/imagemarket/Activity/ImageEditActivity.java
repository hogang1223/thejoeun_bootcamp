package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.aosproject.imagemarket.Bean.ImageHJ;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskImageHJ;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.ShareVar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class ImageEditActivity extends Activity {

    ArrayAdapter<CharSequence> adapterFormat = null;
    ArrayAdapter<CharSequence> adapterCategory = null;
    String urlAddr, urlAddr2 = null;
    int code = 0;
    TextInputLayout titleLayout, contentLayout, priceLayout, locationLayout = null;
    TextInputEditText titleEdit, contentEdit, priceEdit, locationEdit = null;
    Spinner formatSpinner, categorySpinner = null;
    ImageView imageView, back, plus = null;
    Button backBtn, okBtn = null;
    ArrayList<ImageHJ> images = null;
    EditText tagEdit = null;
    ChipGroup chipGroup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_edit);

        Intent intent = getIntent();
        code = intent.getIntExtra("code", 0);

        adapterFormat = ArrayAdapter.createFromResource(this, R.array.format, android.R.layout.simple_spinner_dropdown_item);
        adapterCategory = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_dropdown_item);

        titleLayout = findViewById(R.id.edit_name_layout);
        contentLayout = findViewById(R.id.edit_content_layout);
        priceLayout = findViewById(R.id.edit_price_layout);
        locationLayout = findViewById(R.id.edit_location_layout);
        titleEdit = findViewById(R.id.edit_name_edit);
        contentEdit = findViewById(R.id.edit_content_edit);
        tagEdit = findViewById(R.id.edit_tag_edit);
        priceEdit = findViewById(R.id.edit_price_edit);
        locationEdit = findViewById(R.id.edit_location_edit);
        formatSpinner = findViewById(R.id.edit_spinner_format);
        categorySpinner = findViewById(R.id.edit_spinner_category);
        imageView = findViewById(R.id.edit_image);
        back = findViewById(R.id.edit_ivbtn_back);
        plus = findViewById(R.id.edit_tag_plus);
        backBtn = findViewById(R.id.edit_btn_back);
        okBtn = findViewById(R.id.edit_btn_ok);
        chipGroup = findViewById(R.id.edit_tag_chip_group);

        formatSpinner.setAdapter(adapterFormat);
        categorySpinner.setAdapter(adapterCategory);

        urlAddr = ShareVar.macIP + "jsp/detailImageSelect.jsp?code=" + code;
        Log.v("Message", urlAddr);
        try {
            NetworkTaskImageHJ networkTask = new NetworkTaskImageHJ(this, urlAddr, "detailSelect");
            Object obj = networkTask.execute().get();
            images = (ArrayList<ImageHJ>) obj;
            Log.v("Message", "성공");
        }catch (Exception e){
            e.printStackTrace();
            Log.v("Message", "실패");
        }

        String[] tags = images.get(0).getTag().split(",");
        LayoutInflater inflater = LayoutInflater.from(ImageEditActivity.this);
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
        }

        titleEdit.setText(images.get(0).getTitle());
        contentEdit.setText(images.get(0).getDetail());
//        tagEdit.setText(images.get(0).getTag());
        priceEdit.setText(Integer.toString(images.get(0).getPrice()));
        if(images.get(0).getLocation().equals("none")){
        }else {
            locationEdit.setText(images.get(0).getLocation());
        }

        if(images.get(0).getFileformat().equals("JPG")){
            formatSpinner.setSelection(1);
        }else if(images.get(0).getFileformat().equals("JPEG")){
            formatSpinner.setSelection(2);
        }else if(images.get(0).getFileformat().equals("PNG")){
            formatSpinner.setSelection(3);
        }else if(images.get(0).getFileformat().equals("PDF")){
            formatSpinner.setSelection(4);
        }else if(images.get(0).getFileformat().equals("SVG")){
            formatSpinner.setSelection(5);
        }else if(images.get(0).getFileformat().equals("GIF")){
            formatSpinner.setSelection(6);
        }else {
            formatSpinner.setSelection(0);
        }

        for(int i=0; i<=3; i++){
            if(images.get(0).getCategory()==i){
                categorySpinner.setSelection(i);
            }
        }

        Glide.with(this).load(ShareVar.macIP + "image/" + images.get(0).getFilepath()).transform(new FitCenter(), new RoundedCorners(25)).into(imageView);

        tagEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                plus.setVisibility(View.VISIBLE);
            }
        });

        back.setOnClickListener(onClickListener);
        backBtn.setOnClickListener(onClickListener);
        okBtn.setOnClickListener(onClickListener);
        plus.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.edit_tag_plus:
                    String[] tags = tagEdit.getText().toString().split(" ");
                    LayoutInflater inflater = LayoutInflater.from(ImageEditActivity.this);
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
                    }
                    break;
                case R.id.edit_ivbtn_back:
                    finish();
                    break;
                case R.id.edit_btn_back:
                    finish();
                    break;
                case R.id.edit_btn_ok:
                    new AlertDialog.Builder(ImageEditActivity.this)
                            .setMessage("이미지 정보를 수정하시겠습니까?")
                            .setCancelable(false)
                            .setNegativeButton("Cancel", null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int category = 0;

//                                    String[] parserData = tagEdit.getText().toString().split(",");

                                    if(titleEdit.getText().toString().isEmpty()){
                                        titleLayout.setError("이미지 이름을 입력해주세요!");
                                    }
                                    if(contentEdit.getText().toString().isEmpty()){
                                        contentLayout.setError("이미지 설명을 입력해주세요!");
                                    }
                                    if(chipGroup.getChildCount()==0){
                                        Snackbar.make(v, "태그는 1개 이상 입력해주세요!", Snackbar.LENGTH_SHORT).show();
                                    }else if (chipGroup.getChildCount()>9){
                                        Snackbar.make(v, "태그는 9개 이하로 입력해주세요!", Snackbar.LENGTH_SHORT).show();
                                    }
                                    if(priceEdit.getText().toString().isEmpty()){
                                        priceLayout.setError("이미지 가격을 입력해주세요!");
                                    }
                                    if(categorySpinner.getSelectedItem().toString().equals("이미지 카테고리 선택")){
                                        Snackbar.make(v, "이미지 카테고리를 선택해주세요!", Snackbar.LENGTH_SHORT).show();
                                    }else if (categorySpinner.getSelectedItem().toString().equals("사진")){
                                        category = 1;
                                    }else if (categorySpinner.getSelectedItem().toString().equals("일러스트")){
                                        category = 2;
                                    }else if (categorySpinner.getSelectedItem().toString().equals("캘리그라피")){
                                        category = 3;
                                    }
                                    if(formatSpinner.getSelectedItem().toString().equals("이미지 파일 형식 선택")){
                                        Snackbar.make(v, "이미지 파일 형식을 선택해주세요!", Snackbar.LENGTH_SHORT).show();
                                    }

                                    if(titleEdit.getText().toString().isEmpty()||contentEdit.getText().toString().isEmpty()||chipGroup.getChildCount()==0||chipGroup.getChildCount()>9
                                    ||priceEdit.getText().toString().isEmpty()||categorySpinner.getSelectedItem().equals("이미지 카테고리 선택")
                                    ||formatSpinner.getSelectedItem().equals("이미지 파일 형식 선택")){
                                        new AlertDialog.Builder(ImageEditActivity.this)
                                                .setMessage("필수적 이미지 정보 사항을 입력해주세요!")
                                                .setCancelable(false)
                                                .setPositiveButton("OK", null)
                                                .show();
                                    }else {
                                        StringBuffer result = new StringBuffer("");
                                        for(int i=0; i<chipGroup.getChildCount(); i++) {
                                            Chip chip = (Chip) chipGroup.getChildAt(i);
                                            if (chip.isChecked())
                                                if (i < chipGroup.getChildCount() - 1)
                                                    result.append(chip.getText()).append(",");
                                                else result.append(chip.getText());
                                        }

                                        urlAddr2 = ShareVar.macIP + "jsp/imageUpdate.jsp?title=" + titleEdit.getText().toString() + "&detail=" + contentEdit.getText().toString() + "&fileformat=" +
                                                formatSpinner.getSelectedItem().toString() + "&category=" + category + "&tag=" + result.toString() + "&price=" + priceEdit.getText().toString() +
                                                "&location=" + locationEdit.getText().toString() + "&code=" + code;
                                        Log.v("Message", urlAddr2);
                                        String result2 = connectUpdateData();
                                        if(result2.equals("1")) {
                                            new AlertDialog.Builder(ImageEditActivity.this)
                                                    .setMessage("이미지 정보가 수정되었습니다!")
                                                    .setCancelable(false)
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            finish();
                                                        }
                                                    })
                                                    .show();
                                        }
                                    }
                                }
                            })
                            .show();
                    break;
            }
        }
    };

    private String connectUpdateData(){
        String result2 = null;
        try {
            // NetworkTask 로 넘겨줌
            NetworkTaskImageHJ networkTask = new NetworkTaskImageHJ(ImageEditActivity.this, urlAddr2, "update");
            Object obj = networkTask.execute().get();
            // 1이 들어오면 성공한 것, 만약 그 이외의 숫자면 실패한 것
            result2 = (String) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result2;
    }
}