package com.jsl.capstonedesign.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.jsl.capstonedesign.R;
import com.jsl.capstonedesign.activity.Retrofit.ApiService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {

    //로그인
    public static GoogleSignInAccount account;
    private static final int RC_SIGN_IN = 1001;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    //retrofit
    private Retrofit retrofit ;
    private ApiService apiService;

    String ans; //지갑 여부인데 나중에수정
    String email="";

    //log
    final private String TAG = getClass().getSimpleName()+"tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView loginlogo1 = (ImageView)findViewById(R.id.login_logo1);
        ImageView loginlogo2 = (ImageView)findViewById(R.id.login_logo2);
        ImageView login_or = (ImageView)findViewById(R.id.login_or);
        ImageView login_signup = (ImageView)findViewById(R.id.login_signup);
        ImageView login_bottom = (ImageView)findViewById(R.id.login_bottom);

        Glide.with(this).load(R.drawable.loginlogo1).into(loginlogo1);
        Glide.with(this).load(R.drawable.loginlogo2).into(loginlogo2);
        Glide.with(this).load(R.drawable.or).into(login_or);
        Glide.with(this).load(R.drawable.loginsignup).into(login_signup);
        Glide.with(this).load(R.drawable.loginbottom).into(login_bottom);

        Log.e(TAG, "onCreate in Login");
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

        ImageButton btn_login =(ImageButton) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new Button.OnClickListener()
                                     {
                                         @Override
                                         public void onClick(View v)
                                         {


//                                             Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//                                             startActivityForResult(signInIntent, RC_SIGN_IN);
//                                             finish();

                                             Intent intent = new Intent(getApplicationContext(), Main.class);
                                             startActivity(intent);
                                             //finish();


                                         }
                                     }


        );


    }

    public void onStart() { // 사용자가 현재 로그인되어 있는지 확인
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser!=null) {
//            Intent intent = new Intent(getApplicationContext(), Main.class);
//            startActivity(intent);
//            finish();
//        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG,"onAcitvityResult");

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                account = result.getSignInAccount();
                email = account.getEmail();
                Log.e(TAG, "이름 =" + account.getDisplayName());
                Log.e(TAG, "이메일=" + email);
//                Log.e(TAG, "getId()=" + account.getId());
//                Log.e(TAG, "getAccount()=" + account.getAccount());
//                Log.e(TAG, "getIdToken()=" + account.getIdToken());
                //firebaseAuthWithGoogle(account);

//                String wallet=data.getStringExtra("result");
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
                finish();



            } else {
                // Google Sign In failed, update UI appropriately
                // ...
                Log.e(TAG, "로그인 실패");
            }

        }
    }
/*
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                            Log.d(TAG, "signInWithCredential:success");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }

                        // ...
                    }
                });
    }
*/




}
