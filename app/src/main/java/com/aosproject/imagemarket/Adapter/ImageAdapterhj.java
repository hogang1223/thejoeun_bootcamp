package com.aosproject.imagemarket.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aosproject.imagemarket.Bean.Imagehj;
import com.aosproject.imagemarket.R;

import java.util.ArrayList;

public class ImageAdapterhj extends RecyclerView.Adapter<ImageAdapterhj.ViewHolder> {

    private Context mContext = null;
    private int layout = 0;
    private ArrayList<Imagehj> images = null;
    private LayoutInflater inflater = null;
    private OnItemClickListener mListener = null;
    private OnItemLongClickListener mLongListener = null;
    private ArrayList<Imagehj> unFilteredlist;
    private ArrayList<Imagehj> filteredList;

    public ImageAdapterhj(Context mContext, int layout, ArrayList<Imagehj> images){
        this.mContext = mContext;
        this.layout = layout;
        this.images = images;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void  filterList(ArrayList<Imagehj> filteredList) {
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
        public TextView img;

        public ViewHolder(View itemView){
            super(itemView);
            img = itemView.findViewById(R.id.main_img_random);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION){
                        mListener.onItemClick(v, position);
                    }
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
    public void onBindViewHolder(@NonNull ImageAdapterhj.ViewHolder holder, int position) {

        holder.img.setText(images.get(position).getFilepath());

//        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
//        layoutParams.height = (int) ((position + 1) / 5.0 * 500);
//        holder.itemView.setLayoutParams(layoutParams);
//        holder.itemView.layout(0,0,0,0)
//        Glide.with(context).load(url).placeholder(R.color.gray).dontTransform().into(imageView);
    }

    @Override
    public int getItemCount() {
        //Log.v("Message", "Count " + Integer.toString(images.size()));
        return images.size();
    }
}
