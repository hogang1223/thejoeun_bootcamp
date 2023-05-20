package com.aosproject.imagemarket.NetworkTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class DownloadDeviceNetworkTaskHK extends AsyncTask<Void, Void, Bitmap> {

    private final String TAG = "DownloadNetworkTaskHK";
    Context context = null;
    String mAddr = null;

    public DownloadDeviceNetworkTaskHK(Context context, String mAddr) {
        this.context = context;
        this.mAddr = mAddr;
    }
    private static HashMap<String, Bitmap> bitmapHash = new HashMap<String, Bitmap>();
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }


    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap bitmap = null;

        try{
            if(bitmapHash.containsKey(mAddr)){
                Bitmap oldBitmap = bitmapHash.remove(mAddr);
                if(oldBitmap != null){
                    oldBitmap.recycle();
                    oldBitmap = null;
                }

            }
            URL url = new URL(mAddr);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            bitmapHash.put(mAddr, bitmap);

        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }

}
