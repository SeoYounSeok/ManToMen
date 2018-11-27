package com.project.taeil.mantomen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.taeil.mantomen.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MemberModifyActivity extends AppCompatActivity {

    static Context mContext = null;
    final static String TAG = "AndroidNodeJS";

    final int REQ_CODE_SELECT_IMAGE = 100;


    Variable variable;

    TextView UserID;
    TextView UserName2;
    EditText UserPassword;
    TextView UserEmail;
    EditText UserGender;
    TextView UserName;
    EditText UserAge;
    TextView UserIdentity;
    CheckBox Checkbox1;
    CheckBox Checkbox2;
    CheckBox Checkbox3;
    CheckBox Checkbox4;
    CheckBox Checkbox5;
    CheckBox Checkbox6;
    ImageView UserPicture;

    boolean picturecheck = false;

    ProgressDialog asyncDialog;
    String getServerURL = variable.HttpAddres;  //민영이 서버
    String getImgURL = "";
    String getImgName = "";

    // EditText UserIdentity = findViewById(R.id.modify_UserIdentity);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_modify);
        picturecheck = false;
        Intent intent = getIntent();
        //String userID = intent.getStringExtra("userID"); //마이페이지수정버튼을 통해 ID를 받아옴

        variable = Variable.getInstance();

        UserID = findViewById(R.id.modify_userID);

        UserPassword = findViewById(R.id.modify_UserPassword);
        UserEmail = findViewById(R.id.modify_UserEmail);
        UserGender = findViewById(R.id.modify_UserGender);
        UserName = findViewById(R.id.modify_UserName);
        UserName2 = findViewById(R.id.modify_UserName2);
        UserAge = findViewById(R.id.modify_UserAge);
        UserIdentity = findViewById(R.id.modify_UserIdentity);

        Checkbox1 = findViewById(R.id.modify_checkBox1);
        Checkbox2 = findViewById(R.id.modify_checkBox2);
        Checkbox3 = findViewById(R.id.modify_checkBox3);
        Checkbox4 = findViewById(R.id.modify_checkBox4);
        Checkbox5 = findViewById(R.id.modify_checkBox5);
        Checkbox6 = findViewById(R.id.modify_checkBox6);

        UserID.setText(variable.getUserID());
        // UserPassword.setText(variable.getUserPassword());
        UserEmail.setText(variable.getUserEmail());
        UserGender.setText(variable.getUserGender());
        UserName.setText(variable.getUserName());
        UserName2.setText(variable.getUserName());
        UserAge.setText(variable.getUserAge());
        UserIdentity.setText(variable.getUserIdentity());
        Button modifybtn = findViewById(R.id.Membermodify_Btn);

        UserPicture = findViewById(R.id.modify_userPicture);

        new DownloadImageTask((ImageView) findViewById(R.id.modify_userPicture))    // 이미지뷰에 url에 저장된 사진 세팅
                .execute(variable.getUserPicture());

        UserPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        });


        if (variable.getUserCategory().matches(".*Programing.*")) {
            Checkbox1.setChecked(true);

        }
        if (variable.getUserCategory().matches(".*Design.*")) {
            Checkbox2.setChecked(true);

        }
        if (variable.getUserCategory().matches(".*Language.*")) {
            Checkbox3.setChecked(true);

        }
        if (variable.getUserCategory().matches(".*Music.*")) {
            Checkbox4.setChecked(true);

        }
        if (variable.getUserCategory().matches(".*Beauty.*")) {
            Checkbox5.setChecked(true);

        }
        if (variable.getUserCategory().matches(".*Etc.*")) {
            Checkbox6.setChecked(true);

        }

        modifybtn.setOnClickListener(new View.OnClickListener() { //클릭하면
            @Override
            public void onClick(View v) {

                String userCategory = "";

                if (Checkbox1.isChecked())
                    userCategory = "Programing//";
                if (Checkbox2.isChecked())
                    userCategory += "Design//";
                if (Checkbox3.isChecked())
                    userCategory += "Language//";
                if (Checkbox4.isChecked())
                    userCategory += "Music//";
                if (Checkbox5.isChecked())
                    userCategory += "Beauty//";
                if (Checkbox6.isChecked())
                    userCategory += "Etc//";


                String userPassword = UserPassword.getText().toString();
                String userName = UserName.getText().toString();
                String userAge = UserAge.getText().toString();


//                Variable.setUserCN(String.valueOf(UserCN.getText()));
//                Variable.setUserGender(String.valueOf(UserGender.getText()));
//                Variable.setUserName(String.valueOf(UserName.getText()));
//                Variable.setUserAge(String.valueOf(UserAge.getText()));


                //  modify_userIdentity = String.valueOf(UserIdentity.getText());

                JSONObject postDataParam = new JSONObject();

                try {
                    postDataParam.put("userID", variable.getUserID());
                    postDataParam.put("userPassword", userPassword);
                    postDataParam.put("userEmail", variable.getUserEmail());
                    postDataParam.put("userName", userName);
                    postDataParam.put("userAge", userAge);
                    postDataParam.put("userGender", variable.getUserGender());
                    postDataParam.put("userCategory", userCategory);
                    postDataParam.put("userIdentity", variable.getUserIdentity());
                    postDataParam.put("userParticipateClass", variable.getUserParticipateClass());
                    postDataParam.put("userOperateClass", variable.getUserOperateClass());

                } catch (JSONException e) {
                    Log.e(TAG, "JSONEXception");
                }

                new MemberModifyInsertData(MemberModifyActivity.this).execute(postDataParam);

                Main2Activity mActivity = (Main2Activity) Main2Activity.mActivity;
                mActivity.finish();   //메인액티비티 종료
                finish();
            }


        });


    }


    // 선택된 이미지 가져오기
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Toast.makeText(getBaseContext(), "resultCode : "+resultCode,Toast.LENGTH_SHORT).show();

        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    String name_Str = getImageNameToUri(data.getData());

                    Log.i("myTag", name_Str);

                    //이미지 데이터를 비트맵으로 받아온다.
                    Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    ImageView image = (ImageView) findViewById(R.id.modify_userPicture);

                    //배치해놓은 ImageView에 set
                    image.setImageBitmap(image_bitmap);


                    /**
                     * getImgBtn 버튼 클릭을 통해, 업로드할 사진의 절대경로를 가져옴
                     * 서버로 보내는 시간을 고려하여 진행바를 넣어줌
                     */

                    asyncDialog = new ProgressDialog(MemberModifyActivity.this);
                    asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    asyncDialog.setMessage("로딩중입니다..");

                    // show dialog
                    asyncDialog.show();

                    uploadFile(getImgURL, getImgName);
                    picturecheck = true;
                    //Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 선택된 이미지 파일명 가져오기
    public String getImageNameToUri(Uri data) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);

        getImgURL = imgPath;
        getImgName = imgName;

        return "success";
    }

    /**
     * Upload Image Client Code
     */

    private void uploadFile(String ImgURL, String ImgName) {

        /**
         * 현재 연결된 서버의 URL을 받아옴
         */
        String url = getServerURL;

        /**
         * 다시 연결 시도
         */
        // create upload service client

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("cookie", variable.getCookies())
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .build();

        NetworkServiceUserModify service = retrofit.create(NetworkServiceUserModify.class);

        /**
         * 서버로 보낼 파일의 전체 url을 이용해 작업
         */

        File photo = new File(ImgURL);
        RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), photo);

