package com.project.taeil.mantomen;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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

public class MypagePostRequest extends AsyncTask<JSONObject, Void, String> {
    Activity activity;
    URL url;
    Variable variable;


    public MypagePostRequest(Activity activity) {
        this.activity = activity;
    }


    @Override
    protected String doInBackground(JSONObject... postDataParams) {

        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();


            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(10000 /* milliseconds */);
            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송
//            conn.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음
            String cookieString = variable.getCookies();   // 헤더에 로그인 토큰값 첨가
            if (cookieString != null) {
                conn.setRequestProperty("cookie", cookieString);
            }
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
                    SbExtraction(sb); // 스트링버퍼를 추출해서 세팅해줌
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

        variable = Variable.getInstance();
        super.onPostExecute(result);
        String temp;
        String message1 = "회원정보수정실패!";
        String message2 = "회원정보수정성공!";
        temp = result.trim();

//
//        if (temp == null || temp.equals("0")) {
//            Toast.makeText(activity, message1,
//                    Toast.LENGTH_LONG).show();
//            return;
//        } else if (temp.equals("1")) {
//            Toast.makeText(activity, message2,
//                    Toast.LENGTH_LONG).show();
//
//            MemberModifyActivity.mContext = activity;
//
////            String userID = variable.getUserID();
////            String userPassword = variable.getUserPassword();
//            Intent GoToLoginintent = new Intent((MemberModifyActivity.mContext), LoginActivity.class); //메인액티비티로 보내는 인텐트
//            ((MemberModifyActivity) MemberModifyActivity.mContext).startActivity(GoToLoginintent);
//            // GoToLoginintent.putExtra("userID", userID);
//            ((MemberModifyActivity) MemberModifyActivity.mContext).overridePendingTransition(0, 0);  //화면전환효과 없애기
//
//
//        }

    }

    private String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;
        variable = Variable.getInstance();

        Iterator<String> itr = params.keys();


        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);

            if (key.equals("userPicture"))
                variable.setUserPicture(value.toString());
            if (key.equals("userID"))
                variable.setUserID(value.toString());
            if (key.equals("userPassword"))
                variable.setUserPassword(value.toString());


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
//            JSONArray jsonArray = new JSONArray(SB);
//            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            JSONObject jsonObject = new JSONObject(SB);

            variable.setUserPicture(jsonObject.getString("userPicture"));
            variable.setUserID(jsonObject.getString("userID"));
            variable.setUserPassword(jsonObject.getString("userPassword"));
            variable.setUserEmail(jsonObject.getString("userEmail"));
            variable.setUserName(jsonObject.getString("userName"));
            variable.setUserAge(jsonObject.getString("userAge"));
            variable.setUserGender(jsonObject.getString("userGender"));
            variable.setUserCategory(jsonObject.getString("userCategory"));
            variable.setUserIdentity(jsonObject.getString("userIdentity"));
            variable.setUserParticipateClass(jsonObject.getString("userParticipateClass"));
            variable.setUserOperateClass(jsonObject.getString("userOperateClass"));
            variable.setUserPhoneNumber(jsonObject.getString("userPhoneNumber"));

            variable.setUserPoint(Integer.parseInt(jsonObject.getString("userPoint")));  // 포인트 받는 부분
            Log.e("포인트확인2", Integer.toString(variable.getUserPoint()));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
