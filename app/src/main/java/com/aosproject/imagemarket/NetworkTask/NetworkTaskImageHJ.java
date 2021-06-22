package com.aosproject.imagemarket.NetworkTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.aosproject.imagemarket.Bean.ImageHJ;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NetworkTaskImageHJ extends AsyncTask<Integer, String, Object> {

    Context context = null;
    String mAddr = null;
    ProgressDialog progressDialog = null;
    ArrayList<ImageHJ> images = null;
    String where = null;

    public NetworkTaskImageHJ(Context context, String mAddr, String where){
        this.context = context;
        this.mAddr = mAddr;
        this.images = images;
        this.images = new ArrayList<ImageHJ>();
        this.where = where;
    }

    @Override
    protected void onPreExecute() {
        Log.v("Message", "onPreExecute");
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Dialog");
        progressDialog.setMessage("Get.....");
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
                if (where.equals("mainSelect")) {
                    parserSelect(stringBuffer.toString());
                } else if(where.equals("detailSelect")){
                    parserSelect2(stringBuffer.toString());
                }else if(where.equals("insert")){
                    result = parserAction(stringBuffer.toString());
                }else if(where.equals("insertSelect")){
                    parserSelect3(stringBuffer.toString());
                }else {
                    result = parserAction(stringBuffer.toString());
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
        if(where.equals("mainSelect")){
            return images;
        }else if(where.equals("detailSelect")){
            return images;
        }else if(where.equals("insert")){
            return result;
        }else if (where.equals("select")){
            return images;
        }else if (where.equals("insertSelect")){
            return images;
        }else {
            return result;
        }
    }

    private String parserAction(String str){
        String returnValue = null;
        try {
            JSONObject jsonObject = new JSONObject(str);
            returnValue = jsonObject.getString("result");
            Log.v("Message", returnValue);
            // {"result" : "ok"} 이런걸 서버에서 받아온다는 것임
        }catch (Exception e){
            e.printStackTrace();
        }
        return returnValue;
    }

    private void parserSelect(String str){
        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("codes_info"));
            images.clear();

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                int code = jsonObject1.getInt("code");
                String filepath = jsonObject1.getString("filepath");
                String title = jsonObject1.getString("title");
                int price = jsonObject1.getInt("price");

                ImageHJ image = new ImageHJ(code, filepath, title, price);
                images.add(image);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void parserSelect2(String str){
        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("images_info"));
            images.clear();

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                int category = jsonObject1.getInt("category");
                int price = jsonObject1.getInt("price");
                String tag = jsonObject1.getString("tag");
                String detail = jsonObject1.getString("detail");
                String title = jsonObject1.getString("title");
                String filepath = jsonObject1.getString("filepath");
                String location = jsonObject1.getString("location");
                String fileformat = jsonObject1.getString("fileformat");
                String user_email = jsonObject1.getString("user_email");

                ImageHJ image = new ImageHJ(filepath, title, detail, fileformat, category, tag, price, location, user_email);
                images.add(image);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void parserSelect3(String str){
        try {
            Log.v("Message", "parserSelect3");
            JSONObject jsonObject = new JSONObject(str);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("code_info"));
            images.clear();

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                int code = jsonObject1.getInt("code");

                ImageHJ image = new ImageHJ(code);
                images.add(image);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}