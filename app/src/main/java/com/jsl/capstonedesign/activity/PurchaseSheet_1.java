package com.jsl.capstonedesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jsl.capstonedesign.R;

public class PurchaseSheet_1 extends AppCompatActivity {

//    결제하기 화면
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchasesheet1);


        Button btn_buy_2 =(Button) findViewById(R.id.btn_buy_2);
        btn_buy_2.setOnClickListener(new Button.OnClickListener()
                                     {
                                         @Override
                                         public void onClick(View v)
                                         {
                                             Intent intent = new Intent(getApplicationContext(), PurchaseSheet_2.class);
                                             startActivity(intent);
                                         }
                                     }
        );
    }
}
