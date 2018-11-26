package com.project.taeil.mantomen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.Constants;
import com.anjlab.android.iab.v3.SkuDetails;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.project.taeil.mantomen.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    boolean loading = true; //로딩 최초 한번만실행
    private BillingProcessor bp;
    public static ArrayList<SkuDetails> products;

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

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }



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
