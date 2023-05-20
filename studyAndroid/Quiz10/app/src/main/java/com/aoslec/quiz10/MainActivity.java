package com.aoslec.quiz10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    CheckBox checkBox;
    LinearLayout linearLayout;
    RadioGroup radioGroup;
    Button button;
    ImageView dog, duck, cat;

    String rbCheck = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.pbar);
        checkBox = findViewById(R.id.cbStart);
        linearLayout = findViewById(R.id.linearAnimal);
        radioGroup = findViewById(R.id.rgAnimal);
        button = findViewById(R.id.btnAnimal);
        dog = findViewById(R.id.imgDog);
        duck = findViewById(R.id.imgDuck);
        cat = findViewById(R.id.imgCat);

        checkBox.setOnCheckedChangeListener(cbCheckChangeListener);
        radioGroup.setOnCheckedChangeListener(rgCheckChangeListener);
        button.setOnClickListener(btnClickListener);

    } // onCreate

    CompoundButton.OnCheckedChangeListener cbCheckChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(checkBox.isChecked() == true){
                linearLayout.setVisibility(View.VISIBLE);
                progressBar.incrementProgressBy(1);
            }else{
                linearLayout.setVisibility(View.INVISIBLE);
                progressBar.setProgress(0);
            }
        }
    };


    RadioGroup.OnCheckedChangeListener rgCheckChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            RadioButton radioButton = findViewById(checkedId);
            rbCheck = radioButton.getText().toString();
            progressBar.incrementProgressBy(1);

        }
    };

    View.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (rbCheck){
                case "강아지":
                    dog.setVisibility(View.VISIBLE);
                    duck.setVisibility(View.INVISIBLE);
                    cat.setVisibility(View.INVISIBLE);
//                    Toast.makeText(MainActivity.this, rbCheck, Toast.LENGTH_SHORT).show();
                    break;
                case "오리":
                    dog.setVisibility(View.INVISIBLE);
                    duck.setVisibility(View.VISIBLE);
                    cat.setVisibility(View.INVISIBLE);
                    break;
                case "고양이":
                    dog.setVisibility(View.INVISIBLE);
                    duck.setVisibility(View.INVISIBLE);
                    cat.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };



} // MainActivity