package com.aosproject.imagemarket.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aosproject.imagemarket.Bean.CartHK;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.ShareVar;
import com.bumptech.glide.Glide;


import java.util.ArrayList;

public class DealCartItemAdapterHK extends RecyclerView.Adapter<DealCartItemAdapterHK.ViewHolder> {

    private final String TAG = "DealCartItemAdapterHK";

    private Context context;
    private ArrayList<CartHK> dealCartItems = null;

    //Constructor
    public DealCartItemAdapterHK(Context context, ArrayList<CartHK> dealCartItems) {
        this.context = context;
        this.dealCartItems = dealCartItems;
    }
    //View Holder Class
    public class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout linearLayout;
        public ImageView ivImageFile, ivWaterMark;
        public TextView tvSellName, tvImageTitle, tvImagePrice;

        public ViewHolder(View itemView){
            super(itemView);

            linearLayout = itemView.findViewById(R.id.cartlist_layout);
            ivImageFile = itemView.findViewById(R.id.cartlist_iv_imageFile);
            tvSellName = itemView.findViewById(R.id.cartlist_tv_sellUserName);
            tvImageTitle = itemView.findViewById(R.id.cartlist_tv_imageTitle);
            tvImagePrice = itemView.findViewById(R.id.cartlist_tv_imagePrice);
            ivWaterMark = itemView.findViewById(R.id.cartlist_iv_watermark);
        }

    } // END view holder class

    @NonNull
    @Override
    public DealCartItemAdapterHK.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_recyclerview_layout, parent, false);
        DealCartItemAdapterHK.ViewHolder viewHolder = new DealCartItemAdapterHK.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DealCartItemAdapterHK.ViewHolder holder, int position) {
        // load image into imageview using glide
        Glide.with(context)
                .load(ShareVar.macIP + "image/" + dealCartItems.get(position).getImageFilepath())
                .override(110, 110)
                .centerCrop()
                .error(R.drawable.cart_image_error)
                .into(holder.ivImageFile);

        holder.tvSellName.setText(dealCartItems.get(position).getSellName());
        holder.tvImageTitle.setText(dealCartItems.get(position).getImageTitle());
        holder.tvImagePrice.setText(Integer.toString(dealCartItems.get(position).getImagePrice()) + "원");
        holder.linearLayout.setPadding(20,0,0,20);
    }

    @Override
    public int getItemCount() {
        return dealCartItems.size();
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }
}
