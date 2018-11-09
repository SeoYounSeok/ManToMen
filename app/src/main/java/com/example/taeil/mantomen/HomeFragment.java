package com.example.taeil.mantomen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements AbsListView.OnScrollListener {
    VariableOfClass variableOfClass;
    private boolean lastitemVisibleFlag = false;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        LinearLayout homefragment = (LinearLayout) inflater.inflate(R.layout.fragment_home, container, false); //밑에 주석처리는 리스트뷰 처리 이건 수정요망
        ListView fitlistView = (ListView) homefragment.findViewById(R.id.Fitlistview);

        variableOfClass.getInstance();


        if (variableOfClass.getAllClass() == null) {

        } else {
            List<AllClass> AllClassList = new ArrayList<AllClass>();  //리스트뷰와 어댑터 초기화
            AllClassList = variableOfClass.getAllClass();  //저장된 컬랙션호출

            AllClassListAdapter allClassListAdapter = new AllClassListAdapter(getActivity(), AllClassList);
            fitlistView.setAdapter(allClassListAdapter);  // 어댑터연결


            // 스크롤 리스너 등록
            fitlistView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                    if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastitemVisibleFlag) {
                        // 데이터 로드부분 그러니까 민영이한테 추가적으로 받는 부분을 구현
                    }
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                    lastitemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);

                }
            });
            //리스트뷰를 펼치기위해 높이를 설정한 메서드를 실행한것
            listViewHeightSet(allClassListAdapter, fitlistView);


            private void setData(){

                if(allClassListAdapter.getItem().size() ; != 0){
                    for(int i = 0 ; i < allClassListAdapter.getItems().size() ; i++){
                        arr_listItems.add(listItem.getItems().get(i));
                    }
                }

                this.runOnUiThread(updateUI);
            }

            private Runnable updateUI = new Runnable() {
                public void run() {
                    MainActivity.this.adapter.notifyDataSetChanged();
                }
            };


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

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
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
