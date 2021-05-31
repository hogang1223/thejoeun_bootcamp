package com.aoslec.quiz_calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText textNum1;
    EditText textNum2;
    Button add, sub, mul, div;
    TextView viewResult;
    String result = "";
    int num1 = 0, num2 = 0;

    //10개 숫자버튼 배열
    Button[] numButtons = new Button[10];
    //10개 숫자 버튼의 id값 배열

    Integer[] numBtnsIDs = {R.id.btnNum0, R.id.btnNum1,R.id.btnNum2,R.id.btnNum3,
                            R.id.btnNum4,R.id.btnNum5,R.id.btnNum6,
                            R.id.btnNum7,R.id.btnNum8,R.id.btnNum9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("계산기");


        textNum1 = (EditText)findViewById(R.id.textNum1);
        textNum2 = (EditText)findViewById(R.id.textNum2);
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        mul = findViewById(R.id.mul);
        div = findViewById(R.id.div);
        viewResult = (TextView)findViewById(R.id.viewResult);

        add.setOnClickListener(onClickListener);
        sub.setOnClickListener(onClickListener);
        mul.setOnClickListener(onClickListener);
        div.setOnClickListener(onClickListener);

        // 숫자 버튼 10개를 대입
        for(int i=0; i< numBtnsIDs.length; i++){
            numButtons[i] = findViewById(numBtnsIDs[i]);
        }

        // 숫자 버튼 10개에 대한 클릭 이벤트 처리
        for(int i=0; i< numBtnsIDs.length; i++){
            final int index = i;

            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 포커스가 되어있는 에디트 텍스트에 숫자 추가
                    if(textNum1.isFocused()==true){
                        num1 = Integer.parseInt(textNum1.getText().toString() + numButtons[index].getText().toString());
                        textNum1.setText(Integer.toString(num1));
                    }else if(textNum2.isFocused()==true){
                        num2 = Integer.parseInt(textNum2.getText().toString() + numButtons[index].getText().toString());
                        textNum2.setText(Integer.toString(num2));
                    }else{
                        Toast.makeText(MainActivity.this, "입력 항목을 선택하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (textNum1.length() == 0 || textNum2.length() == 0) {
                Toast.makeText(MainActivity.this, "값을 입력해주세요", Toast.LENGTH_SHORT).show();
            } else {
                num1 = Integer.parseInt(textNum1.getText().toString());
                num2 = Integer.parseInt(textNum2.getText().toString());
                switch (v.getId()) {
                    case R.id.add:
                        result = Integer.toString(num1 + num2);
                        break;
                    case R.id.sub:
                        result =Integer.toString( num1 - num2);
                        break;
                    case R.id.mul:
                        result = Integer.toString(num1 * num2);
                        break;
                    case R.id.div:
                        result = Double.toString((double)num1 / num2);
                        break;
                }
                viewResult.setText("계산결과 : " + result);
            }
        }
    };
}