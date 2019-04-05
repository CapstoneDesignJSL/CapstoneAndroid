package com.jsl.capstonedesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.jsl.capstonedesign.R;

public class Home extends AppCompatActivity implements BottomSheetDialog.BottomSheetListener{
    //나중에 삭제할것  **수정
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



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


        //하단 네비게이션 바********************************************************************8888
        final TextView message = (TextView)findViewById(R.id.textview_main_message);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView_main_menu);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                        // 어떤 메뉴 아이템이 터치되었는지 확인합니다.
                        switch (item.getItemId()) {

                            case R.id.menuitem_bottombar_home:

                                message.setText("home 버튼을 눌렀습니다.");

                                return true;

                            case R.id.menuitem_bottombar_search:

                                message.setText("search 버튼을 눌렀습니다.");

                                return true;

                            case R.id.menuitem_bottombar_category:

                                message.setText("category 버튼을 눌렀습니다.");

                                BottomSheetDialog bottomSheet = new BottomSheetDialog();
                                bottomSheet.show(getSupportFragmentManager(),"Category");

                                return true;
                            case R.id.menuitem_bottombar_user:

                                message.setText("Search 버튼을 눌렀습니다.");

                                return true;
                        }
                        return false;
                    }
                });


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

    }

    @Override
    public void onButtonClicked() {
        Intent intent = new Intent(getApplicationContext(), Upload_2.class);
        startActivity(intent);
    }
}

