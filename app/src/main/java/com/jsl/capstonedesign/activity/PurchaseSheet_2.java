package com.jsl.capstonedesign.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.jsl.capstonedesign.R;

public class PurchaseSheet_2 extends AppCompatActivity {


//    결제완료 후 다운받기 버튼 클릭시 다운받기 화면으로 넘어감
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puschasesheet2);

        ImageView purchase2_waveDesign = (ImageView)findViewById(R.id.purchase2_waveDesign);
        ImageView purchase2_complete = (ImageView)findViewById(R.id.purchase2_complete);
        ImageView purchase2_scatterLine = (ImageView)findViewById(R.id.purchase2_scatterLine);
        ImageView purchase2_generalLine = (ImageView)findViewById(R.id.purchase2_generalLine);
        ImageView purchase2_itemImage = (ImageView)findViewById(R.id.purchase2_itemImage);

        Glide.with(this).load(R.drawable.homebg).into(purchase2_waveDesign);
        Glide.with(this).load(R.drawable.purchase2_complete).into(purchase2_complete);
        Glide.with(this).load(R.drawable.purchase2_scatterline).into(purchase2_scatterLine);
        Glide.with(this).load(R.drawable.purchase2_generalline).into(purchase2_generalLine);
        Glide.with(this).load(R.drawable.manexample).into(purchase2_itemImage);


    }
}
