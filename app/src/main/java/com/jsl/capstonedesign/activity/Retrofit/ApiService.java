package com.jsl.capstonedesign.activity.Retrofit;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.ArrayList;

import javax.xml.transform.Result;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    public static final String API_URL = "http://10.0.2.2:8000/"; //로컬서버

    //1번 지갑정보확인용
    @GET("eth/check")
    Call<ResponseBody>getComment(@Query("email")String email);

    //2번 지갑 생성
    @GET("eth/mining")
    Call<ResponseBody>mining(@Query("email")String email);

    @POST("eth/bal")
    Call<RequestBody>balance(@Query("email")String email);

    @GET("eth/mining_stop")
    Call<ResponseBody>mining_stop(@Query("email")String email);


    @GET("list")
    Call<ResponseBody>getPictureList();

    @FormUrlEncoded
    @POST("upload")
    Call<RequestBody>uploadImg(@Field("email_txt") String email, @Field("picture_name") String name, @Field("image") String img, @Field("price") int price);

//    @FormUrlEncoded
//    @POST("upload")
//    Call<RequestBody>uploadImg(@Field("email_txt") String email, @Field("picture_name") String name, @Part MultipartBody.Part file, @Field("price") String price);

}
