package com.aosproject.imagemarket.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.aosproject.imagemarket.NetworkTask.NetworkTaskImageAddHJ;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskImageHJ;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.ShareVar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageAddImageActivity extends Activity {

    String imageName = null;
    ImageView uploadImg, imageView = null;
    Button button = null;
    private final int REQ_CODE_SELECT_IMAGE = 300; // Gallery Return Code
    private String img_path = null; // 최종 file name
    private String f_ext = null;    // 최종 file extension
    File tempSelectFile;

    String devicePath = Environment.getDataDirectory().getAbsolutePath() + "/data/com.aosproject.imagemarket/";
    String urlAddr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_add_image);

        ActivityCompat.requestPermissions(ImageAddImageActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
        ActivityCompat.requestPermissions(ImageAddImageActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MODE_PRIVATE);

        uploadImg = findViewById(R.id.image_upload);
        button = findViewById(R.id.add_image_btn_next);
        imageView = findViewById(R.id.add_image_ivbtn_back);

        uploadImg.setOnClickListener(onClickListener);
        button.setOnClickListener(onClickListener);
        imageView.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.image_upload:
                    intent = new Intent(Intent.ACTION_PICK);
                    intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                    intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
                    break;
                case R.id.add_image_btn_next:
                    urlAddr = ShareVar.macIP + "jsp/multipartRequest.jsp";
                    Log.v("Message", urlAddr);
                    NetworkTaskImageAddHJ networkTask = new NetworkTaskImageAddHJ(ImageAddImageActivity.this, urlAddr, img_path, uploadImg);
                    try {
                        Integer result = (Integer) networkTask.execute(100).get();
                        switch (result) {
                            case 1:
                                //connectInsertData(imageName);
                                File file = new File(img_path);
                                file.delete();
                                intent = new Intent(ImageAddImageActivity.this, ImageAddNameActivity.class);
                                intent.putExtra("filepath", imageName);
                                startActivity(intent);
                                break;
                            case 0:
                                Toast.makeText(ImageAddImageActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                break;

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.add_image_ivbtn_back:
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.v("Message", "Data :" + String.valueOf(data));

        if (requestCode == REQ_CODE_SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
            try {
                //이미지의 URI를 얻어 경로값으로 반환.
                img_path = getImagePathToUri(data.getData());
                Log.v("Message", "image path :" + img_path);
                Log.v("Message", "Data :" +String.valueOf(data.getData()));

                //이미지를 비트맵형식으로 반환
                Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                int height = image_bitmap.getHeight();
                int width = image_bitmap.getWidth();

                Bitmap image_bitmap_copy = null;


                //image_bitmap 으로 받아온 이미지의 사이즈를 임의적으로 조절함. width: 400 , height: 300
//                Bitmap image_bitmap_copy = Bitmap.createScaledBitmap(image_bitmap, 400, 300, true);
                while (width > 400){
                    image_bitmap_copy = Bitmap.createScaledBitmap(image_bitmap, 400, (height*400)/width, true);
                    height = image_bitmap_copy.getHeight();
                    width = image_bitmap_copy.getWidth();
                }
                uploadImg.setImageBitmap(image_bitmap_copy);

                // 파일 이름 및 경로 바꾸기(임시 저장, 경로는 임의로 지정 가능)
                String date = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
                imageName = date + "." + f_ext;
                tempSelectFile = new File(devicePath , imageName);
                OutputStream out = new FileOutputStream(tempSelectFile);
                image_bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

                // 임시 파일 경로로 위의 img_path 재정의
                img_path = devicePath + imageName;
                Log.v("Message","fileName :" + img_path);
                Log.v("Message","imageName_activityResult :" + imageName);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String getImagePathToUri(Uri data) {

        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        //이미지의 경로 값
        String imgPath = cursor.getString(column_index);
        Log.v("Message", "Image Path :" + imgPath);

        //이미지의 이름 값
        String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);

        // 확장자 명 저장
        f_ext = imgPath.substring(imgPath.length()-3, imgPath.length());

        return imgPath;
    }
}