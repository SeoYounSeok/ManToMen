package com.example.taeil.mantomen;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment{
    final static String TAG = "AndroidNodeJS";
    VariableOfClass variableOfClass;
    static AllClassListAdapter allClassListAdapter;
    List<AllClass> AllClassList;  //리스트뷰와 어댑터 초기화



    public HomeFragment() {
        // Required empty public constructor


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {     // 연습으로 주석처리함
        //Inflate the layout for this fragment

        variableOfClass.getInstance();
        LinearLayout homefragment = (LinearLayout) inflater.inflate(R.layout.fragment_home, container, false); //밑에 주석처리는 리스트뷰 처리 이건 수정요망
        ListView fitlistView = homefragment.findViewById(R.id.Fitlistview);


        if (variableOfClass.getAllClass() == null) {

        } else {
           // List<AllClass> AllClassList;  //리스트뷰와 어댑터 초기화

            AllClassList = variableOfClass.getAllClass();  //저장된 컬랙션호출
            allClassListAdapter = new AllClassListAdapter(getActivity(), AllClassList);
            fitlistView.setAdapter(allClassListAdapter);  // 어댑터연결
            allClassListAdapter.notifyDataSetChanged();
            //리스트뷰를 펼치기위해 높이를 설정한 메서드를 실행한것
            listViewHeightSet(allClassListAdapter, fitlistView);

        }





        fitlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {     // 클래스 상세 정보 요건 나중에 삭제 xx
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Main2Activity.mContext = getActivity();

                Intent GoToClassDetailintent = new Intent(((Main2Activity) Main2Activity.mContext), ClassDetail.class); // ClassDetail액티비티로 보내는 인텐트
                ((Main2Activity) Main2Activity.mContext).startActivity(GoToClassDetailintent);


                GoToClassDetailintent.putExtra("ClassName", variableOfClass.getAllClass().get(position).getClassName());  //클래스 네임 보내고
                ((Main2Activity) Main2Activity.mContext).overridePendingTransition(0, 0);  //화면전환효과 없애기
                //getActivity().finish();  // 액티비티 삭제
            }
        });

        return homefragment;
    }


    private void listViewHeightSet(AllClassListAdapter listAdapter, ListView listView) {  //리스트뷰를 펼치기위해 높이를 설정한 메서드
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


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }



    public interface OnFragmentInteractionListener {
    }

}
