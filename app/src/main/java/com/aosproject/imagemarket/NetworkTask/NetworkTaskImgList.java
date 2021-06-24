package com.aosproject.imagemarket.NetworkTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aosproject.imagemarket.Bean.ImgListBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NetworkTaskImgList extends AsyncTask<Integer, String, Object> {

    // Field
    Context context = null;
    String mAddr = null;
    ProgressDialog progressDialog = null;
    ArrayList<ImgListBean> imglist;

    String where = null;

    // Construct
    public NetworkTaskImgList(Context context, String mAddr) {
        this.context = context;
        this.mAddr = mAddr;
        this.imglist = imglist;
        this.imglist = new ArrayList<ImgListBean>();
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

        return imglist;
    }

    private void parserBuyList(String str) {
        try {
            Log.v("Chk", "NetWork_parserBuyList_start");
            JSONObject jsonObject = new JSONObject(str);
            Log.v("Chk", "NetWork_parserBuyList_JSONObject");
            JSONArray jsonArray = new JSONArray(jsonObject.getString("profile_buylist"));
            Log.v("Chk", "NetWork_parserBuyList_JSONArray");
            imglist.clear();
            Log.v("Chk", "NetWork_parserBuyList_clear");

            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                int imageCode = jsonObject1.getInt("imageCode");
                String filepath = jsonObject1.getString("filepath");
                String title = jsonObject1.getString("title");
                String price = jsonObject1.getString("price");
                String sellCount = jsonObject1.getString("sellCount");

                ImgListBean img = new ImgListBean(imageCode, filepath, title, price, sellCount);
                imglist.add(img);
            }
            Log.v("Chk", "NetWork_doInBackground_parserBuyList selllist : " + imglist);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
