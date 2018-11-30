package com.project.taeil.mantomen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdvPage_2 extends android.support.v4.app.Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout linearLayout=(LinearLayout)inflater.inflate(R.layout.viewpagerlayout,container,false);

        ImageView Adv_Picture = linearLayout.findViewById(R.id.Adv_Picture);
        TextView Adv_Name = linearLayout.findViewById(R.id.Adv_Name);
        TextView Adv_Intro = linearLayout.findViewById(R.id.Adv_Intro);


        Adv_Picture.setImageResource(R.drawable.adv2);
        Adv_Name.setText("독전");
        Adv_Intro.setText("독한 자들의 전쟁이 시작된다.");



        return linearLayout;
    }
}