package com.project.taeil.mantomen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    final static String TAG = "AndroidNodeJS";
    VariableOfClass variableOfClass;
    AllClassListAdapter allClassListAdapter;
    List<AllClass> AllClassList;  //리스트뷰와 어댑터 초기화
    static String ClassName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ListView fitlistView2 = findViewById(R.id.Fitlistview2);   // 핏뷰2
       // TextView value = findViewById(R.id.CategroyActivity_value);   // 검색했던 거
        //value.setText(SearchFragment.SelectedCategory);

        if (variableOfClass.getAllClass().size() == 0) {

            //value.setText(SearchFragment.SelectedCategory + "에 대한 검색 결과가 없습니다.");

        } else {
            // List<AllClass> AllClassList;  //리스트뷰와 어댑터 초기화

            AllClassList = variableOfClass.getAllClass();  //저장된 컬랙션호출
            allClassListAdapter = new AllClassListAdapter(CategoryActivity.this, AllClassList);
            fitlistView2.setAdapter(allClassListAdapter);  // 어댑터연결
            // allClassListAdapter.notifyDataSetChanged();
            // 리스트뷰를 펼치기위해 높이를 설정한 메서드를 실행한것
            // listViewHeightSet(allClassListAdapter, fitlistView);

        }


        fitlistView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {     // 클래스 상세 정보 요건 나중에 삭제 xx
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                JSONObject postDataParam = new JSONObject();
                // variableOfClass.getAllClass().get(position).getClassName();
                try {
                    postDataParam.put("ClassName", variableOfClass.getAllClass().get(position).getClassName());
                    ClassName = variableOfClass.getAllClass().get(position).getClassName();
                } catch (JSONException e) {
                    Log.e(TAG, "JSONEXception");
                }

                new ClassDetailInsertData(CategoryActivity.this).execute(postDataParam);
                new GetReviewData(CategoryActivity.this).execute();


                Log.e("리뷰", variableOfClass.getAllClass().get(position).getClassName());

                // getActivity().finish();  // 액티비티 삭제
            }
        });

    }
}
