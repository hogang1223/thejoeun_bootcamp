package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.aosproject.imagemarket.Bean.ImageHJ;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskImageHJ;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.ShareVar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class ImageEditActivity extends Activity {

    String urlAddr, filepath = null;
    int code = 0;
    TextInputLayout titleLayout, contentLayout, tagLayout, priceLayout, locationLayout = null;
    TextInputEditText titleEdit, contentEdit, tagEdit, priceEdit, locationEdit = null;
    Spinner formatSpinner, categorySpinner = null;
    ImageView imageView, back = null;
    Button backBtn, okBtn = null;
    ArrayList<ImageHJ> images = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_edit);

        Intent intent = getIntent();
        code = intent.getIntExtra("code", 0);

        urlAddr = ShareVar.macIP + "jsp/detailImageSelect.jsp?code=" + code;
        Log.v("Message", urlAddr);
        try {
            NetworkTaskImageHJ networkTask = new NetworkTaskImageHJ(this, urlAddr, "detailSelect");
            Object obj = networkTask.execute().get();
            images = (ArrayList<ImageHJ>) obj;
        }catch (Exception e){
            e.printStackTrace();
        }

        titleEdit.setText(images.get(0).getTitle());
        contentEdit.setText(images.get(0).getDetail());
        tagEdit.setText(images.get(0).getTag());
        priceEdit.setText(images.get(0).getPrice());
        if(images.get(0).getLocation().equals("none")){
        }else {
            locationEdit.setText(images.get(0).getLocation());
        }

        //images.get(0).getFileformat().equals("")


    }
}