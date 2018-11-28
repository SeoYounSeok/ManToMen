package com.project.taeil.mantomen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.taeil.mantomen.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;


public class Mypage2Fragment extends Fragment implements Main2Activity.OnBackPressedListener{

    static Context mContext = null;
    final static String TAG = "AndroidNodeJS";

    Variable variable = Variable.getInstance();
    final int REQ_CODE_SELECT_IMAGE = 100;
    ProgressDialog asyncDialog;
    String getServerURL = variable.HttpAddres;  //민영이 서버
    String getImgURL = "";
    String getImgName = "";
    Bitmap bitmap;   // 비트맵을 담기위한 비트맵 객체선언

    public Mypage2Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        LinearLayout mypage2fragment = (LinearLayout) inflater.inflate(R.layout.fragment_mypage2, container, false);

        Button operateclass = (Button) mypage2fragment.findViewById(R.id.Mypage2_OperateClass); //클래스값받아오는버튼

        Button gotomypage = (Button) mypage2fragment.findViewById(R.id.Mypage2_Mypage); //마이페이지로가는버튼
        Button tutorregister = mypage2fragment.findViewById(R.id.Mypage2_TutorRegister); // 튜터등록 버튼

        Button pointbuy = mypage2fragment.findViewById(R.id.Mypage2_BuyPoint);

        TextView userID = mypage2fragment.findViewById(R.id.Mypage2_userID);

        // ImageView userPicture = getActivity().findViewById(R.id.Mypage2_userPicture); // 유저사진

        userID.setText(variable.getUserID());

        new DownloadImageTask((ImageView) mypage2fragment.findViewById(R.id.Mypage2_userPicture))
                .execute(variable.getUserPicture());
        Log.e("수정후",variable.getUserPicture());

        tutorregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if문으로 튜티인지 튜터인지 확인 후 튜터이면 진행
                if (variable.getUserIdentity().equals("Tutee")) {
                    Main2Activity.mContext = getActivity();

                    Intent GoToTutorregisterintent = new Intent(((Main2Activity) Main2Activity.mContext), TutorRegisterActivity.class); // TutorRegisterActivity로 보내는 인텐트
                    ((Main2Activity) Main2Activity.mContext).startActivity(GoToTutorregisterintent);

                } else {
                    Toast.makeText(getActivity(), "이미 등록되셨습니다.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        pointbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent GoToBuyPointrintent = new Intent(((Main2Activity) Main2Activity.mContext), PointBuyActivity.class); // TutorRegisterActivity로 보내는 인텐트
//                ((Main2Activity) Main2Activity.mContext).startActivity(GoToBuyPointrintent);

                Intent GoToBuyPointrintent = new Intent(((Main2Activity) Main2Activity.mContext), PointActivity.class); // TutorRegisterActivity로 보내는 인텐트
                ((Main2Activity) Main2Activity.mContext).startActivity(GoToBuyPointrintent);

            }
        });


        gotomypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Main2Activity) getActivity()).switchFragment(4);

            }
        });

        operateclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 여기서 이동이 아니라 해도되는데 값 받아오는걸로
                // 오퍼레ㅣ트클래스인서트로 userID값을 보내면 서버에서 tutorID에 맞는 정보들을 이제 줌

                if(variable.getUserIdentity().equals("Tutee")){
                    Toast.makeText(getActivity(), "튜티는 들어갈 수 없습니다.",
                            Toast.LENGTH_LONG).show();
                } else{
                    JSONObject postDataParam = new JSONObject();
                    try {
                        postDataParam.put("ClassTutorID", variable.getUserID());
                    } catch (JSONException e) {
                        Log.e(TAG, "JSONEXception");
                    }
                    new OperateClassInsertData(getActivity()).execute(postDataParam);
                }

                // new GetData(LoginActivity.this).execute(); // 홈누르면 ㅇㅋ? ㅋ_ㅋ 잠시 꺼놔야함 ; 중요부분

//                Main2Activity.mContext = getActivity();
//                Intent GoToOperateClassintent = new Intent(((Main2Activity) Main2Activity.mContext), OperateClassActivity.class); //MakeClass액티비티로 보내는 인텐트
//                ((Main2Activity) Main2Activity.mContext).startActivity(GoToOperateClassintent);
//                ((Main2Activity) Main2Activity.mContext).overridePendingTransition(0, 0);  //화면전환효과 없애기
//                //getActivity().finish();  // 액티비티 삭제
//


            }
        });


        SharedPreferences auto = getActivity().getSharedPreferences("auto", Activity.MODE_PRIVATE);
        //처음에는 SharedPreferences에 아무런 정보도 없으므로 값을 저장할 키들을 생성한다.
        // getString의 첫 번째 인자는 저장될 키, 두 번쨰 인자는 값입니다.
        final SharedPreferences.Editor editor = auto.edit();

        Button logout = mypage2fragment.findViewById(R.id.mypage2_LogoutButton);
        logout.setOnClickListener(new View.OnClickListener() {   //로그아웃 누르면 쉐어드 프레퍼런스에 있는거 삭제
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.commit();
                variable.setCookies("");  // 나중에 이거 쿠키가 가지고있는 토큰도 쉐어드 프레퍼런스로 만들기
            }
        });




        // Inflate the layout for this fragment
        return mypage2fragment;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Other", "onAttach()");
        // ((Main2Activity) context).setOnBackPressedListener(this);
    }


    @Override
    public void onBack() {
        Log.e("Other", "onBack()");
        // 리스너를 설정하기 위해 Activity 를 받아옵니다.
        Main2Activity activity = (Main2Activity) getActivity();
        // 한번 뒤로가기 버튼을 눌렀다면 Listener 를 null 로 해제해줍니다.
        activity.setOnBackPressedListener(null);
        // MainFragment 로 교체
        ((Main2Activity) getActivity()).switchFragment(0);
        // Activity 에서도 뭔가 처리하고 싶은 내용이 있다면 하단 문장처럼 호출해주면 됩니다.
        // activity.onBackPressed();

    }

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
