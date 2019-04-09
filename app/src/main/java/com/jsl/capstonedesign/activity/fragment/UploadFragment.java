package com.jsl.capstonedesign.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.jsl.capstonedesign.R;
import com.jsl.capstonedesign.activity.SearchResultFragment;
import com.jsl.capstonedesign.activity.Upload_2;

public class UploadFragment extends Fragment {

    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_upload, container, false);


        Button btn_revise =(Button) v.findViewById(R.id.btn_revise);
        btn_revise.setOnClickListener(new Button.OnClickListener()
                                     {
                                         @Override
                                         public void onClick(View v)
                                         {
                                             //서버에 업로드 요청


                                             if(true){ //업로드요청에 대해 받은 응답으로 트루값들어오면 진행

                                                 Intent intent = new Intent(getContext(), Upload_2.class);
                                                 startActivity(intent);

                                             }else {
                                                    //업로드에 실패하였습니다.
                                             }
                                         }
                                     }
        );

        ImageView img_search_2 =(ImageView) v.findViewById(R.id.img_search_2);
        img_search_2.setOnClickListener(new Button.OnClickListener()
                                      {
                                          @Override
                                          public void onClick(View v)
                                          {
                                              Intent intent = new Intent(getContext(), SearchResultFragment.class);
                                              startActivity(intent);
                                          }
                                      }
        );
        return v;
    }
}
