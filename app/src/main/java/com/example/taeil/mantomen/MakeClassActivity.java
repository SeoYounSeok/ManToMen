package com.example.taeil.mantomen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MakeClassActivity extends AppCompatActivity {


    final static String TAG = "AndroidNodeJS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_class);


        Button Make_OK = (Button) findViewById(R.id.make_ClassOK); //
        Make_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JSONObject postDataParam = new JSONObject();
                JSONArray review = new JSONArray();

                String ClassName = "이민영의 자바교실";
                String ClassTutorID = "이민영";
                String ClassTuteeID = "없음";
                String ClassCategory = "Programing";
                String ClassTotalPeople = "5";
                String ClassCurrentPeople = "0";
                String ClassRPeriod = "2018.10.8";
                String ClassOPeriod = "2018.12.31";
                String ClassIntro = "잘부탁합니다";


                try {
                    review.put(0,"A");
                    review.put(1,"A");
                    review.put(2,"20202");
                    postDataParam.put("ClassName", ClassName);
                    postDataParam.put("ClassTutorID", ClassTutorID);
                    postDataParam.put("ClassTuteeID", ClassTuteeID);
                    postDataParam.put("ClassCategory", ClassCategory);
                    postDataParam.put("ClassTotalPeople", ClassTotalPeople);
                    postDataParam.put("ClassCurrentPeople", ClassCurrentPeople);
                    postDataParam.put("ClassRPeriod", ClassRPeriod);
                    postDataParam.put("ClassOPeriod", ClassOPeriod);
                    postDataParam.put("ClassIntro", ClassIntro);
                    postDataParam.put("ClassReview",review);

                } catch (JSONException e) {
                    Log.e(TAG, "JSONEXception");
                }


                new MakeClassInsertData(MakeClassActivity.this).execute(postDataParam); //나중에 클래스 디테일

            }
        });



        Spinner spinner = (Spinner) findViewById(R.id.make_TotalPeople);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
