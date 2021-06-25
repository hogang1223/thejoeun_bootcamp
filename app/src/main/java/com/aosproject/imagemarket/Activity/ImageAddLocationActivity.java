package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.aosproject.imagemarket.Bean.ImageHJ;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskAddImageHJ;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskImageAddHJ;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskImageHJ;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.ShareVar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageAddLocationActivity extends AppCompatActivity {

    String filepath, title, detail, fileformat, tag, img_path = null;
    int category, price, image_code = 0;
    Button button;
    ImageView imageView;
    TextInputLayout layout;
    TextInputEditText editText;
    String urlAddr, urlAddr2, urlAddr3, urlAddr4 = null;
    ArrayList<ImageHJ> images = null;
    List<Address> address = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_add_location);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        filepath = intent.getStringExtra("filepath");
        title = intent.getStringExtra("title");
        detail = intent.getStringExtra("detail");
        fileformat = intent.getStringExtra("fileformat");
        category = intent.getIntExtra("category", 0);
        tag = intent.getStringExtra("tag");
        price = intent.getIntExtra("price", 0);
        img_path = intent.getStringExtra("img_path");

        Log.v("Message", "이미지 업로드 확인!!!" + img_path);

        button = findViewById(R.id.add_location_btn_next);
        editText = findViewById(R.id.add_location_edit);
        layout = findViewById(R.id.add_location_layout);
        imageView = findViewById(R.id.add_location_ivbtn_back);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(300)});

        button.setOnClickListener(onClickListener);
        imageView.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String[] parserData = editText.getText().toString().split(",");
            urlAddr = ShareVar.macIP + "jsp/";

            switch (v.getId()){
                case R.id.add_location_btn_next:

                    urlAddr4 = ShareVar.macIP + "jsp/multipartRequest.jsp";
                    Log.v("Message", urlAddr4);
                    NetworkTaskAddImageHJ networkTask = new NetworkTaskAddImageHJ(ImageAddLocationActivity.this, urlAddr4, img_path);
                    try {
                        Integer result = (Integer) networkTask.execute(100).get();
                        switch (result) {
                            case 1:
                                File file = new File(img_path);
                                file.delete();
                                new AlertDialog.Builder(ImageAddLocationActivity.this)
                                        .setMessage("이미지 판매 등록을 완료하시겠습니까?")
                                        .setCancelable(false)
                                        .setNegativeButton("Cancel", null)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = null;

                                                if(editText.getText().toString().isEmpty()){
                                                    urlAddr = urlAddr + "imageInsert2.jsp?filepath=" + filepath + "&title=" + title + "&detail=" + detail + "&fileformat=" + fileformat
                                                            + "&category=" + category + "&tag=" + tag + "&price=" + price;
                                                    Log.v("Message", "나와라!!!" + urlAddr);

                                                    String result = connectInsertData();

                                                    if (result.equals("1")){
                                                        try {
                                                            urlAddr3 = ShareVar.macIP + "jsp/imageSelect.jsp";
                                                            Log.v("Message", urlAddr3);
                                                            NetworkTaskImageHJ networkTask = new NetworkTaskImageHJ(ImageAddLocationActivity.this, urlAddr3, "insertSelect");
                                                            Object obj = networkTask.execute().get();
                                                            images = (ArrayList<ImageHJ>) obj;
                                                            image_code = images.get(0).getCode();
                                                            Log.v("Message", "code : " + Integer.toString(image_code));
                                                            urlAddr2 = ShareVar.macIP + "jsp/imageInsert3.jsp?";
                                                            urlAddr2 = urlAddr2 + "user_email=" + ShareVar.loginEmail + "&image_code=" + image_code;
                                                            Log.v("Message", urlAddr2);
                                                            String result2 = connectInsertData2();
                                                            Log.v("Message", "제발!!!" + result2);
                                                            if (result2.equals("1")){
                                                                Log.v("Message", "한 번 더!!!" + result2);
                                                                new AlertDialog.Builder(ImageAddLocationActivity.this)
                                                                        .setMessage("이미지 등록이 완료되었습니다!")
                                                                        .setCancelable(false)
                                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialog, int which) {
                                                                                Intent intent = new Intent(ImageAddLocationActivity.this, ImageEditDeleteActivity.class);
                                                                                intent.putExtra("code", image_code);
                                                                                startActivity(intent);
                                                                            }
                                                                        })
                                                                        .show();
                                                            }else {
                                                                Toast.makeText(ImageAddLocationActivity.this, "입력이 실패 되었습니다", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }catch (Exception e){

                                                        }
                                                    }else {
                                                        Toast.makeText(ImageAddLocationActivity.this, "입력이 실패 되었습니다", Toast.LENGTH_SHORT).show();
                                                    }
                                                }else {
                                                    try {
                                                        Geocoder g = new Geocoder(ImageAddLocationActivity.this);

                                                        address = g.getFromLocationName(editText.getText().toString(), 1);
                                                        double mLat = address.get(0).getLatitude();
                                                        double mLng = address.get(0).getLongitude();
                                                        String lat = Double.toString(mLat);
                                                        String lng = Double.toString(mLng);
                                                        Log.v("Message", "위도랑 경도!!" + mLat);
                                                        Log.v("Message", "위도랑 경도!!" + mLng);
                                                        urlAddr = urlAddr + "imageInsert.jsp?filepath=" + filepath + "&title=" + title + "&detail=" + detail + "&fileformat=" + fileformat
                                                                + "&category=" + category + "&tag=" + tag + "&price=" + price + "&location=" + editText.getText().toString()
                                                                + "&latitude=" + lat + "&longitude=" + lng;
                                                        Log.v("Message", urlAddr);
                                                        String result = connectInsertData();
                                                        if (result.equals("1")){
                                                            try {
                                                                urlAddr3 = ShareVar.macIP + "jsp/imageSelect.jsp";
                                                                Log.v("Message", urlAddr3);
                                                                NetworkTaskImageHJ networkTask = new NetworkTaskImageHJ(ImageAddLocationActivity.this, urlAddr3, "insertSelect");
                                                                Object obj = networkTask.execute().get();
                                                                images = (ArrayList<ImageHJ>) obj;
                                                                image_code = images.get(0).getCode();
                                                                Log.v("Message", "code : " + Integer.toString(image_code));
                                                                urlAddr2 = ShareVar.macIP + "jsp/imageInsert3.jsp?";
                                                                urlAddr2 = urlAddr2 + "user_email=" + ShareVar.loginEmail + "&image_code=" + image_code;
                                                                Log.v("Message", urlAddr2);
                                                                String result2 = connectInsertData2();
                                                                Log.v("Message", "제발!!!" + result2);
                                                                if (result2.equals("1")){
                                                                    Log.v("Message", "한 번 더!!!" + result2);
                                                                    new AlertDialog.Builder(ImageAddLocationActivity.this)
                                                                            .setMessage("이미지 등록이 완료되었습니다!")
                                                                            .setCancelable(false)
                                                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                                @Override
                                                                                public void onClick(DialogInterface dialog, int which) {
                                                                                    Intent intent = new Intent(ImageAddLocationActivity.this, ImageEditDeleteActivity.class);
                                                                                    intent.putExtra("code", image_code);
                                                                                    startActivity(intent);
                                                                                }
                                                                            })
                                                                            .show();
                                                                }else {
                                                                    Toast.makeText(ImageAddLocationActivity.this, "입력이 실패 되었습니다", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }catch (Exception e){

                                                            }
                                                        }else {
                                                            Toast.makeText(ImageAddLocationActivity.this, "입력이 실패 되었습니다", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                        Toast.makeText(ImageAddLocationActivity.this, "올바르지 않은 주소 정보입니다", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        })
                                        .show();
                                break;
                            case 0:
                                Toast.makeText(ImageAddLocationActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.add_location_ivbtn_back:
                    finish();
                    break;
            }

        }
    };

    private String connectInsertData(){
        String result = null;
        try {
            // NetworkTask 로 넘겨줌
            NetworkTaskImageHJ networkTask = new NetworkTaskImageHJ(ImageAddLocationActivity.this, urlAddr, "insert");
            Object obj = networkTask.execute().get();
            // 1이 들어오면 성공한 것, 만약 그 이외의 숫자면 실패한 것
            result = (String) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private String connectInsertData2(){
        String result2 = null;
        try {
            // NetworkTask 로 넘겨줌
            NetworkTaskImageHJ networkTask = new NetworkTaskImageHJ(ImageAddLocationActivity.this, urlAddr2, "insert");
            Object obj = networkTask.execute().get();
            // 1이 들어오면 성공한 것, 만약 그 이외의 숫자면 실패한 것
            result2 = (String) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.v("Message", "result2 : " + result2);
        return result2;
    }
}