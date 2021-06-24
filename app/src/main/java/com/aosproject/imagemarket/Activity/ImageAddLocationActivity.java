package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.aosproject.imagemarket.Bean.ImageHJ;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskImageHJ;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.ShareVar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class ImageAddLocationActivity extends Activity {

    String filepath, title, detail, fileformat, tag = null;
    int category, price, image_code = 0;
    Button button;
    ImageView imageView;
    TextInputLayout layout;
    TextInputEditText editText;
    String urlAddr, urlAddr2, urlAddr3 = null;
    ArrayList<ImageHJ> images = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_add_location);

        Intent intent = getIntent();
        filepath = intent.getStringExtra("filepath");
        title = intent.getStringExtra("title");
        detail = intent.getStringExtra("detail");
        fileformat = intent.getStringExtra("fileformat");
        category = intent.getIntExtra("category", 0);
        tag = intent.getStringExtra("tag");
        price = intent.getIntExtra("price", 0);

        Log.v("Message", "파일 형식 확인!!!" + fileformat);

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
                                            Toast.makeText(ImageAddLocationActivity.this, "이미지가 추가되었습니다", Toast.LENGTH_SHORT).show();
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
                                        urlAddr = urlAddr + "imageInsert.jsp?filepath=" + filepath + "&title=" + title + "&detail=" + detail + "&fileformat=" + fileformat
                                                + "&category=" + category + "&tag=" + tag + "&price=" + price + "&location=" + editText.getText().toString();

                                        String result = connectInsertData();
                                        //String result3 = connectInsertData3();

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
                                    }
                                }
                            })
                            .show();
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