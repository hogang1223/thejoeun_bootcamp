package com.aosproject.imagemarket.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aosproject.imagemarket.Activity.DealCartItemActivityHK;
import com.aosproject.imagemarket.Adapter.CartAdapterHK;
import com.aosproject.imagemarket.Bean.CartHK;
import com.aosproject.imagemarket.NetworkTask.CartNetworkTaskHK;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.CartItemLongClickListener;
import com.aosproject.imagemarket.Util.CartItemSwipeListener;
import com.aosproject.imagemarket.Util.CartItemTouchHelperCallback;
import com.aosproject.imagemarket.Util.ShareVar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class CartFragment extends Fragment implements CartItemLongClickListener, CartItemSwipeListener {

    private final String TAG = "CartFragment";

    boolean clickStatus = false;
    String urlAddr = null;
    ArrayList<CartHK> cartItems;
    ArrayList<CartHK> selectedCartItems = new ArrayList<>();


    RecyclerView cartRecyclerView;
    RecyclerView.LayoutManager cartLayoutManager;
    CartAdapterHK cartAdapter;
    ItemTouchHelper cartHelper;

    LinearLayout yesItemLayout, noItemLayout, bottomLayout, bottomLayoutBig, bottomLayoutSmall;
    TextView btnAll, tvCount, tvPrice, btnDelete, btnDeal, tvPriceSmall;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        addListener(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

            connectGetData();
    }

    // button Action
    View.OnClickListener onButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                // 상품 전체 선택 버튼 클릭 시
                case R.id.cart_tv_btn_all:
                    if (clickStatus == false) {
                        bottomLayoutSmall.setVisibility(View.GONE);
                        bottomLayoutBig.setVisibility(View.VISIBLE);
                        btnAll.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cart_button_shape_bgpurple));
                        btnAll.setTextColor(Color.WHITE);
                        btnAll.setText("상품 전체 해제");
                        tvCount.setText(cartItems.size() + "개");
                        tvPrice.setText(String.format("%,d", cartTotalPrice("all")) + "원");
                        tvPriceSmall.setText(String.format("%,d", cartTotalPrice("all")) + "원");
                        cartAdapter.selectAllItems();
                        cartItemIsSelectedTrue();
                        clickStatus = true;
                        btnDelete.setOnClickListener(onAllItemBtnClickListener);
                        btnDeal.setOnClickListener(onAllItemBtnClickListener);

                    } else {
                        bottomLayoutSmall.setVisibility(View.VISIBLE);
                        bottomLayoutBig.setVisibility(View.GONE);
                        btnAll.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cart_button_shape_bgwhite));
                        btnAll.setTextColor(0xFF845EC2);
                        btnAll.setText("상품 전체 선택");
                        tvCount.setText("0개");
                        tvPrice.setText("0원");
                        tvPriceSmall.setText("0원");
                        cartAdapter.unSelectAllItems();
                        cartItemIsSelectedFalse();
                        clickStatus = false;
                        btnDelete.setOnClickListener(onAllItemBtnClickListener);
                        btnDeal.setOnClickListener(onAllItemBtnClickListener);
                    }
                    break;
                case R.id.cart_layout_bottom_big:
                    Log.v(TAG, "cart_layout_bottom_big btn");
                    bottomLayoutBig.setVisibility(View.GONE);
                    bottomLayoutSmall.setVisibility(View.VISIBLE);
                    break;
                case R.id.cart_layout_bottom_small:
                    Log.v(TAG, "cart_layout_bottom_small btn");
                    bottomLayoutSmall.setVisibility(View.GONE);
                    bottomLayoutBig.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    // selected Item deal or delete button Click
    View.OnClickListener onSelectedItemBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                // delete
                case R.id.cart_tv_btn_delete:
                    if (selectedCartItems.size() == 0) {
                        Snackbar.make(v, "삭제할 상품을 선택해주세요", Snackbar.LENGTH_SHORT).show();
                    } else {
                        new AlertDialog.Builder(getContext())
                                .setMessage("선택하신 상품을 장바구니에서 삭제하시겠습니까?")
                                .setCancelable(false)
                                .setNegativeButton("Cancel", null)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String result = connectItemDelete("selected");
                                        if (result.equals("0")) {
                                            Snackbar.make(v, "삭제에 실패했습니다.", Snackbar.LENGTH_SHORT).show();
                                        } else {
                                            Snackbar.make(v, "선택하신 상품을 장바구니에서 비웠습니다.", Snackbar.LENGTH_SHORT).show();
                                        }
                                        bottomLayoutSmall.setVisibility(View.VISIBLE);
                                        bottomLayoutBig.setVisibility(View.GONE);
                                        tvPriceSmall.setText("0원");
                                        tvPrice.setText("0원");
                                        tvCount.setText("0개");
                                        onResume();
                                    }

                                })
                                .show();
                    }
                    break;

                // deal all
                case R.id.cart_tv_btn_deal:
                    if (selectedCartItems.size() == 0) {
                        Snackbar.make(v, "주문할 상품을 선택해주세요", Snackbar.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(getContext(), DealCartItemActivityHK.class);
                        intent.putExtra("dealCartItems", selectedCartItems);
                        startActivity(intent);
                    }
                    break;
            }

        }
    };

    // All Item deal or delete button click
    View.OnClickListener onAllItemBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                // delete all
                case R.id.cart_tv_btn_delete:
                    if (clickStatus == false) {
                        Snackbar.make(v, "삭제할 상품을 선택해주세요", Snackbar.LENGTH_SHORT).show();
                    } else {
                        new AlertDialog.Builder(getContext())
                                .setMessage("장바구니를 비우시겠습니까?")
                                .setCancelable(false)
                                .setNegativeButton("Cancel", null)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String result = connectItemDelete("all");
                                        if (result.equals("0")) {
                                            Snackbar.make(v, "삭제에 실패했습니다.", Snackbar.LENGTH_SHORT).show();
                                        } else {
                                            Snackbar.make(v, "장바구니를 비웠습니다.", Snackbar.LENGTH_SHORT).show();
                                        }
//                                        bottomLayout.setVisibility(View.GONE);
                                        onResume();

                                    }

                                })
                                .show();

                    }
                    break;
                // deal all
                case R.id.cart_tv_btn_deal:
                    if (clickStatus == false) {
                        Snackbar.make(v, "주문할 상품을 선택해주세요", Snackbar.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(getContext(), DealCartItemActivityHK.class);
                        intent.putExtra("dealCartItems", cartItems);
                        startActivity(intent);
                    }
                    break;
            }
        }
    }; // all item deal or delete button action


    @Override
    public void onCartItemSwipeListener(boolean isSelected) {
        if (isSelected){
            onResume();
        }
    }

    // button Long Click Listener
    @Override
    public void onCartLongClickAction(boolean isSelected) {

        selectedCartItems = cartAdapter.getSelectedItems();

        if(isSelected){
            bottomLayoutSmall.setVisibility(View.GONE);
            bottomLayoutBig.setVisibility(View.VISIBLE);
            tvCount.setText( selectedCartItems.size()+ "개");
            tvPrice.setText(String.format("%,d", cartTotalPrice("selected")) + "원");
            tvPriceSmall.setText(String.format("%,d", cartTotalPrice("selected")) + "원");

            if(selectedCartItems.size() == cartItems.size()){
                btnAll.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cart_button_shape_bgpurple));
                btnAll.setTextColor(Color.WHITE);
                btnAll.setText("상품 전체 해제");
                clickStatus = true;
            }

        }else {
            bottomLayoutSmall.setVisibility(View.VISIBLE);
            bottomLayoutBig.setVisibility(View.GONE);
            tvCount.setText(selectedCartItems.size() + "개");
            tvPrice.setText(String.format("%,d", cartTotalPrice("selected")) + "원");
            tvPriceSmall.setText(String.format("%,d", cartTotalPrice("selected")) + "원");
            btnAll.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cart_button_shape_bgwhite));
            btnAll.setTextColor(0xFF845EC2);
            btnAll.setText("상품 전체 선택");
            clickStatus = false;
        }
        btnDeal.setOnClickListener(onSelectedItemBtnClickListener);
        btnDelete.setOnClickListener(onSelectedItemBtnClickListener);

    } // End button Long Click Listener

    // total price 구하기
    private int cartTotalPrice (String itemStatus) {
        int totalPrice = 0;
        if(itemStatus.equals("selected")){
            selectedCartItems = cartAdapter.getSelectedItems();
            for(int i=0; i<selectedCartItems.size(); i++){
                totalPrice += selectedCartItems.get(i).getImagePrice();
            }
        }else {
            for (int i = 0; i < cartItems.size(); i++) {
                totalPrice += cartItems.get(i).getImagePrice();
            }
        }
        return totalPrice;
    }

    // 전체 선택시 Cart Bean의 isSelected true
    private void cartItemIsSelectedTrue(){
        for(int i=0; i<cartItems.size(); i++){
            cartItems.get(i).setSelected(true);
        }
    }

    // 전체 선택 해제시 Cart Bean의 isSelected false
    private void cartItemIsSelectedFalse(){
        for(int i=0; i<cartItems.size(); i++){
            cartItems.get(i).setSelected(false);
        }
    }

    // DB 연결 Method
    // cart Adapter (recyclerView) 연결
    public void connectGetData() {
        try {
            urlAddr = ShareVar.macIP + "jsp/cart_select_all_HK.jsp?loginEmail=" + ShareVar.loginEmail;
            CartNetworkTaskHK cartNetworkTask = new CartNetworkTaskHK(getContext(), urlAddr, "select");
            Object obj = cartNetworkTask.execute().get();
            cartItems = (ArrayList<CartHK>) obj;

            if (cartItems.size() == 0) {
                noItemLayout.setVisibility(View.VISIBLE);
                yesItemLayout.setVisibility(View.GONE);
                bottomLayout.setVisibility(View.GONE);
            } else {
                noItemLayout.setVisibility(View.GONE);
                yesItemLayout.setVisibility(View.VISIBLE);
                bottomLayout.setVisibility(View.VISIBLE);
                bottomLayoutSmall.setVisibility(View.VISIBLE);
                bottomLayoutBig.setVisibility(View.GONE);

                if(selectedCartItems.isEmpty()==false) {
                    for (int i = 0; i < selectedCartItems.size(); i++) {
                        for (int j = 0; j < cartItems.size(); j++) {
                            if (selectedCartItems.get(i).getCartNo() == cartItems.get(j).getCartNo()) {
                                Log.v(TAG,"selected:"+selectedCartItems.get(i).getCartNo() + "cart:" + cartItems.get(j).getCartNo());
                                cartItems.get(j).setSelected(true);
                            }
                        }
                    }
                }

                // recycler view 연결
                cartAdapter = new CartAdapterHK(getContext(), cartItems,this, this::onCartItemSwipeListener,  clickStatus);
                cartRecyclerView.setAdapter(cartAdapter);

                // item swipe
                cartHelper = new ItemTouchHelper(new CartItemTouchHelperCallback(cartAdapter));
                cartHelper.attachToRecyclerView(cartRecyclerView);
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    // selected Item Parsing
    private String itemParsing(String itemStatus){
        StringBuilder cartNos = new StringBuilder();
        if(itemStatus == "selected") {
            for (int i = 0; i < selectedCartItems.size(); i++) {
                if (i == 0) {
                    cartNos.append(selectedCartItems.get(i).getCartNo());
                } else {
                    cartNos.append(",").append(selectedCartItems.get(i).getCartNo());
                }
            }
        }else{
            for (int i = 0; i < cartItems.size(); i++) {
                if (i == 0) {
                    cartNos.append(cartItems.get(i).getCartNo());
                } else {
                    cartNos.append(",").append(cartItems.get(i).getCartNo());
                }
            }
        }
        return cartNos.toString();
    }

    // item delete
    private String connectItemDelete(String itemStatus){
        String result = "";
        String cartNos = "";

        if(itemStatus == "selected"){
            cartNos = itemParsing("selected");
        }else {
            cartNos = itemParsing("all");
        }

        String urlAddr = ShareVar.macIP + "jsp/cart_delete_item_HK.jsp?cartNos=" + cartNos;
        try {
            CartNetworkTaskHK networkTask = new CartNetworkTaskHK(getContext(), urlAddr, "delete");
            Object obj = networkTask.execute().get();
            result = (String) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    // layout widget connect
    private void addListener (View view) {
        bottomLayout = view.findViewById(R.id.cart_layout_bottom);
        tvCount = view.findViewById(R.id.cart_tv_total_count);
        tvPrice = view.findViewById(R.id.cart_tv_total_price);
        tvPriceSmall = view.findViewById(R.id.cart_tv_total_price_small);
        yesItemLayout = view.findViewById(R.id.cart_layout_yesItem);
        noItemLayout = view.findViewById(R.id.cart_layout_noItem);

        // button
        btnAll = view.findViewById(R.id.cart_tv_btn_all);
        btnDelete = view.findViewById(R.id.cart_tv_btn_delete);
        btnDeal = view.findViewById(R.id.cart_tv_btn_deal);
        bottomLayoutBig = view.findViewById(R.id.cart_layout_bottom_big);
        bottomLayoutSmall = view.findViewById(R.id.cart_layout_bottom_small);

        // recycler View
        cartRecyclerView = view.findViewById(R.id.cart_rv_list);
        cartLayoutManager = new LinearLayoutManager(getContext());
        cartRecyclerView.setLayoutManager(cartLayoutManager);

        // button click listener
        btnAll.setOnClickListener(onButtonClickListener);
        bottomLayoutBig.setOnClickListener(onButtonClickListener);
        bottomLayoutSmall.setOnClickListener(onButtonClickListener);
    }
}