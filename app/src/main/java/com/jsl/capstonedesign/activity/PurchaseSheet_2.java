package com.jsl.capstonedesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jsl.capstonedesign.R;

public class PurchaseSheet_2 extends AppCompatActivity {

//    결제완료 후 다운받기 버튼 클릭시 다운받기 화면으로 넘어감
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puschasesheet2);

        Button btn_download =(Button) findViewById(R.id.btn_download);
        btn_download.setOnClickListener(new Button.OnClickListener()
                                     {
                                         @Override
                                         public void onClick(View v)
                                         {
                                             Intent intent = new Intent(getApplicationContext(), Download.class);
                                             startActivity(intent);
                                         }
                                     }
        );
    }
}
