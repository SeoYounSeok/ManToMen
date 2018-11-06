package com.example.taeil.mantomen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    boolean loading = true; //로딩 최초 한번만실행

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Intent LoadingIntent = new Intent(this, LoadingActivity.class);
        Intent LoginIntent = new Intent(this, LoginActivity.class);
        Intent RegisterIntent = new Intent(this, RegisterActivity.class);

        startActivity(LoadingIntent);

        //로딩화면

        Button Loginbutton = (Button) findViewById(R.id.Loginbutton); //로그인 버튼객체
        Button Registerbutton = (Button) findViewById(R.id.RegisterButton); //회원가입 버튼객체
        LoginButtonClick(Loginbutton, LoginIntent);  //로그인 클릭처리
        RegisterButtonClick(Registerbutton,RegisterIntent);  //회원가입 클릭처리
        //버튼과 인텐트를 객체로 받아 메소드 실행

        // 실험




        //실험



    }


    //로그인 클릭을 처리하기위한 버튼 메소드
    private void LoginButtonClick(Button LoginButton, final Intent LoginIntent){
        LoginButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(LoginIntent);
            }
        });

    }

    private void RegisterButtonClick(Button RegisterButton, final Intent RegisterIntent){
        RegisterButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(RegisterIntent);
            }
        });

    }

}
