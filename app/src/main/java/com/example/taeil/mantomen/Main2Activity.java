package com.example.taeil.mantomen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import java.util.concurrent.Delayed;

public class Main2Activity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener, SearchFragment.OnFragmentInteractionListener {

    static Context mContext = null;
    static Activity mActivity;
    static int page = 1;    //
    boolean Lock = true;
    static ScrollView scrollView;
    static ProgressBar progressBar;


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
            super.onBackPressed();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        //finish();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    new GetData(Main2Activity.this).execute(); // 홈누르면 ㅇㅋ? ㅋ_ㅋ 잠시 꺼놔야함 ; 중요부분
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


        super.onCreate(savedInstanceState);
        mActivity = Main2Activity.this;  //다른곳에서 이 액티비티를 끄기위해 지정
        setContentView(R.layout.activity_main2);


        scrollView = findViewById(R.id.fragment);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);


        //navigationImageview = (ImageView)findViewById(R.id.);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //바텀바 연결

        switchFragment(0);  //홈버튼이 눌리면 0전송

//        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                Log.d("페이지", String.valueOf(page));
//                Log.d("페이지", String.valueOf(scrollY));
//                if(page == 1){
//                    if(scrollY >=1089){
//                        progressBar.setVisibility(View.VISIBLE);
//                        new GetDatamore(Main2Activity.this).execute();
//                        HomeFragment.allClassListAdapter.notifyDataSetChanged();
//                        switchFragment(0);  //새로고침을 위한?
//                        page++;
//
//
//
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                // AllClassListAdapter.notifyDataSetChanged();
//                                progressBar.setVisibility(View.GONE);
//
//                            }
//                        },1000);
//                    }
//                } else if(page > 1){
//                    if(scrollY >= 1089+(page-1)*2403){
//                        progressBar.setVisibility(View.VISIBLE);
//                        new GetDatamore(Main2Activity.this).execute();
//                        HomeFragment.allClassListAdapter.notifyDataSetChanged();
//
//                        switchFragment(0);  //새로고침을 위한?
//                        page++;
//
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                // AllClassListAdapter.notifyDataSetChanged();
//                                progressBar.setVisibility(View.GONE);
//
//                            }
//                        },1000);
//                    }
//                }
//
//
//            }
//        });
    }



    HomeFragment homeFragment = new HomeFragment();
    final SearchFragment searchFragment = new SearchFragment();
    final ChatFragment chatFragment = new ChatFragment();
    final MypageFragment mypageFragment = new MypageFragment();
    final Mypage2Fragment mypage2Fragment = new Mypage2Fragment();

    public void switchFragment(int id) {   //프래그먼트 교체 메인화면, 검색화면, 채팅화면, 마이페이지 총 네개의 프래그먼트 활용예정
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (id == 0)
            fragmentTransaction.replace(R.id.fragment, homeFragment);
        else if (id == 1)
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


    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
