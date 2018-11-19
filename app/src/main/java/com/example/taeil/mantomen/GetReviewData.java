package com.example.taeil.mantomen;

import android.app.Activity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by kwanwoo on 2017. 10. 17..
 */

public class GetReviewData extends GetRequest {
    public GetReviewData(Activity activity) {
        super(activity);
    }

    String ClassName = HomeFragment.ClassName;
    VariableOfClass variableOfClass;

    @Override
    protected void onPreExecute() {
        String serverURLStr = Variable.HttpAddres;  //민영이 서버
        String page = HomeFragment.TAG;

        try {
            url = new URL(serverURLStr + "/class/review?value=" + ClassName);  // http://serverURLStr/get-data
            Log.d("디테일", ClassName);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(String jsonString) {
        variableOfClass = VariableOfClass.getInstance();
        Log.d("리뷰", "겟데이터 온포스트익시큩 시작부분");

        if (jsonString == null) //받아온값이 없으면 리턴
            return;

        else if(jsonString.trim().equals("0")){
            return;
        }

        else{
            ArrayList<AllReview> arrayList = getArrayListFromJSONString(jsonString); //전체를 저장해야하니까
            variableOfClass.setAllReview(arrayList);  //


        }

    }

    protected boolean True() {
        return true;
    }

    protected ArrayList<AllReview> getArrayListFromJSONString(String jsonString) {  //올클래스 어레이리스트 만들기
        ArrayList<AllReview> output = new ArrayList();  //AllClass형태의 어레이리스트생성
        try {

            Log.d("리뷰", "겟데이터에 메소드부분");
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                AllReview allReview =
                        new AllReview(jsonObject.getString("ReviewClassName"),   //이건 결국 클래스 하나의 정보밖에 받지 못함
                                jsonObject.getString("ReviewuserID"),
                                jsonObject.getString("ReviewContents"),
                                jsonObject.getString("ReviewDate"),
                                jsonObject.getString("ReviewScore")
                                // 제일중요
                                //리뷰는 잠깐 뺐음 json오브젝트라 이거 전역으로 뺄까??
                        );


                Log.d("알라", "실행되네 ㅇㅇ");
                output.add(allReview); // 어레이 길이만큼 반복되니까 여기에 저장된다는거아녀 허허
            }

        } catch (JSONException e) {
            Log.e(TAG, "Exception in processing JSONString.", e);
            e.printStackTrace();
        }
        return output;
    }
}
