package com.aoslec.circlechart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<WritingVO> writing = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        writing = new ArrayList<WritingVO>();
        Canvas canvas = new Canvas();
        WritingVO wVO1 = new WritingVO((float)100, (float)100);
        WritingVO wVO2 = new WritingVO((float)1, (float)10);

        writing.add(wVO1);
        writing.add(wVO2);

        CircleChart cc = new CircleChart(this, writing, 100, 500);
        setContentView(cc);

    } // onCreate
} // MainActivity

