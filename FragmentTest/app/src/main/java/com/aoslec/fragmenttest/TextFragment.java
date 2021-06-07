package com.aoslec.fragmenttest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TextFragment extends Fragment {

    TextView textView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.v("message", "text_onCreateView_Start");
        View view = inflater.inflate(R.layout.fragment_text, container, false);

        // TextVIew 연결
        textView = view.findViewById(R.id.f2_text);
        Log.v("message", "text_onCreateView_End");

        return view;
    }

    public void changeTextProperties(int fontSize, String str){
        Log.v("message", "text_changeTextProperties");
        textView.setTextSize(fontSize);
        textView.setText(str);
    }

}