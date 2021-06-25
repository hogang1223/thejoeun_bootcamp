package com.aosproject.imagemarket.Activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.aosproject.imagemarket.Adapter.ImgListAdapter;
import com.aosproject.imagemarket.Adapter.SellReportAdapter;
import com.aosproject.imagemarket.Bean.ImgListBean;
import com.aosproject.imagemarket.Bean.SellReportBean;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskBestItems;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskSellReport;
import com.aosproject.imagemarket.R;

import java.util.ArrayList;

import static com.aosproject.imagemarket.Util.ShareVar.loginEmail;
import static com.aosproject.imagemarket.Util.ShareVar.macIP;

public class SellReport extends AppCompatActivity {

    ImageView iv_back, iv_light;
    TextView tv_all_profit, tv_now_profit, tv_past_profit, tv_all_item, tv_today_item, tv_month_item;
    LinearLayout layoutPast, layout_null;
    ListView lv_best_items;

    BottomSheet bottomSheet;

    String urlAddr = null;
    int allProfit, nowProfit, allItem, todayItem, monthItem;
    ArrayList<SellReportBean> sellreport_best;
    SellReportAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_report);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        iv_back = findViewById(R.id.profile_iv_sellreport_back);
        iv_light = findViewById(R.id.profile_iv_sellreport_light);

        tv_all_profit = findViewById(R.id.profile_tv_sellreport_all_profit);
        tv_now_profit = findViewById(R.id.profile_tv_sellreport_now_profit);
        layoutPast = findViewById(R.id.profile_layout_sellreport_past);
        tv_past_profit = findViewById(R.id.profile_tv_sellreport_past_profit);
        tv_all_item = findViewById(R.id.profile_tv_sellreport_all_item);
        tv_today_item = findViewById(R.id.profile_tv_sellreport_today_item);
        tv_month_item = findViewById(R.id.profile_tv_sellreport_month_item);

        lv_best_items = findViewById(R.id.profile_lv_sellreport_best_items);

        layout_null = findViewById(R.id.profile_layout_sellreport_null);
        layout_null.setVisibility(View.INVISIBLE);

        iv_back.setOnClickListener(onClickListener);
        iv_light.setOnClickListener(onClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        connectGetData();

        Log.v("Chk", "SellReport_onResume connectGetData_end");

        tv_all_profit.setText(String.format("%,d", allProfit) + "원");
        tv_now_profit.setText(String.format("%,d", nowProfit) + "원");
        tv_past_profit.setText(String.format("%,d", allProfit - nowProfit) + "원");
        tv_all_item.setText(String.format("%,d", allItem) + "건");
        tv_today_item.setText(String.format("%,d", todayItem) + "건");
        tv_month_item.setText(String.format("%,d", monthItem) + "건");
    }

    private void connectGetData() {
        try {
            urlAddr = macIP + "jsp/profile_sellreport_allProfit.jsp?loginEmail=" + loginEmail;
            NetworkTaskSellReport networkTaskAllProfit = new NetworkTaskSellReport(SellReport.this, urlAddr, "all_profit");
            Object objAllProfit = networkTaskAllProfit.execute().get();

            allProfit = Integer.parseInt((String) objAllProfit);
            Log.v("Chk", "SellReport_connectGetData allProfit : " + allProfit);

            urlAddr = macIP + "jsp/profile_sellreport_nowProfit.jsp?loginEmail=" + loginEmail;
            NetworkTaskSellReport networkTaskNowProfit = new NetworkTaskSellReport(SellReport.this, urlAddr, "now_profit");
            Object objNowProfit = networkTaskNowProfit.execute().get();

            nowProfit = Integer.parseInt((String) objNowProfit);
            Log.v("Chk", "SellReport_connectGetData nowProfit : " + nowProfit);

            urlAddr = macIP + "jsp/profile_sellreport_allItem.jsp?loginEmail=" + loginEmail;
            NetworkTaskSellReport networkTaskAllItem = new NetworkTaskSellReport(SellReport.this, urlAddr, "all_item");
            Object objAllItem = networkTaskAllItem.execute().get();

            allItem = Integer.parseInt((String) objAllItem);
            Log.v("Chk", "SellReport_connectGetData allItem : " + allItem);

            urlAddr = macIP + "jsp/profile_sellreport_todayItem.jsp?loginEmail=" + loginEmail;
            NetworkTaskSellReport networkTaskTodayItem = new NetworkTaskSellReport(SellReport.this, urlAddr, "today_item");
            Object objTodayItem = networkTaskTodayItem.execute().get();

            todayItem = Integer.parseInt((String) objTodayItem);
            Log.v("Chk", "SellReport_connectGetData todayItem : " + todayItem);

            urlAddr = macIP + "jsp/profile_sellreport_monthItem.jsp?loginEmail=" + loginEmail;
            NetworkTaskSellReport networkTaskMonthItem = new NetworkTaskSellReport(SellReport.this, urlAddr, "month_item");
            Object objMonthItem = networkTaskMonthItem.execute().get();

            monthItem = Integer.parseInt((String) objMonthItem);
            Log.v("Chk", "SellReport_connectGetData monthItem : " + monthItem);

            urlAddr = macIP + "jsp/profile_sellreport_bestItems.jsp?loginEmail=" + loginEmail;
            NetworkTaskBestItems networkTaskBestItems = new NetworkTaskBestItems(SellReport.this, urlAddr);
            Object obj = networkTaskBestItems.execute().get();
            sellreport_best = (ArrayList<SellReportBean>) obj;
            Log.v("Chk", "SellReport_connectGetData sellreport_best(before adapter) : " + sellreport_best);

            if(sellreport_best.size() == 0) {
                lv_best_items.setVisibility(View.INVISIBLE);
                layout_null.setVisibility(View.VISIBLE);
            }else {
                adapter = new SellReportAdapter(SellReport.this, R.layout.sellreport_innerlist, sellreport_best);
                lv_best_items.setAdapter(adapter);
            }

            Log.v("Chk", "SellReport_connectGetData sellreport_best : " + sellreport_best);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.profile_iv_sellreport_back:
                    finish();
                    break;
                case R.id.profile_iv_sellreport_light:

                    iv_light.setColorFilter(Color.parseColor("#845EC2"));

                    bottomSheet = new BottomSheet();
                    bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                    break;
            }
        }
    };
}