package com.aoslec.radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.rg_01);
        radioGroup.setOnCheckedChangeListener(checkedChangeListener);

    } // onCreate

    RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            String str = "";
            switch (checkedId){
                case R.id.rb_01:
                    str += getString(R.string.area01);
                    break;
                case R.id.rb_02:
                    str += getString(R.string.area02);
                    break;
                case R.id.rb_03:
                    str += getString(R.string.area03);
                    break;
                case R.id.rb_04:
                    str += getString(R.string.area04);
                    break;
                case R.id.rb_05:
                    str += getString(R.string.area05);
                    break;
                case R.id.rb_06:
                    str += getString(R.string.area06);
                    break;
                case R.id.rb_07:
                    str += getString(R.string.area07);
                    break;
            }
            Toast.makeText(MainActivity.this, "당신의 고향은 " + str + " 입니다.", Toast.LENGTH_SHORT).show();
        }
    };


} // MainActivity