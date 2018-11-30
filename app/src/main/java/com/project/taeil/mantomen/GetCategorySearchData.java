package com.project.taeil.mantomen;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by kwanwoo on 2017. 10. 17..
 */

public class GetCategorySearchData extends GetRequest {
    public GetCategorySearchData(Activity activity) {
        super(activity);
    }

    VariableOfClass variableOfClass;
    String Category = SearchFragment.SelectedCategory;

    @Override
    protected void onPreExecute() {
        String serverURLStr = Variable.HttpAddres;  //민영이 서버
        String page = HomeFragment.TAG;
        try {
            url = new URL(serverURLStr + "/class/search?value=" + Category);  // http://serverURLStr/get-data
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(String jsonString) {
        variableOfClass = VariableOfClass.getInstance();
        Log.d("리뷰", "겟데이터 온포스트익시큩 시작부분");

        if(jsonString.trim().equals("0")){  // 검색결과없음
            Toast.makeText(activity, "검색된 결과가 없습니다.",
                    Toast.LENGTH_LONG).show();

            return;
        }

        else{  //
            ArrayList<AllClass> arrayList = getArrayListFromJSONString(jsonString); //전체를 저장해야하니까
            variableOfClass.setAllClass(arrayList);  //
            // Log.d("어레이", arrayList.get(0).getClassCategory());
            Intent gotoCategory = new Intent(Main2Activity.mActivity, CategoryActivity.class);
            Main2Activity.mActivity.startActivity(gotoCategory);

        }


        //어레이리스트 각각 칸에는 클래스에대한 정보가 들어있음
        //어레이리스튼데 이걸 지금 list뷰에 뿌렸단말이지 관우형이

//        ArrayAdapter adapter = new ArrayAdapter(activity,
//                android.R.layout.simple_list_item_1,
//                arrayList.toArray());
//        ListView txtList = activity.findViewById(R.id.txtList);
//        txtList.setAdapter(adapter);


    }

    protected boolean True() {
        return true;
    }

    protected ArrayList<AllClass> getArrayListFromJSONString(String jsonString) {  //올클래스 어레이리스트 만들기
        ArrayList<AllClass> output = new ArrayList();  //AllClass형태의 어레이리스트생성
        try {

            Log.d("리뷰", "겟데이터에 메소드부분");
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                AllClass allClass =
                        new AllClass(
                                jsonObject.getString("ClassPicture"),   //이건 결국 클래스 하나의 정보밖에 받지 못함
                                jsonObject.getString("ClassName"),
                                jsonObject.getString("ClassTutorID"),
                                jsonObject.getString("ClassTuteeID"),
                                jsonObject.getString("ClassCategory"),
                                jsonObject.getString("ClassTotalPeople"),
                                jsonObject.getString("ClassCurrentPeople"),
                                jsonObject.getString("ClassTutorIntro"),
                                jsonObject.getString("ClassIntro"),
                                jsonObject.getString("ClassContents"),
                                jsonObject.getString("ClassWhom"),
                                jsonObject.getString("ClassPrice"),
                                jsonObject.getString("ClassHour"),
                                jsonObject.getString("ClassNumberOfTime"),
                                jsonObject.getString("ClassPlace"),
                                jsonObject.getString("ClassPlaceDetail"),
                                jsonObject.getString("ClassWeek"),
                                jsonObject.getString("ClassTime"),
                                jsonObject.getString("ClassFirstTime"),
                                jsonObject.getString("ClassIdentity"),
                                jsonObject.getString("ClassScore")
                                // 제일중요
                                //리뷰는 잠깐 뺐음 json오브젝트라 이거 전역으로 뺄까??
                        );
                Log.e( "장난?", jsonObject.getString("ClassPicture"));
                Log.e( "알라3", allClass.getClassPicture());
                Log.e( "알라3", allClass.getClassName());
                Log.e( "알라3", allClass.getClassTutorID());
                Log.e( "알라3", allClass.getClassTuteeID());
                Log.e( "알라3", allClass.getClassCategory());
                Log.e( "알라3", allClass.getClassTotalPeople());
                Log.e( "알라3", allClass.getClassCurrentPeople());
                Log.e( "알라3", allClass.getClassTutorIntro());
                Log.e( "알라3", allClass.getClassIntro());
                Log.e( "알라3", allClass.getClassContents());
                Log.e( "알라3", allClass.getClassWhom());
                Log.e( "알라3", allClass.getClassPrice());
                Log.e( "알라3", allClass.getClassHour());
                Log.e( "알라3", allClass.getClassNumberOfTime());
                Log.e( "알라3", allClass.getClassPlace());
                Log.e( "알라3", allClass.getClassPlaceDetail());
                Log.e( "알라3", allClass.getClassWeek());
                Log.e( "알라3", allClass.getClassTime());
                Log.e( "알라3", allClass.getClassFirstTime());
                Log.e( "알라3", allClass.getClassIdentity());
                Log.e( "알라3", allClass.getClassScore());
                Log.d("알라3", "실행되네 ㅇㅇ");
                output.add(allClass); // 어레이 길이만큼 반복되니까 여기에 저장된다는거아녀 허허
            }

        } catch (JSONException e) {
            Log.e(TAG, "Exception in processing JSONString.", e);
            e.printStackTrace();
        }
        return output;
    }
}
