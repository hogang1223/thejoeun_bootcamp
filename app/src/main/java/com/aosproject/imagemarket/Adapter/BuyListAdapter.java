package com.aosproject.imagemarket.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aosproject.imagemarket.Activity.BuyList;
import com.aosproject.imagemarket.Bean.BuyListBean;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskBuyList;
import com.aosproject.imagemarket.R;

import java.util.ArrayList;

import static com.aosproject.imagemarket.Util.ShareVar.macIP;

public class BuyListAdapter extends BaseAdapter {

    private Context mContext = null;
    private int layout = 0;
    private ArrayList<BuyListBean> data = null;
    private LayoutInflater inflater = null;

    String urlAddr = null;
    int dealNo;
    int recommendInt;

    public BuyListAdapter(Context mContext, int layout, ArrayList<BuyListBean> data) {
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
        return data.get(position).getDealNo();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(this.layout, parent, false);

        TextView date = convertView.findViewById(R.id.profile_tv_buylist_date);
        ImageView img = convertView.findViewById(R.id.profile_tv_buylist_img);
        TextView seller = convertView.findViewById(R.id.profile_tv_buylist_seller);
        TextView title = convertView.findViewById(R.id.profile_tv_buylist_title);
        TextView price = convertView.findViewById(R.id.profile_tv_buylist_price);

        ImageView download = convertView.findViewById(R.id.profile_iv_buylist_download);
        ImageView recommend = convertView.findViewById(R.id.profile_tv_buylist_recommend);

        date.setText(data.get(position).getDealDate() + " - " + data.get(position).getBuyCode());
//        img.setImageResource();
        seller.setText(data.get(position).getMyname());
        title.setText(data.get(position).getTitle());

        String priceData = data.get(position).getPrice();
        int priceNum = Integer.parseInt(priceData);
        price.setText(String.format("%,d", priceNum) + "원");

        if(data.get(position).getDownloadCount() >= 3) {
            download.setVisibility(View.INVISIBLE);
        }else {
            download.setVisibility(View.VISIBLE);
            download.setColorFilter(Color.parseColor("#845EC2"));
        }

        recommendInt = data.get(position).getRecommend();
        dealNo = data.get(position).getDealNo();

        if(recommendInt == 1) {
            recommend.setImageResource(R.drawable.ic_baseline_thumb_up_alt_24);
            recommend.setColorFilter(Color.parseColor("#845EC2"));
        }else {
            recommend.setImageResource(R.drawable.ic_baseline_thumb_up_off_alt_24);
            recommend.setColorFilter(Color.parseColor("#845EC2"));
        }

        download.setOnClickListener(onClickListener);
        recommend.setOnClickListener(onClickListener);

        return convertView;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.profile_iv_buylist_download:
                    //효경언니 여기 추가하세요~~
                    break;
                case R.id.profile_tv_buylist_recommend:
                    String result = connectInsertData();
                    if(result.equals("1")) {
                        Toast.makeText(mContext, "추천을 취소했습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, BuyList.class);
                    }else {
                        Toast.makeText(mContext, "추천 취소를 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }
            }
        }
    };

    private String connectInsertData() {

        int update;
        if(recommendInt == 1) {
            update = 0;
        }else {
            update = 1;
        }

        urlAddr = macIP + "jsp/profile_buylist_recommend.jsp?dealNo=" + dealNo + "&update=" + update;
        String result = null;

        try {
            NetworkTaskBuyList networkTask = new NetworkTaskBuyList(mContext, urlAddr, "recommend");
            Object obj = networkTask.execute().get();
            result = (String) obj;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
