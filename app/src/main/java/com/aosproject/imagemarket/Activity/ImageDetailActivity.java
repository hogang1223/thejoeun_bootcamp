package com.aosproject.imagemarket.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aosproject.imagemarket.Bean.DealHJ;
import com.aosproject.imagemarket.Bean.ImageHJ;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskDealHJ;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskImageHJ;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.ShareVar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class ImageDetailActivity extends Activity {

    String urlAddr, urlAddr2, urlAddr3, user_email, filepath = null;
    int code, recommend = 0;
    TextView detailImageName, detailImageRecommend, detailImagePrice, detailImageFormat, detailImageDetail, detailImageCategory = null;
    ImageView imageView, iv1, iv2, iv3, back;
    ArrayList<ImageHJ> images = null;
    ArrayList<DealHJ> deals = null;
    Chip c0, c1, c2, c3, c4, c5, c6, c7, c8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        Intent intent = getIntent();
        code = intent.getIntExtra("code", 0);

        urlAddr = ShareVar.macIP + "jsp/detailImageSelect.jsp?code=" + code;
        Log.v("Message", urlAddr);
        try {
            NetworkTaskImageHJ networkTask = new NetworkTaskImageHJ(this, urlAddr, "detailSelect");
            Object obj = networkTask.execute().get();
            images = (ArrayList<ImageHJ>) obj;
        }catch (Exception e){
            e.printStackTrace();
        }

        detailImageName = findViewById(R.id.detail_textview_name);
        detailImageRecommend = findViewById(R.id.detail_textview_recommend);
        detailImagePrice = findViewById(R.id.detail_textview_price);
        detailImageFormat = findViewById(R.id.detail_textview_format);
        detailImageDetail = findViewById(R.id.detail_textview_detail);
        detailImageCategory = findViewById(R.id.detail_textview_category);
        imageView = findViewById(R.id.detail_image);
        back = findViewById(R.id.detail_ivbtn_back);
        c0 = findViewById(R.id.detail_chip_0);
        c1 = findViewById(R.id.detail_chip_1);
        c2 = findViewById(R.id.detail_chip_2);
        c3 = findViewById(R.id.detail_chip_3);
        c4 = findViewById(R.id.detail_chip_4);
        c5 = findViewById(R.id.detail_chip_5);
        c6 = findViewById(R.id.detail_chip_6);
        c7 = findViewById(R.id.detail_chip_7);
        c8 = findViewById(R.id.detail_chip_8);

        Chip[] chip = new Chip[9];
        chip = new Chip[]{c0, c1, c2, c3, c4, c5, c6, c7, c8};

        detailImageName.setText(images.get(0).getTitle());
        Glide.with(this).load(ShareVar.macIP + "image/" + images.get(0).getFilepath()).transform(new FitCenter(), new RoundedCorners(25)).into(imageView);
        detailImagePrice.setText(String.format("%,d", images.get(0).getPrice()) + " 원");
        String[] parserData = images.get(0).getTag().split(",");
        Log.v("Message", parserData[0]);
        for(int i=0; i<parserData.length; i++){
            chip[i].setText(parserData[i]);
            chip[i].setVisibility(View.VISIBLE);
        }

        urlAddr2 = ShareVar.macIP + "jsp/imageRecommend.jsp?code=" + code;
        Log.v("Message", urlAddr2);
        try {
            Log.v("Message", "network");
            NetworkTaskDealHJ networkTask = new NetworkTaskDealHJ(ImageDetailActivity.this, urlAddr2, "recommendSelect");
            Object obj = networkTask.execute().get();
            deals = (ArrayList<DealHJ>) obj;
            recommend = deals.get(0).getRecommend();
            Log.v("Message", deals.get(0).getRecommend() + "log");
            detailImageRecommend.setText(recommend + " 명이 추천합니다");
        }catch (Exception e){
            Log.v("Message", "error");
            detailImageRecommend.setText("0 명이 추천합니다");
            e.printStackTrace();
        }

        detailImageFormat.setText(images.get(0).getFileformat());
        detailImageDetail.setText(images.get(0).getDetail());
        if(images.get(0).getCategory()==1){
            detailImageCategory.setText("사진");
        }else if(images.get(0).getCategory()==2){
            detailImageCategory.setText("일러스트");
        }else if(images.get(0).getCategory()==2){
            detailImageCategory.setText("캘리그라피");
        }

        user_email = images.get(0).getUser_email();
        urlAddr3 = ShareVar.macIP + "jsp/otherImagesSelect.jsp?user_email=" + user_email;
        Log.v("Message", urlAddr3);
        try {
            Log.v("Message", "network");
            NetworkTaskDealHJ networkTask = new NetworkTaskDealHJ(ImageDetailActivity.this, urlAddr2, "imageSelect");
            Object obj = networkTask.execute().get();
            images = (ArrayList<ImageHJ>) obj;
            filepath = images.get(0).getFilepath();
            Log.v("Message", images.get(0).getFilepath() + "log");
        }catch (Exception e){
            Log.v("Message", "error");
            e.printStackTrace();
        }

        back.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.detail_ivbtn_back:
                    finish();
                    break;
            }
        }
    };
}