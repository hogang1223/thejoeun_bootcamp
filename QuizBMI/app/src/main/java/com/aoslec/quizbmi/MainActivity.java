package com.aoslec.quizbmi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn01, btn02, btn03, btn04;
    LinearLayout linear01, linear02, linear03, linear04, linear05, linear06;
    ProgressBar progressBar;
    TextView text1, textName, textHeight, textWeight, textBmi, textTitle, textScore;
    EditText edit1, edit2, edit3;
    String name = "", height="", weight="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // title
        setTitle("건강 지킴이");

        // LinearLayout
        linear01 = findViewById(R.id.linearMain);
        linear02 = findViewById(R.id.linearInsert);
        linear03 = findViewById(R.id.linearName);
        linear04 = findViewById(R.id.linearHeight);
        linear05 = findViewById(R.id.linearWeight);
        linear06 = findViewById(R.id.linearResult);

        // text
        text1 = findViewById(R.id.text_c1);
        textName = findViewById(R.id.textName);
        textHeight = findViewById(R.id.textHeight);
        textWeight = findViewById(R.id.textWeight);
        textBmi = findViewById(R.id.textBmi);
        textTitle = findViewById(R.id.textTitle);
        textScore = findViewById(R.id.textScore);

        // main text Content - 건강 지킴이 색상 초록색 설정
        String content = text1.getText().toString();
        SpannableString spannableString = new SpannableString(content);
        String word = "건강 지킴이";
        int start = content.indexOf(word);
        int end = start + word.length();

        // 색상바꾸기
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#4CAF50")),start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // BOLD STYLE
        spannableString.setSpan(new StyleSpan(Typeface.BOLD),start,end,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // font size (1.1배)
        spannableString.setSpan(new RelativeSizeSpan(1.1f),start,end,SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

        text1.setText(spannableString);

        // editText
        edit1 = findViewById(R.id.editName);
        edit2 = findViewById(R.id.editHeight);
        edit3 = findViewById(R.id.editWeight);

        // progressBar
        progressBar = findViewById(R.id.pbInsert);

        // button
        btn01 = findViewById(R.id.btnMain);
        btn02 = findViewById(R.id.btnName);
        btn03 = findViewById(R.id.btnHeight);
        btn04 = findViewById(R.id.btnWeight);

        btn01.setOnClickListener(changeLayout);
        btn02.setOnClickListener(changeLayout);
        btn03.setOnClickListener(changeLayout);
        btn04.setOnClickListener(changeLayout);



    } // onCreate

    View.OnClickListener changeLayout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnMain:
                    linear01.setVisibility(View.INVISIBLE);
                    linear02.setVisibility(View.VISIBLE);
                    linear03.setVisibility(View.VISIBLE);
                    progressBar.incrementProgressBy(1);
                    edit1.requestFocus();
                    break;
                case R.id.btnName:
                    if(edit1.getText().length() == 0){
                        Toast.makeText(MainActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }else {
                        linear03.setVisibility(View.INVISIBLE);
                        linear04.setVisibility(View.VISIBLE);
                        progressBar.incrementProgressBy(1);
                        name = edit1.getText().toString();
                        edit2.requestFocus();
                    }
                    break;
                case R.id.btnHeight:
                    if(edit2.getText().length() == 0){
                        Toast.makeText(MainActivity.this, "키를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }else {
                        linear04.setVisibility(View.INVISIBLE);
                        linear05.setVisibility(View.VISIBLE);
                        progressBar.incrementProgressBy(1);
                        height = edit2.getText().toString();
                        edit3.requestFocus();
                    }
                    break;
                case R.id.btnWeight:
                    if(edit3.getText().length() == 0){
                        Toast.makeText(MainActivity.this, "몸무게를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }else {
                        linear05.setVisibility(View.INVISIBLE);
                        linear02.setVisibility(View.INVISIBLE);
                        weight = edit3.getText().toString();

                        // bmi 지수 계산하기
                        double dHeight = Double.parseDouble(height)/100;
                        double dWeight = Double.parseDouble(weight);

                        double dBmi = dWeight / dHeight; // 계산식 잘못된듯

                        // 키보드 숨기기
                        InputMethodManager immhide = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                        textTitle.setText(name + " 님의 건강 점수는");
                        textName.setText(name);
                        textHeight.setText(height + " cm");
                        textWeight.setText(weight + " kg");
                        textBmi.setText(Double.toString(dBmi));

                        linear06.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        }
    };
} // mainActivity