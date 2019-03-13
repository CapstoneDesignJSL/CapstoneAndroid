package com.jsl.capstonedesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jsl.capstonedesign.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView img_israel = (ImageView)findViewById(R.id.img_israel);
        img_israel.setOnClickListener(new Button.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View v)
                                    {
                                        Intent intent = new Intent(getApplicationContext(), picture.class);
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

        ImageView img_menu = (ImageView)findViewById(R.id.img_menu);
        img_menu.setOnClickListener(new Button.OnClickListener()
                                      {
                                          @Override
                                          public void onClick(View v)
                                          {
                                              Intent intent = new Intent(getApplicationContext(), Menu.class);
                                              startActivity(intent);
                                          }
                                      }

        );

        ImageView img_user = (ImageView)findViewById(R.id.img_user);
        img_user.setOnClickListener(new Button.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(View v)
                                        {
                                            Intent intent = new Intent(getApplicationContext(), User.class);
                                            startActivity(intent);
                                        }
                                    }

        );

    }
}
