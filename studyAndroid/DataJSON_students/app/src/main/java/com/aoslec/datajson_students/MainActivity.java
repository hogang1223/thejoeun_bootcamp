package com.aoslec.datajson_students;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "Status";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parser();
    }

    private void parser(){
        Log.v(TAG, "parser()");
        InputStream inputStream = getResources().openRawResource(R.raw.students);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuffer stringBuffer = new StringBuffer();
        String line = null;

        try{
            while((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }
            Log.v(TAG, "stringBuffer : " + stringBuffer.toString());

            JSONObject jsonObject = new JSONObject(stringBuffer.toString());
            JSONArray jsonArray = new JSONArray(jsonObject.getString("students_info"));

//            "code" : "S001",
//            "name" : "박소명",
//            "dept" : "컴퓨터공학과",
//            "phone" : "123-4567"
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                String code = jsonObject1.getString("code");
                Log.v(TAG, "code : " + code);
                String name = jsonObject1.getString("name");
                Log.v(TAG, "name : " + name);
                String dept = jsonObject1.getString("dept");
                Log.v(TAG, "dept : " + dept);
                String phone = jsonObject1.getString("phone");
                Log.v(TAG, "phone : " + phone);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(inputStream != null) inputStream.close();
                if(inputStreamReader != null) inputStreamReader.close();
                if(bufferedReader != null) bufferedReader.close();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}