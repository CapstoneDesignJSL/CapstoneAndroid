package com.jsl.capstonedesign.activity.FragmentView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Url;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.jsl.capstonedesign.R;
import com.jsl.capstonedesign.activity.Picture;
import com.jsl.capstonedesign.activity.Retrofit.ApiService;
import com.jsl.capstonedesign.activity.recyclerview.Data;
import com.jsl.capstonedesign.activity.recyclerview.RecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment implements RecyclerAdapter.MyRecyclerViewClickListener{
    View v;

    private Button search;
    private FragmentPagerAdapter adapterViewPager;

    private String title;
    private int page;
    private RecyclerAdapter adapter;
    View view;

    List<String> listTitle =  new ArrayList<String>();
    List<String> listContent= new ArrayList<String>();
    List<String> listResId = new ArrayList<String>();
    List<Double> listPrice= new ArrayList<Double>();

    final private String TAG = getClass().getSimpleName();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        final Fragment searchFragment = new SearchResultFragment();



        v = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);


        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new RecyclerAdapter();

        adapter.setOnClickListener(HomeFragment.this);

        recyclerView.setAdapter(adapter);
        getData();

//
//        ViewPager vpPager = (ViewPager)v.findViewById(R.id.home_vpPager);
//        adapterViewPager = new MyPagerAdapter(getActivity().getSupportFragmentManager());
//
//        vpPager.setAdapter(adapterViewPager);
//
//        CircleIndicator indicator = (CircleIndicator) v.findViewById(R.id.home_indicator);
//        indicator.setViewPager(vpPager);
//
//        search = (Button)v.findViewById(R.id.home_search_button);
//
//        search.setOnClickListener(new Button.OnClickListener(){
//
//            @Override
//            public void onClick(View v)
//            {
//                openFragment(searchFragment);
//            }
//
//        });



        return v;
    }

    public void onItemClicked(int position, Data data) {
        //Toast.makeText(getActivity().getApplicationContext(), position + " 번 아이템 클릭됨", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(v.getContext(), Picture.class);

        intent.putExtra("title", data.getTitle()); /*송신*/
        intent.putExtra("resid", data.getResId());
        intent.putExtra("content", data.getContent());
        intent.putExtra("url",data.getResId());
        intent.putExtra("price", data.getPrice());
        v.getContext().startActivity(intent);
    }


    private void getData() {
        // 임의의 데이터입니다.

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final ApiService apiService = retrofit.create(ApiService.class);

        //Part of GET
        Call<ResponseBody> res = apiService.getPictureList();

        res.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e(TAG, "onResponse in getData");

                try {
                    String str =response.body().string();
                    try {
                        listContent.clear();
                        listResId.clear();
                        listTitle.clear();
                        listPrice.clear();

                        JSONArray jsonArray = new JSONArray(str);
                        Log.e("testtest",str);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jObject = jsonArray.getJSONObject(i);

                            listTitle.add(jObject.getString("picture_name"));
                            listContent.add(jObject.getString("picture_name") + "입니다");
                            listPrice.add(jObject.getDouble("price"));
                            String ur = apiService.API_URL+jObject.getString("url");
                            listResId.add(ur);

                        }

                        for (int i = 0; i < listTitle.size(); i++) {
                            // 각 List의 값들을 data 객체에 set 해줍니다.
                            Data data = new Data();
                            data.setTitle(listTitle.get(i));
                            data.setContent(listContent.get(i));
                            data.setResId(listResId.get(i));
                            data.setPrice(listPrice.get(i));
                            // 각 값이 들어간 data를 adapter에 추가합니다.
                            adapter.addItem(data);
                        }

                        // adapter의 값이 변경되었다는 것을 알려줍니다.
                        adapter.notifyDataSetChanged();

                    }catch (JSONException e){

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure in getData");

                listTitle = Arrays.asList("이스라엘", "폴란드", "러시아", "러시아2", "러시아3", "러시아4", "러시아5", "러시아6", "러시아7");
                listContent = Arrays.asList(
                        "이스라엘 설명 텍스트",
                        "폴란드 설명 텍스트",
                        "러시아1 설명 텍스트",
                        "러시아2 설명 텍스트",
                        "러시아3 설명 텍스트",
                        "러시아4 설명 텍스트",
                        "러시아5 설명 텍스트",
                        "러시아6 설명 텍스트",
                        "러시아7 설명 텍스트"
                );



            }
        });



    }
}

