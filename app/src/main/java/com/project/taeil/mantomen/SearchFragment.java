package com.project.taeil.mantomen;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.project.taeil.mantomen.R;


public class SearchFragment extends Fragment implements Main2Activity.OnBackPressedListener {

    static String SelectedCategory = null;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout searchfragment = (LinearLayout) inflater.inflate(R.layout.fragment_search, container, false);

        final EditText searchedit = searchfragment.findViewById(R.id.SearchFragment_SearchEdit);
        ImageView searchbutton = searchfragment.findViewById(R.id.SearchFragment_SearchButton);
        final ImageView programing = searchfragment.findViewById(R.id.SearchFragment_Programing);
        ImageView design = searchfragment.findViewById(R.id.SearchFragment_Design);
        ImageView language = searchfragment.findViewById(R.id.SearchFragment_Language);
        ImageView music = searchfragment.findViewById(R.id.SearchFragment_Music);
        ImageView beauty = searchfragment.findViewById(R.id.SearchFragment_Beauty);
        ImageView etc = searchfragment.findViewById(R.id.SearchFragment_ETC);


        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SelectedCategory = searchedit.getText().toString();
                searchedit.setText("");
                new GetCategorySearchData(getActivity()).execute();

            }
        });


        programing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectCategory("programing");

                // 카테고리를 프로그래밍으로 서버에 보내고 서버가 그거에 맞는 걸 보내줌 그거를 액티비티에 홈프래그먼트 처럼 뿌려줌

            }
        });


        design.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCategory("design");
            }
        });
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCategory("language");
            }
        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCategory("music");
            }
        });
        beauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCategory("beauty");
            }
        });
        etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCategory("etc");
            }
        });
        return searchfragment;

    }

    public void selectCategory(String category) {
        SelectedCategory = category;
        new GetCategoryData(getActivity()).execute();    // 카테고리에 맞게 보내줌

    }





    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Other", "onAttach()");
        // ((Main2Activity) context).setOnBackPressedListener(this);
    }


    @Override
    public void onBack() {
        Log.e("Other", "onBack()");
        // 리스너를 설정하기 위해 Activity 를 받아옵니다.
        Main2Activity activity = (Main2Activity) getActivity();
        // 한번 뒤로가기 버튼을 눌렀다면 Listener 를 null 로 해제해줍니다.
        activity.setOnBackPressedListener(null);
        // MainFragment 로 교체
        // ((Main2Activity) getActivity()).switchFragment(0);
        // Activity 에서도 뭔가 처리하고 싶은 내용이 있다면 하단 문장처럼 호출해주면 됩니다.
        // activity.onBackPressed();

    }

}