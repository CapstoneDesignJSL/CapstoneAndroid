package com.jsl.capstonedesign.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jsl.capstonedesign.R;

import java.io.ByteArrayOutputStream;

public class UploadPopup extends Activity implements OnClickListener {
    private static final String TAG = "SYSTEM";
    private Context mContext = null;
    private final int imgWidth = 320;
    private final int imgHeight = 372;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagepopup);
        mContext = this;

        /** 전송메시지 */
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        String imgPath = extras.getString("filename");

        /** 완성된 이미지 */
        BitmapFactory.Options bfo = new BitmapFactory.Options();
        bfo.inSampleSize = 2;
        ImageView iv = findViewById(R.id.sell_image);
        Bitmap bm = BitmapFactory.decodeFile(imgPath, bfo);
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
                Toast.makeText(getApplicationContext(),"취소",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, Upload.class);
                startActivity(intent);
                break;
            case R.id.btn_upload_register:
                EditText nameEdit = findViewById(R.id.sell_name);
                EditText priceEdit = findViewById(R.id.sell_price);
                String pricename = nameEdit.getText().toString();
                String pricevalue = priceEdit.getText().toString();
                Toast.makeText(mContext, pricename+pricevalue, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public byte[] bitmapToByteArray( Bitmap $bitmap ) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream() ;
        $bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream) ;
        byte[] byteArray = stream.toByteArray() ;
        return byteArray ;
    }
}
