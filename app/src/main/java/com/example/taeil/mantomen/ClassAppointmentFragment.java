package com.example.taeil.mantomen;

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
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class ClassAppointmentFragment extends Fragment {

    VariableOfClass variableOfClass = VariableOfClass.getInstance();

    final static String TAG = "AndroidNodeJS";

    public ClassAppointmentFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout classappointmentfragment = (LinearLayout) inflater.inflate(R.layout.fragment_class_appointment, container, false);

        Button previousbutton = classappointmentfragment.findViewById(R.id.TutorRegister4_Previous); // 이전
        Button completebutton = classappointmentfragment.findViewById(R.id.TutorRegister4_Complete); // 완료버튼을 누르면 자료 보내기


        final EditText ClassPlace = classappointmentfragment.findViewById(R.id.TutorRegister4_ClassPlace);
        final EditText ClassPlaceDetail = classappointmentfragment.findViewById(R.id.TutorRegister4_ClassPlaceDetail);
        final Button ClassWeek1 = classappointmentfragment.findViewById(R.id.TutorRegister4_Classweek1);
        final Button ClassWeek2 = classappointmentfragment.findViewById(R.id.TutorRegister4_Classweek2);
        final Button ClassWeek3 = classappointmentfragment.findViewById(R.id.TutorRegister4_Classweek3);
        final Button ClassWeek4 = classappointmentfragment.findViewById(R.id.TutorRegister4_Classweek4);
        final Button ClassWeek5 = classappointmentfragment.findViewById(R.id.TutorRegister4_Classweek5);
        final Button ClassWeek6 = classappointmentfragment.findViewById(R.id.TutorRegister4_Classweek6);
        final Button ClassWeek7 = classappointmentfragment.findViewById(R.id.TutorRegister4_Classweek7);
        final EditText ClassTime = classappointmentfragment.findViewById(R.id.TutorRegister4_ClassTime);
        final EditText ClassFirstTime = classappointmentfragment.findViewById(R.id.TutorRegister4_ClassFirstTime);


        previousbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TutorRegisterActivity) getActivity()).switchFragment(3);  // 프래그먼트 교체

            }
        });


        completebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                variableOfClass.setClassPlace(ClassPlace.getText().toString());
                variableOfClass.setClassPlaceDetail(ClassPlaceDetail.getText().toString());
                variableOfClass.setClassWeek("월");  // 임의로 정해둠
                variableOfClass.setClassTime(ClassTime.getText().toString());
                variableOfClass.setClassFirstTime(ClassFirstTime.getText().toString());


                JSONObject postDataParam = new JSONObject();

                try {


//                        Toast.makeText(RegisterActivity.this, "이메일형식을 입력해 주세요",
//                                Toast.LENGTH_LONG).show();

                    postDataParam.put("ClassPicture",variableOfClass.getClassPicture());
                    postDataParam.put("ClassName",variableOfClass.getClassPicture());
                    postDataParam.put("ClassTutorID",variableOfClass.getClassPicture());
                    postDataParam.put("ClassCategory",variableOfClass.getClassPicture());
                    postDataParam.put("ClassTotalPeople",variableOfClass.getClassPicture());
                    postDataParam.put("ClassCurrentPeople",variableOfClass.getClassPicture());
                    postDataParam.put("ClassTutorIntro",variableOfClass.getClassPicture());
                    postDataParam.put("ClassIntro",variableOfClass.getClassPicture());
                    postDataParam.put("ClassContents",variableOfClass.getClassPicture());
                    postDataParam.put("ClassWhom",variableOfClass.getClassPicture());
                    postDataParam.put("ClassPrice",variableOfClass.getClassPicture());
                    postDataParam.put("ClassHour",variableOfClass.getClassPicture());
                    postDataParam.put("ClassNumberOfTime",variableOfClass.getClassPicture());
                    postDataParam.put("ClassPlace",variableOfClass.getClassPicture());
                    postDataParam.put("ClassPlaceDetail",variableOfClass.getClassPicture());
                    postDataParam.put("ClassWeek",variableOfClass.getClassPicture());
                    postDataParam.put("ClassTime",variableOfClass.getClassPicture());
                    postDataParam.put("ClassPlaceDetail",variableOfClass.getClassPicture());
                    postDataParam.put("ClassFirstTime",variableOfClass.getClassPicture());


                    new TutorRegisterInsertData(getActivity()).execute(postDataParam);


                } catch (JSONException e) {
                    Log.e(TAG, "JSONEXception");
                }

            }
        });


        // Inflate the layout for this fragment
        return classappointmentfragment;
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
