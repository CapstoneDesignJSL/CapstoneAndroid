package com.jsl.capstonedesign.activity.FragmentView;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsl.capstonedesign.R;
import com.jsl.capstonedesign.activity.Picture;
import com.jsl.capstonedesign.activity.recyclerview.Data;
import com.jsl.capstonedesign.activity.recyclerview.RecyclerAdapter;

import java.util.Arrays;
import java.util.List;


public class HomeTotalFragment extends Fragment implements RecyclerAdapter.MyRecyclerViewClickListener{
    // Store instance variables
    private String title;
    private int page;
    private RecyclerAdapter adapter;
    View view;
    public void onItemClicked(int position, Data data) {
        //Toast.makeText(getActivity().getApplicationContext(), position + " 번 아이템 클릭됨", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(view.getContext() , Picture.class);

        intent.putExtra("title",data.getTitle()); /*송신*/
        intent.putExtra("resid",data.getResId());
        intent.putExtra("content",data.getContent());

        view.getContext().startActivity(intent);


    }

    // newInstance constructor for creating fragment with arguments
    public static HomeTotalFragment newInstance(int page, String title) {
        HomeTotalFragment fragment = new HomeTotalFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_vp1, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new RecyclerAdapter();

        adapter.setOnClickListener(HomeTotalFragment.this);

        recyclerView.setAdapter(adapter);
        getData();

        TextView tvLabel = (TextView) view.findViewById(R.id.home_indicator_text);
        tvLabel.setText(title);

        return view;
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
                R.drawable.womenexample,
                R.drawable.poland,
                R.drawable.manexample,
                R.drawable.russia,
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