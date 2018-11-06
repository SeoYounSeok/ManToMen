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


//                new GetData(LoginActivity.this).execute();
//                new GetData(LoginActivity.this).execute();
                try {
                    postDataParam.put("userID", userID);
                    postDataParam.put("userPassword", userPassword);
                } catch (JSONException e) {
                    Log.e(TAG, "JSONEXception");
                }

                new LoginInsertData(LoginActivity.this).execute(postDataParam);


                new GetData(LoginActivity.this).execute();
                Log.d("리뷰","실행되나?");

                overridePendingTransition(0, 0);  //화면전환효과 없애기

//                LoginActivity.this.startActivity(GoToMainintent);
//                GoToMainintent.putExtra("userID", userID);
//                overridePendingTransition(0, 0);  //화면전환효과 없애기


//                if (userID.equals(master)) {
////                    Intent GoToMainintent = new Intent(LoginActivity.this, Main2Activity.class); //메인액티비티로 보내는 인텐트
//                    LoginActivity.this.startActivity(GoToMainintent);
//                    GoToMainintent.putExtra("userID", userID);
//                    overridePendingTransition(0, 0);  //화면전환효과 없애기
//                }  //마스터 아이디로 로그인할시


//                Response.Listener<String> responseListener = new Response.Listener<String>() {
//
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonResponse = new JSONObject(response);
//                            boolean success = jsonResponse.getBoolean("success");
//                            Toast.makeText(getApplicationContext(), "success" + success, Toast.LENGTH_SHORT).show();
////                            Toast.makeText(getApplicationContext(), "ID"+IDText, Toast.LENGTH_SHORT).show();
////                            Toast.makeText(getApplicationContext(), "PW"+PWText, Toast.LENGTH_SHORT).show();
//
//                            if (success) {
//                                String userID = jsonResponse.getString("userID");
//                                String userPassword = jsonResponse.getString("userPassword");
//                                String userName = jsonResponse.getString("userName");
//                                Intent GoToMainintent = new Intent(LoginActivity.this, Main2Activity.class); //메인액티비티로 보내는 인텐트
//                                GoToMainintent.putExtra("userID", userID);
//                                GoToMainintent.putExtra("userPassword", userPassword);
//                                GoToMainintent.putExtra("userName", userName);
//                                LoginActivity.this.startActivity(GoToMainintent);
//                            } else {
//                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                                builder.setMessage("로그인에 실패하였습니다.")
//                                        .setNegativeButton("다시 시도", null)
//                                        .create()
//                                        .show();
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                };
//                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
//                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
//                queue.add(loginRequest);



            }

        });
    }
}
