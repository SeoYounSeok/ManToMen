package com.project.taeil.mantomen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdvPage_3 extends android.support.v4.app.Fragment {
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

        Adv_Picture.setImageResource(R.drawable.adv1);
        Adv_Name.setText("신비한 동물사전");
        Adv_Intro.setText("신비한 동물들을 구하라!");


        return linearLayout;
    }
}