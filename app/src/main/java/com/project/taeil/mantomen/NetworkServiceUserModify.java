package com.project.taeil.mantomen;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface NetworkServiceUserModify {
    @Multipart
    @POST("/index/upload")
    Call<ResponseBody> upload(@Part MultipartBody.Part file, @Part("name") RequestBody description);

}
