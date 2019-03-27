package com.jsl.capstonedesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.jsl.capstonedesign.R;

public class Home extends AppCompatActivity {
    //나중에 삭제할것  **수정
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


//        프래그먼트
        final ImageView menu = (ImageView) findViewById(R.id.img_menu);
        final LinearLayout notice = (LinearLayout) findViewById(R.id.notice);

        menu.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view)
                                          {
                                              notice.setVisibility(View.GONE);
                                              FragmentManager fragmentManager = getSupportFragmentManager();
                                              FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                              fragmentTransaction.replace(R.id.fragment, new MenuFragment());
                                              fragmentTransaction.commit();
                                          }
                                      }

        );


        //*************************************************로그인관련************
        mAuth = FirebaseAuth.getInstance();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        //*************************************************로그인관련************

        test.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.e("알람","리스너");
                signOut();
            }
        });

        ImageView img_israel = (ImageView)findViewById(R.id.img_israel);
        img_israel.setOnClickListener(new Button.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View v)
                                    {
                                        Intent intent = new Intent(getApplicationContext(), Picture.class);
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

        ImageView img_cart = (ImageView)findViewById(R.id.img_cart);
        img_cart.setOnClickListener(new Button.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(View v)
                                        {
                                            Intent intent = new Intent(getApplicationContext(), Shopping_cart.class);
                                            startActivity(intent);
                                        }
                                    }

        );
    }
}
