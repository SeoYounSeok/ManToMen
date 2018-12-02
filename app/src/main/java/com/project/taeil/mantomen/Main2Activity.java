package com.project.taeil.mantomen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.project.taeil.mantomen.R;
import com.project.taeil.mantomen.firebase.UserData;

public class Main2Activity extends AppCompatActivity {

    static Context mContext = null;
    static Activity mActivity;
    static int page = 1;    //
    boolean pausecheck = true;
    static ScrollView scrollView;
    static ProgressBar progressBar;
    Variable variable;
    VariableOfClass variableOfClass;

    static int FRAGE_KEY = 0;


    private long time = 0;


    // 리스너 생성
    public interface OnBackPressedListener {
        public void onBack();
    }

    // 리스너 객체 생성
    private OnBackPressedListener mBackListener;

    // 리스너 설정 메소드
    public void setOnBackPressedListener(OnBackPressedListener listener) {
        mBackListener = listener;
    }


    @Override
    public void onBackPressed() { //뒤로가기 버튼 누를때
        // 다른 Fragment 에서 리스너를 설정했을 때 처리됩니다.
        if (mBackListener != null) {
            mBackListener.onBack();
        } else {
            Log.e("Other", "onBack()");
            // 리스너를 설정하기 위해 Activity 를 받아옵니다.   // 백버튼을 누르면 홈 프래그먼트로 이동
            if (System.currentTimeMillis() - time >= 2000) {
                time = System.currentTimeMillis();
                Toast.makeText(getApplicationContext(), "뒤로 버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
            } else if (System.currentTimeMillis() - time < 2000) {
                finish();
            }

        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        // new GetData(Main2Activity.this).execute();  // 클래스 정보를 죄다 받아오는거  // 개많이 받아옴 계속해서
        //finish();
//        if(pausecheck){
//            switchFragment(0);
//            Log.d("포즈","확인");
//            pausecheck = false;
//        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    new GetData(Main2Activity.this).execute(); //
                    switchFragment(0);  //홈버튼이 눌리면 0전송

                    // variableOfClass.getAllClass().clear();
                    //navigationImageview.setVisibility(View.INVISIBLE);
                    return true;

                case R.id.navigation_search:
                    switchFragment(1); //서치버튼이 눌리면
                    //181004 추후 메소드로 묶고 오류를 수정할 예정
                    return true;

                case R.id.navigation_chat:
                    switchFragment(2); //챗버튼이 눌리면
                    return true;

                case R.id.navigation_mypage:
                    //회원정보를 가져오는 메소드 로그인할 때 이미 변수에 로그인 패스워드가 담겨있음 그걸가지고 이제 민영이한테서 정보를 받아올거임
                    switchFragment(3); //마이페이지버튼이 눌리면
                    return true;
            }
            return false;
        }
    };


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        pausecheck = true;

//        if(serviceflag){  // 서비스안해
//            Toast.makeText(getApplicationContext(),"Service 시작",Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(Main2Activity.this,MyService.class);
//            startService(intent);
//            serviceflag = false;
//        }


        super.onCreate(savedInstanceState);

        mActivity = this;  //다른곳에서 이 액티비티를 끄기위해 지정
        mContext = this;


        setContentView(R.layout.activity_main2);


        scrollView = findViewById(R.id.fragment);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);


        //navigationImageview = (ImageView)findViewById(R.id.);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //바텀바 연결

        switchFragment(0);  //홈버튼이 눌리면 0전송

        passPushTokenToServer();  //

    }

    HomeFragment homeFragment = new HomeFragment();

    final SearchFragment searchFragment = new SearchFragment();
    final ChatFragment chatFragment = new ChatFragment();
    final MypageFragment mypageFragment = new MypageFragment();
    final Mypage2Fragment mypage2Fragment = new Mypage2Fragment();

    public void switchFragment(int id) {   //프래그먼트 교체 메인화면, 검색화면, 채팅화면, 마이페이지 총 네개의 프래그먼트 활용예정
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (id == 0) {
            fragmentTransaction.replace(R.id.fragment, homeFragment);
        } else if (id == 1)
            fragmentTransaction.replace(R.id.fragment, searchFragment);
        else if (id == 2)
            fragmentTransaction.replace(R.id.fragment, chatFragment);
        else if (id == 3)
            //여기에 값을 받아오는 메소드를 추가
            fragmentTransaction.replace(R.id.fragment, mypage2Fragment); //마이페이지2로연결
        else if (id == 4)
            fragmentTransaction.replace(R.id.fragment, mypageFragment); //마이페이지1로연결
        fragmentTransaction.commit();
    }

    void passPushTokenToServer(){
        String uid = Variable.getUserID();
        String token = FirebaseInstanceId.getInstance().getToken();
        UserData userData = new UserData();
        userData.users.put("pushToken",token);
        FirebaseDatabase.getInstance().getReference().child("users").child(uid).updateChildren(userData.users);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new GetData(Main2Activity.this).execute();  // 클래스 정보를 죄다 받아오는거
        //homeFragment.allClassListAdapter.upDateItemList(variableOfClass.getAllClass());
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.fragment, new MypageFragment());


//        if(FRAGE_KEY==1){
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.fragment, new MypageFragment());
//            Log.d("메인리쥼","화인2");
//            Log.d("메인리쥼디테일2", String.valueOf(Main2Activity.FRAGE_KEY));
//        }
//        FRAGE_KEY = 0;
//        Log.d("메인리쥼","화인");
//        Bundle args = new Bundle();
//        args.putString("key",value);
//        mypageFragment.setArguments(args);
//        fragmentTransaction.replace(R.id.fragment, mypageFragment);
//        fragmentTransaction.commit();


    }
}
