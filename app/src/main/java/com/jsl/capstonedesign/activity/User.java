package com.jsl.capstonedesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsl.capstonedesign.R;

public class User extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        TextView text_logo_2 =(TextView) findViewById(R.id.text_logo_2);
        text_logo_2.setOnClickListener(new Button.OnClickListener()
                                     {
                                         @Override
                                         public void onClick(View v)
                                         {
                                             Intent intent = new Intent(getApplicationContext(), Home.class);
                                             startActivity(intent);
                                         }
                                     }
        );

        ImageView img_search_3 =(ImageView) findViewById(R.id.img_search_3);
        img_search_3.setOnClickListener(new Button.OnClickListener()
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
