package com.aosproject.imagemarket.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.aosproject.imagemarket.Activity.DealItemActivityHK;
import com.aosproject.imagemarket.Activity.ImageDetailActivity;
import com.aosproject.imagemarket.Bean.CartHK;
import com.aosproject.imagemarket.Fragment.CartFragment;
import com.aosproject.imagemarket.NetworkTask.CartNetworkTaskHK;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.CartItemLongClickListener;
import com.aosproject.imagemarket.Util.CartItemSwipeListener;
import com.aosproject.imagemarket.Util.CartItemTouchHelperCallback;
import com.aosproject.imagemarket.Util.CartItemTouchHelperListener;
import com.aosproject.imagemarket.Util.ShareVar;
import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CartAdapterHK extends RecyclerView.Adapter<CartAdapterHK.ViewHolder> implements CartItemTouchHelperListener {

    private final String TAG = "CartAdapterHK";

    private Context context;
    private ArrayList<CartHK> cartItems = null;
    private CartItemLongClickListener cartItemLongClickListener;
    private CartItemSwipeListener cartItemSwipeListener;
    boolean allBtnClickStatus = false;
    private ArrayList<View> itemViews;
    ArrayList<CartHK> selectedItems = null;


    //Constructor
    public CartAdapterHK(Context context, ArrayList<CartHK> cartItems, CartItemLongClickListener cartItemLongClickListener, CartItemSwipeListener cartItemSwipeListener, boolean allBtnClickStatus) {
        this.context = context;
        this.cartItems = cartItems;
        this.cartItemLongClickListener = cartItemLongClickListener;
        this.allBtnClickStatus = allBtnClickStatus;
        this.cartItemSwipeListener = cartItemSwipeListener;
        itemViews = new ArrayList<>();
    }

    //View Holder Class
    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardview;
        public ImageView ivImageFile, ivWaterMark;
        public TextView tvSellName, tvImageTitle, tvImagePrice;

        public ViewHolder(View itemView){
            super(itemView);

            cardview = itemView.findViewById(R.id.cartlist_cv_view);
            ivImageFile = itemView.findViewById(R.id.cartlist_iv_imageFile);
            tvSellName = itemView.findViewById(R.id.cartlist_tv_sellUserName);
            tvImageTitle = itemView.findViewById(R.id.cartlist_tv_imageTitle);
            tvImagePrice = itemView.findViewById(R.id.cartlist_tv_imagePrice);
            ivWaterMark = itemView.findViewById(R.id.cartlist_iv_watermark);
            itemViews.add(itemView);
        }

    } // END view holder class


    // Recycler View
    @NonNull
    @Override
    public CartAdapterHK.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_recyclerview_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapterHK.ViewHolder holder, int position) {
        // load image into imageview using glide
        Glide.with(context)
                .load(ShareVar.macIP + "image/" + cartItems.get(position).getImageFilepath())
                .override(110, 110)
                .centerCrop()
                .error(R.drawable.cart_image_error)
                .into(holder.ivImageFile);
        holder.tvSellName.setText(cartItems.get(position).getSellName());
        holder.tvImageTitle.setText(cartItems.get(position).getImageTitle());
        holder.tvImagePrice.setText(Integer.toString(cartItems.get(position).getImagePrice()) + "원");

        //tag
        holder.itemView.setTag(cartItems.get(position).getCartNo());
        holder.tvImageTitle.setTag(cartItems.get(position).getCartNo());

        if(cartItems.get(position).isSelected() == true){
            holder.ivWaterMark.setVisibility(View.VISIBLE);
            holder.itemView.setBackgroundColor(Color.LTGRAY);
            cartItemLongClickListener.onCartLongClickAction(true);
        }

        // click시 DetailView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(context, ImageDetailActivity.class);
                intent.putExtra("code", cartItems.get(position).getImageCode());
            }
        });
