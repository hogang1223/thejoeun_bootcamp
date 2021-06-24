package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aosproject.imagemarket.Bean.CartHK;
import com.aosproject.imagemarket.NetworkTask.CartNetworkTaskHK;
import com.aosproject.imagemarket.NetworkTask.DealNetworkTaskHK;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.ShareVar;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DealItemActivityHK extends AppCompatActivity {

    int imageCode;
    ImageView btnBack, ivImageFile;
    TextView tvSellUserName, tvImageTitle, tvImagePrice, tvTotalCount, tvTotalPrice, tvFinalPrice;
    CheckBox cb1, cb2, cb3, cb4;
    Button btnDeal;
    ArrayList<CartHK> dealItem;
    LinearLayout lycard, lycash, lykakao, lynaver;
    boolean cardStatus = false;
    boolean cashStatus = false;
    boolean kakaoStatus = false;
    boolean naverStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_item);

        // hide actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // detail view 'code' - image code로 select 할 것
        Intent intent = getIntent();
        imageCode = intent.getIntExtra("code", 0);

        // layout widget connect
        addListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        connectSelectImageData();

        // order item view
        Glide.with(DealItemActivityHK.this)
                .load(ShareVar.macIP + "image/" + dealItem.get(0).getImageFilepath())
                .override(110, 110)
                .centerCrop()
                .error(R.drawable.cart_image_error)
                .into(ivImageFile);
        tvSellUserName.setText(dealItem.get(0).getSellName());
        tvImageTitle.setText(dealItem.get(0).getImageTitle());
        tvImagePrice.setText(String.format("%,d", dealItem.get(0).getImagePrice()) + "원");

//      deal info - setText
        tvTotalCount.setText("1개");
        tvTotalPrice.setText(String.format("%,d", dealItem.get(0).getImagePrice()) + "원");
        tvFinalPrice.setText(String.format("%,d", dealItem.get(0).getImagePrice()) + "원");

    }


    // button Listener
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.deal_iv_btn_back:
                    finish();
                    break;

                // 결제 버튼 클릭 시
                case R.id.deal_btn_deal:
//            connectInsertDealData();    // DB : Insert Deal Table
//            connectUpdateCartStatus();  // DB : Update Cart Table cstatus
                    Intent intent = new Intent(DealItemActivityHK.this, DealCompeletedActivityHK.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    CompoundButton.OnCheckedChangeListener cbOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(cb1.isChecked() && cb2.isChecked() && cb3.isChecked() && cb4.isChecked()){
                btnDeal.setEnabled(true);
            }else{
                btnDeal.setEnabled(false);
            }
        }
    };

    // payment onClickListener
    View.OnClickListener paymentOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.deal_layout_card:
                    if(cardStatus==false){
                        lycard.setBackgroundColor(Color.LTGRAY);
                        lycash.setBackgroundColor(Color.TRANSPARENT);
                        lykakao.setBackgroundColor(Color.TRANSPARENT);
                        lynaver.setBackgroundColor(Color.TRANSPARENT);
                        cardStatus = true;
                        cashStatus = false;
                        kakaoStatus = false;
                        naverStatus = false;
                    }else{
                        lycard.setBackgroundColor(Color.TRANSPARENT);
                        cardStatus = false;
                    }
                    break;
                case R.id.deal_layout_cash:
                    if(cashStatus==false){
                        lycard.setBackgroundColor(Color.TRANSPARENT);
                        lycash.setBackgroundColor(Color.LTGRAY);
                        lykakao.setBackgroundColor(Color.TRANSPARENT);
                        lynaver.setBackgroundColor(Color.TRANSPARENT);
                        cashStatus = true;
                        cardStatus = false;
                        kakaoStatus = false;
                        naverStatus = false;
                    }else{
                        lycash.setBackgroundColor(Color.TRANSPARENT);
                        cashStatus = false;
                    }
                    break;
                case R.id.deal_layout_kakao:
                    if(kakaoStatus==false){
                        lycard.setBackgroundColor(Color.TRANSPARENT);
                        lycash.setBackgroundColor(Color.TRANSPARENT);
                        lykakao.setBackgroundColor(Color.LTGRAY);
                        lynaver.setBackgroundColor(Color.TRANSPARENT);
                        kakaoStatus = true;
                        cashStatus = false;
                        cardStatus = false;
                        naverStatus = false;
                    }else{
                        lykakao.setBackgroundColor(Color.TRANSPARENT);
                        kakaoStatus = false;
                    }
                    break;
                case R.id.deal_layout_naver:
                    if(naverStatus==false){
                        lycard.setBackgroundColor(Color.TRANSPARENT);
                        lycash.setBackgroundColor(Color.TRANSPARENT);
                        lykakao.setBackgroundColor(Color.TRANSPARENT);
                        lynaver.setBackgroundColor(Color.LTGRAY);
                        naverStatus = true;
                        kakaoStatus = false;
                        cashStatus = false;
                        cardStatus = false;
                    }else{
                        lynaver.setBackgroundColor(Color.TRANSPARENT);
                        naverStatus = false;
                    }
                    break;
            }
        }
    };


    private void addListener(){

        // order image
        ivImageFile = findViewById(R.id.deal_iv_imageFile);
        tvSellUserName = findViewById(R.id.deal_tv_sellUserName);
        tvImageTitle = findViewById(R.id.deal_tv_imageTitle);
        tvImagePrice = findViewById(R.id.deal_tv_imagePrice);

        // deal price
        tvTotalCount = findViewById(R.id.deal_tv_total_count);
        tvTotalPrice = findViewById(R.id.deal_tv_total_price);
        tvFinalPrice = findViewById(R.id.deal_tv_final_price);

        // checkbox
        cb1 = findViewById(R.id.deal_cb1);
        cb2 = findViewById(R.id.deal_cb2);
        cb3 = findViewById(R.id.deal_cb3);
        cb4 = findViewById(R.id.deal_cb4);

        // button
        btnBack = findViewById(R.id.deal_iv_btn_back);
        btnDeal = findViewById(R.id.deal_btn_deal);

        // layout (payment)
        lycard = findViewById(R.id.deal_layout_card);
        lycash = findViewById(R.id.deal_layout_cash);
        lykakao = findViewById(R.id.deal_layout_kakao);
        lynaver = findViewById(R.id.deal_layout_naver);

        //payment onClickListener
        lycard.setOnClickListener(paymentOnClickListener);
        lycash.setOnClickListener(paymentOnClickListener);
        lykakao.setOnClickListener(paymentOnClickListener);
        lynaver.setOnClickListener(paymentOnClickListener);

        // button onClickListener
        btnBack.setOnClickListener(onClickListener);
        btnDeal.setOnClickListener(onClickListener);

        // checkbox onClickListener
        cb1.setOnCheckedChangeListener(cbOnCheckedChangeListener);
        cb2.setOnCheckedChangeListener(cbOnCheckedChangeListener);
        cb3.setOnCheckedChangeListener(cbOnCheckedChangeListener);
        cb4.setOnCheckedChangeListener(cbOnCheckedChangeListener);

    }


    // DB : select image detail
    private void connectSelectImageData(){
        String urlAddr = ShareVar.macIP + "jsp/deal_select_item_HK.jsp?imageCode=" +imageCode ;
        try {
            DealNetworkTaskHK networkTask = new DealNetworkTaskHK(DealItemActivityHK.this, urlAddr, "select");
            Object obj = networkTask.execute().get();
            dealItem = (ArrayList<CartHK>) obj;
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}