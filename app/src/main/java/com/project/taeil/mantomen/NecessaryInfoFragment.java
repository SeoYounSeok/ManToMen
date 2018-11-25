package com.project.taeil.mantomen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.taeil.mantomen.R;

import org.json.JSONException;
import org.json.JSONObject;

public class NecessaryInfoFragment extends Fragment {

    Variable variable = Variable.getInstance();
    VariableOfClass variableOfClass = VariableOfClass.getInstance();  // 유저정보와 클래스정보를가져오기위함
    final static String TAG = "AndroidNodeJS";
    boolean AuthFlag = false;

    public NecessaryInfoFragment() {

    }


    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AuthFlag = false;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout necessaryInfofragment = (LinearLayout) inflater.inflate(R.layout.fragment_necessary_info, container, false);

        Button nextbutton = necessaryInfofragment.findViewById(R.id.TutorRegister0_nextbutton); //클래스값받아오는버튼
        final TextView tutorID = necessaryInfofragment.findViewById(R.id.TutorRegister0_ID);
        final TextView tutorPhoneNumber = necessaryInfofragment.findViewById(R.id.TutorRegister0_PhoneNumber);
        final EditText InputPhoneNumber = necessaryInfofragment.findViewById(R.id.TutorRegister0_InputPhoneNumber);
        final Button sendNumber = necessaryInfofragment.findViewById(R.id.TutorRegister0_Sendnumber);
        final EditText AuthNumber = necessaryInfofragment.findViewById(R.id.TutorRegister0_AuthNumber);
        final Button InsertAuth = necessaryInfofragment.findViewById(R.id.TutorRegister0_InsertAuth);


        tutorID.setText(variable.getUserID());
        Log.d("오류", variable.getUserID());




        InsertAuth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String input = AuthNumber.getText().toString();

                if(AuthFlag){  // 프래그가 폴스면실행안되잖아 트루라면
                    Toast.makeText(getActivity(), "이미 인증되었습니다.",
                            Toast.LENGTH_LONG).show();
                } else{

                    if(input == null){ // 입력을 안했을 때
                        Toast.makeText(getActivity(), "인증번호를 입력해주세요",
                                Toast.LENGTH_LONG).show();
                    } else if(!input.equals(variable.getAuthnumber())){  // 값이 틀릴 때
                        Toast.makeText(getActivity(), "인증번호를 확인해주세요",
                                Toast.LENGTH_LONG).show();
                    }
                    else{ // 이게 맞을 경우잖아
                        Toast.makeText(getActivity(), "인증되셨습니다.",
                                Toast.LENGTH_LONG).show();
                        tutorPhoneNumber.setText(variable.getUserPhoneNumber()); //전화번호고정
                        AuthFlag = true;  // 플래그를 폴스에서 트루로 바꿈
                    }
                }
            }
        });



        sendNumber.setOnClickListener(new View.OnClickListener() {

            JSONObject postDataParam = new JSONObject();

            @Override
            public void onClick(View v) {
                try {
                    String userPhoneNumber = InputPhoneNumber.getText().toString();  // 전화번호

                    postDataParam.put("userPhoneNumber", userPhoneNumber);

                    variable.setUserPhoneNumber(userPhoneNumber);

                    Log.e(TAG, userPhoneNumber);

                    new AuthPhoneInsertData(getActivity()).execute(postDataParam);


                } catch (JSONException e) {
                    Log.e(TAG, "JSONEXception");
                }

            }

        });

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(AuthFlag){  // 인증통과를 했다는 의미
                    variableOfClass.setClassTutorID(tutorID.getText().toString());
                    Log.d("오류", tutorID.getText().toString());
                    Log.d("오류", tutorPhoneNumber.getText().toString());
                    ((TutorRegisterActivity) getActivity()).switchFragment(1);  // 프래그먼트 교체
                    AuthFlag = false;
                }
                else{
                    Toast.makeText(getActivity(), "전화번호 인증을 해주세요.",
                            Toast.LENGTH_LONG).show();
                }





            }
        });

        // Inflate the layout for this fragment
        return necessaryInfofragment;
    }


    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
