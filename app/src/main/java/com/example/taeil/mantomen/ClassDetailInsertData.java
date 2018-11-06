package com.example.taeil.mantomen;

import android.app.Activity;

import java.net.MalformedURLException;
import java.net.URL;


public class ClassDetailInsertData extends ClassDetailPostRequest { //레지스터
    public ClassDetailInsertData(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
//      EditText server = activity.findViewById(R.id.server);
        String serverURLStr = Variable.HttpAddres;  //민영이 서버
        try {
            url = new URL(serverURLStr + "/class/get");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
