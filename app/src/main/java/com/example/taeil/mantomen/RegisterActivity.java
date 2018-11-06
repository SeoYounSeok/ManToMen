package com.example.taeil.mantomen;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    static Context mContext = null;
    final static String TAG = "AndroidNodeJS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        final EditText IDText = (EditText) findViewById(R.id.ID_R_Input);
        final EditText PWText = (EditText) findViewById(R.id.PW_R_Input);
        final EditText SIDText = (EditText) findViewById(R.id.StudentID_R_Input);
        final EditText NameText = (EditText) findViewById(R.id.Name_R_Input);
        final EditText AgeText = (EditText) findViewById(R.id.Age_R_Input);
        final RadioGroup GenderGroup = (RadioGroup) findViewById(R.id.GenderRadio);
        final CheckBox Checkbox1 = (CheckBox) findViewById(R.id.checkBox1);
        final CheckBox Checkbox2 = (CheckBox) findViewById(R.id.checkBox2);
        final CheckBox Checkbox3 = (CheckBox) findViewById(R.id.checkBox3);
        final CheckBox Checkbox4 = (CheckBox) findViewById(R.id.checkBox4);
        final CheckBox Checkbox5 = (CheckBox) findViewById(R.id.checkBox5);
        final CheckBox Checkbox6 = (CheckBox) findViewById(R.id.checkBox6);

        Button registerButton = (Button) findViewById(R.id.RegisterSubmitbutton); //회원가입 완료버튼

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = IDText.getText().toString();   //ID텍스트의 문자열을 반환 받는값
                String userPassword = PWText.getText().toString();
                String userCN = SIDText.getText().toString();  //문자열을 인트형으로 변경
                String userName = NameText.getText().toString();
                String userAge = AgeText.getText().toString(); //
                final RadioButton GenderChoice = (RadioButton) findViewById(GenderGroup.getCheckedRadioButtonId());  //라디오 그룹에서
                String userGender = GenderChoice.getText().toString();  //라디오버튼


                String userCategory = "";

                if (Checkbox1.isChecked())
                    userCategory = "Programing//";
                if (Checkbox2.isChecked())
                    userCategory += "Design//";
                if (Checkbox3.isChecked())
                    userCategory += "Language//";
                if (Checkbox4.isChecked())
                    userCategory += "Music//";
                if (Checkbox5.isChecked())
                    userCategory += "Beauty//";
                if (Checkbox6.isChecked())
                    userCategory += "Etc//";



//                for(int i=0; i<6; i++){ //모든값을 공백
//                    userCategory[i] = "";
//                }

                Variable.setUserIdentity("Tuti");
                String userIdentity = Variable.getUserIdentity();
                String userParticipateClass = "Null";
                String userOperateClass = "Null";

                JSONObject postDataParam = new JSONObject();


                try {
                    postDataParam.put("userID", userID);
                    postDataParam.put("userPassword", userPassword);
                    postDataParam.put("userCN", userCN);
                    postDataParam.put("userName", userName);
                    postDataParam.put("userAge", userAge);
                    postDataParam.put("userGender", userGender);
                    postDataParam.put("userCategory", userCategory);
                    postDataParam.put("userIdentity", userIdentity);
                    postDataParam.put("userParticipateClass", userParticipateClass);
                    postDataParam.put("userOperateClass", userOperateClass);
                } catch (JSONException e) {
                    Log.e(TAG, "JSONEXception");
                }

                new RegisterInsertData(RegisterActivity.this).execute(postDataParam);


//                Response.Listener<String> responseListener = new Response.Listener<String>() {  //특정요청을 한 이후에 리스너에서 원하는 결과값을 다룰 수 있게함
//
//                    @Override
//                    public void onResponse(String response) {
//                        try{
//                            JSONObject jsonObject = new JSONObject(response);
//                            boolean success = jsonObject.getBoolean("success");
//
//                            if(success){    //값을 정확히 받아왔을때
//                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);  //회원가입완료를 알리는 AlertDialog
//                                builder.setMessage("회원가입완료").setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        Intent GotoLogin = new Intent(RegisterActivity.this, LoginActivity.class);  //인텐트를 만들어서 LoginActivity로 보냄
//                                        RegisterActivity.this.startActivity(GotoLogin);   //확인을 눌러야만 페이지 이동
//                                    }
//                                }).create().show();
//
//
//
//                            }else{
//
//                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);  //회원가입실료를 알리는 AlertDialog
//                                builder.setMessage("회원가입실패").setNegativeButton("돌아가기",null).create().show();
//
//                            }
//
//
//                        }
//                        catch (JSONException e){
//                            e.printStackTrace();  //오류 출력
//                        }
//                    }
//                };
                //회원가입을 신청하는 리퀘스트 부분작성
//                RegisterRequest registerRequest = new RegisterRequest(userID, userPW, userSID, userName, userAge, userGender, responseListener);
//                //매개변수를 넘겨줌
//
//                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
//                queue.add(registerRequest);
//                //버튼을 눌렀을 때 레지스터 리퀘스트가 실행되서 Response를 받게되고 해당 response를 받았을때 정상적인 정보일시 성공
            }
        });


    }
}
