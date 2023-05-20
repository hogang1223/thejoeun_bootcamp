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

public class CartNetworkTaskHK extends AsyncTask<Integer, String, Object> {

    private final String TAG = "CartNetworkTaskHK";

    Context context = null;
    String mAddr = null;
    ProgressDialog progressDialog = null;
    ArrayList<CartHK> cartImages;

    // Network Task를 검색 입력, 수정, 삭제 구분없이 하나로 사용키위해 생성자 변수 추가
    String where = null;

    public CartNetworkTaskHK(Context context, String mAddr, String where) {
        this.context = context;
        this.mAddr = mAddr;
        this.cartImages = cartImages;
        this.cartImages = new ArrayList<CartHK>();
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
                switch (where) {
                    case "select":
                        parserSelect(stringBuffer.toString());
                        break;
                    default:
                        result = parserAction(stringBuffer.toString());
                        break;

                }
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

        switch (where) {
            case "select":
                return cartImages;
            default:
                return result;
        }
//        if(where.equals("select")){
//            return nameCards;
//        }else{
//            return result;
//        }
    }

    private String parserAction(String str){
        String returnValue = null;
        try{
            JSONObject jsonObject = new JSONObject(str);
            returnValue = jsonObject.getString("result");


        }catch (Exception e){
            e.printStackTrace();
        }

        return returnValue;
    }

    private void parserSelect(String str){
        try{
            cartImages.clear();
            JSONObject jsonObject = new JSONObject(str);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("cart_info"));
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);

                int imageCode = jsonObject1.getInt("imageCode");
                int cartNo = jsonObject1.getInt("cartNo");
                String sellName = jsonObject1.getString("sellName");
                String sellEmail = jsonObject1.getString("sellEmail");
                String imageTitle = jsonObject1.getString("imageTitle");
                String imageFilepath = jsonObject1.getString("imageFilepath");
                int imagePrice = jsonObject1.getInt("imagePrice");
                int cartStatus = jsonObject1.getInt("cartStatus");
                Log.v("CartNetworkTaskHK", "imageTitle : " + imageTitle);

                CartHK cartImage = new CartHK(imageCode, cartNo, sellName, sellEmail, imageTitle, imageFilepath, imagePrice, cartStatus);

                cartImages.add(cartImage);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

} // end line
