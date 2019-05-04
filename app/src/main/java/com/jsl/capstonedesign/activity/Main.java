<<<<<<< HEAD
package com.jsl.capstonedesign.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jsl.capstonedesign.R;
import com.jsl.capstonedesign.activity.FragmentView.HomeFragment;
import com.jsl.capstonedesign.activity.FragmentView.InquiryFragment;
import com.jsl.capstonedesign.activity.FragmentView.SearchResultFragment;
import com.jsl.capstonedesign.activity.FragmentView.Upload_2Fragment;
import com.jsl.capstonedesign.activity.FragmentView.UserFragment;
=======
        package com.jsl.capstonedesign.activity;

        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.design.widget.BottomNavigationView;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentTransaction;
        import android.support.v7.app.AppCompatActivity;
        import android.view.MenuItem;

        import com.google.android.gms.auth.api.Auth;
        import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
        import com.google.android.gms.common.ConnectionResult;
        import com.google.android.gms.common.api.GoogleApiClient;
        import com.google.firebase.auth.FirebaseAuth;
        import com.jsl.capstonedesign.R;
        import com.jsl.capstonedesign.activity.FragmentView.HomeFragment;
        import com.jsl.capstonedesign.activity.FragmentView.InquiryFragment;
        import com.jsl.capstonedesign.activity.FragmentView.SearchResultFragment;
        import com.jsl.capstonedesign.activity.FragmentView.Upload_2Fragment;
        import com.jsl.capstonedesign.activity.FragmentView.UserFragment;
>>>>>>> 061fcd9259bccac8817043f218f8fc327cbeeb83

public class Main extends AppCompatActivity implements BottomSheetDialog.BottomSheetListener {
    //나중에 삭제할것  **수정
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;

    //    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Fragment homeFragment = new HomeFragment();
        final Fragment searchFragment = new SearchResultFragment();
        final Fragment inquiryFragment = new InquiryFragment();
        final Fragment uploadFragment = new Upload_2Fragment();
        final Fragment userFragment = new UserFragment();

        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        openFragment(homeFragment);

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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView_main_menu);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                        // 어떤 메뉴 아이템이 터치되었는지 확인합니다.
                        switch (item.getItemId()) {

                            case R.id.menuitem_bottombar_home:
                                openFragment(homeFragment);
                                return true;

                            case R.id.menuitem_bottombar_search:
                                openFragment(searchFragment);
                                return true;

                            case R.id.menuitem_bottombar_inquire:
                                openFragment(inquiryFragment);

                                return true;
                            case R.id.menuitem_bottombar_uproad:
                                openFragment(uploadFragment);

                                return true;
                            case R.id.menuitem_bottombar_user:
                                openFragment(userFragment);
                                return true;
                        }
                        return false;
                    }
                });
    }
    @Override
    public void onButtonClicked() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu) ;

        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search :
                // 버튼기능 추가 위치
                return true ;
            case R.id.menu_account :
                // 버튼기능 추가 위치
                return true ;
            case R.id.menu_logout :
                // 버튼기능 추가 위치
                return true ;
            default :
                return super.onOptionsItemSelected(item) ;
        }
    }

    private void openFragment(final Fragment fragment)   {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.body_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}