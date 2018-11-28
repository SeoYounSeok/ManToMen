package com.project.taeil.mantomen;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.taeil.mantomen.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class HomeFragment extends Fragment implements Main2Activity.OnBackPressedListener {
    final static String TAG = "AndroidNodeJS";
    VariableOfClass variableOfClass;
    AllClassListAdapter allClassListAdapter;
    List<AllClass> AllClassList;  //리스트뷰와 어댑터 초기화
    static String ClassName = "";

    public HomeFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        if(variableOfClass.getAllClass() == null){

        }else{
            allClassListAdapter.upDateItemList(variableOfClass.getAllClass());
            allClassListAdapter.notifyDataSetChanged();
            Log.d("리쥼", "확인");
        }

    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {     // 연습으로 주석처리함
        //Inflate the layout for this fragment

        LinearLayout homefragment = (LinearLayout) inflater.inflate(R.layout.fragment_home, container, false); //밑에 주석처리는 리스트뷰 처리 이건 수정요망
        ListView fitlistView = homefragment.findViewById(R.id.Fitlistview);
        TextView ClassNumber = homefragment.findViewById(R.id.HomeFragment_Number);

        if (variableOfClass.getAllClass() == null) {

        } else {
            // List<AllClass> AllClassList;  //리스트뷰와 어댑터 초기화

            AllClassList = variableOfClass.getAllClass();  //저장된 컬랙션호출
            allClassListAdapter = new AllClassListAdapter(getActivity(), AllClassList);
            fitlistView.setAdapter(allClassListAdapter);  // 어댑터연결
            //allClassListAdapter.notifyDataSetChanged();
            //리스트뷰를 펼치기위해 높이를 설정한 메서드를 실행한것
            listViewHeightSet(allClassListAdapter, fitlistView);
            ClassNumber.setText(variableOfClass.getAllClass().size() + "개 수업");

        }


        fitlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {     // 클래스 상세 정보 요건 나중에 삭제 xx
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Main2Activity.mContext = getActivity();


                JSONObject postDataParam = new JSONObject();
                // variableOfClass.getAllClass().get(position).getClassName();
                try {
                    postDataParam.put("ClassName", variableOfClass.getAllClass().get(position).getClassName());
                    ClassName = variableOfClass.getAllClass().get(position).getClassName();
                } catch (JSONException e) {
                    Log.e(TAG, "JSONEXception");
                }

                new ClassDetailInsertData(getActivity()).execute(postDataParam);

                new GetReviewData(getActivity()).execute();
                Log.e("리뷰", variableOfClass.getAllClass().get(position).getClassName());

                // getActivity().finish();  // 액티비티 삭제
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
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onBack() {

    }
}
