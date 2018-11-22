package com.example.taeil.mantomen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {
    static Context mContext = null;
    final static String TAG = "AndroidNodeJS";
    Variable variable = Variable.getInstance();
    boolean AuthFlag = false;
    final int REQ_CODE_SELECT_IMAGE = 100;
    ProgressDialog asyncDialog;
    String getServerURL = variable.HttpAddres;  //민영이 서버
    String getImgURL = "";
    String getImgName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AuthFlag = false; //액티비티가 실행될때마다 false로 초기화

        setContentView(R.layout.activity_register);

        final EditText IDText = findViewById(R.id.Register_ID);
        final EditText PWText = findViewById(R.id.PW_R_Input);
        final EditText UserEmail = findViewById(R.id.Register_userEmail);
        final EditText NameText = findViewById(R.id.Name_R_Input);
        final EditText AgeText = findViewById(R.id.Age_R_Input);
        final EditText Authnumber = findViewById(R.id.Register_AuthNumber);
        final RadioGroup GenderGroup = findViewById(R.id.GenderRadio);
        final CheckBox Checkbox1 = findViewById(R.id.checkBox1);
        final CheckBox Checkbox2 = findViewById(R.id.checkBox2);
        final CheckBox Checkbox3 = findViewById(R.id.checkBox3);
        final CheckBox Checkbox4 = findViewById(R.id.checkBox4);
        final CheckBox Checkbox5 = findViewById(R.id.checkBox5);
        final CheckBox Checkbox6 = findViewById(R.id.checkBox6);

        final Button authbutton = findViewById(R.id.Register_Sendnumber);  // 인증번호 보내기 버튼
        final Button inputauthbutton = findViewById(R.id.Register_InsertAuth); // 인증번호 확인 버튼

        // final ImageView userImage = findViewById(R.id.Img_R_Input);  // 유저사진
        final Button selectpicture = findViewById(R.id.select_picture);  // 셀릭트 버튼


        selectpicture.setOnClickListener(new View.OnClickListener() {  // 사진선택 버튼
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        });


        inputauthbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String input = Authnumber.getText().toString();

                if (AuthFlag) {  // 프래그가 폴스면실행안되잖아 트루라면
                    Toast.makeText(RegisterActivity.this, "이미 인증되었습니다.",
                            Toast.LENGTH_LONG).show();
                } else {

                    if (input == null) { // 입력을 안했을 때
                        Toast.makeText(RegisterActivity.this, "인증번호를 입력해주세요",
                                Toast.LENGTH_LONG).show();
                    } else if (!input.equals(variable.getAuthnumber())) {  // 값이 틀릴 때
                        Toast.makeText(RegisterActivity.this, "인증번호를 확인해주세요",
                                Toast.LENGTH_LONG).show();
                    } else { // 이게 맞을 경우잖아
                        Toast.makeText(RegisterActivity.this, "인증되셨습니다.",
                                Toast.LENGTH_LONG).show();
                        AuthFlag = true;  // 플래그를 폴스에서 트루로 바꿈
                    }
                }
            }
        });

        authbutton.setOnClickListener(new View.OnClickListener() {

            JSONObject postDataParam = new JSONObject();

            @Override
            public void onClick(View v) {
                try {
                    String userEmail = UserEmail.getText().toString();  //문자열을 인트형으로 변경
                    if (!userEmail.contains("@")) {  // @가 포함되어있지 않으면
                        Toast.makeText(RegisterActivity.this, "이메일형식을 입력해 주세요",
                                Toast.LENGTH_LONG).show();
                    } else if (userEmail.contains("@")) {
                        postDataParam.put("userEmail", userEmail);
                        Log.e(TAG, userEmail);
                        new AuthInsertData(RegisterActivity.this).execute(postDataParam);
                    }

                } catch (JSONException e) {
                    Log.e(TAG, "JSONEXception");
                }

            }
        });

        Button registerButton = (Button) findViewById(R.id.RegisterSubmitbutton); //회원가입 완료버튼

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userID = IDText.getText().toString();   //ID텍스트의 문자열을 반환 받는값
                String userPassword = PWText.getText().toString();
                String userEmail = UserEmail.getText().toString();  //문자열을 인트형으로 변경
                String userName = NameText.getText().toString();
                String userAge = AgeText.getText().toString(); //
                final RadioButton GenderChoice = (RadioButton) findViewById(GenderGroup.getCheckedRadioButtonId());  //라디오 그룹에서
                String userGender = GenderChoice.getText().toString();  //라디오버튼
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

//                for(int i=0; i<6; i++){ //모든값을 공백
//                    userCategory[i] = "";
//                }

                //Variable.setUserIdentity("Tutee");
                //String userIdentity = Variable.getUserIdentity();
                //String userParticipateClass = "Null";
                //String userOperateClass = "Null";

                JSONObject postDataParam = new JSONObject();

                if (userID == null || userPassword == null || userName == null || userAge == null || userGender == null || userCategory == null) {
                    Toast.makeText(RegisterActivity.this, "값을 다 입력해 주세요",
                            Toast.LENGTH_LONG).show();
                } else {
                    try {
                        postDataParam.put("userID", userID);
                        postDataParam.put("userPassword", userPassword);
                        postDataParam.put("userEmail", userEmail);
                        postDataParam.put("userName", userName);
                        postDataParam.put("userAge", userAge);
                        postDataParam.put("userGender", userGender);
                        postDataParam.put("userCategory", userCategory);
                        //postDataParam.put("userIdentity", userIdentity);
                        //postDataParam.put("userParticipateClass", userParticipateClass);
                        //postDataParam.put("userOperateClass", userOperateClass);
                    } catch (JSONException e) {
                        Log.e(TAG, "JSONEXception");
                    }

                    if (AuthFlag) {  // 인증통과를 했다는 의미

                        new RegisterInsertData(RegisterActivity.this).execute(postDataParam);
                        AuthFlag = false;
                    } else {
                        Toast.makeText(RegisterActivity.this, "이메일 인증을 해주세요.",
                                Toast.LENGTH_LONG).show();
                    }
                }
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
                    ImageView image = (ImageView) findViewById(R.id.Img_R_Input);

                    //배치해놓은 ImageView에 set
                    image.setImageBitmap(image_bitmap);


                    /**
                     * getImgBtn 버튼 클릭을 통해, 업로드할 사진의 절대경로를 가져옴
                     * 서버로 보내는 시간을 고려하여 진행바를 넣어줌
                     */

                    asyncDialog = new ProgressDialog(RegisterActivity.this);
                    asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    asyncDialog.setMessage("로딩중입니다..");

                    // show dialog
                    asyncDialog.show();

                    uploadFile(getImgURL, getImgName);
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .build();

        NetworkServiceRegister service = retrofit.create(NetworkServiceRegister.class);


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


}
