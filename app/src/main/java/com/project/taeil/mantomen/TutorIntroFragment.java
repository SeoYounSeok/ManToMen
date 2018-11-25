package com.project.taeil.mantomen;

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
import android.widget.Toast;

import com.project.taeil.mantomen.R;


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

        Button previousbutton = tutorintrofragment.findViewById(R.id.TutorRegister2_Previous); // 이전
        Button nextbutton = tutorintrofragment.findViewById(R.id.TutorRegister2_Next); // 다음

        final EditText ClassTutorIntro = tutorintrofragment.findViewById(R.id.TutorRegister2_TutorIntro);
        final EditText ClassIntro = tutorintrofragment.findViewById(R.id.TutorRegister2_ClassIntro);
        final EditText ClassContents = tutorintrofragment.findViewById(R.id.TutorRegister2_ClassContents);
        final EditText ClassWhom = tutorintrofragment.findViewById(R.id.TutorRegister2_ClassWhom);


        previousbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TutorRegisterActivity) getActivity()).switchFragment(1);  // 프래그먼트 교체
            }
        });


        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ClassTutorIntro.getText() == null || ClassIntro.getText() == null || ClassContents.getText() == null || ClassWhom.getText() == null) {
                    Toast.makeText(getActivity(), "빈칸을 채워주세요",
                            Toast.LENGTH_LONG).show();

                } else {
                    variableOfClass.setClassTutorIntro(ClassTutorIntro.getText().toString());
                    variableOfClass.setClassIntro(ClassIntro.getText().toString());
                    variableOfClass.setClassContents(ClassContents.getText().toString());
                    variableOfClass.setClassWhom(ClassWhom.getText().toString());

                    ((TutorRegisterActivity) getActivity()).switchFragment(3);  // 프래그먼트 교체

                }

            }
        });


        return tutorintrofragment;
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
