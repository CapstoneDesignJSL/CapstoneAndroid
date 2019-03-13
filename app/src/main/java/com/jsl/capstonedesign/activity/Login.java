package com.jsl.capstonedesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jsl.capstonedesign.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button btn_login =(Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new Button.OnClickListener()
                                     {
                                         @Override
                                         public void onClick(View v)
                                         {
                                             Intent intent = new Intent(getApplicationContext(), Register.class);
                                             startActivity(intent);
                                         }
                                     }

        );

    }
}
