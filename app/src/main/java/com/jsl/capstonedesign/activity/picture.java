package com.jsl.capstonedesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jsl.capstonedesign.R;

public class picture extends AppCompatActivity {

//    구매하기 클릭시 결제 창으로 넘어감
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);


        Button btn_buy =(Button) findViewById(R.id.btn_buy);
        btn_buy.setOnClickListener(new Button.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View v)
                                            {
                                                Intent intent = new Intent(getApplicationContext(), PurchaseSheet_1.class);
                                                startActivity(intent);
                                            }
                                        }
        );

        TextView text_logo =(TextView) findViewById(R.id.text_logo);
        text_logo.setOnClickListener(new Button.OnClickListener()
                                   {
                                       @Override
                                       public void onClick(View v)
                                       {
                                           Intent intent = new Intent(getApplicationContext(), Home.class);
                                           startActivity(intent);
                                       }
                                   }
        );
    }
}
