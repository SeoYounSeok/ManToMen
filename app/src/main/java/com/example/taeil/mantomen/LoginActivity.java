package com.example.taeil.mantomen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    final static String TAG = "AndroidNodeJS";
    static Context mContext = null;
    Variable variable;
    
    @Override
    protected void onPause() {
        super.onPause();

//        finish();  //로그인 하고 없애버림
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        final EditText IDText = (EditText) findViewById(R.id.ID_Input);
        final EditText PWText = (EditText) findViewById(R.id.PW_Input);
        final Button LoginButton = (Button) findViewById(R.id.LoginSubmitbutton);

        final String master = "taeil"; //마스터아이디

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String userID = IDText.getText().toString();
                final String userPassword = PWText.getText().toString();
                variable = Variable.getInstance();
                variable.setUserID(userID);  //공통변수에 id 비번 저장
                variable.setUserPassword(userPassword);

                JSONObject postDataParam = new JSONObject();


                try {
                    postDataParam.put("userID", userID);
                    postDataParam.put("userPassword", userPassword);
                } catch (JSONException e) {
                    Log.e(TAG, "JSONEXception");
                }

                new LoginInsertData(LoginActivity.this).execute(postDataParam);


//                new GetData(LoginActivity.this).execute();
//                Log.d("리뷰","실행되나?");

                overridePendingTransition(0, 0);  //화면전환효과 없애기
            }

        });
    }
}
