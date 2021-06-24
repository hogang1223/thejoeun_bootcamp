package com.aosproject.imagemarket.NetworkTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aosproject.imagemarket.Bean.CartHK;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DealNetworkTaskHK extends AsyncTask<Integer, String, Object> {

    private final String TAG = "DealNetworkTaskHK";
    Context context = null;
    String mAddr = null;
    ProgressDialog progressDialog = null;
    ArrayList<CartHK> dealImages;

    // Network Task를 검색 입력, 수정, 삭제 구분없이 하나로 사용키위해 생성자 변수 추가
    String where = null;

    public DealNetworkTaskHK(Context context, String mAddr, String where) {
        this.context = context;
        this.mAddr = mAddr;
        this.dealImages = dealImages;
        this.dealImages = new ArrayList<CartHK>();
        this.where = where;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Dialog");
        progressDialog.setMessage("Get....");
        progressDialog.show();
    }

    @Override
    //바뀌는 데이터... 진행중에 쓰는것
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        progressDialog.dismiss();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        progressDialog.dismiss();
    }

    @Override
    protected Object doInBackground(Integer... integers) {

        StringBuffer stringBuffer = new StringBuffer();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        String result = null;

        try {
            URL url = new URL(mAddr);
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
                parserSelect(stringBuffer.toString());
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
        return dealImages;

    }


    private void parserSelect (String str){
        try {
            dealImages.clear();
            JSONObject jsonObject = new JSONObject(str);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("deal_info"));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);

                int imageCode = jsonObject1.getInt("imageCode");
                String sellName = jsonObject1.getString("sellName");
                String sellEmail = jsonObject1.getString("sellEmail");
                String imageTitle = jsonObject1.getString("imageTitle");
                String imageFilepath = jsonObject1.getString("imageFilepath");
                int imagePrice = jsonObject1.getInt("imagePrice");
                Log.v("DealNetworkTaskHK", "imageTitle : " + imageTitle);

                CartHK dealImage = new CartHK(imageCode, sellName, sellEmail, imageTitle, imageFilepath, imagePrice);

                dealImages.add(dealImage);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
