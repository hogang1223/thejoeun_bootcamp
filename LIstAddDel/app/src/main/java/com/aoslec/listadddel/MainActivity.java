package com.aoslec.listadddel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items;
    ArrayAdapter<String> adapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        items = new ArrayList<String>();
        items.add("First");
        items.add("Second");
        items.add("Third");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, items); // single choice layout 선택
        list = findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE); // 한가지 선택

        list.setOnItemClickListener(mItemClickListener); // item click

        findViewById(R.id.add).setOnClickListener(mClickListener);
        findViewById(R.id.del).setOnClickListener(mClickListener);


    } // onCreate

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText editText = findViewById(R.id.newItem);
            switch (v.getId()){
                case R.id.add :
                    String text = editText.getText().toString();
                    if(text.length() != 0){
                        items.add(text);
                        editText.setText("");
                        adapter.notifyDataSetChanged();
                    }
                    break;
                case R.id.del:
                    int id = list.getCheckedItemPosition();
                    if(id != ListView.INVALID_POSITION){
                        items.remove(id);
                        list.clearChoices();
                        adapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };

    AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String message  = "Select item = " + items.get(position);
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

} // MainActivity