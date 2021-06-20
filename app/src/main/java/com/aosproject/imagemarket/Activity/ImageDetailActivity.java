package com.aosproject.imagemarket.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aosproject.imagemarket.Bean.ImageHJ;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskImageHJ;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.ShareVar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class ImageDetailActivity extends Activity {

    String urlAddr = null;
    int code = 0;
    TextView detailImageName, detailImageRecommend, detailImagePrice = null;
    ImageView imageView;
    ArrayList<ImageHJ> images = null;
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
        imageView = findViewById(R.id.detail_image);
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
    }
}