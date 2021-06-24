package com.aosproject.imagemarket.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aosproject.imagemarket.Bean.ImgListBean;
import com.aosproject.imagemarket.Bean.SellListBean;
import com.aosproject.imagemarket.R;

import java.util.ArrayList;

public class SellListAdapter extends BaseAdapter {

    private Context mContext = null;
    private int layout = 0;
    private ArrayList<SellListBean> data = null;
    private LayoutInflater inflater = null;

    public SellListAdapter(Context mContext, int layout, ArrayList<SellListBean> data) {
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
        return data.get(position).getBuyCode();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(this.layout, parent, false);

        ImageView img = convertView.findViewById(R.id.profile_iv_selllist_img);
        TextView title = convertView.findViewById(R.id.profile_tv_selllist_title);
        TextView price = convertView.findViewById(R.id.profile_tv_selllist_price);
        TextView buyCode = convertView.findViewById(R.id.profile_tv_selllist_buycode);
        TextView dealDate = convertView.findViewById(R.id.profile_tv_selllist_selldate);

//        img.setImageResource();
        title.setText(data.get(position).getTitle());
        price.setText(data.get(position).getPrice() + "원");
        dealDate.setText(data.get(position).getDealDate());
        buyCode.setText(data.get(position).getBuyCode());

        return convertView;
    }
}
