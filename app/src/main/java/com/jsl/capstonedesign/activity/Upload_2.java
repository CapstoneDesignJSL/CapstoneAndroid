package com.jsl.capstonedesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.jsl.capstonedesign.R;

public class Upload_2 extends AppCompatActivity {

//    스피너 연결
    private ArrayAdapter adapter;
    private Spinner spinner;

//    스피너 연결 끝
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_2);
// 스피너 연결
        spinner = (Spinner) findViewById(R.id.category);
        adapter = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

//스피너 연결 끝
        Button btn_upload_register =(Button) findViewById(R.id.btn_upload_register);
        btn_upload_register.setOnClickListener(new Button.OnClickListener()
                                     {
                                         @Override
                                         public void onClick(View v)
                                         {
                                             Intent intent = new Intent(getApplicationContext(), upload.class);
                                             startActivity(intent);
                                         }
                                     }
        );
    }
}
