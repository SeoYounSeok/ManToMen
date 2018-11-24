package com.example.taeil.mantomen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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


public class ClassTitleFragment extends Fragment {

    Variable variable = Variable.getInstance();
    VariableOfClass variableOfClass = VariableOfClass.getInstance();
    final int REQ_CODE_SELECT_IMAGE = 100;
    ProgressDialog asyncDialog;
    String getServerURL = variable.HttpAddres;  //민영이 서버
    String getImgURL = "";
    String getImgName = "";
    boolean pictureflag = false;

    public ClassTitleFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout classtitlefragment = (LinearLayout) inflater.inflate(R.layout.fragment_class_title, container, false);

        final Button previousbutton = classtitlefragment.findViewById(R.id.TutorRegister1_Previous); // 이전
        final Button nextbutton = classtitlefragment.findViewById(R.id.TutorRegister1_Next); // 다음
        final EditText ClassName = classtitlefragment.findViewById(R.id.TutorRegister1_ClassName);
        final ImageView ClassPicture = classtitlefragment.findViewById(R.id.TutorRegister1_ClassPicture);
        final Spinner ClassCategory = classtitlefragment.findViewById(R.id.TutorRegister1_ClassCategory);  // 스피너 누를 때 다이얼로그창이 뜨면서 카테고리선택
        final Spinner ClassTotalPeople = classtitlefragment.findViewById(R.id.TutorRegister1_ClassTotalPeople);


        final Button selectpicture = classtitlefragment.findViewById(R.id.TutorRegister1_SelectPrictureButton);  // 셀릭트 버튼

        selectpicture.setOnClickListener(new View.OnClickListener() {  // 사진선택 버튼
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        });


        previousbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TutorRegisterActivity) getActivity()).switchFragment(0);  // 프래그먼트 교체
            }
        });


        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ClassName.getText() == null || pictureflag == false) {
                    Toast.makeText(getActivity(), "빈칸을 채워주세요",
                            Toast.LENGTH_LONG).show();

                } else {
                    variableOfClass.setClassName(ClassName.getText().toString());
                    // variableOfClass.setClassName(ClassPicture.getText().toString());  // 사진은 임의의 사진 일단 스트링으로 보냄시험삼아
                    //variableOfClass.setClassPicture("시험사진");
                    //variableOfClass.setClassCategory(ClassCategory.getText().toString());
                    variableOfClass.setClassCategory(ClassCategory.getSelectedItem().toString());
                    variableOfClass.setClassTotalPeople(ClassTotalPeople.getSelectedItem().toString());
                    ((TutorRegisterActivity) getActivity()).switchFragment(2);  // 프래그먼트 교체

                }

            }
        });


        // Inflate the layout for this fragment
        return classtitlefragment;
    }

    // 선택된 이미지 가져오기
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Toast.makeText(getBaseContext(), "resultCode : "+resultCode,Toast.LENGTH_SHORT).show();

        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    String name_Str = getImageNameToUri(data.getData());

                    Log.i("myTag", name_Str);

                    //이미지 데이터를 비트맵으로 받아온다.
                    Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), data.getData());

                    ImageView image = (ImageView) getActivity().findViewById(R.id.TutorRegister1_ClassPicture);

                    //배치해놓은 ImageView에 set
                    image.setImageBitmap(image_bitmap);
                    //배치해놓은 ImageView에 set
                    image.setImageBitmap(image_bitmap);


                    /**
                     * getImgBtn 버튼 클릭을 통해, 업로드할 사진의 절대경로를 가져옴
                     * 서버로 보내는 시간을 고려하여 진행바를 넣어줌
                     */

                    asyncDialog = new ProgressDialog(getActivity());
                    asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    asyncDialog.setMessage("로딩중입니다..");

                    // show dialog
                    asyncDialog.show();

                    pictureflag = true;


                    uploadFile(getImgURL, getImgName);
                    //Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();
                    Log.e("사진", getImgName + getImgURL);
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
        Cursor cursor = getActivity().managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);

        getImgURL = imgPath;
        getImgName = imgName;

        return "success";
    }

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


        NetworkServiceClassRegister service = retrofit.create(NetworkServiceClassRegister.class);

        /**
         * 서버로 보낼 파일의 전체 url을 이용해 작업
         */

        File photo = new File(ImgURL);

        Log.e("사진3", ImgURL);
        Log.e("사진3", String.valueOf(photo.exists()));

        RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), photo);

//      Log.i("myTag","this file'name is "+ photo.getName());

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

//                        if (result.equals("1")) {
//                            Toast.makeText(getActivity().getApplicationContext(), "사진 업로드 성공!!!!", Toast.LENGTH_SHORT).show();
//                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.i("MyTag", "error : " + e.getMessage());
                    }


                } else {
//                    Toast.makeText(getApplicationContext(), "사진 업로드 실패!!!!", Toast.LENGTH_SHORT).show();
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


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
