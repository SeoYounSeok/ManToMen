package com.example.taeil.mantomen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class ReviewFragment extends Fragment {

    final static String TAG = "AndroidNodeJS";
    VariableOfClass variableOfClass;
    AllReviewListAdapter allReviewListAdapter;
    List<AllReview> AllReviewList;  //리스트뷰와 어댑터 초기화

    public ReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        variableOfClass.getInstance();
        LinearLayout reviewfragment = (LinearLayout) inflater.inflate(R.layout.fragment_review, container, false);
        ListView reviewlistview = reviewfragment.findViewById(R.id.reviewlist);
        Button reviewwrite = reviewfragment.findViewById(R.id.ReviewFragment_ReviewWrite);  // 리뷰작성 버튼

        reviewwrite.setOnClickListener(new View.OnClickListener() {  // 리뷰작성하는 액티비티로 이동하게하자
            @Override
            public void onClick(View v) {

                
            }
        });

        // List<AllClass> AllClassList;  //리스트뷰와 어댑터 초기화

        AllReviewList = variableOfClass.getAllReview();  //저장된 컬랙션호출
        allReviewListAdapter = new AllReviewListAdapter(getActivity(), AllReviewList);
        reviewlistview.setAdapter(allReviewListAdapter);  // 어댑터연결
        allReviewListAdapter.notifyDataSetChanged();
        //리스트뷰를 펼치기위해 높이를 설정한 메서드를 실행한것
        listViewHeightSet(allReviewListAdapter, reviewlistview);


        return reviewfragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }



    private void listViewHeightSet(AllReviewListAdapter listAdapter, ListView listView) {  //리스트뷰를 펼치기위해 높이를 설정한 메서드
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        //Log.d("높이2", String.valueOf(totalHeight));
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        //Log.d("높이3", String.valueOf(params.height));


    }



}
