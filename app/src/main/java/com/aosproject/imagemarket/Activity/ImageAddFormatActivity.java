package com.aosproject.imagemarket.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aosproject.imagemarket.R;

public class ImageAddFormatActivity extends Activity {

    ArrayAdapter<CharSequence> adapter = null;
    Spinner spinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_add_format);

        adapter = ArrayAdapter.createFromResource(this, R.array.format, android.R.layout.simple_spinner_dropdown_item);

//        adapter = new ArrayAdapter<CharSequence>(this, R.layout.activity_image_add_format, R.array.format) {
//
//            @Override
//            public boolean isEnabled(int position) {
//                if (position == 0) {
//                    return false;
//                } else {
//                    return true;
//                }
//            }
//
//            @Override
//            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                View view = super.getDropDownView(position, convertView, parent);
//                TextView tv = (TextView) view;
//                if (position == 0) {
//                    tv.setTextColor(Color.GRAY);
//                } else {
//                    tv.setTextColor(Color.BLACK);
//                }
//                return view;
//            }
//        };

        //adapter.setDropDownViewResource(R.layout.activity_image_add_format);
        spinner = findViewById(R.id.add_spinner_format);
        spinner.setAdapter(adapter);

//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItemText = (String) parent.getItemAtPosition(position);
//                if(position > 0){
//                    Toast.makeText(getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            });
//
//    }

    }
}