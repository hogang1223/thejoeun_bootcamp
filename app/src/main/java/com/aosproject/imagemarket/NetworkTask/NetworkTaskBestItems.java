package com.aosproject.imagemarket.NetworkTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aosproject.imagemarket.Bean.ImgListBean;
import com.aosproject.imagemarket.Bean.SellReportBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NetworkTaskBestItems extends AsyncTask<Integer, String, Object> {

    // Field
    Context context = null;
    String mAddr = null;
    ProgressDialog progressDialog = null;
    ArrayList<SellReportBean> sellreport;

    // Construct
    public NetworkTaskBestItems(Context context, String mAddr) {
        this.context = context;
        this.mAddr = mAddr;
        this.sellreport = sellreport;
        this.sellreport = new ArrayList<SellReportBean>();
    }

    // progress 실행
    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Dialog");
        progressDialog.setMessage("Get....");
        progressDialog.show();
    }

    // progress 종료
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        progressDialog.dismiss();
    }

    // progress 취소
    @Override
    protected void onCancelled() {
        super.onCancelled();
        progressDialog.dismiss();
    }

    @Override
    protected Object doInBackground(Integer... integers) {

        Log.v("Chk", "NetWork doInBackground start");

        StringBuffer stringBuffer = new StringBuffer();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        String result = null;

        try {
            Log.v("Chk", "NetWork doInBackground start try");
            URL url = new URL(mAddr);
            Log.v("Chk", "NetWork doInBackground  mAddr : " + mAddr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);

                while (true) {
                    String strline = bufferedReader.readLine();
                    if (strline == null) break;
                    stringBuffer.append(strline + "\n");
                }

                Log.v("Chk", "NetWork_doInBackground_parserBuyList_start stringBuffer : " + stringBuffer.toString());
                parserBuyList(stringBuffer.toString());
                Log.v("Chk", "NetWork_doInBackground_parserBuyList_end");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (inputStreamReader != null) inputStreamReader.close();
                if (inputStream != null) inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Log.v("Chk", "NetWork_doInBackground_return sellreport : " + sellreport);
        return sellreport;
    }

    private void parserBuyList(String str) {

        Log.v("Chk", "NetWork_parserBuyList start");
        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("profile_sellreport_bestItems"));
            sellreport.clear();

            Log.v("Chk", "NetWork_parserBuyList sellreport clear");

            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                int imgCode = jsonObject1.getInt("imgCode");
                String filepath = jsonObject1.getString("filepath");
                String title = jsonObject1.getString("title");
                String price = jsonObject1.getString("price");
                String sellCount = jsonObject1.getString("sellCount");

                SellReportBean best = new SellReportBean(imgCode, filepath, title, price, sellCount);
                sellreport.add(best);
            }

            Log.v("Chk", "NetWork_parserBuyList sellreport : " + sellreport);
        }catch(Exception e) {
            e.printStackTrace();
            Log.v("Chk", "NetWork_parserBuyList error");
        }
    }
}
