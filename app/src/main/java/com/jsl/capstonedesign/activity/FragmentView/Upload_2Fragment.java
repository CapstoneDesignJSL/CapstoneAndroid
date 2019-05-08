package com.jsl.capstonedesign.activity.FragmentView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.jsl.capstonedesign.R;
import com.jsl.capstonedesign.activity.Upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Upload_2Fragment extends Fragment {

    View v;
    private ArrayAdapter adapter;
    private Spinner spinner;

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {





        v = inflater.inflate(R.layout.fragment_upload_2, container, false);

        // 스피너 연결
        spinner = (Spinner) v.findViewById(R.id.category);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.category, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //스피너 연결 끝

        Button btn_upload_register =(Button) v.findViewById(R.id.btn_upload_register);
        btn_upload_register.setOnClickListener(new Button.OnClickListener()
                                               {
                                                   @Override
                                                   public void onClick(View v)
                                                   {
                                                       Intent intent = new Intent(getContext().getApplicationContext(), Upload.class);
                                                       startActivity(intent);
                                                   }
                                               }
        );

        requirePermission();

        ImageView camera = (ImageView) v.findViewById(R.id.camera_button);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                boolean camera = ContextCompat.checkSelfPermission
                        (view.getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;


                boolean write = ContextCompat.checkSelfPermission
                        (view.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

                if (camera && write) {
                    //사진찍은 인텐트 코드 넣기

                    takePicture();
                } else {
                    Toast.makeText(getContext(), "카메라 권한 및 쓰기 권한을 주지 않았습니다.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ImageView gallery = (ImageView) v.findViewById(R.id.gallery_button);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                boolean gallery = ContextCompat.checkSelfPermission
                        (view.getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;


                boolean write = ContextCompat.checkSelfPermission
                        (view.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

                if (gallery && write) {
                    //사진찍은 인텐트 코드 넣기

                    getPicture();
                } else {
                    Toast.makeText(getContext(), "카메라 권한 및 쓰기 권한을 주지 않았습니다.", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return v;
    }


    private String mCurrentPhotoPath;



    void requirePermission() {

        String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ArrayList<String> listPermissionsNeeded = new ArrayList<>();

        for (String permission : permissions) {

            if (ContextCompat.checkSelfPermission(getContext(), permission) == PackageManager.PERMISSION_DENIED) {
                //권한이 허가가 안됬을 경우 요청할 권한을 모집하는 부분
                listPermissionsNeeded.add(permission);
            }

        }

        if (!listPermissionsNeeded.isEmpty()) {

            //권한 요청 하는 부분
            ActivityCompat.requestPermissions(getActivity(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
        }


    }
    void takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            File phtoFile = createImageFile();
            Uri photoUri = FileProvider.getUriForFile(getContext(),"com.jsl.capstonedesign.fileprovider",phtoFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
            startActivityForResult(intent, 10);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    void getPicture(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    private File createImageFile() throws IOException {


        // Create an image file namΩe
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView imageView = (ImageView)v.findViewById(R.id.imageView);
        if(requestCode == 10){

            imageView.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));

        }else if(requestCode == 1){
            if(resultCode == -1){
                try{
                    InputStream in = getContext().getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    //이미지 표시
                    imageView.setImageBitmap(img);
                }catch(Exception e){
                    e.printStackTrace();;
                }
            }
        }

    }
}

