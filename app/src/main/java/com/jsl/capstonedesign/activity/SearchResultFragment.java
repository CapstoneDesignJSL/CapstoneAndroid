package com.jsl.capstonedesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsl.capstonedesign.R;
import com.jsl.capstonedesign.activity.recyclerview.Data;
import com.jsl.capstonedesign.activity.recyclerview.RecyclerAdapter;

import java.util.Arrays;
import java.util.List;

public class SearchResultFragment extends Fragment  implements RecyclerAdapter.MyRecyclerViewClickListener {

    View v;
    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private ImageView search;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {



        v = inflater.inflate(R.layout.activity_search_result, container, false);
        recyclerView = v.findViewById(R.id.recyclerView2);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        adapter = new RecyclerAdapter();


        init();
//        getData();
        search = v.findViewById(R.id.ic_search);

        search.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                adapter.setOnClickListener(SearchResultFragment.this);
                recyclerView.setAdapter(adapter);
                getData();
            }

        });

        return v;
    }

    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    public void onItemClicked(int position, Data data) {
        //Toast.makeText(getActivity().getApplicationContext(), position + " 번 아이템 클릭됨", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(v.getContext() , Picture.class);

        intent.putExtra("title",data.getTitle()); /*송신*/
        intent.putExtra("resid",data.getResId());
        intent.putExtra("content",data.getContent());

        v.getContext().startActivity(intent);
    }


    private void getData() {
        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList("이스라엘", "폴란드", "러시아", "러시아2","러시아3","러시아4","러시아5","러시아6","러시아7");
        List<String> listContent = Arrays.asList(
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
        List<Integer> listResId = Arrays.asList(
                R.drawable.israel,
                R.drawable.poland,
                R.drawable.russia,
                R.drawable.russia_2,
                R.drawable.russia_2,
                R.drawable.russia_2,
                R.drawable.russia_2,
                R.drawable.russia_2,
                R.drawable.russia_2,
                R.drawable.russia_2
        );
        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            data.setResId(listResId.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }
}
