package com.jsl.capstonedesign.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.jsl.capstonedesign.R;

public class Upload extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        //화면크기 얻기
        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int displayWidth = display.getWidth();

        GridView gridView = (GridView) findViewById(R.id.gridview1);
        //gridView.setAdapter(new ImageAdapter(this));
        //ImageAdapter adapter = new ImageAdapter(this);
        ImageAdapter adapter = new ImageAdapter(this,displayWidth); //가로크기의 정보를 같이 넘긴다.
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Toast.makeText(getApplicationContext(), position+"클릭함",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void btn_str_cancel(View view) {
        Toast.makeText(this,"취소",Toast.LENGTH_LONG).show();
    }

    public void btn_str_ok(View view) {
        Toast.makeText(this,"확인",Toast.LENGTH_LONG).show();
    }
}
