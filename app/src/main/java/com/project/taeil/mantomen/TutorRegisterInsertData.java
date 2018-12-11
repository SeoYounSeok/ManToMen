package com.project.taeil.mantomen;

import android.app.Activity;
import android.app.ProgressDialog;

import java.net.MalformedURLException;
import java.net.URL;


public class TutorRegisterInsertData extends TutorRegisterPostRequest { //레지스터
    static ProgressDialog LoadingDialog;

    public TutorRegisterInsertData(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
//      EditText server = activity.findViewById(R.id.server);

        LoadingDialog = new ProgressDialog(TutorRegisterActivity.mActivity);

        LoadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        LoadingDialog.setMessage("로딩중입니다..");

        // show dialog
        if (!TutorRegisterActivity.mActivity.isFinishing()) {
            LoadingDialog.show();
        }


        String serverURLStr = Variable.HttpAddres;  //민영이 서버
        try {
            url = new URL(serverURLStr + "/class/push");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
