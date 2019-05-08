package com.jsl.capstonedesign.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jsl.capstonedesign.R;
import com.jsl.capstonedesign.activity.FragmentView.Upload_2Fragment;

public class Upload extends AppCompatActivity {

    @Override

//    수정 창으로 넘어감
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        Button btn_revise =(Button) findViewById(R.id.btn_revise);
        btn_revise.setOnClickListener(new Button.OnClickListener()
                                     {
                                         @Override
                                         public void onClick(View v)
                                         {
                                             //서버에 업로드 요청


                                             if(true){ //업로드요청에 대해 받은 응답으로 트루값들어오면 진행

                                                 Intent intent = new Intent(getApplicationContext(), Upload_2Fragment.class);
                                                 startActivity(intent);

                                             }else {
                                                    //업로드에 실패하였습니다.
                                             }
                                         }
                                     }
        );

    }
}
