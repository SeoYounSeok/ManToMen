package com.project.taeil.mantomen;

import android.app.Activity;
import android.app.ProgressDialog;

import java.net.MalformedURLException;
import java.net.URL;


public class ClassDetailInsertData extends ClassDetailPostRequest { //로그인할때 쓰는거 값을 받고 받아오는걸로 로그인함

    static ProgressDialog LoadingDialog = new ProgressDialog(
            Main2Activity.mActivity);



    public ClassDetailInsertData(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
        if (Main2Activity.loading) {
            LoadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            LoadingDialog.setMessage("로딩중입니다..");

            // show dialog
            LoadingDialog.show();
        } else{
            CategoryActivity.LoadingDialog2.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            CategoryActivity.LoadingDialog2.setMessage("로딩중입니다..");

            // show dialog
            CategoryActivity.LoadingDialog2.show();
        }

//      EditText server = activity.findViewById(R.id.server);


        String serverURLStr = Variable.HttpAddres;  //민영이 서버
        try {
            url = new URL(serverURLStr + "/class/detail");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
