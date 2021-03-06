package com.jsl.capstonedesign.activity.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsl.capstonedesign.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    private MyRecyclerViewClickListener mListener;


    public interface MyRecyclerViewClickListener {
        // 아이템 전체 부분 클릭
        void onItemClicked(int position,Data data);

    }

    //리스너
    public void setOnClickListener(MyRecyclerViewClickListener listener) {
        mListener = listener;
    }

    // adapter에 들어갈 list 입니다.
    private ArrayList<Data> listData = new ArrayList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_gallery_view, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        final Data data = listData.get(position);
        holder.onBind(data);

        if(mListener != null) {
            final int pos = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked(pos,data);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    public void addItem(Data data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }


    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {


        private TextView textView1;
        private TextView textView2;
        private ImageView imageView;

        Data currentData;


        ItemViewHolder(View itemView) {
            super(itemView);

            currentData = new Data();

            textView1 = itemView.findViewById(R.id.hashTag_item);
            textView2 = itemView.findViewById(R.id.price_item);
            imageView = itemView.findViewById(R.id.imageView_item);


            currentData.setContent("default");
            currentData.setResId("1");
            currentData.setTitle("default");
        }

        void onBind(Data data) {
            textView1.setText(data.getTitle());
            textView2.setText(data.getContent());
            String ur = data.getResId();
            CropSquareTransformation tf = new CropSquareTransformation();
//            Bitmap bit = Picasso.get().load(;
             Picasso.get().load(ur).transform(tf).into(imageView);
        }

    }

    public class CropSquareTransformation implements Transformation {
        @Override public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 10;
            int y = (source.getHeight() - size) / 10;
            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override public String key() { return "square()"; }
    }

}