//        holder.ivImageFile.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int position = holder.getAdapterPosition();
//                Intent intent = new Intent(context, ImageDetailActivity.class);
//                intent.putExtra("code", cartItems.get(position).getImageCode());
//
//
////                // **********************************
////                // 현재 구매 페이지로 가는 중 detailView로 변경 필요
////                Intent intent = new Intent(context, DealItemActivityHK.class);
////                intent.putExtra("code", cartItems.get(position).getImageCode());
////                context.startActivity(intent);
////                // **********************************
//                return false;
//            }
//        });

        // long Click 시 선택
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                if(cartItems.get(position).isSelected() == false){
                    holder.ivWaterMark.setVisibility(View.VISIBLE);
                    holder.itemView.setBackgroundColor(Color.LTGRAY);
                    cartItems.get(position).setSelected(true);
                    cartItemLongClickListener.onCartLongClickAction(true);
                }else{
                    holder.ivWaterMark.setVisibility(View.INVISIBLE);
                    holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                    cartItems.get(position).setSelected(false);
                    cartItemLongClickListener.onCartLongClickAction(false);
                    if(getSelectedItems().size() == 0){
                        cartItemLongClickListener.onCartLongClickAction(false);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }


    // 미 사용
    @Override
    public boolean onItemMove(int from_position, int to_position) {
        return false;
    }


    @Override
    public void onItemSwipe(int position) {

        int cartNo = cartItems.get(position).getCartNo();
        String imageTitle = cartItems.get(position).getImageTitle();
        cartItems.remove(position);
        notifyDataSetChanged();
//        notifyItemRemoved(position);

        // 삭제 db 연결
        String result = connectSwipeDelete(cartNo);

        if(result.equals("1")){
            Toast.makeText(context, imageTitle + "를 장바구니에서 삭제했습니다.", Toast.LENGTH_SHORT).show();
            cartItemSwipeListener.onCartItemSwipeListener(true);
        }else {
            Toast.makeText(context, "삭제에 실패했습니다.", Toast.LENGTH_SHORT).show();
            cartItemSwipeListener.onCartItemSwipeListener(false);
        }
    }

    // 스와이프 한 아이템 삭제
    private String connectSwipeDelete(int cartNo){
        String result = null;
        String urlAddr = ShareVar.macIP + "jsp/cart_delete_swipe_item_HK.jsp?cartNo=" + cartNo;
        try {
            CartNetworkTaskHK networkTask = new CartNetworkTaskHK(context, urlAddr, "delete");
            Object obj = networkTask.execute().get();
            result = (String) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    // 롱클릭한 이미지를 selected ArrayList에 저장
    public ArrayList<CartHK> getSelectedItems(){
        selectedItems = new ArrayList<>();
        for(int i=0; i<cartItems.size(); i++){
            if(cartItems.get(i).isSelected()){
                selectedItems.add(cartItems.get(i));
            }
        }
        return selectedItems;
    }

    public void selectAllItems(){
        for(int i=0; i<itemViews.size(); i++){
            ImageView ivWaterMark =  itemViews.get(i).findViewById(R.id.cartlist_iv_watermark);
            CardView cardView = itemViews.get(i).findViewById(R.id.cartlist_cv_view);
            ivWaterMark.setVisibility(View.VISIBLE);
            cardView.setBackgroundColor(Color.LTGRAY);
        }
    }
    public void unSelectAllItems(){
        for(int i=0; i<itemViews.size(); i++){
            ImageView ivWaterMark =  itemViews.get(i).findViewById(R.id.cartlist_iv_watermark);
            CardView cardView = itemViews.get(i).findViewById(R.id.cartlist_cv_view);
            ivWaterMark.setVisibility(View.INVISIBLE);
            cardView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

//    public void selectedItemsSetting() {
//        if (selectedItems.isEmpty() == false) {
//            for (int i = 0; i < selectedItems.size(); i++) {
//                for (int j = 0; j < cartItems.size(); j++) {
//                    if (selectedItems.get(i).getCartNo() == cartItems.get(j).getCartNo()) {
//                        Log.v(TAG,"selected:"+selectedItems.get(i).getCartNo() + "cart:" + cartItems.get(j).getCartNo());
//                        ImageView ivWaterMark = itemViews.get(j).findViewById(R.id.cartlist_iv_watermark);
//                        CardView cardView = itemViews.get(j).findViewById(R.id.cartlist_cv_view);
//                        ivWaterMark.setVisibility(View.VISIBLE);
//                        cardView.setBackgroundColor(Color.LTGRAY);
//                        cartItems.get(j).setSelected(true);
//                    }
//                }
//            }
//        }
//    }

}
