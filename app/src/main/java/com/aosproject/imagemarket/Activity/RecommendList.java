package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.aosproject.imagemarket.Adapter.RecommendListAdapter;
import com.aosproject.imagemarket.Bean.RecommendListBean;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskRecommendList;
import com.aosproject.imagemarket.R;

import java.util.ArrayList;

import static com.aosproject.imagemarket.Util.ShareVar.loginEmail;
import static com.aosproject.imagemarket.Util.ShareVar.macIP;

public class RecommendList extends AppCompatActivity {

    String urlAddr = null;
    ArrayList<RecommendListBean> recommendlist;
    RecommendListAdapter adapter;

    ListView profile_lv_recommendlist_list;
    ImageView profile_iv_recommendlist_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_list);

        Log.v("Chk", "BuyList_onCreate");

        profile_iv_recommendlist_back = findViewById(R.id.profile_iv_recommendlist_back);
        profile_lv_recommendlist_list = findViewById(R.id.profile_lv_recommendlist_list);

        urlAddr = macIP + "profile_recommendlist.jsp?loginEmail=" + loginEmail;

        profile_iv_recommendlist_back.setOnClickListener(onClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.v("Chk", "BuyList_onResume");
        connectGetData();
        Log.v("Chk", "BuyList_onResume_connectGetData");
    }

    private void connectGetData() {
        try {

            Log.v("Chk", "BuyList_connectGetData");
            NetworkTaskRecommendList networkTask = new NetworkTaskRecommendList(RecommendList.this, urlAddr, "select");
            Log.v("Chk", "BuyList_connectGetData_NetworkTaskProfileMain");
            Object obj = networkTask.execute().get();
            recommendlist = (ArrayList<RecommendListBean>) obj;

            adapter = new RecommendListAdapter(RecommendList.this, R.layout.recommendlist_innerlist, recommendlist);
            profile_lv_recommendlist_list.setAdapter(adapter);
//            profile_lv_buylist_list.setOnItemClickListener(onItemClickListener);
//            profile_lv_buylist_list.setOnItemLongClickListener(onItemLongClickListener);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.profile_iv_recommendlist_back:

                    break;
            }
        }
    };
}