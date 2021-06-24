package com.aosproject.imagemarket.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aosproject.imagemarket.Bean.MyPageBean;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskBuyList;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskMyPage;
import com.aosproject.imagemarket.R;

import java.util.ArrayList;

import static com.aosproject.imagemarket.Util.ShareVar.loginEmail;
import static com.aosproject.imagemarket.Util.ShareVar.macIP;

public class MyPage extends Activity {

    LinearLayout profile_layout_mypage_account, profile_layout_mypage_pw_chk;
    ImageView profile_iv_mypage_back;
    TextView profile_tv_mypage_name, profile_tv_mypage_email, profile_tv_mypage_account;
    TextView profile_tv_mypage_pw_save, profile_tv_mypage_pw_msg, profile_tv_mypage_name_save, profile_tv_mypage_phone_save;
    EditText profile_et_mypage_pw, profile_et_mypage_pw_chk, profile_et_mypage_name, profile_et_mypage_phone;

    String pw, pwChk, name, phone;
    String urlAddr = null;
    ArrayList<MyPageBean> mypage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        Log.v("Chk", "MyPage_onCreate");

        profile_iv_mypage_back = findViewById(R.id.profile_iv_mypage_back);
        profile_tv_mypage_name = findViewById(R.id.profile_tv_mypage_name);
        profile_tv_mypage_email = findViewById(R.id.profile_tv_mypage_email);
        profile_et_mypage_pw = findViewById(R.id.profile_et_mypage_pw);
        profile_et_mypage_name = findViewById(R.id.profile_et_mypage_name);
        profile_et_mypage_phone = findViewById(R.id.profile_et_mypage_phone);
        profile_layout_mypage_account = findViewById(R.id.profile_layout_mypage_account);
        profile_tv_mypage_account = findViewById(R.id.profile_tv_mypage_account);

        profile_layout_mypage_pw_chk = findViewById(R.id.profile_layout_mypage_pw_chk);
        profile_et_mypage_pw_chk = findViewById(R.id.profile_et_mypage_pw_chk);
        profile_tv_mypage_pw_save = findViewById(R.id.profile_tv_mypage_pw_save);
        profile_tv_mypage_name_save = findViewById(R.id.profile_tv_mypage_name_save);
        profile_tv_mypage_phone_save = findViewById(R.id.profile_tv_mypage_phone_save);
        profile_tv_mypage_pw_msg = findViewById(R.id.profile_tv_mypage_pw_msg);

        Log.v("Chk", "MyPage_onCreate_id");

        profile_tv_mypage_pw_save.setVisibility(View.INVISIBLE);
        profile_tv_mypage_name_save.setVisibility(View.INVISIBLE);
        profile_tv_mypage_phone_save.setVisibility(View.INVISIBLE);
        profile_tv_mypage_pw_msg.setVisibility(View.INVISIBLE);

        profile_et_mypage_pw.setOnFocusChangeListener(onFocusListener);
        profile_et_mypage_name.setOnFocusChangeListener(onFocusListener);
        profile_et_mypage_phone.setOnFocusChangeListener(onFocusListener);

        profile_iv_mypage_back.setOnClickListener(onClickListener);
        profile_layout_mypage_account.setOnClickListener(onClickListener);

        profile_tv_mypage_name_save.setOnClickListener(onClickListener);
        profile_tv_mypage_phone_save.setOnClickListener(onClickListener);
        profile_tv_mypage_pw_save.setOnClickListener(onClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        connectGetData();
    }

