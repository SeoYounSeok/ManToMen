package com.example.taeil.mantomen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class MypageFragment extends Fragment implements Main2Activity.OnBackPressedListener {

    // TODO: Rename and change types of parameters


    final static String TAG = "AndroidNodeJS";

    Variable variable = Variable.getInstance();

    private OnFragmentInteractionListener mListener;

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
    }

    @Override
    public void onBack() {
        Log.e("Other", "onBack()");
        // 리스너를 설정하기 위해 Activity 를 받아옵니다.
        ((Main2Activity) getActivity()).switchFragment(3);


    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
