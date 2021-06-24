package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.GoalRow;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aosproject.imagemarket.NetworkTask.CartNetworkTaskHK;
import com.aosproject.imagemarket.NetworkTask.DownloadDeviceNetworkTaskHK;
import com.aosproject.imagemarket.NetworkTask.DownloadEmailNetworkTaskHK;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.ShareVar;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DealCompeletedActivityHK extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvGoOrderList;

    //------
    // Deal ArrayList에서 가져올 것들 (+ Date dealDate)
    int dealNo = 18;
    int image_code = 5;
    int downloadCount = 1;
    String imageFilepath = "img1.JPG";
    String imageTitle = "봄에는 역시 벚꽃이지";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_compeleted_hk);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        addListener();

    }

    private void addListener(){
        ivBack = findViewById(R.id.deal_comp_iv_btn_back);
        tvGoOrderList = findViewById(R.id.deal_comp_tv_btn_go_order_list);

        ivBack.setOnClickListener(onClickListener);
        tvGoOrderList.setOnClickListener(onClickListener);

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.deal_comp_iv_btn_back:
                    finish();
                    break;
                case R.id.deal_comp_tv_btn_go_order_list:
                    // my order list로 가기
                    Intent intent = new Intent(DealCompeletedActivityHK.this, BuyList.class);
                    startActivity(intent);
                    break;
            }
        }
    };

}