package com.project.taeil.mantomen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.project.taeil.mantomen.R;

import org.w3c.dom.Text;

import java.io.InputStream;


public class ClassIntroFragment extends Fragment {

    VariableOfClass variableOfClass;

    public ClassIntroFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        variableOfClass.getInstance();
        LinearLayout classIntro = (LinearLayout) inflater.inflate(R.layout.fragment_class_intro, container, false); //밑에 주석처리는 리스트뷰 처리 이건 수정요망
        TextView ClassPlace = classIntro.findViewById(R.id.ClassIntro_ClassPlace);
        TextView ClassPlaceDetail = classIntro.findViewById(R.id.ClassIntro_ClassPlaceDetail);
        TextView ClassNumberOfTime = classIntro.findViewById(R.id.ClassIntro_ClassNumberOfTime);
        TextView ClassHour = classIntro.findViewById(R.id.ClassIntro_ClassHour);
        TextView ClassWeek = classIntro.findViewById(R.id.ClassIntro_ClassWeek);
        TextView ClassTotalPeople = classIntro.findViewById(R.id.ClassIntro_ClassTotalPeople);
        TextView ClassIntro = classIntro.findViewById(R.id.ClassIntro_ClassIntro);
        TextView ClassWhom = classIntro.findViewById(R.id.ClassIntro_ClassWhom);
        TextView ClassTutorName = classIntro.findViewById(R.id.mypage_ClassTutorName);
        TextView ClassFirstTime = classIntro.findViewById(R.id.ClassIntro_ClassFirstTime);
        TextView ClassNumberOfTime2 = classIntro.findViewById(R.id.ClassIntro_ClassNumberOfTime2);
        TextView ClassHour2 = classIntro.findViewById(R.id.ClassIntro_ClassHour2);
        TextView ClassTutorIntro = classIntro.findViewById(R.id.mypage_ClassTutorIntro);
        TextView ClassPrice2 = classIntro.findViewById(R.id.ClassIntro_ClassPrice2);
        TextView ClassContents = classIntro.findViewById(R.id.ClassIntro_ClassContents);





        ImageView ClassPicture = classIntro.findViewById(R.id.ClassIntro_ClassPicture);


        ClassPlace.setText(variableOfClass.getClassPlace());
        ClassPlaceDetail.setText(variableOfClass.getClassPlaceDetail());
        ClassNumberOfTime.setText(variableOfClass.getClassNumberOfTime() + "회");
        ClassHour.setText(variableOfClass.getClassHour() + "시간");
        ClassWeek.setText(variableOfClass.getClassWeek() + "요일");
        ClassTotalPeople.setText(variableOfClass.getClassTotalPeople() + "명 모집 중");
        ClassIntro.setText(variableOfClass.getClassIntro());
        ClassWhom.setText(variableOfClass.getClassWhom());
        ClassFirstTime.setText(variableOfClass.getClassFirstTime() + "까지");
        ClassTutorName.setText(variableOfClass.getClassTutorID());
        ClassNumberOfTime2.setText(variableOfClass.getClassNumberOfTime() + "회");
        ClassHour2.setText(variableOfClass.getClassHour() + "시간");
        ClassTutorIntro.setText(variableOfClass.getClassTutorIntro());
        ClassPrice2.setText(variableOfClass.getClassPrice() + "원");
        ClassContents.setText(variableOfClass.getClassContents());


        Log.d("튜터인트로",variableOfClass.getClassTutorIntro());


        return classIntro;
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