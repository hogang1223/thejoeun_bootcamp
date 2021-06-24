package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.aosproject.imagemarket.Adapter.BuyListAdapter;
import com.aosproject.imagemarket.Bean.BuyListBean;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskBuyList;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskProfileMain;
import com.aosproject.imagemarket.R;

import java.util.ArrayList;

import static com.aosproject.imagemarket.Util.ShareVar.loginEmail;
import static com.aosproject.imagemarket.Util.ShareVar.macIP;

public class BuyList extends Activity {

    String urlAddr = null;
    ArrayList<BuyListBean> buylist;
    BuyListAdapter adapter;

    ListView profile_lv_buylist_list;
    ImageView profile_iv_buylist_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_list);

        Log.v("Chk", "BuyList_onCreate");

        profile_iv_buylist_back = findViewById(R.id.profile_iv_buylist_back);
        profile_lv_buylist_list = findViewById(R.id.profile_lv_buylist_list);

        urlAddr = macIP + "jsp/profile_buylist.jsp?loginEmail=" + loginEmail;

        profile_iv_buylist_back.setOnClickListener(onClickListener);
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
            NetworkTaskBuyList networkTask = new NetworkTaskBuyList(BuyList.this, urlAddr, "select");
            Log.v("Chk", "BuyList_connectGetData_NetworkTaskProfileMain");
            Object obj = networkTask.execute().get();
            buylist = (ArrayList<BuyListBean>) obj;

            adapter = new BuyListAdapter(BuyList.this, R.layout.buylist_innerlist, buylist);
            profile_lv_buylist_list.setAdapter(adapter);
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
                case R.id.profile_iv_buylist_back:
                    finish();
                    break;
            }
        }
    };
}