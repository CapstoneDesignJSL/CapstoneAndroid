package com.jsl.capstonedesign.activity.FragmentView;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jsl.capstonedesign.R;
import com.jsl.capstonedesign.activity.Charge;
import com.jsl.capstonedesign.activity.Login;
import com.jsl.capstonedesign.activity.Main;
import com.jsl.capstonedesign.activity.MainActivity;
import com.jsl.capstonedesign.activity.Order_inquiry;
import com.jsl.capstonedesign.activity.Retrofit.ApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserFragment extends Fragment {



    private Button btn_logout;
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    private TextView bal;
    private TextView usr;

    View v;

    private final String TAG = getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_user, container, false);
        btn_logout = v.findViewById(R.id.btn_logout);  //로그아웃 버튼
        bal = v.findViewById(R.id.balance);
        usr = v.findViewById(R.id.user_name);


        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(new MainActivity(), new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        usr.setText(mAuth.getCurrentUser().getEmail()+" 회원");
        //*************************************************로그인관련************


        //Part of GET


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final ApiService apiService = retrofit.create(ApiService.class);

////         Check if user is signed in (non-null) and update UI accordingly.
//        Call<RequestBody> res = apiService.balance(mAuth.getCurrentUser().getEmail());
//
//        Log.e(TAG, "onResponse in onResume"+ mAuth.getCurrentUser().getEmail());
////
//        res.enqueue(new Callback<RequestBody>() {
//            @Override
//            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
//                Log.e(TAG, "onResponse in onResume");
//
////                try {
//                    Log.e(TAG, response.body().toString());
//                    String str = response.body().toString();
//                    try {
//                        JSONObject js = new JSONObject(str);
//                        String wei = js.getString("balance");
//                        bal.setText(wei);
//                    }catch (JSONException e){
//
//                    }
////
////                }catch (IOException e){
////
////                }
//            }
//            @Override
//            public void onFailure(Call<RequestBody> call, Throwable t) {
//                Log.e(TAG, "onFailure in upload");
//
//            }
//        });



        btn_logout.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.e(TAG,"로그아웃");
                signOut();
            }
        });

        Button btn_charge =(Button) v.findViewById(R.id.btn_charge);
        btn_charge.setOnClickListener(new Button.OnClickListener()
                                      {
                                          @Override
                                          public void onClick(View v)
                                          {
                                              Intent intent = new Intent(getActivity(), Charge.class);
                                              startActivity(intent);
                                              FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                              fragmentManager.beginTransaction().remove(UserFragment.this).commit();
                                              fragmentManager.popBackStack();
                                          }
                                      }
        );

        return v;
    }


    public void onResume() { // 사용자가 현재 로그인되어 있는지 확인
        super.onResume();
        Log.e(TAG, "onResume in user");


    }


    public void signOut() {
        mGoogleApiClient.connect();
        mGoogleApiClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {
                mAuth.signOut();
                if(mGoogleApiClient.isConnected()) {
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            if (status.isSuccess()) {
                                Log.v("알림", "로그아웃 성공");
                                getActivity().setResult(1);
                            } else {
                                getActivity().setResult(0);
                            }

                            Intent intent = new Intent(getActivity(), Login.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    });
                }
            }
            @Override
            public void onConnectionSuspended(int i) {

                Log.v("알림", "Google API Client Connection Suspended");

                getActivity().setResult(-1);

                getActivity().finish();

            }

        });

    }
}


