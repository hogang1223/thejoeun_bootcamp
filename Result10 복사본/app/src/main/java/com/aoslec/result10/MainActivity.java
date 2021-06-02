package com.aoslec.result10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView text1, text2;
    CheckBox chkAgree;
    RadioGroup rGroup1;
    RadioButton rdoDog, rdoCat, rdoRabbit;
    Button btnOK;
    ImageView imgPet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("좋아하는 동물 선택하기");

        // widget을 변수에 대입
        text1 = findViewById(R.id.Text1);
        chkAgree = findViewById(R.id.ChkAgree);

        text2 = findViewById(R.id.Text2);
        rGroup1 = findViewById(R.id.Rgroup1);
        rdoDog = findViewById(R.id.RdoDog);
        rdoCat = findViewById(R.id.RdoCat);
        rdoRabbit = findViewById(R.id.RdoRabbit);

        btnOK = findViewById(R.id.BtnOK);
        imgPet = findViewById(R.id.ImgPet);

        // 시작함 체크박스의 체크가 변경되면 ....
        chkAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 체크되면 모두 보이기
                if(chkAgree.isChecked() == true){
                    text2.setVisibility(View.VISIBLE);
                    rGroup1.setVisibility(View.VISIBLE);
                    btnOK.setVisibility(View.VISIBLE);
                    imgPet.setVisibility(View.VISIBLE);
                }else{
                    text2.setVisibility(View.INVISIBLE);
                    rGroup1.setVisibility(View.INVISIBLE);
                    btnOK.setVisibility(View.INVISIBLE);
                    imgPet.setVisibility(View.INVISIBLE);
                }
            }
        });

        // 버튼을 클릭하면 ......
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rGroup1.getCheckedRadioButtonId()){
                    case R.id.RdoDog:
                        imgPet.setImageResource(R.drawable.siba);
                        break;
                    case R.id.RdoCat:
                        imgPet.setImageResource(R.drawable.cat);
                        break;
                    case R.id.RdoRabbit:
                        imgPet.setImageResource(R.drawable.duck);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "동물을 선택하세요", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });



    } // onCreate

} // MainActivity