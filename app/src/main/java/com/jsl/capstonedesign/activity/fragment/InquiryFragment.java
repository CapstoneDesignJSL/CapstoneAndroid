package com.jsl.capstonedesign.activity.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.jsl.capstonedesign.R;
import com.jsl.capstonedesign.activity.Picture;
import com.jsl.capstonedesign.activity.recyclerview.Data;
import com.jsl.capstonedesign.activity.recyclerview.RecyclerAdapter;

import java.util.Arrays;
import java.util.List;

public class InquiryFragment extends Fragment implements RecyclerAdapter.MyRecyclerViewClickListener {

    View v;

    //임시 데이터
    List<String> listTitle = Arrays.asList("이스라엘", "폴란드", "러시아", "러시아2","러시아3","러시아4","러시아5","러시아6","러시아7");

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

    List<String> listContentBuy = Arrays.asList(
            "이스라엘 설명 구매한것 ",
            "폴란드 설명 구매한 것",
            "러시아1 설명 구매한 것",
            "러시아2 설명 구매한 것",
            "러시아3 설명 구매한 것",
            "러시아4 설명 구매한 것",
            "러시아5 설명 구매한 것",
            "러시아6 설명 구매한 것",
            "러시아7 설명 구매한 것"
    );

    List<String> listContentSell = Arrays.asList(
            "이스라엘 설명 판매한 것 ",
            "폴란드 설명 판매한 것",
            "러시아1 설명 판매한 것",
            "러시아2 설명 판매한 것",
            "러시아3 설명 판매한 것",
            "러시아4 설명 판매한 것",
            "러시아5 설명 판매한 것",
            "러시아6 설명 판매한 것",
            "러시아7 설명 판매한 것"
    );
    //임시 데이터 끝

    public void onItemClicked(int position, Data data) {
        //Toast.makeText(getActivity().getApplicationContext(), position + " 번 아이템 클릭됨", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(v.getContext() , Picture.class);

        intent.putExtra("title",data.getTitle()); /*송신*/
        intent.putExtra("resid",data.getResId());
        intent.putExtra("content",data.getContent());

        v.getContext().startActivity(intent);
    }

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerAdapter new_adapter;

        v = inflater.inflate(R.layout.fragment_inqury, container, false); //오타지만 그냥
        final RecyclerView recyclerView = v.findViewById(R.id.recyclerView);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        new_adapter = new RecyclerAdapter();

        new_adapter.setOnClickListener(InquiryFragment.this);

        recyclerView.setAdapter(new_adapter);

        getSellData(new_adapter);

        //버튼 누를떄마다 어댑터 초기화.
        LabeledSwitch labeledSwitch = v.findViewById(R.id.switch_inquiry);
        labeledSwitch.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                RecyclerAdapter newnew_adapter = new RecyclerAdapter();
                recyclerView.setAdapter(newnew_adapter);
                if(isOn == true)
                    getBuyData(newnew_adapter);
                else
                    getSellData(newnew_adapter);
            }
        });



        return v;
    }

    private void getSellData(RecyclerAdapter new_adapter ) {
        //임시 판매 데이터


        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContentSell.get(i));
            data.setResId(listResId.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            new_adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        new_adapter.notifyDataSetChanged();
    }

    private void getBuyData(RecyclerAdapter new_adapter) {
        // 임의의 데이터입니다.

        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContentBuy.get(i));
            data.setResId(listResId.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            new_adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        new_adapter.notifyDataSetChanged();
    }
}
