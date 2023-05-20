package com.aoslec.listener02;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = findViewById(R.id.text);
        Button btn = findViewById(R.id.btn);
        ImageView img = findViewById(R.id.img);

        Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();


        text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(MainActivity.this, "text", Toast.LENGTH_SHORT).show();
                return false;
            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "button", Snackbar.LENGTH_SHORT).show();
            }
        });

        img.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                View dialogView = View.inflate(MainActivity.this, R.layout.dialog, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                ImageView dialogImg = dialogView.findViewById(R.id.dialogImg);

                dialogImg.setImageBitmap(bitmap);

                dlg.setView(dialogView);
                dlg.setNegativeButton("닫기", null);
                dlg.show();

                return false;
            }
        });


    }
}