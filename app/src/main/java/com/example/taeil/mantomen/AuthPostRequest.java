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

public class AuthPostRequest extends AsyncTask<JSONObject, Void, String> {
    Activity activity;
    URL url;
    Variable variable = Variable.getInstance();

    public AuthPostRequest(Activity activity) {
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
        String message1 = "인증실패";
        String message2 = "인증성공";
        temp = result.trim();


        String SB = temp; //일단 String버퍼를 스트링 형식으로 변형


        if (temp == null) {
            Toast.makeText(activity, message1,
                    Toast.LENGTH_LONG).show();
            return;
        } else if (temp.equals("0")) {
            Toast.makeText(activity, "중복된 이메일입니다.",
                    Toast.LENGTH_LONG).show();
            variable.setAuthnumber("9999");
            return;
        } else { // 비밀번호가 왔어염
            int index = SB.indexOf(":"); // :로자르고
            String AuthNumber;
            AuthNumber = SB.substring(index + 1, SB.length() - 1);
            variable.setAuthnumber(AuthNumber);
            Toast.makeText(activity, message2,
                    Toast.LENGTH_LONG).show();
            Log.e("userEmail", AuthNumber);  // 난수 확인용

//            RegisterActivity mContext = (RegisterActivity)RegisterActivity.mContext;
//            mContext.finish();  //종료

//            String userID = "ABC";
//            Intent GoToMainintent = new Intent(((RegisterActivity)RegisterActivity.mContext), MainActivity.class); //메인액티비티로 보내는 인텐트
//            ((RegisterActivity)RegisterActivity.mContext).startActivity(GoToMainintent);
//            GoToMainintent.putExtra("userID", userID);
//            ((RegisterActivity)RegisterActivity.mContext).overridePendingTransition(0, 0);  //화면전환효과 없애기

        }
        Log.e("userEmail", temp);  // 난수 확인용

        Toast.makeText(activity, temp,
                Toast.LENGTH_LONG).show();

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
