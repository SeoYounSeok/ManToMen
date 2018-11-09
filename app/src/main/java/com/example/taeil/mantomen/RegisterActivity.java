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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    static Context mContext = null;
    final static String TAG = "AndroidNodeJS";
    Variable variable = Variable.getInstance();
    boolean AuthFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AuthFlag = false; //액티비티가 실행될때마다 false로 초기화

        setContentView(R.layout.activity_register);

        final EditText IDText =  findViewById(R.id.Register_ID);
        final EditText PWText =  findViewById(R.id.PW_R_Input);
        final EditText UserEmail = findViewById(R.id.Register_userEmail);
        final EditText NameText = findViewById(R.id.Name_R_Input);
        final EditText AgeText =  findViewById(R.id.Age_R_Input);
        final EditText Authnumber = findViewById(R.id.Register_AuthNumber);
        final RadioGroup GenderGroup =  findViewById(R.id.GenderRadio);
        final CheckBox Checkbox1 =  findViewById(R.id.checkBox1);
        final CheckBox Checkbox2 =  findViewById(R.id.checkBox2);
        final CheckBox Checkbox3 =  findViewById(R.id.checkBox3);
        final CheckBox Checkbox4 =  findViewById(R.id.checkBox4);
        final CheckBox Checkbox5 =  findViewById(R.id.checkBox5);
        final CheckBox Checkbox6 =  findViewById(R.id.checkBox6);


        final Button authbutton = findViewById(R.id.Register_Sendnumber);  // 인증번호 보내기 버튼
        final Button inputauthbutton = findViewById(R.id.Register_InsertAuth); // 인증번호 확인 버튼

        inputauthbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String input = Authnumber.getText().toString();

                if(AuthFlag){  // 프래그가 폴스면실행안되잖아 트루라면
                    Toast.makeText(RegisterActivity.this, "이미 인증되었습니다.",
                            Toast.LENGTH_LONG).show();
                } else{

                    if(input == null){ // 입력을 안했을 때
                        Toast.makeText(RegisterActivity.this, "인증번호를 입력해주세요",
                                Toast.LENGTH_LONG).show();
                    } else if(!input.equals(variable.getAuthnumber())){  // 값이 틀릴 때
                        Toast.makeText(RegisterActivity.this, "인증번호를 확인해주세요",
                                Toast.LENGTH_LONG).show();
                    }
                    else{ // 이게 맞을 경우잖아
                        Toast.makeText(RegisterActivity.this, "인증되셨습니다.",
                                Toast.LENGTH_LONG).show();
                        AuthFlag = true;  // 플래그를 폴스에서 트루로 바꿈
                    }
                }
            }
        });

        authbutton.setOnClickListener(new View.OnClickListener() {

            JSONObject postDataParam = new JSONObject();

            @Override
            public void onClick(View v) {
                try {
                    String userEmail = UserEmail.getText().toString();  //문자열을 인트형으로 변경
                    if(!userEmail.contains("@")){  // @가 포함되어있지 않으면
                        Toast.makeText(RegisterActivity.this, "이메일형식을 입력해 주세요",
                                Toast.LENGTH_LONG).show();
                    } else if(userEmail.contains("@")){
                        postDataParam.put("userEmail", userEmail);
                        Log.e(TAG, userEmail);
                        new AuthInsertData(RegisterActivity.this).execute(postDataParam);
                    }


                } catch (JSONException e) {
                    Log.e(TAG, "JSONEXception");
                }

            }
        });

        Button registerButton = (Button) findViewById(R.id.RegisterSubmitbutton); //회원가입 완료버튼

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userID = IDText.getText().toString();   //ID텍스트의 문자열을 반환 받는값
                String userPassword = PWText.getText().toString();
                String userEmail = UserEmail.getText().toString();  //문자열을 인트형으로 변경
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

                //Variable.setUserIdentity("Tutee");
                //String userIdentity = Variable.getUserIdentity();
                //String userParticipateClass = "Null";
                //String userOperateClass = "Null";

                JSONObject postDataParam = new JSONObject();

                if(userID == null || userPassword == null || userName == null || userAge == null || userGender == null || userCategory==null){
                    Toast.makeText(RegisterActivity.this, "값을 다 입력해 주세요",
                            Toast.LENGTH_LONG).show();
                } else{
                    try {
                        postDataParam.put("userID", userID);
                        postDataParam.put("userPassword", userPassword);
                        postDataParam.put("userEmail", userEmail);
                        postDataParam.put("userName", userName);
                        postDataParam.put("userAge", userAge);
                        postDataParam.put("userGender", userGender);
                        postDataParam.put("userCategory", userCategory);
                        //postDataParam.put("userIdentity", userIdentity);
                        //postDataParam.put("userParticipateClass", userParticipateClass);
                        //postDataParam.put("userOperateClass", userOperateClass);
                    } catch (JSONException e) {
                        Log.e(TAG, "JSONEXception");
                    }

                    if(AuthFlag){  // 인증통과를 했다는 의미
                        new RegisterInsertData(RegisterActivity.this).execute(postDataParam);
                        AuthFlag = false;
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "이메일 인증을 해주세요.",
                                Toast.LENGTH_LONG).show();
                    }

                }







            }
        });


    }
}
