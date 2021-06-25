package com.aosproject.imagemarket.NetworkTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aosproject.imagemarket.Bean.BuyListBean;
import com.aosproject.imagemarket.Bean.RecommendListBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NetworkTaskRecommendList extends AsyncTask<Integer, String, Object> {

    // Field
    Context context = null;
    String mAddr = null;
    ProgressDialog progressDialog = null;
    ArrayList<RecommendListBean> recommendlist;

    String where = null;

    // Construct
    public NetworkTaskRecommendList(Context context, String mAddr, String where) {
        this.context = context;
        this.mAddr = mAddr;
        this.recommendlist = recommendlist;
        this.recommendlist = new ArrayList<RecommendListBean>();
        this.where = where;
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
                if(where.equals("update")) {
                    result = parserAction(stringBuffer.toString());
                }else {
                    parserRecommendList(stringBuffer.toString());
                }
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

        if(where.equals("update")) {
            return result;
        }else {
            return recommendlist;
        }
    }

    private void parserRecommendList(String str) {
        try {
            Log.v("Chk", "NetWork_parserBuyList_start");
            JSONObject jsonObject = new JSONObject(str);
            Log.v("Chk", "NetWork_parserBuyList_JSONObject");
            JSONArray jsonArray = new JSONArray(jsonObject.getString("profile_recommendlist"));
            Log.v("Chk", "NetWork_parserBuyList_JSONArray");
            recommendlist.clear();
            Log.v("Chk", "NetWork_parserBuyList_clear");

            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                int imgCode = jsonObject1.getInt("imgCode");
                String filepath = jsonObject1.getString("filepath");
                String myname = jsonObject1.getString("myname");
                String title = jsonObject1.getString("title");
                String price = jsonObject1.getString("price");
                int recommend = jsonObject1.getInt("recommend");

                RecommendListBean rl = new RecommendListBean(imgCode, filepath, myname, title, price, recommend);
                recommendlist.add(rl);
            }
            Log.v("Chk", "NetWork_doInBackground_parserBuyList buylist : " + recommendlist);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private String parserAction(String str) {
        String returnValue = null;
        try {
            JSONObject jsonObject = new JSONObject(str);
            returnValue = jsonObject.getString("result");
        }catch(Exception e) {
            e.printStackTrace();
        }
        return  returnValue;
    }
}
