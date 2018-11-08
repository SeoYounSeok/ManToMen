package com.example.taeil.mantomen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TutorRegisterActivity extends AppCompatActivity {

    static Context mContext = null;
    static Activity mActivity;

    Variable variable = Variable.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = TutorRegisterActivity.this;  //다른곳에서 이 액티비티를 끄기위해 지정
        setContentView(R.layout.activity_tutor_register);
        switchFragment(0);

    }

    final NecessaryInfoFragment necessaryInfoFragment = new NecessaryInfoFragment();  // 필수정보 입력칸
    final ClassTitleFragment classTitleFragment = new ClassTitleFragment(); // 클래스 타이틀 프래그먼트
    final TutorIntroFragment tutorIntroFragment = new TutorIntroFragment();
    final ClassPriceFragment classPriceFragment  = new ClassPriceFragment();  // 클래스 프라이스 프래그먼트
    final ClassAppointmentFragment classAppointmentFragment = new ClassAppointmentFragment();

    public void switchFragment(int id) {   // 프래그먼트 교체 메인화면
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (id == 0)
            fragmentTransaction.replace(R.id.fragment3, necessaryInfoFragment);
        else if (id == 1)
            fragmentTransaction.replace(R.id.fragment3, classTitleFragment);
        else if (id == 2)
            fragmentTransaction.replace(R.id.fragment3, tutorIntroFragment);
        else if (id == 3)
            //여기에 값을 받아오는 메소드를 추가
            fragmentTransaction.replace(R.id.fragment3, classPriceFragment); //마이페이지2로연결
        else if (id == 4)
            fragmentTransaction.replace(R.id.fragment3, classAppointmentFragment); //마이페이지1로연결
        fragmentTransaction.commit();
    }




}
