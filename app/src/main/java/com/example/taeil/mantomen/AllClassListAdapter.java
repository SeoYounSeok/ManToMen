package com.example.taeil.mantomen;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class AllClassListAdapter extends BaseAdapter{

    private Context context;
    private List<AllClass> AllClass;

    public AllClassListAdapter(Context context, List<AllClass> AllClass){
        this.context = context;
        this.AllClass = AllClass;
    }

    @Override
    public int getCount() {
        return AllClass.size();
    }

    @Override
    public Object getItem(int i) {
        return AllClass.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(context, R.layout.fitlist,null);

        // 클래스 확립될 동안 리스트뷰를 잠시꺼둠
//
//        TextView ClassName= (TextView) v.findViewById(R.id.Home_ClassName);
//        TextView ClassTutorID = (TextView) v.findViewById(R.id.Home_ClassTutorID);
//        // ClassTuteeID 튜티들
//        TextView ClassTotalPeople = (TextView) v.findViewById(R.id.Home_ClassTotalPeople);  //클래스 수강인원
//        // ClassCurrentPeople 현재원
//        TextView ClassRPeriod = (TextView) v.findViewById(R.id.Home_ClassRPeriod);  //신청기간
//        // ClassOPeriod 강의기간
//        RatingBar ClassScore = (RatingBar) v.findViewById(R.id.ClassScore); //임의로 정해둬 지금은
//        TextView ClassIntro = (TextView) v.findViewById(R.id.Home_ClassIntro);
//
//        //ImageView ClassPhoto= (ImageView) v.findViewById(R.id.ClassImg);  //사진은 아직 안넣음
//        //ImageView MentorPhoto= (ImageView) v.findViewById(R.id.Mentor_Photo);
//
//
//        ClassName.setText(AllClass.get(i).getClassName());
//        ClassTutorID.setText(AllClass.get(i).getClassTutorID());
//        ClassTotalPeople.setText(AllClass.get(i).getClassTotalPeople());
//        // 현재원필요
//        ClassRPeriod.setText(AllClass.get(i).getClassRPeriod());
//        // 강의기간 필요
//        // ClassScore.setRating(AllClass.get(i).getClassScore());
//        ClassScore.setRating(5);  //5점으로고정 지금 없으니까
//        ClassIntro.setText(AllClass.get(i).getClassIntro());
//
////        ClassPhoto.setImageResource(AllClass.get(i).getClassPhoto());  //사진없으니까 뺌
////        MentorPhoto.setImageResource(AllClass.get(i).getMentorPhoto());
//
//        v.setTag(AllClass.get(i).getClassName());


        return v;
    }
}
