package com.example.taeil.mantomen;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class ClassInfoListAdapter extends BaseAdapter{

    private Context context;
    private List<ClassInfo> ClassInfoList;

    public ClassInfoListAdapter(Context context, List<ClassInfo> ClassInfoList){
        this.context = context;
        this.ClassInfoList = ClassInfoList;
    }

    @Override
    public int getCount() {
        return ClassInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return ClassInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.fitlist,null);
        TextView ClassName = (TextView) v.findViewById(R.id.Home_ClassName);
        TextView ClassTutorID = (TextView) v.findViewById(R.id.Home_ClassTutorID);
        TextView ClassIntro = (TextView) v.findViewById(R.id.Home_ClassIntro);
        TextView ClassTotalPeople = (TextView) v.findViewById(R.id.Home_ClassTotalPeople);
        TextView ClassRPeriod = (TextView) v.findViewById(R.id.Home_ClassRPeriod);

        RatingBar ClassScore = (RatingBar) v.findViewById(R.id.ClassScore);
        ImageView ClassPhoto= (ImageView) v.findViewById(R.id.ClassImg);
        ImageView MentorPhoto= (ImageView) v.findViewById(R.id.Mentor_Photo);


        ClassName.setText(ClassInfoList.get(i).getClassName());
        ClassTutorID.setText(ClassInfoList.get(i).getMentorName());
        ClassIntro.setText(ClassInfoList.get(i).getMentor_word());
        ClassTotalPeople.setText(ClassInfoList.get(i).getStudentNum());
        ClassRPeriod.setText(ClassInfoList.get(i).getClassTerm());
        ClassScore.setRating(ClassInfoList.get(i).getClassScore());
        ClassPhoto.setImageResource(ClassInfoList.get(i).getClassPhoto());
        MentorPhoto.setImageResource(ClassInfoList.get(i).getMentorPhoto());

        v.setTag(ClassInfoList.get(i).getClassName());

        return v;
    }
}
