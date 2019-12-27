package com.kingphung.voucher2.model.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.kingphung.voucher2.R;
import com.kingphung.voucher2.model.entity.Resaurant;
import com.kingphung.voucher2.model.entity.Voucher;
import com.kingphung.voucher2.presenter.addToMylist.P_AddToMyList;
import com.kingphung.voucher2.ultils.Animation;
import com.kingphung.voucher2.view.doubleClick.DoubleClickListener;
import com.kingphung.voucher2.view.showPopUpAddToMyList.V_I_ShowPopUpAddToMyList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyPagerAdapter extends PagerAdapter
    implements V_I_ShowPopUpAddToMyList {

    Resaurant resaurant;
    ArrayList<Voucher> listVoucher;
    Context context;
    LayoutInflater layoutInflater;

    public MyPagerAdapter(Context context, Resaurant resaurant) {
        this.resaurant = resaurant;
        this.listVoucher = resaurant.getListVoucher();
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
        ImageView ivImage, ivHeart;
        tvTitle = view.findViewById(R.id.tvTitle);
        tvCode = view.findViewById(R.id.tvCode);
        tvDescription = view.findViewById(R.id.tvDescription);
        ivImage = view.findViewById(R.id.ivImage);
        tvTitle.setText(listVoucher.get(position).getTitle());
        tvCode.setText(listVoucher.get(position).getCode());
        tvDescription.setText(listVoucher.get(position).getDescription());
        Picasso.get().load(listVoucher.get(position).getimg_url()).into(ivImage);
        ivHeart = view.findViewById(R.id.ivHeart);

        container.addView(view, 0);

        view.setOnClickListener(new DoubleClickListener() {
            @Override
            public void onDoubleClick() {
                addToMyList(listVoucher.get(position));
                ivHeart.setImageResource(R.drawable.heart);
                Animation.animateHeart(ivHeart);
            }
        });
        return view;
    }

    private void addToMyList(Voucher voucher) {
        P_AddToMyList p_addToMyList = new P_AddToMyList(context, this, resaurant, voucher);
        p_addToMyList.add();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onCompleteShowPopUpAddToMyList(boolean isSuccessfully) {
        if(isSuccessfully) Toast.makeText(context, "Add successfully!", Toast.LENGTH_LONG).show();
        else Toast.makeText(context, "Add failed!", Toast.LENGTH_LONG).show();
    }


}
