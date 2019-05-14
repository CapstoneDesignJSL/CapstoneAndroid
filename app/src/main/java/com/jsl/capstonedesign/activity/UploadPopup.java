package com.jsl.capstonedesign.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jsl.capstonedesign.R;
import com.jsl.capstonedesign.activity.Retrofit.ApiService;
import com.jsl.capstonedesign.activity.recyclerview.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.xml.transform.Result;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UploadPopup extends Activity implements OnClickListener {
    private static final String TAG = UploadPopup.class.getSimpleName();
    private Context mContext = null;
    private final int imgWidth = 320;
    private final int imgHeight = 372;
    private File img;
    private String imgPath;
    private Bitmap bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagepopup);
        mContext = this;

        /** 전송메시지 */
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        imgPath = extras.getString("filename");
        Log.e(TAG, "img path = "+imgPath);

        img = new File(imgPath);

        /** 완성된 이미지 */
        BitmapFactory.Options bfo = new BitmapFactory.Options();
        bfo.inSampleSize = 2;
        ImageView iv = findViewById(R.id.sell_image);
        bm = BitmapFactory.decodeFile(imgPath, bfo);
        Bitmap resized = Bitmap.createScaledBitmap(bm, imgWidth, imgHeight, true);
        iv.setImageBitmap(resized);

        Button btn = findViewById(R.id.btn_upload_cancel);
        Button btn2 = findViewById(R.id.btn_upload_register);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_upload_cancel:
//                Toast.makeText(getApplicationContext(),"취소",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, Upload.class);
                startActivity(intent);
                break;
            case R.id.btn_upload_register:
                EditText nameEdit = findViewById(R.id.sell_name);
                EditText priceEdit = findViewById(R.id.sell_price);
                String name = nameEdit.getText().toString();
                String pricevalue = priceEdit.getText().toString();
//                float price = Float.parseFloat(pricevalue);
                pricevalue="0";
                upload(name, pricevalue);


//                Toast.makeText(mContext, pricename+pricevalue, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public byte[] bitmapToByteArray( Bitmap $bitmap ) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream() ;
        $bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream) ;
        byte[] byteArray = stream.toByteArray() ;
        return byteArray ;
    }

    public void upload(String name, String price_str){

        String str_img =  getStringFromBitmap(bm);
        String email = "dlgusrb0813@gmail.com";

        float price = Float.parseFloat(price_str);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final ApiService apiService = retrofit.create(ApiService.class);
        Uri url = Uri.parse(imgPath);
        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(
                      //   getContentResolver().getType(url))
                        "multipart/form-data")
                    ,
                        img
                );


        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("picture", img.getName(), requestFile);


        //Part of GET
        Call<RequestBody> res = apiService.uploadImg(email,name,str_img,price);

        res.enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                Log.e(TAG, "onResponse in upload");


            }
            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {
                Log.e(TAG, "onFailure in upload");



            }
        });


    }

    private String getStringFromBitmap(Bitmap bitmapPicture) {
        String encodedImage;
        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
        bitmapPicture.compress(Bitmap.CompressFormat.PNG, 100, byteArrayBitmapStream);
        byte[] b = byteArrayBitmapStream.toByteArray();
        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }
}
