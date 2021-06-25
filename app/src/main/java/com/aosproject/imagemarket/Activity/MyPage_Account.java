package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aosproject.imagemarket.NetworkTask.NetworkTaskBuyList;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskMyPage;
import com.aosproject.imagemarket.R;

import static com.aosproject.imagemarket.Util.ShareVar.loginEmail;
import static com.aosproject.imagemarket.Util.ShareVar.macIP;

public class MyPage_Account extends AppCompatActivity {

    String name, bank, number;
    String new_name, new_bank, new_number;
    String urlAddr;

    ImageView back;
    LinearLayout layout_bank;
    TextView tv_bank;
    EditText et_name, et_number;
    Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_account);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        bank = intent.getStringExtra("bank");
        number = intent.getStringExtra("number");

        back = findViewById(R.id.profile_iv_mypage_account_back);
        layout_bank = findViewById(R.id.profile_layout_mypage_account_bank);
        tv_bank = findViewById(R.id.profile_tv_mypage_account_bank);
        et_name = findViewById(R.id.profile_et_mypage_account_name);
        et_number = findViewById(R.id.profile_et_mypage_account_number);
        btn_update = findViewById(R.id.profile_btn_mypage_account_update);

        if (bank.equals("non")) {
            tv_bank.setText("은행을 선택하세요.");
        }else {
            tv_bank.setText(bank);
            et_name.setText(name);
            et_number.setText(number);
        }

        back.setOnClickListener(onClickListener);
        layout_bank.setOnClickListener(onClickListener);
        btn_update.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch(v.getId()) {
                case R.id.profile_iv_mypage_account_back:
                    finish();
                    break;
                case R.id.profile_layout_mypage_account_bank:
                    new AlertDialog.Builder(MyPage_Account.this)
                                .setTitle("은행을 선택하세요.")
                                .setItems(R.array.banks,        // 여러개 항목 중 선택할 수 있는 메소드 = items
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            String[] banks = getResources().getStringArray(R.array.banks);
                                            tv_bank.setText(banks[which]);       // which가 몇번째인지 알고있음
                                        }
                                    }
                                )
                                .setNegativeButton("취소", null)
                                .show();
                    break;
                case R.id.profile_btn_mypage_account_update:
                    new_name = et_name.getText().toString();
                    new_bank = tv_bank.getText().toString();
                    new_number = et_number.getText().toString();

                    urlAddr = macIP + "jsp/profile_mypage_account.jsp?loginEmail=" + loginEmail + "&name=" + new_name + "&bank=" + new_bank + "&number=" + new_number;

                    if(new_bank.equals("은행을 선택하세요.")) {
                        Toast.makeText(MyPage_Account.this, "은행을 선택해주세요.", Toast.LENGTH_SHORT).show();
                    }else if(new_name.length() == 0) {
                        Toast.makeText(MyPage_Account.this, "예금주를 입력하세요.", Toast.LENGTH_SHORT).show();
                    }else if(new_number.length() == 0) {
                        Toast.makeText(MyPage_Account.this, "계좌번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    }else {
                        String result = connectUpdateData();
                        if(result.equals("1")) {
                            Toast.makeText(MyPage_Account.this, "계좌 정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(MyPage_Account.this, "계좌 정보 수정 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
            }
        }

        private String connectUpdateData() {

            String result = null;

            try {
                NetworkTaskMyPage networkTask = new NetworkTaskMyPage(MyPage_Account.this, urlAddr, "update_account");
                Object obj = networkTask.execute().get();
                result = (String) obj;
            }catch(Exception e) {
                e.printStackTrace();
            }
            return result;
        }
    };
}