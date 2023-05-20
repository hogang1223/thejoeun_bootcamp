package com.aosproject.imagemarket.NetworkTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkTaskAddImageHJ extends AsyncTask<Integer, String, Integer> {

    Context context = null;
    String mAddr = null;
    ProgressDialog progressDialog = null;
    String devicePath;

    public NetworkTaskAddImageHJ(Context context, String mAddr, String devicePath) {
        this.context = context;
        this.mAddr = mAddr;
        this.devicePath = devicePath;
    }


    @Override
    protected void onPreExecute() {
        Log.v("Message", "onPreExecute()");
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Status");
        progressDialog.setMessage("Uploading ....");
        progressDialog.show();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        Log.v("Message", "onProgressUpdate()");
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        Log.v("Message", "onPostExecute()");
        progressDialog.dismiss();

    }

    @Override
    protected void onCancelled() {
        Log.v("Message","onCancelled()");
        super.onCancelled();
    }


    @Override
    protected Integer doInBackground(Integer... integers) {
        File file = new File(devicePath);
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file))
                .build();

        Request request = new Request.Builder()
                .url(mAddr)
                .post(requestBody)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            Log.v("Message", "Success");
            return 1;

        }catch (Exception e){
            Log.v("Message", "Error");
            e.printStackTrace();
            return 0;
        }
    }

}
