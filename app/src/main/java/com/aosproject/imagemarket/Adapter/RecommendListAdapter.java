package com.aosproject.imagemarket.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aosproject.imagemarket.Activity.BuyList;
import com.aosproject.imagemarket.Activity.RecommendList;
import com.aosproject.imagemarket.Bean.RecommendListBean;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskRecommendList;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.RecommendListClickListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.aosproject.imagemarket.Util.ShareVar.loginEmail;
import static com.aosproject.imagemarket.Util.ShareVar.macIP;

public class RecommendListAdapter extends BaseAdapter {

    private Context mContext = null;
    private int layout = 0;
    private ArrayList<RecommendListBean> data = null;
    private LayoutInflater inflater = null;
    private RecommendListClickListener listener;

    String urlAddr = null;
    int imgCode;

    public RecommendListAdapter(Context mContext, int layout, ArrayList<RecommendListBean> data, RecommendListClickListener listener) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.listener = listener;
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

        ImageView img = convertView.findViewById(R.id.profile_tv_recommendlist_img);
        TextView seller = convertView.findViewById(R.id.profile_tv_recommendlist_seller);
        TextView title = convertView.findViewById(R.id.profile_tv_recommendlist_title);
        TextView price = convertView.findViewById(R.id.profile_tv_recommendlist_price);

        ImageView recommend = convertView.findViewById(R.id.profile_tv_recommendlist_recommend);
        recommend.setTag(data.get(position).getImageCode());

        Glide.with(mContext)
                .load(macIP + "/image/" + data.get(position).getFilepath())
                .override(110, 110)
                .centerCrop()
//                .error(R.drawable.---)
                .into(img);
        seller.setText(data.get(position).getMyname());
        title.setText(data.get(position).getTitle());

        String priceData = data.get(position).getPrice();
        int priceNum = Integer.parseInt(priceData);
        price.setText(String.format("%,d", priceNum) + "원");

        if(data.get(position).getRecommend() == 1) {
            recommend.setImageResource(R.drawable.ic_baseline_thumb_up_alt_24);
            recommend.setColorFilter(Color.parseColor("#845EC2"));
        }else {
            recommend.setImageResource(R.drawable.ic_baseline_thumb_up_off_alt_24);
            recommend.setColorFilter(Color.parseColor("#845EC2"));
        }

        imgCode = (Integer) recommend.getTag();
        recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = connectInsertData((Integer) recommend.getTag());
                if(result.equals("0")) {
                    Toast.makeText(mContext, "추천 취소를 실패하였습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext, "추천을 취소했습니다.", Toast.LENGTH_SHORT).show();
                    listener.onRecommendListClickListener(true);
                }
            }
        });

        return convertView;
    }

//    View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            String result = connectInsertData(imgCode);
//            if(result.equals("0")) {
//                Toast.makeText(mContext, "추천 취소를 실패하였습니다.", Toast.LENGTH_SHORT).show();
//            }else {
//                Toast.makeText(mContext, "추천을 취소했습니다.", Toast.LENGTH_SHORT).show();
//                listener.onRecommendListClickListener(true);
//            }
//
//        }
//    };

    private String connectInsertData(int code) {

        Log.v("Chk", "RecommendListAdapter connectInsertData imgCode : " + code);

        urlAddr = macIP + "jsp/profile_recommendlist_update.jsp?imgCode=" + code + "&loginEmail=" + loginEmail;
        String result = null;

        try {
            NetworkTaskRecommendList networkTask = new NetworkTaskRecommendList(mContext, urlAddr, "update");
            Object obj = networkTask.execute().get();
            result = (String) obj;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
