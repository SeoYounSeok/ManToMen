package com.example.taeil.mantomen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MemberModifyActivity extends AppCompatActivity {

    static Context mContext = null;
    final static String TAG = "AndroidNodeJS";

    Variable variable;

    TextView UserID;
    TextView UserName2;
    EditText UserPassword;
    TextView UserEmail;
    EditText UserGender;
    TextView UserName;
    EditText UserAge;
    TextView UserIdentity;
    CheckBox Checkbox1;
    CheckBox Checkbox2;
    CheckBox Checkbox3;
    CheckBox Checkbox4;
    CheckBox Checkbox5;
    CheckBox Checkbox6;


    // EditText UserIdentity = findViewById(R.id.modify_UserIdentity);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_modify);

        Intent intent = getIntent();
        //String userID = intent.getStringExtra("userID"); //마이페이지수정버튼을 통해 ID를 받아옴

        variable = Variable.getInstance();

        UserID = findViewById(R.id.modify_userID);

        UserPassword = findViewById(R.id.modify_UserPassword);
        UserEmail = findViewById(R.id.modify_UserEmail);
        UserGender = findViewById(R.id.modify_UserGender);
        UserName = findViewById(R.id.modify_UserName);
        UserName2 = findViewById(R.id.modify_UserName2);
        UserAge = findViewById(R.id.modify_UserAge);
        UserIdentity = findViewById(R.id.modify_UserIdentity);

        Checkbox1 = findViewById(R.id.modify_checkBox1);
        Checkbox2 = findViewById(R.id.modify_checkBox2);
        Checkbox3 = findViewById(R.id.modify_checkBox3);
        Checkbox4 = findViewById(R.id.modify_checkBox4);
        Checkbox5 = findViewById(R.id.modify_checkBox5);
        Checkbox6 = findViewById(R.id.modify_checkBox6);

        UserID.setText(variable.getUserID());
        UserPassword.setText(variable.getUserPassword());
        UserEmail.setText(variable.getUserEmail());
        UserGender.setText(variable.getUserGender());
        UserName.setText(variable.getUserName());
        UserName2.setText(variable.getUserName());
        UserAge.setText(variable.getUserAge());
        UserIdentity.setText(variable.getUserIdentity());
        Button modifybtn = findViewById(R.id.Membermodify_Btn);


        if (variable.getUserCategory().matches(".*Programing.*")) {
            Checkbox1.setChecked(true);

        }
        if (variable.getUserCategory().matches(".*Design.*")) {
            Checkbox2.setChecked(true);

        }
        if (variable.getUserCategory().matches(".*Language.*")) {
            Checkbox3.setChecked(true);

        }
        if (variable.getUserCategory().matches(".*Music.*")) {
            Checkbox4.setChecked(true);

        }
        if (variable.getUserCategory().matches(".*Beauty.*")) {
            Checkbox5.setChecked(true);

        }
        if (variable.getUserCategory().matches(".*Etc.*")) {
            Checkbox6.setChecked(true);

        }

        modifybtn.setOnClickListener(new View.OnClickListener() { //클릭하면
            @Override
            public void onClick(View v) {

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


                String userPassword = UserPassword.getText().toString();
                String userName = UserName.getText().toString();
                String userAge = UserAge.getText().toString();


//                Variable.setUserCN(String.valueOf(UserCN.getText()));
//                Variable.setUserGender(String.valueOf(UserGender.getText()));
//                Variable.setUserName(String.valueOf(UserName.getText()));
//                Variable.setUserAge(String.valueOf(UserAge.getText()));


                //  modify_userIdentity = String.valueOf(UserIdentity.getText());

                JSONObject postDataParam = new JSONObject();

                try {
                    postDataParam.put("userID", variable.getUserID());
                    postDataParam.put("userPassword", userPassword);
                    postDataParam.put("userEmail", variable.getUserEmail());
                    postDataParam.put("userName", userName);
                    postDataParam.put("userAge", userAge);
                    postDataParam.put("userGender", variable.getUserGender());
                    postDataParam.put("userCategory", userCategory);
                    postDataParam.put("userIdentity", variable.getUserIdentity());
                    postDataParam.put("userParticipateClass", variable.getUserParticipateClass());
                    postDataParam.put("userOperateClass", variable.getUserOperateClass());

                } catch (JSONException e) {
                    Log.e(TAG, "JSONEXception");
                }

                new MemberModifyInsertData(MemberModifyActivity.this).execute(postDataParam);

                Main2Activity mActivity = (Main2Activity)Main2Activity.mActivity;
                mActivity.finish();   //메인액티비티 종료
                finish();
            }
        });


    }
}
