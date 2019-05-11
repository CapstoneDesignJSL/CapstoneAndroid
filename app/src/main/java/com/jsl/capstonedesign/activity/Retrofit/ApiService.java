package com.jsl.capstonedesign.activity.Retrofit;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    public static final String API_URL = "http://10.0.2.2:8000/"; //로컬서버

    //1번 지갑정보확인용
    @GET("eth/check")
    Call<ResponseBody>getComment(@Query("email")String email);

    //2번 지갑 생성
    @POST("eth/make")
    Call<ResponseBody>postComment();

    @GET("list")
    Call<ResponseBody>getPictureList();

    

}
