package com.project.taeil.mantomen;

import android.content.Context;
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


public class ClassPriceFragment extends Fragment {

    VariableOfClass variableOfClass = VariableOfClass.getInstance();

    public ClassPriceFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        LinearLayout classpricefragment = (LinearLayout) inflater.inflate(R.layout.fragment_class_price, container, false);

        Button previousbutton = classpricefragment.findViewById(R.id.TutorRegister3_Previous); // 이전
        Button nextbutton = classpricefragment.findViewById(R.id.TutorRegister3_Next); // 다음

        final EditText ClassPrice = classpricefragment.findViewById(R.id.TutorRegister3_ClassPrice);
        final EditText ClassHour = classpricefragment.findViewById(R.id.TutorRegister3_ClassHour);
        final EditText ClassNumberOfTime = classpricefragment.findViewById(R.id.TutorRegister3_ClassNumberOfTime);


        previousbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TutorRegisterActivity)getActivity()).switchFragment(2);  // 프래그먼트 교체

            }
        });


        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ClassPrice.getText() == null || ClassHour.getText() == null || ClassNumberOfTime.getText() == null) {
                    Toast.makeText(getActivity(), "빈칸을 채워주세요",
                            Toast.LENGTH_LONG).show();

                } else{
                    variableOfClass.setClassPrice(ClassPrice.getText().toString());
                    variableOfClass.setClassHour(ClassHour.getText().toString());
                    variableOfClass.setClassNumberOfTime(ClassNumberOfTime.getText().toString());

                    ((TutorRegisterActivity)getActivity()).switchFragment(4);  // 프래그먼트 교체
                }


            }
        });


        return classpricefragment;
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
