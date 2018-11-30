package com.project.taeil.mantomen;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class ClassDetailPostRequest extends AsyncTask<JSONObject, Void, String> {
    Activity activity;
    URL url;
    VariableOfClass variableOfClass;
    Variable variable;

    public ClassDetailPostRequest(Activity activity) {
        this.activity = activity;
    }


    @Override
    protected String doInBackground(JSONObject... postDataParams) {

        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(10000 /* milliseconds */);
            conn.setRequestMethod("POST");
            String cookieString = variable.getCookies();   // 헤더에 로그인 토큰값 첨가
            if (cookieString != null) {
                conn.setRequestProperty("cookie", cookieString);
            }
//            conn.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송
//            conn.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //서버로 보내기위해서 스트림 만듬
            OutputStream os = conn.getOutputStream();
            //버퍼를 생성하고 넣음
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            String str = getPostDataString(postDataParams[0]);

            Log.e("params", "Post String = " + str);

            writer.write(str);

            writer.flush();
            writer.close(); //버퍼를 받아줌
            os.close();

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                //서버로 부터 데이터를 받음
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                in.close();
                if (!sb.toString().trim().equals("0"))
                    SbExtraction(sb); // 스트링버퍼를 추출해서
                Log.d("확인2", sb.toString());

                return sb.toString(); //서버로 부터 받은 값을 리턴해줌 아마 OK!!가 들어올것임

            } else {
                return new String("Server Error : " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //Log.d("파람스3", result);
        String temp;
        temp = result.trim();
//        Log.d("오류", result);

        Log.d("디테일", temp);

        if (temp == null || temp.equals("0")) {  // 에러났을때
            Toast.makeText(activity, "서버에러발생",
                    Toast.LENGTH_LONG).show();

            return;

        } else {
//            Toast.makeText(activity, result,
//                    Toast.LENGTH_LONG).show();
            Intent GoToDetailintent = new Intent(((Main2Activity) Main2Activity.mContext), ClassDetail.class); //
            ((Main2Activity) Main2Activity.mContext).startActivity(GoToDetailintent);
        }

    }

    private String getPostDataString(JSONObject params) throws Exception {

        variableOfClass.getInstance();
        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();


        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

    private void SbExtraction(StringBuffer sb) {

        String SB = sb.toString(); //일단 String버퍼를 스트링 형식으로 변형

        try {
            JSONArray jsonArray = new JSONArray(SB);
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            //JSONObject jsonObject = new JSONObject(SB);
            Log.e("로그6",jsonObject.toString());

            variableOfClass.setClassPicture(jsonObject.getString("ClassPicture"));
            variableOfClass.setClassName(jsonObject.getString("ClassName"));
            variableOfClass.setClassTutorID(jsonObject.getString("ClassTutorID"));
            variableOfClass.setClassTuteeID(jsonObject.getString("ClassTuteeID"));
            variableOfClass.setClassCategory(jsonObject.getString("ClassCategory"));
            variableOfClass.setClassTotalPeople(jsonObject.getString("ClassTotalPeople"));
            variableOfClass.setClassCurrentPeople(jsonObject.getString("ClassCurrentPeople"));
            variableOfClass.setClassTutorIntro(jsonObject.getString("ClassTutorIntro"));
            variableOfClass.setClassIntro(jsonObject.getString("ClassIntro"));
            variableOfClass.setClassContents(jsonObject.getString("ClassContents"));
            variableOfClass.setClassWhom(jsonObject.getString("ClassWhom"));
            variableOfClass.setClassPrice(jsonObject.getString("ClassPrice"));
            variableOfClass.setClassHour(jsonObject.getString("ClassHour"));
            variableOfClass.setClassNumberOfTime(jsonObject.getString("ClassNumberOfTime"));
            variableOfClass.setClassPlace(jsonObject.getString("ClassPlace"));
            variableOfClass.setClassPlaceDetail(jsonObject.getString("ClassPlaceDetail"));
            variableOfClass.setClassWeek(jsonObject.getString("ClassWeek"));
            variableOfClass.setClassTime(jsonObject.getString("ClassTime"));
            variableOfClass.setClassFirstTime(jsonObject.getString("ClassFirstTime"));
            variableOfClass.setClassIdentity(jsonObject.getString("ClassIdentity"));
            variableOfClass.setClassScore(jsonObject.getString("ClassScore"));
            Log.e("추출", variableOfClass.getClassCategory());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("추출", variableOfClass.getClassPicture());
        Log.e("추출", variableOfClass.getClassName());
        Log.e("추출", variableOfClass.getClassTutorID());
        Log.e("추출", variableOfClass.getClassTuteeID());
        Log.e("추출", variableOfClass.getClassCategory());
        Log.e("추출", variableOfClass.getClassTotalPeople());
        Log.e("추출", variableOfClass.getClassCurrentPeople());
        Log.e("추출", variableOfClass.getClassTutorIntro());
        Log.e("추출", variableOfClass.getClassIntro());
        Log.e("추출", variableOfClass.getClassContents());
        Log.e("추출", variableOfClass.getClassWhom());
        Log.e("추출", variableOfClass.getClassPrice());
        Log.e("추출", variableOfClass.getClassHour());
        Log.e("추출", variableOfClass.getClassNumberOfTime());
        Log.e("추출", variableOfClass.getClassPlace());
        Log.e("추출", variableOfClass.getClassPlaceDetail());
        Log.e("추출", variableOfClass.getClassWeek());
        Log.e("추출", variableOfClass.getClassTime());
        Log.e("추출", variableOfClass.getClassFirstTime());
        Log.e("추출", variableOfClass.getClassIdentity());
        Log.e("추출", variableOfClass.getClassScore());
    }

}
