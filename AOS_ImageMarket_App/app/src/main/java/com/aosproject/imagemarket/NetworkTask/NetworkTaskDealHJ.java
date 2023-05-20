package com.aosproject.imagemarket.NetworkTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aosproject.imagemarket.Bean.DealHJ;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NetworkTaskDealHJ extends AsyncTask<Integer, String, Object> {

    Context context = null;
    String mAddr = null;
    ProgressDialog progressDialog = null;
    ArrayList<DealHJ> deals = null;
    String where = null;

    public NetworkTaskDealHJ(Context context, String mAddr, String where){
        this.context = context;
        this.mAddr = mAddr;
        this.deals = deals;
        this.deals = new ArrayList<DealHJ>();
        this.where = where;
    }


    @Override
    protected void onPreExecute() {
        Log.v("Message", "onPreExecute");
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Dialog");
        progressDialog.setMessage("로딩중입니다");
        progressDialog.show();
    }

    @Override
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
        Log.v("Message", "doInBackground");
        StringBuffer stringBuffer = new StringBuffer();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        // 입력 수정 삭제 검색했을 때 잘했는지 아닌지를 result에 받아서 처리할 것임
        String result = null;

        try {
            URL url = new URL(mAddr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);
            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                // 연결되었다면!!
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);

                while (true){
                    String strLine = bufferedReader.readLine();
                    if(strLine == null) break;
                    stringBuffer.append(strLine + "\n");
                }
                if (where.equals("recommendSelect")) {
                    parserSelect(stringBuffer.toString());
                } else if(where.equals("detailSelect")){
                    parserSelect(stringBuffer.toString());
                }else if(where.equals("update")){
                    //result = parserAction(stringBuffer.toString());
                }else if(where.equals("accountSelect")){
                    parserSelect(stringBuffer.toString());
                } else {
                    //result = parserAction(stringBuffer.toString());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(bufferedReader != null) bufferedReader.close();
                if(inputStreamReader != null) inputStreamReader.close();
                if(inputStream != null) inputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(where.equals("recommendSelect")){
            return deals;
        }else if(where.equals("detailSelect")){
            return deals;
        }else if(where.equals("update")){
            return result;
        }else if (where.equals("select")){
            return deals;
        }else if(where.equals("accountSelect")){
            return deals;
        }else {
            return result;
        }
    }

//    private String parserAction(String str){
//        String returnValue = null;
//        try {
//            JSONObject jsonObject = new JSONObject(str);
//            returnValue = jsonObject.getString("result");
//            Log.v("Message", returnValue);
//            // {"result" : "ok"} 이런걸 서버에서 받아온다는 것임
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return returnValue;
//    }

    private void parserSelect(String str){
        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("recommend_info"));
            deals.clear();

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                int recommend = jsonObject1.getInt("recommend");

                DealHJ deal = new DealHJ(recommend);
                deals.add(deal);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
