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


public class TutorIntroFragment extends Fragment {

    VariableOfClass variableOfClass = VariableOfClass.getInstance();

    public TutorIntroFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        LinearLayout tutorintrofragment = (LinearLayout) inflater.inflate(R.layout.fragment_tutor_intro, container, false);

        Button previousbutton = tutorintrofragment.findViewById(R.id.TutorRegister1_Previous); //클래스값받아오는버튼
        Button nextbutton = tutorintrofragment.findViewById(R.id.TutorRegister1_Next); //클래스값받아오는버튼

        final EditText tutor

        final TextView tutorID = tutorintrofragment.findViewById(R.id.TutorRegister0_name);
        final EditText tutorPhoneNumber = tutorintrofragment.findViewById(R.id.TutorRegister0_PhoneNumber);

        tutorID.setText(variable.getUserName());

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                variableOfClass.setClassTutorID(tutorID.getText().toString());
                variable.setUserPhoneNumber(tutorPhoneNumber.getText().toString());  // 전화번호인증 나중에 구현
                ((TutorRegisterActivity)getActivity()).switchFragment(1);  // 프래그먼트 교체

            }
        });



        return inflater.inflate(R.layout.fragment_tutor_intro, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
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
