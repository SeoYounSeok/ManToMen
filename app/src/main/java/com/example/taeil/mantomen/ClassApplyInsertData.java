package com.example.taeil.mantomen;

import android.app.Activity;

import java.net.MalformedURLException;
import java.net.URL;


public class ClassApplyInsertData extends ClassApplyPostRequest { //레지스터
    public ClassApplyInsertData(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
//      EditText server = activity.findViewById(R.id.server);
        String serverURLStr = Variable.HttpAddres;  //민영이 서버
        try {
            url = new URL(serverURLStr + "/class/apply");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
