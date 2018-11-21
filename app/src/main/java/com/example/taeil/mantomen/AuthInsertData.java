package com.example.taeil.mantomen;

import android.app.Activity;

import java.net.MalformedURLException;
import java.net.URL;

import static com.example.taeil.mantomen.Variable.HttpAddres;


public class AuthInsertData extends AuthPostRequest { //레지스터
    public AuthInsertData(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
//      EditText server = activity.findViewById(R.id.server);

        String serverURLStr = Variable.HttpAddres;  //민영이 서버
        try {
            url = new URL(serverURLStr + "/home/auth");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