//        Log.i("myTag","this file'name is "+ photo.getName());

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", photo.getName(), photoBody);

        /**
         * 서버에 사진이외의 텍스트를 보낼 경우를 생각해서 일단 넣어둠
         */
        // add another part within the multipart request
        String descriptionString = "android";

        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);


        /**
         * 사진 업로드하는 부분 // POST방식 이용
         */
        Call<ResponseBody> call = service.upload(body, description);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {

//                    Gson gson = new Gson();
                    try {
                        String getResult = response.body().string();

//                        JsonParser parser = new JsonParser();
//                        JsonElement rootObejct = parser.parse(getResult);
//
////                        Log.i("mytag",rootObejct.toString());
//
//                        UploadResult example = gson.fromJson(rootObejct, UploadResult.class);
//
//                        Log.i("mytag", example.url);

                        String result = getResult.trim();

                        if (result.equals("1")) {
                            Toast.makeText(getApplicationContext(), "사진 업로드 성공!!!!", Toast.LENGTH_SHORT).show();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.i("MyTag", "error : " + e.getMessage());
                    }


                } else {
                    Toast.makeText(getApplicationContext(), "사진 업로드 실패!!!!", Toast.LENGTH_SHORT).show();
                }
                // dismiss dialog
                asyncDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());

                // dismiss dialog
                asyncDialog.dismiss();
            }


        });
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


}
