package com.example.taeil.mantomen;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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

public class ClassApplyPostRequest extends AsyncTask<JSONObject, Void, String> {
    Activity activity;
    URL url;
    Variable variable;
    VariableOfClass variableOfClass;
    public ClassApplyPostRequest(Activity activity) {
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
        String temp;
        temp = result.trim();


        if (temp == null || temp.equals("0")){
            Toast.makeText(activity, temp,
                    Toast.LENGTH_LONG).show();
            return;
        }

        else if(temp.equals("1")){
            Toast.makeText(activity, "성공",
                    Toast.LENGTH_LONG).show();
            activity.finish();

//            RegisterActivity mContext = (RegisterActivity)RegisterActivity.mContext;
//            mContext.finish();  //종료

//            String userID = "ABC";
//            Intent GoToMainintent = new Intent(((RegisterActivity)RegisterActivity.mContext), MainActivity.class); //메인액티비티로 보내는 인텐트
//            ((RegisterActivity)RegisterActivity.mContext).startActivity(GoToMainintent);
//            GoToMainintent.putExtra("userID", userID);
//            ((RegisterActivity)RegisterActivity.mContext).overridePendingTransition(0, 0);  //화면전환효과 없애기

        }

    }

    private String getPostDataString(JSONObject params) throws Exception {

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
}
