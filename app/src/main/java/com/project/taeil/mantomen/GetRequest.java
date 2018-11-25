package com.project.taeil.mantomen;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

abstract public class GetRequest extends AsyncTask<String, Void, String> {
    final static String TAG = "AndroidNodeJS";
    Activity activity;
    Variable variable;
    URL url;

    public GetRequest(Activity activity) {
        this.activity = activity;
    }


    @Override
    protected String doInBackground(String... strings) {
        StringBuffer output = new StringBuffer();
        Log.d("리뷰","겟데이터 두인백그라운드 시작부분");
        try {
            if (url == null) {
                Log.e(TAG, "Error: URL is null ");
                return null;
            }
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if (conn == null) {
                Log.e(TAG, "HttpsURLConnection Error");
                return null;
            }
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("GET");

            String cookieString = variable.getCookies();  // 토큰확인
            if (cookieString != null) {
                conn.setRequestProperty("cookie", cookieString);
            }
//            Log.e("쿠키",cookieString);

            conn.setDoInput(true);
            conn.setDoOutput(false);

            int resCode = conn.getResponseCode();

            if (resCode != HttpsURLConnection.HTTP_OK) {
                Log.e(TAG, "HttpsURLConnection ResponseCode: " + resCode);
                conn.disconnect();
                return null;
            }
            Log.d("리뷰","스트림앞");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            Log.d("리뷰","스트림뒤");
            String line = null;
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                output.append(line + "\n");
            }

            reader.close();
            conn.disconnect();

        } catch (IOException ex) {
            Log.e(TAG, "Exception in processing response.", ex);
            ex.printStackTrace();
        }

        Log.d("리뷰","겟데이터 두인백그라운드 끝부분");
        Log.d("리뷰",output.toString());
        return output.toString();
    }


}
