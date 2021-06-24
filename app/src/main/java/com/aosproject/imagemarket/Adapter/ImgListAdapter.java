package com.aosproject.imagemarket.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aosproject.imagemarket.Bean.ImgListBean;
import com.aosproject.imagemarket.R;

import java.util.ArrayList;

public class ImgListAdapter extends BaseAdapter {

    private Context mContext = null;
    private int layout = 0;
    private ArrayList<ImgListBean> data = null;
    private LayoutInflater inflater = null;

    public ImgListAdapter(Context mContext, int layout, ArrayList<ImgListBean> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getImageCode();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(this.layout, parent, false);

        ImageView img = convertView.findViewById(R.id.profile_tv_imglist_img);
        TextView title = convertView.findViewById(R.id.profile_tv_imglist_title);
        TextView price = convertView.findViewById(R.id.profile_tv_imglist_price);
        TextView sellCount = convertView.findViewById(R.id.profile_tv_imglist_sellcount);

//        img.setImageResource();
        title.setText(data.get(position).getTitle());
        price.setText(data.get(position).getPrice() + "원");
        sellCount.setText("판매 수 " + data.get(position).getSellCount());

        return convertView;
    }
}
