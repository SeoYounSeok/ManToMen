package com.example.taeil.mantomen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    VariableOfClass variableOfClass;



    private OnFragmentInteractionListener mListener;

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
        ListView fitlistView = (ListView) homefragment.findViewById(R.id.Fitlistview) ;

        variableOfClass.getInstance();

        List<AllClass> AllClassList= new ArrayList<AllClass>();  //리스트뷰와 어댑터 초기화

        AllClassList = variableOfClass.getAllClass();  //저장된 컬랙션호출

        fitlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Main2Activity.mContext = getActivity();

                Intent GoToClassDetailintent = new Intent(((Main2Activity)Main2Activity.mContext), ClassDetail.class); // ClassDetail액티비티로 보내는 인텐트
                ((Main2Activity)Main2Activity.mContext).startActivity(GoToClassDetailintent);

                GoToClassDetailintent.putExtra("ClassName", variableOfClass.getAllClass().get(position).getClassName());  //클래스 네임 보내고
                ((Main2Activity)Main2Activity.mContext).overridePendingTransition(0, 0);  //화면전환효과 없애기
                //getActivity().finish();  // 액티비티 삭제


            }
        });


        AllClassListAdapter allClassListAdapter = new AllClassListAdapter(getActivity(), AllClassList);
        fitlistView.setAdapter(allClassListAdapter);
        //리스트뷰를 펼치기위해 높이를 설정한 메서드를 실행한것
        listViewHeightSet(allClassListAdapter, fitlistView);

        return homefragment;
    }

    private void listViewHeightSet(AllClassListAdapter listAdapter, ListView listView){  //리스트뷰를 펼치기위해 높이를 설정한 메서드
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
