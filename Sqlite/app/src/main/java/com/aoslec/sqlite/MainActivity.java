package com.aoslec.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnUpdate, btnDelete, btnSelect;
    TextView tvResult;
    MemberInfo memberInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memberInfo = new MemberInfo(MainActivity.this);

        btnInsert = findViewById(R.id.btn_insert);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);
        btnSelect = findViewById(R.id.btn_select);
        tvResult = findViewById(R.id.tv_result);

        btnInsert.setOnClickListener(onClickListener);
        btnUpdate.setOnClickListener(onClickListener);
        btnDelete.setOnClickListener(onClickListener);
        btnSelect.setOnClickListener(onClickListener);

    } // onCreate

    View.OnClickListener onClickListener = new View.OnClickListener() {
        SQLiteDatabase DB;

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_insert:
                    try{
                        DB = memberInfo.getWritableDatabase();
                        String query = "INSERT INTO member (username, userid, passwd) VALUES ('홍길동', 'hkdong', 1111);";
                        DB.execSQL(query);

                        memberInfo.close();
                        Toast.makeText(MainActivity.this, "Insert OK!", Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Insert Error!" , Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.btn_update:
                    try{
                        DB = memberInfo.getWritableDatabase();
                        String query = "UPDATE member SET username = '임꺽정' WHERE userid = 'hkdong';";
                        DB.execSQL(query);

                        memberInfo.close();
                        Toast.makeText(MainActivity.this, "Update OK!", Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Update Error!" , Toast.LENGTH_LONG).show();
                    }
                    break;

                case R.id.btn_select:
                    try{
                        DB = memberInfo.getReadableDatabase();
                        String query = "SELECT username, userid, passwd FROM member;";

                        Cursor cursor = DB.rawQuery(query, null);
                        StringBuffer stringBuffer = new StringBuffer();

                        while(cursor.moveToNext()){
                            String username = cursor.getString(0);
                            String userid = cursor.getString(1);
                            int passwd = cursor.getInt(2);

                            stringBuffer.append("username : " + username + ", user id : " + userid + ", passwd : " + passwd + "\n");
                        }
                        cursor.close();
                        memberInfo.close();
                        tvResult.setText(stringBuffer.toString());
                        Toast.makeText(MainActivity.this, "Select OK!", Toast.LENGTH_LONG).show();


                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Select Error!" , Toast.LENGTH_LONG).show();
                    }
                    break;

                case R.id.btn_delete:
                    try{
                        DB = memberInfo.getWritableDatabase();
                        String query = "DELETE FROM member WHERE id = 3;";
                        DB.execSQL(query);

                        memberInfo.close();
                        Toast.makeText(MainActivity.this, "Delete OK!", Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Delete Error!" , Toast.LENGTH_LONG).show();
                    }
                    break;

            }
        }
    };

} // MainActivity