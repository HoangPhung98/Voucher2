package com.kingphung.voucher2.model.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kingphung.voucher2.R;
import com.kingphung.voucher2.model.entity.Resaurant;
import com.kingphung.voucher2.view.openListVoucherFragment.V_OpenListVoucherFragment;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHoler> {
    Context context;
    ArrayList<Resaurant> listMyRestaurant;

    public RestaurantAdapter(Context context, ArrayList<Resaurant> listRestaurant) {
        this.context = context;
        this.listMyRestaurant = listRestaurant;
        for(Resaurant resaurant:listMyRestaurant){
            Log.d("kkkAdapter",resaurant.getAddress()+"id");
        }
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantAdapter.ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        holder.tvName.setText(listMyRestaurant.get(position).getName());
        holder.tvAddress.setText(listMyRestaurant.get(position).getAddress());
        holder.tvNum.setText(listMyRestaurant.get(position).getNum_voucher()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                V_OpenListVoucherFragment v_openListVoucherFragment = new V_OpenListVoucherFragment(context,listMyRestaurant.get(position));
                v_openListVoucherFragment.open();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMyRestaurant.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        TextView tvName, tvAddress, tvNum;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvResName);
            tvAddress = itemView.findViewById(R.id.tvResAddress);
            tvNum = itemView.findViewById(R.id.tvResNum);
        }
    }
}
