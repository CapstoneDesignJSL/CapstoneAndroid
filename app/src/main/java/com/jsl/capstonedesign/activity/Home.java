package com.jsl.capstonedesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.jsl.capstonedesign.R;
import com.jsl.capstonedesign.activity.recyclerview.Data;
import com.jsl.capstonedesign.activity.recyclerview.RecyclerAdapter;

import java.util.Arrays;
import java.util.List;

public class Home extends AppCompatActivity implements BottomSheetDialog.BottomSheetListener {
    //나중에 삭제할것  **수정
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    private RecyclerAdapter adapter;
//    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();

        getData();



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

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                        // 어떤 메뉴 아이템이 터치되었는지 확인합니다.
                        switch (item.getItemId()) {

                            case R.id.menuitem_bottombar_home:

                                Toast.makeText(Home.this, "home 버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show();

                                return true;

                            case R.id.menuitem_bottombar_search:

                                Toast.makeText(Home.this, "search 버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Search_result.class);
                                startActivity(intent);

                                return true;

                            case R.id.menuitem_bottombar_category:

                                Toast.makeText(Home.this, "category 버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show();
                                BottomSheetDialog bottomSheet = new BottomSheetDialog();
                                bottomSheet.show(getSupportFragmentManager(),"Category");

                                return true;
                            case R.id.menuitem_bottombar_user:

                                Toast.makeText(Home.this, "User 버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show();

                                return true;
                        }
                        return false;
                    }
                });
    }


    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();

        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList("이스라엘", "폴란드", "러시아", "러시아2","러시아3","러시아4","러시아5","러시아6","러시아7");
        List<String> listContent = Arrays.asList(
                "이스라엘 설명 텍스트",
                "폴란드 설명 텍스트",
                "러시아1 설명 텍스트",
                "러시아2 설명 텍스트",
                "러시아3 설명 텍스트",
                "러시아4 설명 텍스트",
                "러시아5 설명 텍스트",
                "러시아6 설명 텍스트",
                "러시아7 설명 텍스트"
        );
        List<Integer> listResId = Arrays.asList(
                R.drawable.israel,
                R.drawable.poland,
                R.drawable.russia,
                R.drawable.russia_2,
                R.drawable.russia_2,
                R.drawable.russia_2,
                R.drawable.russia_2,
                R.drawable.russia_2,
                R.drawable.russia_2,
                R.drawable.russia_2
        );
        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            data.setResId(listResId.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onButtonClicked() {

    }
}
