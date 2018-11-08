package com.example.taeil.mantomen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NecessaryInfoFragment extends Fragment {

    Variable variable = Variable.getInstance();
    VariableOfClass variableOfClass = VariableOfClass.getInstance();  // 유저정보와 클래스정보를가져오기위함

    public NecessaryInfoFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout necessaryInfofragment = (LinearLayout) inflater.inflate(R.layout.fragment_necessary_info, container, false);

        Button nextbutton = necessaryInfofragment.findViewById(R.id.TutorRegister0_nextbutton); //클래스값받아오는버튼
        final TextView tutorID = necessaryInfofragment.findViewById(R.id.TutorRegister0_name);
        final EditText tutorPhoneNumber = necessaryInfofragment.findViewById(R.id.TutorRegister0_PhoneNumber);

        tutorID.setText(variable.getUserName());

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                variableOfClass.setClassTutorID(tutorID.getText().toString());
                variable.setUserPhoneNumber(tutorPhoneNumber.getText().toString());  // 전화번호인증 나중에 구현
                ((TutorRegisterActivity)getActivity()).switchFragment(1);  // 프래그먼트 교체

            }
        });

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_necessary_info, container, false);
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
