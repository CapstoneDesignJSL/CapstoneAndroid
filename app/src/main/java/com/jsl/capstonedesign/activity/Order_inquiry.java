package com.jsl.capstonedesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsl.capstonedesign.R;

public class Order_inquiry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_inquiry);

        ImageView img_israel_order  = (ImageView)findViewById(R.id.img_israel_order);
        img_israel_order.setOnClickListener(new Button.OnClickListener()
                                      {
                                          @Override
                                          public void onClick(View v)
                                          {
                                              Intent intent = new Intent(getApplicationContext(), Picture.class);
                                              startActivity(intent);
                                          }
                                      }

        );

        TextView tx_logo =(TextView) findViewById(R.id.tx_logo);
        tx_logo.setOnClickListener(new Button.OnClickListener()
                                   {
                                       @Override
                                       public void onClick(View v)
                                       {
                                           Intent intent = new Intent(getApplicationContext(), Home.class);
                                           startActivity(intent);
                                       }
                                   }
        );

        ImageView img_search = (ImageView)findViewById(R.id.img_search);
        img_search.setOnClickListener(new Button.OnClickListener()
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
