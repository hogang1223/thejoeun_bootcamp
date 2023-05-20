package com.aoslec.networkjson_student;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/*한번 만들어 놓으면 거의 변하지 않아..*/
public class NetworkTask extends AsyncTask<Integer, String, Object> {
    Context context = null;
    String mAddr = null;
    ProgressDialog progressDialog = null;
    ArrayList<Student> students;
    final static String TAG = "Status";


    public NetworkTask(Context context, String mAddr){
        this.context = context;
        this.mAddr = mAddr;
        this.students = new ArrayList<Student>();
    }
    @Override
    //타스크 도는중
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Dialog");
        progressDialog.setMessage("down...");
        progressDialog.show();
    }
    @Override
    //바뀌는 데이터... 진행중에 쓰는것
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
    @Override
    //끝났다
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        progressDialog.dismiss();
    }
    @Override
    //중간에 취소를 했다
    protected void onCancelled() {
        super.onCancelled();
    }
    @Override
    //백그라운드에서는 불러오기 파싱해서 어레이리스트에 넣어주기
    protected Object doInBackground(Integer... integers) {
        StringBuffer stringBuffer = new StringBuffer();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try{
            URL url = new URL(mAddr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
                while (true){
                    String strline = bufferedReader.readLine();
                    if(strline == null) break;
                    stringBuffer.append(strline + "\n");
                }
                parser(stringBuffer.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if (bufferedReader != null) bufferedReader.close();
                if (inputStreamReader != null) inputStreamReader.close();
                if (inputStream != null) inputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return students;
    }
    //str에 데이터가 있다
    private void parser(String str){
        try{
            JSONObject jsonObject = new JSONObject(str);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("students_info"));
            students.clear();
            //어레이리스트 정리되었다
            for (int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                String code = jsonObject1.getString("code");
                String name = jsonObject1.getString("name");
                Log.v("Status", "name : " + name);
                String dept = jsonObject1.getString("dept");
                String phone = jsonObject1.getString("phone");

                Student student = new Student(code, name, dept, phone);
                students.add(student);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}