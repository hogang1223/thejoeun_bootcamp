package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.aosproject.imagemarket.R;

public class ImageAddCategoryActivity extends Activity {

    ArrayAdapter<CharSequence> adapter = null;
    Spinner spinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_add_category);

        adapter = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_dropdown_item);

        spinner = findViewById(R.id.add_spinner_category);
        spinner.setAdapter(adapter);

    }
}