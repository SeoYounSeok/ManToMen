package com.project.taeil.mantomen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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

import com.project.taeil.mantomen.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;


public class MypageFragment extends Fragment implements Main2Activity.OnBackPressedListener {

    // TODO: Rename and change types of parameters


    final static String TAG = "AndroidNodeJS";

    Variable variable = Variable.getInstance();

    public MypageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        LinearLayout mypagefragment = (LinearLayout) inflater.inflate(R.layout.fragment_mypage, container, false);

        Button modifybtn = (Button) mypagefragment.findViewById(R.id.Mypage_Membermodify_Btn); //회원정보수정버튼
        Button deletebtn = (Button) mypagefragment.findViewById(R.id.Mypage_Memberdelete_Btn); //회원탈퇴버튼

        TextView UserID = (TextView) mypagefragment.findViewById(R.id.mypage_userID);
        // TextView UserPassword = (TextView) mypagefragment.findViewById(R.id.mypage_UserPassword);
        TextView UserEmail = (TextView) mypagefragment.findViewById(R.id.mypage_UserEmail);
        TextView UserGender = (TextView) mypagefragment.findViewById(R.id.mypage_UserGender);
        TextView UserName = (TextView) mypagefragment.findViewById(R.id.mypage_UserName);
        TextView UserAge = (TextView) mypagefragment.findViewById(R.id.mypage_UserAge);
        TextView UserIdentity = (TextView) mypagefragment.findViewById(R.id.mypage_UserIdentity);
        TextView UserCategory = (TextView) mypagefragment.findViewById(R.id.mypage_UserCategory);


        UserID.setText(variable.getUserID());
        // UserPassword.setText(variable.getUserPassword());
        UserEmail.setText(variable.getUserEmail());
        UserGender.setText(variable.getUserGender());
        UserName.setText(variable.getUserName());
        UserAge.setText(variable.getUserAge());
        UserIdentity.setText(variable.getUserIdentity());
        UserCategory.setText(variable.getUserCategory());


        new DownloadImageTask((ImageView) mypagefragment.findViewById(R.id.Mypage_UserPicture))
                .execute(variable.getUserPicture());


        modifybtn.setOnClickListener(new View.OnClickListener() {  //버튼을 누르면 멤버 모디파이 액티비티로 이동
            @Override
            public void onClick(View v) {


                //String userID = ((Main2Activity)getActivity()).getUserID();  //유저 아이디를 가져옴

                String userID = variable.getUserID();  //유저 아이디를 가져옴
                Intent gotoModify = new Intent(getActivity(), MemberModifyActivity.class); //membermodifyactivity로 보내는 인텐트
                getActivity().startActivity(gotoModify);
                gotoModify.putExtra("userID", userID);


                getActivity().overridePendingTransition(0, 0);  //화면전환효과 없애기
                //((Main2Activity)getActivity()).finish(); //홈액티비티종료

            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("회원탈퇴");

// AlertDialog 셋팅
                builder
                        .setMessage("정말 회원탈퇴하시겠습니까?")
                        .setCancelable(false)
                        .setPositiveButton("네",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {


                                        JSONObject postDataParam = new JSONObject();
                                        try {

                                            postDataParam.put("userID", variable.getUserID());
                                            postDataParam.put("userPassword", variable.getUserPassword());


                                        } catch (JSONException e) {
                                            Log.e(TAG, "JSONEXception");
                                        }
                                        new MemberDeleteInsertData(getActivity()).execute(postDataParam);

                                        Main2Activity.mContext = getActivity();

                                        String userID = variable.getUserID();
                                        Intent GoToLoginintent = new Intent(((Main2Activity) Main2Activity.mContext), LoginActivity.class); //메인액티비티로 보내는 인텐트
                                        ((Main2Activity) Main2Activity.mContext).startActivity(GoToLoginintent);
//                                        GoToLoginintent.putExtra("userID", userID);
//                                        ((Main2Activity)Main2Activity.mContext).overridePendingTransition(0, 0);  //화면전환효과 없애기
//                                        getActivity().finish();  // 액티비티 삭제
                                    }
                                })
                        .setNegativeButton("아니요",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        // 다이얼로그를 취소한다
                                        dialog.cancel();
                                    }
                                });
                AlertDialog dialog = builder.create();    // 알림창 객체 생성
                dialog.show();    // 알림창 띄우기

            }
        });

        return mypagefragment;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Other", "onAttach()");
        ((Main2Activity) context).setOnBackPressedListener(this);
    }


    @Override
    public void onBack() {
        Log.e("Other", "onBack()");
        // 리스너를 설정하기 위해 Activity 를 받아옵니다.
        Main2Activity activity = (Main2Activity) getActivity();
        // 한번 뒤로가기 버튼을 눌렀다면 Listener 를 null 로 해제해줍니다.
        activity.setOnBackPressedListener(null);
        // MainFragment 로 교체
        ((Main2Activity) getActivity()).switchFragment(3);
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
