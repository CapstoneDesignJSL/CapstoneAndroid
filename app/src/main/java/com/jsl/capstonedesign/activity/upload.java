package com.jsl.capstonedesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jsl.capstonedesign.R;

public class upload extends AppCompatActivity {

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
                                             Intent intent = new Intent(getApplicationContext(), Upload_2.class);
                                             startActivity(intent);
                                         }
                                     }
        );

        ImageView img_search_2 =(ImageView) findViewById(R.id.img_search_2);
        img_search_2.setOnClickListener(new Button.OnClickListener()
                                      {
                                          @Override
                                          public void onClick(View v)
                                          {
                                              Intent intent = new Intent(getApplicationContext(), Search_result.class);
                                              startActivity(intent);
                                          }
                                      }
        );
    }
}
