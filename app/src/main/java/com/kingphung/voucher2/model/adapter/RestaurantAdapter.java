package com.kingphung.voucher2.model.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kingphung.voucher2.R;
import com.kingphung.voucher2.model.entity.Resaurant;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHoler>
    implements View.OnClickListener {
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
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return listMyRestaurant.size();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "Click Restaurant!", Toast.LENGTH_LONG).show();
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
