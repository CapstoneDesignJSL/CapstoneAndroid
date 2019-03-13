package com.jsl.capstonedesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jsl.capstonedesign.R;

public class Register_password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_password);


//      다음 클릭시 로그인되고 홈 화면으로 넘어감

        Button btn_pw =(Button) findViewById(R.id.btn_pw );
        btn_pw.setOnClickListener(new Button.OnClickListener()
                                          {
                                              @Override
                                              public void onClick(View v)
                                              {
                                                  Intent intent = new Intent(getApplicationContext(), Home.class);
//login,main, download, Menu, Register, Shopping_cart,upload2,User 됨
//home, picture, picturedata, PurchaseSheet_1, PurchaseSheet_2, Search_result ,upload안됨
                                                  startActivity(intent);
                                              }
                                          }

        );
    }
}
