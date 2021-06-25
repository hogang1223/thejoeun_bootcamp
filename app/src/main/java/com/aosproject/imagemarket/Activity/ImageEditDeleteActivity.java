package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aosproject.imagemarket.Adapter.ImageDetailAdapterHJ;
import com.aosproject.imagemarket.Adapter.ImageEditAdapterHJ;
import com.aosproject.imagemarket.Bean.DealHJ;
import com.aosproject.imagemarket.Bean.ImageHJ;
import com.aosproject.imagemarket.Fragment.HomeFragment;
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

public class ImageEditDeleteActivity extends Activity {


    String urlAddr, urlAddr2, urlAddr3, urlAddr4, urlAddr5, user_email, filepath = null;
    int code, recommend = 0;
    ArrayList<ImageHJ> images = null;
    ArrayList<DealHJ> deals = null;
    TextView editDeleteImageName, editDeleteRecommend, editDeleteImagePrice, editDeleteImageFormat, editDeleteImageDetail, editDeleteImageCategory, editDeleteImageLocation, editDeleteImageSeller;
    ImageView imageView, back = null;
    Chip c0, c1, c2, c3, c4, c5, c6, c7, c8 = null;
    Button editBtn, deleteBtn, editSliderBtn, deleteSliderBtn = null;
    RecyclerView recyclerView = null;
    RecyclerView.LayoutManager layoutManager = null;
    ImageEditAdapterHJ adapter = null;
    LinearLayout linearLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_edit_delete);

    }

    @Override
    protected void onResume() {
        super.onResume();
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

        editDeleteImageName = findViewById(R.id.edit_delete_textview_name);
        editDeleteRecommend = findViewById(R.id.edit_delete_textview_recommend);
        editDeleteImagePrice = findViewById(R.id.edit_delete_textview_price);
        editDeleteImageFormat = findViewById(R.id.edit_delete_textview_format);
        editDeleteImageDetail = findViewById(R.id.edit_delete_textview_detail);
        editDeleteImageCategory = findViewById(R.id.edit_delete_textview_category);
        editDeleteImageLocation = findViewById(R.id.edit_delete_textview_location);
        editDeleteImageSeller = findViewById(R.id.edit_delete_textview_seller);
        imageView = findViewById(R.id.edit_delete_image);
        back = findViewById(R.id.edit_delete_ivbtn_back);
        c0 = findViewById(R.id.edit_delete_chip_0);
        c1 = findViewById(R.id.edit_delete_chip_1);
        c2 = findViewById(R.id.edit_delete_chip_2);
        c3 = findViewById(R.id.edit_delete_chip_3);
        c4 = findViewById(R.id.edit_delete_chip_4);
        c5 = findViewById(R.id.edit_delete_chip_5);
        c6 = findViewById(R.id.edit_delete_chip_6);
        c7 = findViewById(R.id.edit_delete_chip_7);
        c8 = findViewById(R.id.edit_delete_chip_8);
        recyclerView = findViewById(R.id.edit_delete_recyclerView);
        linearLayout = findViewById(R.id.edit_delete_layout);
        editBtn = findViewById(R.id.edit_delete_btn_edit);
        deleteBtn = findViewById(R.id.edit_delete_btn_delete);
        editSliderBtn = findViewById(R.id.edit_delete_btn_edit_slide);
        deleteSliderBtn = findViewById(R.id.edit_delete_btn_delete_slide);

        layoutManager = new LinearLayoutManager(ImageEditDeleteActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        Chip[] chip = new Chip[9];
        chip = new Chip[]{c0, c1, c2, c3, c4, c5, c6, c7, c8};

        editDeleteImageName.setText(images.get(0).getTitle());
        Glide.with(this).load(ShareVar.macIP + "image/" + images.get(0).getFilepath()).transform(new FitCenter(), new RoundedCorners(25)).into(imageView);
        editDeleteImagePrice.setText(String.format("%,d", images.get(0).getPrice()) + " 원");
        for(int i=0; i<chip.length; i++){
            chip[i].setVisibility(View.INVISIBLE);
        }
        Log.v("Message", "태그 확인!!!" + images.get(0).getTag());
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
            NetworkTaskDealHJ networkTask = new NetworkTaskDealHJ(ImageEditDeleteActivity.this, urlAddr2, "recommendSelect");
            Object obj = networkTask.execute().get();
            deals = (ArrayList<DealHJ>) obj;
            recommend = deals.get(0).getRecommend();
            Log.v("Message", deals.get(0).getRecommend() + "log");
            editDeleteRecommend.setText(recommend + " 명이 추천합니다");
        }catch (Exception e){
            Log.v("Message", "error");
            editDeleteRecommend.setText("0 명이 추천합니다");
            e.printStackTrace();
        }

        editDeleteImageFormat.setText(images.get(0).getFileformat());
        editDeleteImageDetail.setText(images.get(0).getDetail());

        Log.v("Message", "카테고리 확인!!!" + images.get(0).getCategory());

        if(images.get(0).getCategory()==1){
            editDeleteImageCategory.setText("사진");
        }else if(images.get(0).getCategory()==2){
            editDeleteImageCategory.setText("일러스트");
        }else if(images.get(0).getCategory()==3){
            editDeleteImageCategory.setText("캘리그라피");
        }
        if(images.get(0).getLocation().equals("none")){
            linearLayout.setVisibility(View.GONE);
        }else {
            linearLayout.setVisibility(View.VISIBLE);
            editDeleteImageLocation.setText(images.get(0).getLocation());
        }

        user_email = images.get(0).getUser_email();
        urlAddr3 = ShareVar.macIP + "jsp/otherImagesSelect.jsp?user_email=" + user_email + "&code=" + code;
        Log.v("Message", urlAddr3);
        try {
            Log.v("Message", "network");
            NetworkTaskImageHJ networkTask = new NetworkTaskImageHJ(ImageEditDeleteActivity.this, urlAddr3, "imageSelect");
            Object obj = networkTask.execute().get();
            images = (ArrayList<ImageHJ>) obj;
            filepath = images.get(0).getFilepath();
            Log.v("Message", images.get(0).getFilepath() + "log");

            adapter = new ImageEditAdapterHJ(ImageEditDeleteActivity.this, R.layout.edit_delete_custom_layout, images);
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
            NetworkTaskImageHJ networkTask = new NetworkTaskImageHJ(ImageEditDeleteActivity.this, urlAddr4, "nameSelect");
            Object obj = networkTask.execute().get();
            images = (ArrayList<ImageHJ>) obj;
            editDeleteImageSeller.setText(images.get(0).getMyname() + " 작가");
        }catch (Exception e){
            e.printStackTrace();
        }

        back.setOnClickListener(onClickListener);
        editBtn.setOnClickListener(onClickListener);
        deleteBtn.setOnClickListener(onClickListener);
        editSliderBtn.setOnClickListener(onClickListener);
        deleteSliderBtn.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;

            switch (v.getId()){
                case R.id.edit_delete_ivbtn_back:
                    // *************************** 도희님 판매목록 페이지 연결 ****************************
                    intent = new Intent(ImageEditDeleteActivity.this, ImageDetailActivity.class);
                    // *************************** 도희님 판매목록 페이지 연결 ****************************
                    startActivity(intent);
                    break;
                case R.id.edit_delete_btn_edit:
                case R.id.edit_delete_btn_edit_slide:
                    new AlertDialog.Builder(ImageEditDeleteActivity.this)
                            .setMessage("이미지 정보를 수정하시겠습니까?")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(ImageEditDeleteActivity.this, ImageEditActivity.class);
                                    intent.putExtra("code", code);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                    break;
                case R.id.edit_delete_btn_delete:
                case R.id.edit_delete_btn_delete_slide:
                    new AlertDialog.Builder(ImageEditDeleteActivity.this)
                            .setMessage("해당 이미지 판매를 삭제하시겠습니까?\n한 번 삭제된 이미지는 복구가 불가능합니다.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    urlAddr5 = ShareVar.macIP + "jsp/imageDelete.jsp?registerNo=" + code;
                                    Log.v("Message", urlAddr5);
                                    String result = connectDeleteData();
                                    if(result.equals("1")){
                                        new AlertDialog.Builder(ImageEditDeleteActivity.this)
                                                .setMessage("해당 이미지가 삭제되었습니다!")
                                                .setCancelable(false)
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        // *************************** 도희님 판매목록 페이지 연결 ****************************
                                                        Intent intent = new Intent(ImageEditDeleteActivity.this, ImageAddImageActivity.class);
                                                        // *************************** 도희님 판매목록 페이지 연결 ****************************
                                                        startActivity(intent);
                                                    }
                                                })
                                                .show();
                                    }else {
                                        Toast.makeText(ImageEditDeleteActivity.this, "삭제가 실패 되었습니다", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                    break;
            }
        }
    };

    private String connectDeleteData(){
        String result = null;
        try {
            // NetworkTask 로 넘겨줌
            NetworkTaskImageHJ networkTask = new NetworkTaskImageHJ(ImageEditDeleteActivity.this, urlAddr5, "delete");
            Object obj = networkTask.execute().get();
            // 1이 들어오면 성공한 것, 만약 그 이외의 숫자면 실패한 것
            result = (String) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}