package com.aosproject.imagemarket.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aosproject.imagemarket.Activity.ImageDetailActivity;
import com.aosproject.imagemarket.Activity.ImageEditActivity;
import com.aosproject.imagemarket.Activity.ImageEditDeleteActivity;
import com.aosproject.imagemarket.Bean.ImageHJ;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.ShareVar;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImageEditAdapterHJ extends RecyclerView.Adapter<ImageEditAdapterHJ.ViewHolder> {


    private Context mContext = null;
    private int layout = 0;
    private ArrayList<ImageHJ> images = null;
    private LayoutInflater inflater = null;
    private ImageEditAdapterHJ.OnItemClickListener mListener = null;
    private ImageEditAdapterHJ.OnItemLongClickListener mLongListener = null;


    public ImageEditAdapterHJ(Context mContext, int layout, ArrayList<ImageHJ> images){
        this.mContext = mContext;
        this.layout = layout;
        this.images = images;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void  filterList(ArrayList<ImageHJ> filteredList) {
        images = filteredList;
        notifyDataSetChanged();
    }


    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }

    public interface OnItemLongClickListener
    {
        void onItemLongClick(View v, int pos);
    }

    public void setOnItemClickListener(ImageEditAdapterHJ.OnItemClickListener listener)
    {
        this.mListener = listener;
    }

    public void setOnItemLongClickListener(ImageEditAdapterHJ.OnItemLongClickListener listener)
    {
        this.mLongListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView tv;

        public ViewHolder(View itemView){
            super(itemView);
            img = itemView.findViewById(R.id.edit_delete_imageview);
            tv = itemView.findViewById(R.id.edit_delete_textview_name_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    Log.v("Message", "onClick : " + position);
//                    if(position != RecyclerView.NO_POSITION){
//                        mListener.onItemClick(v, position);
//                    }
                    Intent intent = new Intent(v.getContext(), ImageEditDeleteActivity.class);
                    intent.putExtra("code", images.get(position).getCode());
                    Log.v("Message", "code 확인 : " + images.get(position).getCode());
                    mContext.startActivity(intent);
                }

            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();
                    Log.v("Message", "longClick : " + position);
                    img.setAlpha(80);
                    tv.setVisibility(View.VISIBLE);
                    tv.setText(images.get(position).getTitle()+"\n"+images.get(position).getPrice() + " 원");

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tv.setVisibility(View.INVISIBLE);
                            img.setAlpha(250);
                        }
                    }, 800);
                    return true;
                }
            });
        }
    }

    @Override
    public ImageEditAdapterHJ.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_delete_custom_layout, parent, false);
        ImageEditAdapterHJ.ViewHolder holder = new ImageEditAdapterHJ.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageEditAdapterHJ.ViewHolder holder, int position) {
        ImageHJ data = images.get(position);

        Glide.with(mContext).load(ShareVar.macIP + "image/" + data.getFilepath()).centerCrop().into(holder.img);
    }

    @Override
    public int getItemCount() {
        //Log.v("Message", "Count " + Integer.toString(images.size()));
        return images.size();
    }


}
