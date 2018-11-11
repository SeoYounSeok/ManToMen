package com.example.taeil.mantomen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    VariableOfClass variableOfClass;
    private boolean lastItemVisibleFlag = false;// 리스트 스크롤이 마지막 셀(맨 바닥)로 이동했는지 체크할 변수
    private ProgressBar progressBar;                // 데이터 로딩중을 표시할 프로그레스바
    private boolean mLockListView = false;          // 데이터 불러올때 중복안되게 하기위한 변수

    private int page = 0;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {     // 연습으로 주석처리함
        //Inflate the layout for this fragment

        variableOfClass.getInstance();
        LinearLayout homefragment = (LinearLayout) inflater.inflate(R.layout.fragment_home, container, false); //밑에 주석처리는 리스트뷰 처리 이건 수정요망
        ListView fitlistView = homefragment.findViewById(R.id.Fitlistview);

        progressBar = homefragment.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);


        if (variableOfClass.getAllClass() == null) {

        } else {
            List<AllClass> AllClassList;  //리스트뷰와 어댑터 초기화
            AllClassList = variableOfClass.getAllClass();  //저장된 컬랙션호출

            final AllClassListAdapter allClassListAdapter = new AllClassListAdapter(getActivity(), AllClassList);
            fitlistView.setAdapter(allClassListAdapter);  // 어댑터연결

            fitlistView.setOnScrollListener(new AbsListView.OnScrollListener() {  // 스크롤 리스너
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    // 1. OnScrollListener.SCROLL_STATE_IDLE : 스크롤이 이동하지 않을때의 이벤트(즉 스크롤이 멈추었을때).
                    // 2. lastItemVisibleFlag : 리스트뷰의 마지막 셀의 끝에 스크롤이 이동했을때.
                    // 3. mLockListView == false : 데이터 리스트에 다음 데이터를 불러오는 작업이 끝났을때.
                    // 1, 2, 3 모두가 true일때 다음 데이터를 불러온다.
                    if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastItemVisibleFlag && mLockListView == false) {
                        // 화면이 바닦에 닿을때 처리
                        // 로딩중을 알리는 프로그레스바를 보인다.
                        progressBar.setVisibility(View.VISIBLE);

                        // 다음 데이터를 불러온다.
                        new GetDatamore(getActivity());  // 추가로 요구
                        // 1초 뒤 프로그레스바를 감추고 데이터를 갱신하고, 중복 로딩 체크하는 Lock을 했던 mLockListView변수를 풀어준다.
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                page++;
                                allClassListAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                mLockListView = false;
                            }
                        }, 1000);

                    }
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    // firstVisibleItem : 화면에 보이는 첫번째 리스트의 아이템 번호.
                    // visibleItemCount : 화면에 보이는 리스트 아이템의 갯수
                    // totalItemCount : 리스트 전체의 총 갯수
                    // 리스트의 갯수가 0개 이상이고, 화면에 보이는 맨 하단까지의 아이템 갯수가 총 갯수보다 크거나 같을때.. 즉 리스트의 끝일때. true
                    lastItemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
                }
            });


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



    public interface OnFragmentInteractionListener {
    }
}
