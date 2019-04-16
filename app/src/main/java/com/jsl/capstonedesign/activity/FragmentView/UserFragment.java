package com.jsl.capstonedesign.activity.FragmentView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.jsl.capstonedesign.R;
import com.jsl.capstonedesign.activity.Charge;
import com.jsl.capstonedesign.activity.Login;
import com.jsl.capstonedesign.activity.MainActivity;
import com.jsl.capstonedesign.activity.Order_inquiry;
import com.jsl.capstonedesign.activity.recyclerview.RecyclerAdapter;

public class UserFragment extends Fragment {



    private Button btn_logout;
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;

    View v;

    private final String TAG = getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_user, container, false);
        btn_logout = v.findViewById(R.id.btn_logout);  //로그아웃 버튼
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
        //*************************************************로그인관련************

        btn_logout.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.e(TAG,"로그아웃");
                signOut();
            }
        });

        Button btn_order =(Button) v.findViewById(R.id.btn_order);
        btn_order.setOnClickListener(new Button.OnClickListener()
                                     {
                                         @Override
                                         public void onClick(View v)
                                         {
                                             Intent intent = new Intent(getActivity(), Order_inquiry.class);
                                             startActivity(intent);
                                         }
                                     }
        );

        Button btn_charge =(Button) v.findViewById(R.id.btn_charge);
        btn_charge.setOnClickListener(new Button.OnClickListener()
                                      {
                                          @Override
                                          public void onClick(View v)
                                          {
                                              Intent intent = new Intent(getActivity(), Charge.class);
                                              startActivity(intent);
                                          }
                                      }
        );

        return v;
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


