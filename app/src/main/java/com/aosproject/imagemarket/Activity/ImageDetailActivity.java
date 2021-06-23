package com.aosproject.imagemarket.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aosproject.imagemarket.Adapter.ImageDetailAdapterHJ;
import com.aosproject.imagemarket.Bean.DealHJ;
import com.aosproject.imagemarket.Bean.ImageHJ;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskDealHJ;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskImageHJ;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.MyListDecoration;
import com.aosproject.imagemarket.Util.ShareVar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class ImageDetailActivity extends Activity {

    String urlAddr, urlAddr2, urlAddr3, urlAddr4, user_email, filepath = null;
    int code, recommend = 0;
    TextView detailImageName, detailImageRecommend, detailImagePrice, detailImageFormat, detailImageDetail, detailImageCategory, detailImageLocation, detailImageSeller = null;
    ImageView imageView, back;
    ArrayList<ImageHJ> images = null;
    ArrayList<DealHJ> deals = null;
    Chip c0, c1, c2, c3, c4, c5, c6, c7, c8;
    RecyclerView recyclerView = null;
    RecyclerView.LayoutManager layoutManager = null;
    ImageDetailAdapterHJ adapter = null;
    LinearLayout linearLayout = null;
    Button buy1, buy2, cart1, cart2 = null;

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
        detailImageLocation = findViewById(R.id.detail_textview_location);
        detailImageSeller = findViewById(R.id.detail_textview_seller);
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
        recyclerView = findViewById(R.id.detail_recyclerView);
        linearLayout = findViewById(R.id.detail_layout);
        buy1 = findViewById(R.id.detail_btn_buy);
        buy2 = findViewById(R.id.detail_btn_buy_slide);
        cart1 = findViewById(R.id.detail_btn_cart);
        cart2 = findViewById(R.id.detail_btn_cart_slide);

        layoutManager = new LinearLayoutManager(ImageDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

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
        if(images.get(0).getLocation().equals("none")){
            linearLayout.setVisibility(View.GONE);
        }else {
            linearLayout.setVisibility(View.VISIBLE);
            detailImageLocation.setText(images.get(0).getLocation());
        }

        user_email = images.get(0).getUser_email();
        urlAddr3 = ShareVar.macIP + "jsp/otherImagesSelect.jsp?user_email=" + user_email + "&code=" + code;
        Log.v("Message", urlAddr3);
        try {
            Log.v("Message", "network");
            NetworkTaskImageHJ networkTask = new NetworkTaskImageHJ(ImageDetailActivity.this, urlAddr3, "imageSelect");
            Object obj = networkTask.execute().get();
            images = (ArrayList<ImageHJ>) obj;
            filepath = images.get(0).getFilepath();
            Log.v("Message", images.get(0).getFilepath() + "log");

            adapter = new ImageDetailAdapterHJ(ImageDetailActivity.this, R.layout.detail_custom_layout, images);
            recyclerView.setAdapter(adapter);

            MyListDecoration decoration = new MyListDecoration();
            recyclerView.addItemDecoration(decoration);

        }catch (Exception e){
            Log.v("Message", "error");
            e.printStackTrace();
        }

        urlAddr4 = ShareVar.macIP + "jsp/sellerNameSelect.jsp?user_email=" + user_email;
        Log.v("Message", urlAddr4);
        try {
            NetworkTaskImageHJ networkTask = new NetworkTaskImageHJ(ImageDetailActivity.this, urlAddr4, "nameSelect");
            Object obj = networkTask.execute().get();
            images = (ArrayList<ImageHJ>) obj;
            detailImageSeller.setText(images.get(0).getMyname() + " 작가");
        }catch (Exception e){
            e.printStackTrace();
        }

        back.setOnClickListener(onClickListener);
        buy1.setOnClickListener(onClickListener);
        buy2.setOnClickListener(onClickListener);
        cart1.setOnClickListener(onClickListener);
        cart2.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.detail_ivbtn_back:
                    finish();
                    break;
                case R.id.detail_btn_buy:
                    new AlertDialog.Builder(ImageDetailActivity.this)
                            .setMessage("해당 이미지를 구매하시겠습니까?")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // *************************** 효경님 구매 페이지 연결 ****************************
                                    Intent intent = new Intent(ImageDetailActivity.this, ImageAddNameActivity.class);
                                    // *************************** 효경님 구매 페이지 연결 ****************************
                                    intent.putExtra("code", code);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                    break;
                case R.id.detail_btn_buy_slide:
                    new AlertDialog.Builder(ImageDetailActivity.this)
                            .setMessage("해당 이미지를 구매하시겠습니까?")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // *************************** 효경님 구매 페이지 연결 ****************************
                                    Intent intent = new Intent(ImageDetailActivity.this, ImageAddTagActivity.class);
                                    // *************************** 효경님 구매 페이지 연결 ****************************
                                    intent.putExtra("code", code);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                    break;
                case R.id.detail_btn_cart:
                    new AlertDialog.Builder(ImageDetailActivity.this)
                            .setMessage("해당 이미지를 장바구니에 담았습니다.\n장바구니로 이동하시겠습니까?")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // *************************** 효경님 장바구니 페이지 연결 ****************************
                                    Intent intent = new Intent(ImageDetailActivity.this, ImageAddNameActivity.class);
                                    // *************************** 효경님 장바구니 페이지 연결 ****************************
                                    intent.putExtra("code", code);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                    break;
                case R.id.detail_btn_cart_slide:
                    new AlertDialog.Builder(ImageDetailActivity.this)
                            .setMessage("해당 이미지를 장바구니에 담았습니다.\n장바구니로 이동하시겠습니까?")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // *************************** 효경님 장바구니 페이지 연결 ****************************
                                    Intent intent = new Intent(ImageDetailActivity.this, ImageAddTagActivity.class);
                                    // *************************** 효경님 장바구니 페이지 연결 ****************************
                                    intent.putExtra("code", code);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                    break;
            }
        }
    };
}