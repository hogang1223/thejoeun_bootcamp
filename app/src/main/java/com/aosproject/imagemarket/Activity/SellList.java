package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.aosproject.imagemarket.Adapter.ImgListAdapter;
import com.aosproject.imagemarket.Adapter.SellListAdapter;
import com.aosproject.imagemarket.Bean.ImgListBean;
import com.aosproject.imagemarket.Bean.SellListBean;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskImgList;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskSellList;
import com.aosproject.imagemarket.R;

import java.util.ArrayList;

import static com.aosproject.imagemarket.Util.ShareVar.loginEmail;
import static com.aosproject.imagemarket.Util.ShareVar.macIP;

public class SellList extends Activity {

    String urlAddr = null;
    ArrayList<SellListBean> selllist;
    SellListAdapter adapter;

    ListView profile_lv_selllist_list;
    ImageView profile_iv_selllist_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_list);

        Log.v("Chk", "SellList_onCreate");

        profile_iv_selllist_back = findViewById(R.id.profile_iv_selllist_back);
        profile_lv_selllist_list = findViewById(R.id.profile_lv_selllist_list);

        urlAddr = macIP + "jsp/profile_selllist.jsp?loginEmail=" + loginEmail;

        profile_iv_selllist_back.setOnClickListener(onClickListener);
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
            NetworkTaskSellList networkTask = new NetworkTaskSellList(SellList.this, urlAddr);
            Log.v("Chk", "BuyList_connectGetData_NetworkTaskProfileMain");
            Object obj = networkTask.execute().get();
            selllist = (ArrayList<SellListBean>) obj;

            adapter = new SellListAdapter(SellList.this, R.layout.selllist_innerlist, selllist);
            profile_lv_selllist_list.setAdapter(adapter);
            profile_lv_selllist_list.setOnItemClickListener(onItemClickListener);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.profile_iv_selllist_back:
                    finish();
                    break;
            }
        }
    };

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // 혜지언니 판매 상품 상세 페이지 추가해주세요~
        }
    };
}