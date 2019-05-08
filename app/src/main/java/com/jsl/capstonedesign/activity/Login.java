package com.jsl.capstonedesign.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.jsl.capstonedesign.R;

public class Login extends AppCompatActivity {

    final private String TAG = getClass().getSimpleName();

    private static final int RC_SIGN_IN = 1001;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;

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

                                             Intent intent = new Intent(getApplicationContext(), Main.class);
                                             startActivity(intent);
                                             finish();

                                             // 구글 로그인 생략

                                             //Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                                             //startActivityForResult(signInIntent, RC_SIGN_IN);

                                         }
                                     }
        );
    }

    public void onStart() { // 사용자가 현재 로그인되어 있는지 확인
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){ // 만약 로그인이 되어있으면 다음 액티비티 실행

            haveWallet();
            Intent intent = new Intent(getApplicationContext(), Main.class);
            startActivity(intent);
            finish();



        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();

                Log.d(TAG, "이름 =" + account.getDisplayName());
                Log.d(TAG, "이메일=" + account.getEmail());
                Log.d(TAG, "getId()=" + account.getId());
                Log.d(TAG, "getAccount()=" + account.getAccount());
                Log.d(TAG, "getIdToken()=" + account.getIdToken());
                firebaseAuthWithGoogle(account);

                haveWallet();
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
                finish();



            } else {
                // Google Sign In failed, update UI appropriately
                // ...
                Log.d(TAG, "로그인 실패");
            }

        }
    }

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
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private boolean haveWallet(){
        //이더 지갑 갖고있는지 조회하는 함수 **추후 수정하기
        //지갑정보없다면

        return true;
    }

}
