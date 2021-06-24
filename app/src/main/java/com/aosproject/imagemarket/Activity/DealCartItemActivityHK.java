package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.aosproject.imagemarket.Adapter.CartAdapterHK;
import com.aosproject.imagemarket.Adapter.DealCartItemAdapterHK;
import com.aosproject.imagemarket.Bean.CartHK;
import com.aosproject.imagemarket.NetworkTask.CartNetworkTaskHK;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.ShareVar;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DealCartItemActivityHK extends AppCompatActivity {

    private final String TAG = "DealActivityHK";

    ShareVar shareVar = new ShareVar();
    String urlAddr = null;
    ArrayList<CartHK> dealCartItems;

    RecyclerView dealCartRecyclerView;
    RecyclerView.LayoutManager dealCartLayoutManager;
    DealCartItemAdapterHK dealCartAdapter;

    ImageView btnBack;
    TextView tvTotalCount, tvTotalPrice, tvFinalPrice;
    CheckBox cb1, cb2, cb3, cb4;
    Button btnDeal;
    LinearLayout lycard, lycash, lykakao, lynaver;
    boolean cardStatus = false;
    boolean cashStatus = false;
    boolean kakaoStatus = false;
    boolean naverStatus = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_cart_item_hk);

        // hide actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        dealCartItems = (ArrayList<CartHK>) intent.getSerializableExtra("dealCartItems");

        // layout widget connect
        addListener();

    }

    @Override
    protected void onResume() {
        super.onResume();

        // recycler view 연결
        dealCartAdapter = new DealCartItemAdapterHK(DealCartItemActivityHK.this, dealCartItems);
        dealCartRecyclerView.setAdapter(dealCartAdapter);

    }

    // button Action
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.deal_cart_iv_btn_back:
                    finish();
                    break;

                // 결제 버튼 클릭 시
                case R.id.deal_cart_btn_deal:
                    connectInsertDealData();    // DB : Insert Deal Table
                    connectUpdateCartStatus();  // DB : Update Cart Table cstatus

                    Intent intent = new Intent(DealCartItemActivityHK.this ,DealCompeletedActivityHK.class);
                    startActivity(intent);
            }
        }
    };

    // checkbox checked condition check
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
                case R.id.deal_cart_layout_card:
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
                case R.id.deal_cart_layout_cash:
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
                case R.id.deal_cart_layout_kakao:
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
                case R.id.deal_cart_layout_naver:
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

    // Data Parsing

    // 1. sell Email Parsing
    private String sellEmailParsing(){
        StringBuilder sellEmails = new StringBuilder();
        for (int i = 0; i < dealCartItems.size(); i++) {
            if (i == 0) {
                sellEmails.append(dealCartItems.get(i).getSellEmail());
            } else {
                sellEmails.append(",").append(dealCartItems.get(i).getSellEmail());
            }
        }
        return sellEmails.toString();
    }
    // 2. image Code Parsing
    private String imageCodeParsing(){
        StringBuilder imageCodes = new StringBuilder();
        for (int i = 0; i < dealCartItems.size(); i++) {
            if (i == 0) {
                imageCodes.append(dealCartItems.get(i).getImageCode());
            } else {
                imageCodes.append(",").append(dealCartItems.get(i).getImageCode());
            }
        }
        return imageCodes.toString();
    }
    // 3. Cart No Parsing
    private String cartNoParsing(){
        StringBuilder cartNos = new StringBuilder();
        for (int i = 0; i < dealCartItems.size(); i++) {
            if (i == 0) {
                cartNos.append(dealCartItems.get(i).getCartNo());
            } else {
                cartNos.append(",").append(dealCartItems.get(i).getCartNo());
            }
        }
        return cartNos.toString();
    }

    // DB 연결하기
    // 1. Deal Table Insert
    private String connectInsertDealData(){
        String result =  "";
        String sellEmails = sellEmailParsing();
        String imageCodes = imageCodeParsing();
        String buyCode = new SimpleDateFormat("yyMMddHmsS").format(new Date());
        String urlAddr = shareVar.macIP + "jsp/deal_insert_item_HK.jsp?sellEmails=" + sellEmails + "&imageCodes=" + imageCodes + "&buyCode=" + buyCode + "&loginEmail=" + shareVar.loginEmail;
        try {
            CartNetworkTaskHK networkTask = new CartNetworkTaskHK(DealCartItemActivityHK.this, urlAddr, "insert");
            Object obj = networkTask.execute().get();
            result = (String) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    // 2. Cart Table Update (cstatus = 0)
    private String connectUpdateCartStatus(){
        String result = "";
        String cartNos = cartNoParsing();

        String urlAddr = shareVar.macIP + "jsp/cart_delete_item_HK.jsp?cartNos=" + cartNos;
        try {
            CartNetworkTaskHK networkTask = new CartNetworkTaskHK(DealCartItemActivityHK.this, urlAddr, "update");
            networkTask.execute().get();
            result = "1";
        }catch (Exception e){
            e.printStackTrace();
            result = "0";
        }
        return result;
    }


    // layout widget connect
    private void addListener(){

        // recycler View
        dealCartRecyclerView = findViewById(R.id.deal_cart_rv_list);
        dealCartLayoutManager = new LinearLayoutManager(DealCartItemActivityHK.this);
        dealCartRecyclerView.setLayoutManager(dealCartLayoutManager);

        // textView
        tvTotalCount = findViewById(R.id.deal_cart_tv_total_count);
        tvTotalPrice = findViewById(R.id.deal_cart_tv_total_price);
        tvFinalPrice = findViewById(R.id.deal_cart_tv_final_price);

        // checkbox
        cb1 = findViewById(R.id.deal_cart_cb1);
        cb2 = findViewById(R.id.deal_cart_cb2);
        cb3 = findViewById(R.id.deal_cart_cb3);
        cb4 = findViewById(R.id.deal_cart_cb4);

        // button
        btnBack = findViewById(R.id.deal_cart_iv_btn_back);
        btnDeal = findViewById(R.id.deal_cart_btn_deal);

        // layout (payment)
        lycard = findViewById(R.id.deal_cart_layout_card);
        lycash = findViewById(R.id.deal_cart_layout_cash);
        lykakao = findViewById(R.id.deal_cart_layout_kakao);
        lynaver = findViewById(R.id.deal_cart_layout_naver);

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

        // setText
        tvTotalCount.setText(dealCartItems.size() + "개");
        tvTotalPrice.setText(String.format("%,d", totalPrice()) + "원");
        tvFinalPrice.setText(String.format("%,d", totalPrice()) + "원");
    }

    private int totalPrice(){
        int totalPrice = 0;
        for(int i=0; i<dealCartItems.size(); i++){
            totalPrice += dealCartItems.get(i).getImagePrice();
        }
        return totalPrice;
    }
}