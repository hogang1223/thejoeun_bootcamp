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

    ImageView iv_back, iv_light, iv_img_1, iv_img_2, iv_img_3;
    TextView tv_all_profit, tv_now_profit, tv_past_profit, tv_all_item, tv_today_item, tv_month_item;
//    TextView tv_title_1, tv_price_1, tv_sellcount_1, tv_title_2, tv_price_2, tv_sellcount_2, tv_title_3, tv_price_3, tv_sellcount_3;
    LinearLayout layoutPast;
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

//        iv_img_1 = findViewById(R.id.profile_iv_sellreport_img_1);
//        tv_title_1 = findViewById(R.id.profile_tv_sellreport_title_1);
//        tv_price_1 = findViewById(R.id.profile_tv_sellreport_price_1);
//        tv_sellcount_1 = findViewById(R.id.profile_tv_sellreport_sellcount_1);
//        iv_img_2 = findViewById(R.id.profile_iv_sellreport_img_2);
//        tv_title_2 = findViewById(R.id.profile_tv_sellreport_title_2);
//        tv_price_2 = findViewById(R.id.profile_tv_sellreport_price_2);
//        tv_sellcount_2 = findViewById(R.id.profile_tv_sellreport_sellcount_2);
//        iv_img_3 = findViewById(R.id.profile_iv_sellreport_img_3);
//        tv_title_3 = findViewById(R.id.profile_tv_sellreport_title_3);
//        tv_price_3 = findViewById(R.id.profile_tv_sellreport_price_3);
//        tv_sellcount_3 = findViewById(R.id.profile_tv_sellreport_sellcount_3);

        iv_back.setOnClickListener(onClickListener);
        iv_light.setOnClickListener(onClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        connectGetData();

        Log.v("Chk", "SellReport_onResume connectGetData_end");
        tv_all_profit.setText(Integer.toString(allProfit) + "원");
        tv_now_profit.setText(Integer.toString(nowProfit) + "원");
        tv_past_profit.setText(Integer.toString(allProfit - nowProfit) + "원");
        tv_all_item.setText(Integer.toString(allItem) + "건");
        tv_today_item.setText(Integer.toString(todayItem) + "건");
        tv_month_item.setText(Integer.toString(monthItem) + "건");
    }

    private void connectGetData() {
        try {
            urlAddr = macIP + "profile_sellreport_allProfit.jsp?loginEmail=" + loginEmail;
            NetworkTaskSellReport networkTaskAllProfit = new NetworkTaskSellReport(SellReport.this, urlAddr, "all_profit");
            Object objAllProfit = networkTaskAllProfit.execute().get();

            allProfit = Integer.parseInt((String) objAllProfit);
            Log.v("Chk", "SellReport_connectGetData allProfit : " + allProfit);

            urlAddr = macIP + "profile_sellreport_nowProfit.jsp?loginEmail=" + loginEmail;
            NetworkTaskSellReport networkTaskNowProfit = new NetworkTaskSellReport(SellReport.this, urlAddr, "now_profit");
            Object objNowProfit = networkTaskNowProfit.execute().get();

//            nowProfit = (int) objNowProfit;
            nowProfit = Integer.parseInt((String) objNowProfit);
            Log.v("Chk", "SellReport_connectGetData nowProfit : " + nowProfit);

            urlAddr = macIP + "profile_sellreport_allItem.jsp?loginEmail=" + loginEmail;
            NetworkTaskSellReport networkTaskAllItem = new NetworkTaskSellReport(SellReport.this, urlAddr, "all_item");
            Object objAllItem = networkTaskAllItem.execute().get();

//            allItem = (int) objAllItem;
            allItem = Integer.parseInt((String) objAllItem);
            Log.v("Chk", "SellReport_connectGetData allItem : " + allItem);

            urlAddr = macIP + "profile_sellreport_todayItem.jsp?loginEmail=" + loginEmail;
            NetworkTaskSellReport networkTaskTodayItem = new NetworkTaskSellReport(SellReport.this, urlAddr, "today_item");
            Object objTodayItem = networkTaskTodayItem.execute().get();

//            todayItem = (int) objTodayItem;
            todayItem = Integer.parseInt((String) objTodayItem);
            Log.v("Chk", "SellReport_connectGetData todayItem : " + todayItem);

            urlAddr = macIP + "profile_sellreport_monthItem.jsp?loginEmail=" + loginEmail;
            NetworkTaskSellReport networkTaskMonthItem = new NetworkTaskSellReport(SellReport.this, urlAddr, "month_item");
            Object objMonthItem = networkTaskMonthItem.execute().get();

//            monthItem = (int) objMonthItem;
            monthItem = Integer.parseInt((String) objMonthItem);
            Log.v("Chk", "SellReport_connectGetData monthItem : " + monthItem);

            urlAddr = macIP + "profile_sellreport_bestItems.jsp?loginEmail=" + loginEmail;
            NetworkTaskBestItems networkTaskBestItems = new NetworkTaskBestItems(SellReport.this, urlAddr);
            Object obj = networkTaskBestItems.execute().get();
            sellreport_best = (ArrayList<SellReportBean>) obj;
            Log.v("Chk", "SellReport_connectGetData sellreport_best(before adapter) : " + sellreport_best);

            adapter = new SellReportAdapter(SellReport.this, R.layout.sellreport_innerlist, sellreport_best);
            lv_best_items.setAdapter(adapter);

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