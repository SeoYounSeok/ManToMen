package com.project.taeil.mantomen;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.taeil.mantomen.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ClassAppointmentFragment extends Fragment {


    Variable variable = Variable.getInstance();
    VariableOfClass variableOfClass = VariableOfClass.getInstance();

    final static String TAG = "AndroidNodeJS";
    String ClassFirstTime = null;

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


        final Spinner ClassPlace = classappointmentfragment.findViewById(R.id.TutorRegister4_ClassPlace);
        final EditText ClassPlaceDetail = classappointmentfragment.findViewById(R.id.TutorRegister4_ClassPlaceDetail);
        final CheckBox[] WeekButton = new CheckBox[7];
        WeekButton[0] = classappointmentfragment.findViewById(R.id.TutorRegister4_Classweek0);
        WeekButton[1] = classappointmentfragment.findViewById(R.id.TutorRegister4_Classweek1);
        WeekButton[2] = classappointmentfragment.findViewById(R.id.TutorRegister4_Classweek2);
        WeekButton[3] = classappointmentfragment.findViewById(R.id.TutorRegister4_Classweek3);
        WeekButton[4] = classappointmentfragment.findViewById(R.id.TutorRegister4_Classweek4);
        WeekButton[5] = classappointmentfragment.findViewById(R.id.TutorRegister4_Classweek5);
        WeekButton[6] = classappointmentfragment.findViewById(R.id.TutorRegister4_Classweek6);

        final String[] selectWeek = {""};
        final EditText ClassTime = classappointmentfragment.findViewById(R.id.TutorRegister4_ClassTime);
        final CalendarView ClassFirstTimeCal = classappointmentfragment.findViewById(R.id.TutorRegister4_ClassFirstTime);

        Date today = new Date();

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        ClassFirstTime = date.format(today);

        ClassFirstTimeCal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                month += 1;
                ClassFirstTime = year + "-" + month + "-" + dayOfMonth;
                Log.e("날짜", ClassFirstTime);
            }
        });

        previousbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TutorRegisterActivity) getActivity()).switchFragment(3);  // 프래그먼트 교체

            }
        });


        completebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  월요일날 구현하기
                for (int i = 0; i < 7; i++) {
                    if (WeekButton[i].isChecked()) {   // 만약 체크되어있다면
                        selectWeek[0] += WeekButton[i].getText().toString() + "//";
                    }
                }

                String ClassWeek = selectWeek[0];


                if (ClassWeek.equals("") || ClassPlaceDetail.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "빈칸을 채워주세요",
                            Toast.LENGTH_LONG).show();
                    Log.e("빈칸", ClassWeek);
                    Log.e("빈칸", ClassPlaceDetail.getText().toString());
                } else {

                    variableOfClass.setClassPlace(ClassPlace.getSelectedItem().toString());
                    variableOfClass.setClassPlaceDetail(ClassPlaceDetail.getText().toString());
                    variableOfClass.setClassWeek(ClassWeek);  // 임의로 정해둠
                    variableOfClass.setClassTime(ClassTime.getText().toString());
                    // variableOfClass.setClassFirstTime("2011-10-12");
                    variableOfClass.setClassFirstTime(ClassFirstTime);

                    Log.e("날짜2", ClassPlaceDetail.getText().toString());

                    JSONObject postDataParam = new JSONObject();

                    try {
//                        Toast.makeText(RegisterActivity.this, "이메일형식을 입력해 주세요",
//                                Toast.LENGTH_LONG).show();


                        // postDataParam.put("ClassPicture",variableOfClass.getClassPicture());
                        postDataParam.put("ClassName", variableOfClass.getClassName());
                        // postDataParam.put("ClassTutorID", variableOfClass.getClassTutorID());  // 튜터아이디이상
                        postDataParam.put("ClassTutorID", variable.getUserID());  // 튜터아이디이상
                        // postDataParam.put("ClassTuteeID", "0");                      // 튜티이상
                        postDataParam.put("ClassCategory", variableOfClass.getClassCategory());
                        postDataParam.put("ClassTotalPeople", variableOfClass.getClassTotalPeople());
                        //postDataParam.put("ClassCurrentPeople",variableOfClass.getClassCurrentPeople());    // <현인원

                        postDataParam.put("ClassTutorIntro", variableOfClass.getClassTutorIntro());
                        postDataParam.put("ClassIntro", variableOfClass.getClassIntro());
                        postDataParam.put("ClassContents", variableOfClass.getClassContents());
                        postDataParam.put("ClassWhom", variableOfClass.getClassWhom());
                        postDataParam.put("ClassPrice", variableOfClass.getClassPrice());
                        postDataParam.put("ClassHour", variableOfClass.getClassHour());
                        postDataParam.put("ClassNumberOfTime", variableOfClass.getClassNumberOfTime());
                        postDataParam.put("ClassPlace", variableOfClass.getClassPlace());
                        postDataParam.put("ClassPlaceDetail", variableOfClass.getClassPlaceDetail());
                        postDataParam.put("ClassWeek", variableOfClass.getClassWeek());
                        postDataParam.put("ClassTime", variableOfClass.getClassTime());
                        postDataParam.put("ClassFirstTime", variableOfClass.getClassFirstTime());
                        postDataParam.put("userPhoneNumber", variable.getUserPhoneNumber());

                        new TutorRegisterInsertData(getActivity()).execute(postDataParam);


                    } catch (JSONException e) {
                        Log.e(TAG, "JSONEXception");
                    }
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
