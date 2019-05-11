package com.jsl.capstonedesign.activity;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsl.capstonedesign.R;
import com.squareup.picasso.Picasso;

public class Picture extends AppCompatActivity {

//    구매하기 클릭시 결제 창으로 넘어감
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_picture);

        TextView titleTextView = findViewById(R.id.picture_pictureName);
        TextView priceTextView = findViewById(R.id.picture_picturePrIce);
        TextView contentTextView = findViewById(R.id.picture_pictureDescription);
        ImageView imageView = findViewById(R.id.picture_image);
        Intent intent = getIntent();
        String bringTitle = intent.getStringExtra("title");
        String bringContent = intent.getStringExtra("content");
        Double bringPrice = intent.getDoubleExtra("price",-1);

        Picasso.get().load(intent.getStringExtra("url")).into(imageView);

        titleTextView.setText(bringTitle);
        priceTextView.setText( String.valueOf(bringPrice)+" eth");
        //contentTextView.setText(bringContent);

        Button btn_buy =(Button) findViewById(R.id.btn_buy);
        btn_buy.setOnClickListener(new Button.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View v)
                                            {
                                                Dialog();
                                            }
                                        }
        );
    }
    public void Dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("구매 확인");
        builder.setMessage("구매하시겠습니까?");
        builder.setPositiveButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(),"우측버튼 클릭됨",Toast.LENGTH_LONG).show();
                    }
                });
        builder.setNegativeButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Dialog2();
                        Intent intent = new Intent(getApplicationContext(), PurchaseSheet_2.class);
                        startActivity(intent);
                        //Toast.makeText(getApplicationContext(),"좌측버튼 클릭됨",Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }

    public void Dialog2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("잔액 부족");
        builder.setMessage("코인을 충전하시겠습니까?");
        builder.setPositiveButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(),"우측버튼 클릭됨",Toast.LENGTH_LONG).show();
                    }
                });
        builder.setNegativeButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), Charge.class);
                        startActivity(intent);
                        //Toast.makeText(getApplicationContext(),"좌측버튼 클릭됨",Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }
}
