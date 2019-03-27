package com.jsl.capstonedesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jsl.capstonedesign.R;

public class MainActivity extends AppCompatActivity {
    final private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //로고 클릭시 로그인 화면뜸

        Log.e(TAG,"onCreate in Main");
        ImageView logo = (ImageView)findViewById(R.id.img_in);
        logo.setOnClickListener(new Button.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View v)
                                            {
                                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                                startActivity(intent);
                                            }
                                        }

        );

    }
}
