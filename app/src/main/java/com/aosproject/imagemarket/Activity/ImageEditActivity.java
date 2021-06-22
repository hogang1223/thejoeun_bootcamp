package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.aosproject.imagemarket.Bean.ImageHJ;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskImageHJ;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.ShareVar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class ImageEditActivity extends Activity {

    String urlAddr, urlAddr2 = null;
    int code = 0;
    TextInputLayout titleLayout, contentLayout, tagLayout, priceLayout, locationLayout = null;
    TextInputEditText titleEdit, contentEdit, tagEdit, priceEdit, locationEdit = null;
    Spinner formatSpinner, categorySpinner = null;
    ImageView imageView, back = null;
    Button backBtn, okBtn = null;
    ArrayList<ImageHJ> images = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_edit);

        Intent intent = getIntent();
        code = intent.getIntExtra("code", 0);

        titleLayout = findViewById(R.id.edit_name_layout);
        contentLayout = findViewById(R.id.edit_content_layout);
        tagLayout = findViewById(R.id.edit_tag_layout);
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
        backBtn = findViewById(R.id.edit_btn_back);
        okBtn = findViewById(R.id.edit_btn_ok);

        urlAddr = ShareVar.macIP + "jsp/detailImageSelect.jsp?code=" + code;
        Log.v("Message", urlAddr);
        try {
            NetworkTaskImageHJ networkTask = new NetworkTaskImageHJ(this, urlAddr, "detailSelect");
            Object obj = networkTask.execute().get();
            images = (ArrayList<ImageHJ>) obj;
        }catch (Exception e){
            e.printStackTrace();
        }

        titleEdit.setText(images.get(0).getTitle());
        contentEdit.setText(images.get(0).getDetail());
        tagEdit.setText(images.get(0).getTag());
        priceEdit.setText(images.get(0).getPrice());
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

        back.setOnClickListener(onClickListener);
        backBtn.setOnClickListener(onClickListener);
        okBtn.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
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

                                }
                            })
                            .show();
                    break;
            }
        }
    };
}