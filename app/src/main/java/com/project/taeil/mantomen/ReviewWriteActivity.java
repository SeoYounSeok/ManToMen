package com.project.taeil.mantomen;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.project.taeil.mantomen.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReviewWriteActivity extends AppCompatActivity {
    static Context mContext = null;
    static Activity mActivity;
    final static String TAG = "AndroidNodeJS";
    VariableOfClass variableOfClass;
    Variable variable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_write);
        mActivity = this;
        variableOfClass.getInstance();
        variable.getInstance();

        final EditText ReviewContents = findViewById(R.id.ReviewActivity_Contents);
        Button CancleButton = findViewById(R.id.ReviewActivity_Cancle);
        Button CompleteButton = findViewById(R.id.ReviewActivity_Complete);
        final RadioGroup ScoreRadio = findViewById(R.id.ReviewActivity_ScoreRadio);  //라디오 그룹에서


        CancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // 걍 나가기 액티비티 종료해버리기
            }
        });


        CompleteButton.setOnClickListener(new View.OnClickListener() {  // 완료버튼누르면 전송 리뷰보내기
            @Override
            public void onClick(View v) {

                String Contents = ReviewContents.getText().toString();
                Date date = new Date();
                SimpleDateFormat formatType = new SimpleDateFormat("yyyy-MM-dd");
                String Date = formatType.format(date);

                RadioButton ReviewScore = (RadioButton) findViewById(ScoreRadio.getCheckedRadioButtonId());  //라디오 그룹에서
                String Score = ReviewScore.getText().toString();  //라디오버튼


                // 이거 리뷰보내는거임
                JSONObject postDataParam = new JSONObject();

                try {
                    postDataParam.put("ReviewClassName", variableOfClass.getClassName());
                    postDataParam.put("ReviewuserID", variable.getUserID());
                    postDataParam.put("ReviewContents", Contents);
                    postDataParam.put("ReviewDate", Date);
                    postDataParam.put("ReviewScore", Score);
                } catch (JSONException e) {
                    Log.e(TAG, "JSONEXception");
                }
                new ReviewInsertData(ReviewWriteActivity.this).execute(postDataParam);



                Log.d("리뷰등록",postDataParam.toString());
            }
        });




    }
}
