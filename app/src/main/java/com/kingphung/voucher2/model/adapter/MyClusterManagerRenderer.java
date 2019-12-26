package com.kingphung.voucher2.model.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.abdularis.civ.AvatarImageView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.kingphung.voucher2.R;
import com.kingphung.voucher2.model.entity.Resaurant;

public class MyClusterManagerRenderer extends DefaultClusterRenderer<Resaurant> {
    private IconGenerator iconGenerator;
    private ImageView imageView;
    private AvatarImageView imageViewNum;
    private int markerWidth;
    private int markerHeight;
    private int markerPadding;

    public MyClusterManagerRenderer(Context context, GoogleMap googleMap, ClusterManager<Resaurant> clusterManager){
        super(context, googleMap, clusterManager);

        iconGenerator = new IconGenerator(context.getApplicationContext());
        View view  = LayoutInflater.from(context).inflate(R.layout.marker_custom, null, false);
        imageView = view.findViewById(R.id.iv);
        imageViewNum = view.findViewById(R.id.ivNum);
        markerWidth = (int) context.getResources().getDimension(R.dimen.markerWidth);
        markerHeight = (int) context.getResources().getDimension(R.dimen.markerHeight);
        markerPadding = (int) context.getResources().getDimension(R.dimen.markerPadding);

        view.setLayoutParams(new ViewGroup.LayoutParams(markerWidth, markerHeight));
        view.setPadding(markerPadding, markerPadding, markerPadding, markerPadding);
        iconGenerator.setContentView(view);
    }

    @Override
    protected void onBeforeClusterItemRendered(Resaurant item, MarkerOptions markerOptions) {
        imageView.setImageResource(item.getIconPicture());
        imageViewNum.setText(item.getNum_voucher()+"");
        imageViewNum.setAvatarBackgroundColor(Color.rgb(66,156,245));
        Bitmap icon =  iconGenerator.makeIcon();
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(item.getTitle());
    }
}
