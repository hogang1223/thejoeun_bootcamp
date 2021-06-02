package com.aoslec.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox cb1, cb2, cb3, cb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb1 = findViewById(R.id.cb_01);
        cb2 = findViewById(R.id.cb_02);
        cb3 = findViewById(R.id.cb_03);
        cb4 = findViewById(R.id.cb_04);

        cb1.setOnCheckedChangeListener(checkChageListener);
        cb2.setOnCheckedChangeListener(checkChageListener);
        cb3.setOnCheckedChangeListener(checkChageListener);
        cb4.setOnCheckedChangeListener(checkChageListener);

    } // onCreate

    CompoundButton.OnCheckedChangeListener checkChageListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            String [] str = new String[4];
//            String[] str = {"운동", "요리", "독서", "여행"};
            String ss = "";


//
//            if(cb1.isChecked() == false ){
//                str[0] = " ";
//            }
//            if(cb2.isChecked() == false){
//                str[1] = " ";
//            }
//            if(cb3.isChecked()== false){
//                str[2] = " ";
//            }
//            if(cb4.isChecked()== false){
//                str[3] = " ";
//            }
//            switch(buttonView.getId()){
//                case R.id.cb_01:
//                    // check를 풀려고 클릭했는지, 체크하려 클릭했는지 구분
//                    if(isChecked == true){
//                        str[0] = "운동";
//                    }else{
//                        str[0] = "";
//                    }
//                    break;
//                case R.id.cb_02:
//                    if(isChecked == true){
//                        str[1] += "요리";
//                    }
//                    break;
//                case R.id.cb_03:
//                    if(isChecked == true){
//                        str[2] += "독서";
//                    }
//                    break;
//                case R.id.cb_04:
//                    if(isChecked == true){
//                        str[3] += "여행";
//                    }
//                    break;
//            }
            for(int i = 0; i<str.length; i++){
                ss += str[i];
            }

            Toast.makeText(MainActivity.this, ss+"is checked.", Toast.LENGTH_SHORT).show();
        }
    };

} // MainActivity