    private void connectGetData() {
        try {
            urlAddr = macIP + "jsp/profile_mypage.jsp?loginEmail=" + loginEmail;
            NetworkTaskMyPage networkTask = new NetworkTaskMyPage(MyPage.this, urlAddr, "select");
            Object obj = networkTask.execute().get();
            mypage = (ArrayList<MyPageBean>) obj;

            profile_tv_mypage_name.setText(mypage.get(0).getMyname());
            profile_tv_mypage_email.setText(mypage.get(0).getEmail());
            profile_et_mypage_pw.setText(mypage.get(0).getPassword());
            profile_et_mypage_name.setText(mypage.get(0).getMyname());

            if(mypage.get(0).getPhone().equals("non")) {
                profile_et_mypage_phone.setText("전화번호 정보가 없습니다.");
            }else {
                profile_et_mypage_phone.setText(mypage.get(0).getPhone());
            }
            if(mypage.get(0).getAccount_name().equals("non")) {
                profile_tv_mypage_account.setText("계좌 정보가 없습니다.");
            }else {
                profile_tv_mypage_account.setText(mypage.get(0).getAccount_name() + " (" + mypage.get(0).getAccount_bank() + " " + mypage.get(0).getAccount_number() + ")");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            String result = null;

            switch (v.getId()) {
                case R.id.profile_iv_mypage_back:
                    finish();
                    break;
                case R.id.profile_layout_mypage_account:
                    intent = new Intent(MyPage.this, MyPage_Account.class);
                    intent.putExtra("name", mypage.get(0).getAccount_name());
                    intent.putExtra("bank", mypage.get(0).getAccount_bank());
                    intent.putExtra("number", mypage.get(0).getAccount_number());
                    startActivity(intent);
                    break;
                case R.id.profile_tv_mypage_pw_save:

                    pw = profile_et_mypage_pw.getText().toString();
                    pwChk = profile_et_mypage_pw_chk.getText().toString();

                    if(pw.length() == 0) {
                        Toast.makeText(MyPage.this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }else if(pwChk.length() == 0) {
                        Toast.makeText(MyPage.this, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    }else {
                        if(pw.equals(pwChk)) {
                            urlAddr = macIP + "jsp/profile_mypage_pw.jsp?loginEmail=" + loginEmail + "&pw=" + pwChk;

                            profile_tv_mypage_pw_msg.setVisibility(View.INVISIBLE);

                            result = connectUpdateData();
                            if(result.equals("1")) {
                                onResume();
                                Toast.makeText(MyPage.this, "비밀번호가 수정되었습니다.", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(MyPage.this, "비밀전호 수정 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            profile_tv_mypage_pw_msg.setVisibility(View.VISIBLE);
                        }
                    }

                    break;
                case R.id.profile_tv_mypage_name_save:

                    name = profile_et_mypage_name.getText().toString();

                    urlAddr = macIP + "jsp/profile_mypage_name.jsp?loginEmail=" + loginEmail + "&name=" + name;

                    if(name.length() == 0) {
                        Toast.makeText(MyPage.this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }else {
                        result = connectUpdateData();
                        if(result.equals("1")) {
                            onResume();
                            Toast.makeText(MyPage.this, "이름이 수정되었습니다.", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MyPage.this, "이름 수정 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case R.id.profile_tv_mypage_phone_save:

                    phone = profile_et_mypage_phone.getText().toString();

                    if(phone.length() == 0) {
                        urlAddr = macIP + "jsp/profile_mypage_phone.jsp?loginEmail=" + loginEmail + "&phone=non";
                    }else if(phone.length() == 11) {
                        urlAddr = macIP + "profile_mypage_phone.jsp?loginEmail=" + loginEmail + "&phone=" + phone;
                    }else {
                        Toast.makeText(MyPage.this, "전화번호를 모두 입력해주세요..", Toast.LENGTH_SHORT).show();
                    }

                    result = connectUpdateData();
                    if(result.equals("1")) {
                        onResume();
                        Toast.makeText(MyPage.this, "전화번호가 수정되었습니다.", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MyPage.this, "전화번호 수정 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    View.OnFocusChangeListener onFocusListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            switch(v.getId()) {
                case R.id.profile_et_mypage_pw:
                    profile_tv_mypage_pw_save.setVisibility(View.VISIBLE);

                    ViewGroup.LayoutParams params = profile_layout_mypage_pw_chk.getLayoutParams();
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    profile_layout_mypage_pw_chk.setLayoutParams(params);

                    break;
                case R.id.profile_et_mypage_name:
                    profile_tv_mypage_name_save.setVisibility(View.VISIBLE);
                    break;
                case R.id.profile_et_mypage_phone:
                    profile_tv_mypage_phone_save.setVisibility(View.VISIBLE);
                    break;
            }

        }
    };

    private String connectUpdateData() {


        String result = null;

        try {
            NetworkTaskMyPage networkTask = new NetworkTaskMyPage(MyPage.this, urlAddr, "update_info");
            Object obj = networkTask.execute().get();
            result = (String) obj;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}