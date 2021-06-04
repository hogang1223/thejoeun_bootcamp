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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn01, btn02, btn03, btn04, btn05;
    LinearLayout linear01, linear02, linear03, linear04, linear05, linear06, linear07;
    ProgressBar progressBar;
    TextView text1, textName, textHeight, textWeight, textBmi, textTitle, textScore, textStatus;
    EditText edit1, edit2, edit3;
    String name = "", height="", weight="";

    private ArrayList<Sport> data = null;
    private SportAdapter adapter = null;
    private ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // title
        setTitle("건강 지킴이");

        // LinearLayoutin
        linear01 = findViewById(R.id.linearMain);
        linear02 = findViewById(R.id.linearInsert);
        linear03 = findViewById(R.id.linearName);
        linear04 = findViewById(R.id.linearHeight);
        linear05 = findViewById(R.id.linearWeight);
        linear06 = findViewById(R.id.linearResult);
        linear07 = findViewById(R.id.linearSport);

        // text
        text1 = findViewById(R.id.text_c1);
        textName = findViewById(R.id.textName);
        textHeight = findViewById(R.id.textHeight);
        textWeight = findViewById(R.id.textWeight);
        textBmi = findViewById(R.id.textBmi);
        textTitle = findViewById(R.id.textTitle);
        textScore = findViewById(R.id.textScore);
        textStatus = findViewById(R.id.textStatus);

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
        btn05 = findViewById(R.id.btnSport);


        btn01.setOnClickListener(changeLayout);
        btn02.setOnClickListener(changeLayout);
        btn03.setOnClickListener(changeLayout);
        btn04.setOnClickListener(changeLayout);
        btn05.setOnClickListener(changeLayout);


        // list view Data 만들기
        data = new ArrayList<Sport>();
        data.add(new Sport(R.drawable.s11, "달리기","240 cal"));
        data.add(new Sport(R.drawable.s13, "걷기","150 cal"));
        data.add(new Sport(R.drawable.s9, "스트레칭","210 cal"));
        data.add(new Sport(R.drawable.s10, "명상","90 cal"));
        data.add(new Sport(R.drawable.s3, "줄넘기","315 cal"));
        data.add(new Sport(R.drawable.s4, "자전거","330 cal"));
        data.add(new Sport(R.drawable.s5, "수영","360 cal"));
        data.add(new Sport(R.drawable.s6, "등산","210 cal"));
        data.add(new Sport(R.drawable.s12, "배드민턴","173 cal"));
        data.add(new Sport(R.drawable.s1, "태권도","173 cal"));
        data.add(new Sport(R.drawable.s2, "축구","270 cal"));
        data.add(new Sport(R.drawable.s7, "스케이트","240 cal"));
        data.add(new Sport(R.drawable.s8, "골프","135 cal"));

        // Adapter 연결
        adapter = new SportAdapter(MainActivity.this, R.layout.custom_layout, data);

        // listView
        listView = findViewById(R.id.listSport);
        listView.setAdapter(adapter);


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

                        double dBmi = dWeight / (dHeight*dHeight);

                        // bmi 지수에 따른 체중상태와 건강점수
                        if(dBmi < 18.5){
                            textScore.setText("70");
                            textStatus.setText("저체중");
                        }
                        if(dBmi >= 18.5 && dBmi < 23.0){
                            textScore.setText("90");
                            textStatus.setText("정상체중");
                        }
                        if(dBmi >= 23.0 && dBmi < 25.0){
                            textScore.setText("80");
                            textStatus.setText("과체중");
                        }
                        if(dBmi >= 25.0 && dBmi < 30.0){
                            textScore.setText("70");
                            textStatus.setText("경도비만");
                        }
                        if(dBmi >= 30.0 && dBmi < 35.0){
                            textScore.setText("60");
                            textStatus.setText("중정도비만");
                        }
                        if(dBmi >= 35.0){
                            textScore.setText("50");
                            textStatus.setText("고도비만");
                        }

                        textTitle.setText(name + " 님의 건강 점수는");
                        textName.setText(name);
                        textHeight.setText(height + " cm");
                        textWeight.setText(weight + " kg");
                        textBmi.setText(String.format("%.2f", dBmi));

                        linear06.setVisibility(View.VISIBLE);

                        // 키보드 숨기기
                        InputMethodManager immhide = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                    }
                    break;
                case R.id.btnSport:
                    linear06.setVisibility(View.INVISIBLE);
                    linear07.setVisibility(View.VISIBLE);

                    break;
            }
        }
    };
} // mainActivity