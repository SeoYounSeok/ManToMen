package com.project.taeil.mantomen;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdvPage_1 extends android.support.v4.app.Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout linearLayout=(LinearLayout)inflater.inflate(R.layout.viewpagerlayout,container,false);
        // LinearLayout background=(LinearLayout)linearLayout.findViewById(R.id.background);
        ImageView Adv_Picture = linearLayout.findViewById(R.id.Adv_Picture);
        TextView Adv_Name = linearLayout.findViewById(R.id.Adv_Name);
        TextView Adv_Intro = linearLayout.findViewById(R.id.Adv_Intro);


        Adv_Picture.setImageResource(R.drawable.adv1);
        Adv_Name.setText("밀정");
        Adv_Intro.setText("적은 늘 우리 안에 있었다.");

        return linearLayout;
    }
}