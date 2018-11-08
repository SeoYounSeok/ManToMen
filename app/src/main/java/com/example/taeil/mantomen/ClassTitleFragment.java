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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ClassTitleFragment extends Fragment {

    Variable variable = Variable.getInstance();
    VariableOfClass variableOfClass = VariableOfClass.getInstance();

    public ClassTitleFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout classtitlefragment = (LinearLayout) inflater.inflate(R.layout.fragment_class_title, container, false);

        final Button previousbutton = classtitlefragment.findViewById(R.id.TutorRegister1_Previous); // 이전
        final Button nextbutton = classtitlefragment.findViewById(R.id.TutorRegister1_Next); // 다음
        final EditText ClassName = classtitlefragment.findViewById(R.id.TutorRegister1_ClassName);
        final ImageView ClassPicture = classtitlefragment.findViewById(R.id.TutorRegister1_ClassPicture);
        final Button ClassCategory = classtitlefragment.findViewById(R.id.TutorRegister1_ClassCategory);  // 버튼을 누를 때 다이얼로그창이 뜨면서 카테고리선택
        final EditText ClassTotalPeople = classtitlefragment.findViewById(R.id.TutorRegister1_ClassTotalPeople);


        previousbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TutorRegisterActivity)getActivity()).switchFragment(1);  // 프래그먼트 교체
            }
        });


        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                variableOfClass.setClassName(ClassName.getText().toString());
                // variableOfClass.setClassName(ClassPicture.getText().toString());  // 사진은 임의의 사진 일단 스트링으로 보냄시험삼아
                variableOfClass.setClassName("시험사진");
                variableOfClass.setClassName(ClassCategory.getText().toString());
                variableOfClass.setClassName("Programing");
                variableOfClass.setClassName(ClassTotalPeople.getText().toString());
                ((TutorRegisterActivity)getActivity()).switchFragment(2);  // 프래그먼트 교체

            }
        });




        // Inflate the layout for this fragment
        return classtitlefragment;
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
