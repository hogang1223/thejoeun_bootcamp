package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aosproject.imagemarket.Fragment.ProfileFragment;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskProfileMain;
import com.aosproject.imagemarket.R;

import static com.aosproject.imagemarket.Util.ShareVar.loginEmail;
import static com.aosproject.imagemarket.Util.ShareVar.macIP;

public class MyPage extends Activity {

    LinearLayout profile_layout_mypage_account;
    ImageView profile_iv_mypage_back;
    TextView profile_tv_mypage_name, profile_tv_mypage_email, profile_tv_mypage_pw_chk, profile_tv_mypage_account;
    TextView profile_tv_mypage_pw_save, profile_tv_mypage_pw_msg, profile_tv_mypage_name_save, profile_tv_mypage_phone_save;
    EditText profile_et_mypage_pw, profile_et_mypage_pw_chk, profile_et_mypage_name, profile_et_mypage_phone;

    String pw, name, phone;
    String urlAddr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        Log.v("Chk", "MyPage_onCreate");

        profile_iv_mypage_back.findViewById(R.id.profile_iv_mypage_back);
        profile_tv_mypage_name.findViewById(R.id.profile_tv_mypage_name);
        profile_tv_mypage_email.findViewById(R.id.profile_tv_mypage_email);
        profile_et_mypage_pw.findViewById(R.id.profile_et_mypage_pw);
        profile_et_mypage_name.findViewById(R.id.profile_et_mypage_name);
        profile_et_mypage_phone.findViewById(R.id.profile_et_mypage_phone);
        profile_layout_mypage_account.findViewById(R.id.profile_layout_mypage_account);
        profile_tv_mypage_account.findViewById(R.id.profile_tv_mypage_account);

        profile_tv_mypage_pw_chk.findViewById(R.id.profile_tv_mypage_pw_chk);
        profile_et_mypage_pw_chk.findViewById(R.id.profile_et_mypage_pw_chk);
        profile_tv_mypage_pw_save.findViewById(R.id.profile_tv_mypage_pw_save);
        profile_tv_mypage_name_save.findViewById(R.id.profile_tv_mypage_name_save);
        profile_tv_mypage_phone_save.findViewById(R.id.profile_tv_mypage_phone_save);
        profile_tv_mypage_pw_msg.findViewById(R.id.profile_tv_mypage_pw_msg);

        Log.v("Chk", "MyPage_onCreate_id");

        pw = profile_et_mypage_pw_chk.getText().toString();
        name = profile_et_mypage_name.getText().toString();
        phone = profile_et_mypage_phone.getText().toString();

        Log.v("Chk", "MyPage_getText");

        profile_et_mypage_pw.setOnTouchListener(onTouchListener);
        profile_et_mypage_name.setOnTouchListener(onTouchListener);
        profile_et_mypage_phone.setOnTouchListener(onTouchListener);

        profile_iv_mypage_back.setOnClickListener(onClickListener);
        profile_layout_mypage_account.setOnClickListener(onClickListener);

        profile_tv_mypage_pw_save.setOnClickListener(onClickListener);
        profile_tv_mypage_name_save.setOnClickListener(onClickListener);
        profile_tv_mypage_phone_save.setOnClickListener(onClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        connectGetData();
    }

    private void connectGetData() {
        try {
//            urlAddr = macIP + "profile_main_name.jsp?loginEmail=" + loginEmail;
//            NetworkTaskProfileMain networkTaskName = new NetworkTaskProfileMain(MyPage.this, urlAddr, "profile_main");
//            Object objName = networkTaskName.execute().get();
//            name = (String) objName;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.profile_iv_mypage_back:
                    finish();
                    break;
                case R.id.profile_layout_mypage_account:
                    intent = new Intent(MyPage.this, MyPage_Account.class);
                    break;
                case R.id.profile_tv_mypage_pw_save:
                    break;
                case R.id.profile_tv_mypage_name_save:
                    break;
                case R.id.profile_tv_mypage_phone_save:
                    break;
            }
        }
    };

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch(v.getId()) {
                case R.id.profile_et_mypage_pw:
                    profile_et_mypage_pw.setVisibility(View.VISIBLE);
                    break;
                case R.id.profile_et_mypage_name:
                    profile_et_mypage_name.setVisibility(View.VISIBLE);
                    break;
                case R.id.profile_et_mypage_phone:
                    profile_et_mypage_phone.setVisibility(View.VISIBLE);
                    break;
            }
            return false;
        }
    };
}