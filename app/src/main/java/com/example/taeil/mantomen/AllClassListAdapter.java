package com.example.taeil.mantomen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class AllClassListAdapter extends BaseAdapter{

    private LayoutInflater inflate;
    private RecyclerView.ViewHolder viewHolder;
    private Context context;
    private List<AllClass> AllClass;
    private int count;

    public AllClassListAdapter(Context context, List<AllClass> AllClass){
        // 메인에서 데이터 리스트를 넘겨받음
        notifyDataSetChanged();
        this.context = context;
        this.count = AllClass.size();
        this.AllClass = AllClass;
    }

    @Override
    public int getCount() {
        return count;
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

        TextView ClassName= (TextView) v.findViewById(R.id.Home_ClassName);
        TextView ClassTutorID = (TextView) v.findViewById(R.id.Home_ClassTutorID);
        // ClassTuteeID 튜티들
        TextView ClassTotalPeople = (TextView) v.findViewById(R.id.Home_ClassTotalPeople);  //클래스 수강인원
        // ClassCurrentPeople 현재원
        TextView ClassRPeriod = (TextView) v.findViewById(R.id.Home_ClassRPeriod);  //신청기간
        // ClassOPeriod 강의기간
        RatingBar ClassScore = (RatingBar) v.findViewById(R.id.ClassScore); //임의로 정해둬 지금은
        TextView ClassIntro = (TextView) v.findViewById(R.id.Home_ClassIntro);

        //ImageView ClassPhoto= (ImageView) v.findViewById(R.id.ClassImg);  //사진은 아직 안넣음
        //ImageView MentorPhoto= (ImageView) v.findViewById(R.id.Mentor_Photo);


        ClassName.setText(AllClass.get(i).getClassName());
        ClassTutorID.setText(AllClass.get(i).getClassTutorID());
        ClassTotalPeople.setText(AllClass.get(i).getClassTotalPeople());
        // 현재원필요
        ClassRPeriod.setText(AllClass.get(i).getClassFirstTime()+"까지");   // 모집기간인데 퍼스트 수업 날 전까지로하자 !!!!
        // 강의기간 필요
        // ClassScore.setRating(AllClass.get(i).getClassScore());
        ClassScore.setRating(5);  //5점으로고정 지금 없으니까
        ClassIntro.setText(AllClass.get(i).getClassIntro());

//        ClassPhoto.setImageResource(AllClass.get(i).getClassPhoto());  //사진없으니까 뺌
//        MentorPhoto.setImageResource(AllClass.get(i).getMentorPhoto());

        v.setTag(AllClass.get(i).getClassName());


        return v;
    }



}
