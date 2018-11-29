package com.project.taeil.mantomen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.project.taeil.mantomen.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class ClassDetail extends AppCompatActivity {

    VariableOfClass variableOfClass;
    Variable variable;


    @Override
    protected void onResume() {
        super.onResume();
//        JSONObject postDataParam = new JSONObject();
//        // variableOfClass.getAllClass().get(position).getClassName();
//        try {
//            postDataParam.put("ClassName", variableOfClass.getClassName());
//
//        } catch (JSONException e) {
//            // Log.e(TAG, "JSONEXception");
//        }
//         new ClassDetailInsertData(ClassDetail.this).execute(postDataParam);
//        // ReviewFragment.allReviewListAdapter.notifyDataSetChanged(); // 리스트뷰갱신? 갱신전에 호출이 필요
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        variableOfClass.getAllClass().clear();
//        new GetData(ClassDetail.this).execute();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail);

        Main2Activity.FRAGE_KEY = 1;
        Log.d("메인리쥼디테일", String.valueOf(Main2Activity.FRAGE_KEY));

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation2);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //바텀바 연결
        switchFragment(0);  //홈버튼이 눌리면 0전송

        TextView ClassName = findViewById(R.id.ClassDetail_ClassName);

        Button ClassApply = findViewById(R.id.ClassDetail_ClassApply);

        Button talk = findViewById(R.id.talk);

        TextView ClassPrice = findViewById(R.id.ClassIntro_ClassPrice);
        TextView ClassName2 = findViewById(R.id.ClassIntro_ClassName);
        RatingBar Classratingbar = findViewById(R.id.ClassIntro_ClassScore);
        TextView ClassScore = findViewById(R.id.ClassDetail_ClassScore);
        new DownloadImageTask((ImageView) findViewById(R.id.ClassIntro_ClassPicture))
                .execute(variableOfClass.getClassPicture());



        ClassPrice.setText(variableOfClass.getClassPrice());
        ClassScore.setText(variableOfClass.getClassScore());
        ClassName2.setText(variableOfClass.getClassName());
        Classratingbar.setRating(Float.parseFloat(variableOfClass.getClassScore()));



        ClassName.setText(variableOfClass.getClassName());


        talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GoToChatintent = new Intent(ClassDetail.this, ChattingRoomActivity.class);
                startActivity(GoToChatintent);
            }
        });

        ClassApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 여기에 신청
                JSONObject postDataParam = new JSONObject();
                try {
                    postDataParam.put("ClassName", variableOfClass.getClassName());
                    postDataParam.put("userID", variable.getUserID());
                } catch (JSONException e) {
                    // Log.e(TAG, "JSONEXception");
                }
                new ClassApplyInsertData(ClassDetail.this).execute(postDataParam);

            }
        });


    }


    final ClassIntroFragment classIntroFragment = new ClassIntroFragment();
    final ReviewFragment reviewFragment = new ReviewFragment();
//    final SearchFragment searchFragment = new SearchFragment();
//    final ChatFragment chatFragment = new ChatFragment();
//    final MypageFragment mypageFragment = new MypageFragment();
//    final Mypage2Fragment mypage2Fragment = new Mypage2Fragment();

    public void switchFragment(int id) {   //프래그먼트 교체 메인화면, 검색화면, 채팅화면, 마이페이지 총 네개의 프래그먼트 활용예정
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (id == 0)
            fragmentTransaction.replace(R.id.fragment2, classIntroFragment);
        else if (id == 1)
            fragmentTransaction.replace(R.id.fragment2, reviewFragment);
//        else if (id == 2)
//            fragmentTransaction.replace(R.id.fragment, chatFragment);
//        else if (id == 3)
//            //여기에 값을 받아오는 메소드를 추가
//            fragmentTransaction.replace(R.id.fragment, mypage2Fragment); //마이페이지2로연결
//        else if (id == 4)
//            fragmentTransaction.replace(R.id.fragment, mypageFragment); //마이페이지1로연결
        fragmentTransaction.commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation2_ClassIntro:
                    switchFragment(0);  //홈버튼이 눌리면 0전송
                    //navigationImageview.setVisibility(View.INVISIBLE);
                    return true;

                case R.id.navigation2_ClassReview:
                    switchFragment(1); //서치버튼이 눌리면
                    //181004 추후 메소드로 묶고 오류를 수정할 예정
                    return true;
            }
            return false;
        }
    };


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }




}
