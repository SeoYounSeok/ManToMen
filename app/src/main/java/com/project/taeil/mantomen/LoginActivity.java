package com.project.taeil.mantomen;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.taeil.mantomen.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    final static String TAG = "AndroidNodeJS";
    static Context mContext = null;
    Variable variable;
    String loginId, loginPwd;


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
        final CheckBox autologin = findViewById(R.id.autologin);  // 자동로그인

        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);

        //처음에는 SharedPreferences에 아무런 정보도 없으므로 값을 저장할 키들을 생성한다.
        // getString의 첫 번째 인자는 저장될 키, 두 번쨰 인자는 값입니다.
        // 첨엔 값이 없으므로 키값은 원하는 것으로 하시고 값을 null을 줍니다.

        PWText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE:
                        // 다음 에딧텍스트로 포커스 이동 해야하는데

                        String userID = IDText.getText().toString();
                        String userPassword = PWText.getText().toString();
                        variable = Variable.getInstance();
                        variable.setUserID(userID);  //공통변수에 id 비번 저장
                        variable.setUserPassword(userPassword);


                        if (autologin.isChecked()) { // 자동로그인이 체크되어있다면
                            SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                            //auto의 loginId와 loginPwd에 값을 저장해 줍니다.
                            SharedPreferences.Editor autoLogin = auto.edit();
                            autoLogin.putString("inputId", IDText.getText().toString());
                            autoLogin.putString("inputPwd", PWText.getText().toString());
                            //꼭 commit()을 해줘야 값이 저장됩니다 ㅎㅎ
                            autoLogin.commit();
                        }

                        JSONObject postDataParam = new JSONObject();
                        try {
                            postDataParam.put("userID", userID);
                            postDataParam.put("userPassword", userPassword);
                        } catch (JSONException e) {
                            Log.e(TAG, "JSONEXception");
                        }
                        new LoginInsertData(LoginActivity.this).execute(postDataParam);
                        // new GetData(LoginActivity.this).execute(); // 홈누르면 ㅇㅋ? ㅋ_ㅋ 잠시 꺼놔야함 ; 중요부분
                        new GetData(LoginActivity.this).execute();  // 클래스 정보를 죄다 받아오는거

                        overridePendingTransition(0, 0);  //화면전환효과 없애기

                        break;
                }
                return true;
            }
        });


        loginId = auto.getString("inputId", null);
        loginPwd = auto.getString("inputPwd", null);
        //MainActivity로 들어왔을 때 loginId와 loginPwd값을 가져와서 null이 아니면

        if (loginId != null && loginPwd != null) {  // 자동로그인으로 저장될 아이디 비번이 null이 아니라면 자동로그인상태

            String userID = loginId;
            String userPassword = loginPwd;
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
            // new GetData(LoginActivity.this).execute(); // 홈누르면 ㅇㅋ? ㅋ_ㅋ 잠시 꺼놔야함 ; 중요부분

            new GetData(LoginActivity.this).execute();  // 클래스 정보를 죄다 받아오는거

            overridePendingTransition(0, 0);  //화면전환효과 없애기

        }

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userID = IDText.getText().toString();
                String userPassword = PWText.getText().toString();
                variable = Variable.getInstance();
                variable.setUserID(userID);  //공통변수에 id 비번 저장
                variable.setUserPassword(userPassword);


                if (autologin.isChecked()) { // 자동로그인이 체크되어있다면
                    SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                    //auto의 loginId와 loginPwd에 값을 저장해 줍니다.
                    SharedPreferences.Editor autoLogin = auto.edit();
                    autoLogin.putString("inputId", IDText.getText().toString());
                    autoLogin.putString("inputPwd", PWText.getText().toString());
                    //꼭 commit()을 해줘야 값이 저장됩니다 ㅎㅎ
                    autoLogin.commit();
                }

                JSONObject postDataParam = new JSONObject();
                try {
                    postDataParam.put("userID", userID);
                    postDataParam.put("userPassword", userPassword);
                } catch (JSONException e) {
                    Log.e(TAG, "JSONEXception");
                }
                new LoginInsertData(LoginActivity.this).execute(postDataParam);
                // new GetData(LoginActivity.this).execute(); // 홈누르면 ㅇㅋ? ㅋ_ㅋ 잠시 꺼놔야함 ; 중요부분
                new GetData(LoginActivity.this).execute();  // 클래스 정보를 죄다 받아오는거

                overridePendingTransition(0, 0);  //화면전환효과 없애기
            }

        });


    }
}
