package com.jsl.capstonedesign.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jsl.capstonedesign.R;

public class Picture extends AppCompatActivity {

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
                                                Dialog();
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
                        Dialog2();
                        //Intent intent = new Intent(getApplicationContext(), PurchaseSheet_1.class);
                        //startActivity(intent);
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
