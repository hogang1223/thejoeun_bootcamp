package com.aoslec.fragmenttest;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class ToolBarFragment extends Fragment {

    EditText editText = null;
    Button button = null;
    SeekBar seekBar = null;
    TextView textView1 = null;
    int seekValue = 10;

    ToolbarListener activityCallback;

    // -----------------------------------------
    // MainActivity와의 통신을 위해 interface를 사용한다
    // MainActivity에서는 implements로 사용
    // -----------------------------------------

    public interface ToolbarListener{
        public void onButtonClick(int position, String text);
    }
    // ---------
    // Fragment에서는 onAttach()가 제일 처음으로 실행
    // ---------
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try{
            Log.v("message", "toolbar_Attach_Start");
            activityCallback = (ToolbarListener) context;
            Log.v("message", "toolbar_Attach_End");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.v("message", "toolbar_onCreateView_Start");
        View view = inflater.inflate(R.layout.fragment_toolbar, container, false);
        editText = view.findViewById(R.id.f1_edit);
        button = view.findViewById(R.id.f1_button);
        seekBar = view.findViewById(R.id.f1_seek);
        textView1 = view.findViewById(R.id.f1_text);

        button.setOnClickListener(mClickListener);
        seekBar.setOnSeekBarChangeListener(mSeekBarChangedListener);

        Log.v("message", "toolbar_onCreateView_End");

        return view;
    }

    //-------
    // MainActivity의 onButtonClick()에서 실행
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.v("message", "toolbar_onClick");
            activityCallback.onButtonClick(seekValue, editText.getText().toString());
        }
    };


    SeekBar.OnSeekBarChangeListener mSeekBarChangedListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Log.v("message", "toolbar_onProgressChanged_Start");
            seekValue = progress;
            textView1.setText("font size : " + progress);
            Log.v("message", "toolbar_onProgressChanged_Progress value = " + progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}