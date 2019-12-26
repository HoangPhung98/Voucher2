package com.kingphung.voucher2.model.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.kingphung.voucher2.R;
import com.kingphung.voucher2.model.entity.Voucher;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyPagerAdapter extends PagerAdapter {

    ArrayList<Voucher> listVoucher;
    Context context;
    LayoutInflater layoutInflater;

    public MyPagerAdapter(Context context, ArrayList<Voucher> listVoucher) {
        this.listVoucher = listVoucher;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listVoucher.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_voucher, container, false);

        TextView tvTitle, tvCode, tvDescription;
        ImageView ivImage;
        tvTitle = view.findViewById(R.id.tvTitle);
        tvCode = view.findViewById(R.id.tvCode);
        tvDescription = view.findViewById(R.id.tvDescription);
        ivImage = view.findViewById(R.id.ivImage);
        tvTitle.setText(listVoucher.get(position).getTitle());
        tvCode.setText(listVoucher.get(position).getCode());
        tvDescription.setText(listVoucher.get(position).getDescription());
        Picasso.get().load(listVoucher.get(position).getimg_url()).into(ivImage);

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
