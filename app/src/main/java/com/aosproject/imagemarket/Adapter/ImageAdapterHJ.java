package com.aosproject.imagemarket.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aosproject.imagemarket.Bean.ImageHJ;
import com.aosproject.imagemarket.Fragment.HomeFragment;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.ShareVar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.ArrayList;

public class ImageAdapterHJ extends RecyclerView.Adapter<ImageAdapterHJ.ViewHolder> {

    private Context mContext = null;
    private int layout = 0;
    private ArrayList<ImageHJ> images = null;
    private LayoutInflater inflater = null;
    private OnItemClickListener mListener = null;
    private OnItemLongClickListener mLongListener = null;

    public ImageAdapterHJ(Context mContext, int layout, ArrayList<ImageHJ> images){
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

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener)
    {
        this.mLongListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView tv;

        public ViewHolder(View itemView){
            super(itemView);
            img = itemView.findViewById(R.id.main_imageview);
            tv = itemView.findViewById(R.id.main_textview_name_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    Log.v("Message", "onClick : " + position);
                    if(position != RecyclerView.NO_POSITION){
                        mListener.onItemClick(v, position);
                    }
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
                    return true;
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_custom_layout, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapterHJ.ViewHolder holder, int position) {
        ImageHJ data = images.get(position);

        Glide.with(mContext).load(ShareVar.macIP + "image/" + data.getFilepath()).transform(new FitCenter(), new RoundedCorners(25)).into(holder.img);
    }

    @Override
    public int getItemCount() {
        //Log.v("Message", "Count " + Integer.toString(images.size()));
        return images.size();
    }
}
