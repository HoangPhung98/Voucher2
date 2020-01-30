package com.kingphung.voucher2.model.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.kingphung.voucher2.R;
import com.kingphung.voucher2.model.entity.Resaurant;
import com.kingphung.voucher2.presenter.removeVoucherFromMyList.P_RemoveVoucherFromMyList;
import com.kingphung.voucher2.view.removeVoucherFromMyList.V_I_RemoveVoucherFromMyList;
import com.squareup.picasso.Picasso;


public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.MyViewHolder>
    implements V_I_RemoveVoucherFromMyList {
    Context context;
    Resaurant resaurant;

    public VoucherAdapter(Context context, Resaurant resaurant){
        this.context = context;
        this.resaurant = resaurant;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_voucher, parent, false);
        return new VoucherAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(resaurant.getListVoucher().get(position).getTitle());
        holder.tvCode.setText(resaurant.getListVoucher().get(position).getCode());
        holder.tvAddress.setText(resaurant.getAddress());
        holder.tvDescription.setText(resaurant.getListVoucher().get(position).getDescription());
        Picasso.get().load(resaurant.getListVoucher().get(position).getimg_url()).into(holder.ivImage);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showAlertDialogRemove(position);
                return true;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupChoseDirection();
            }
        });
    }

    private void showPopupChoseDirection() {
    }

    V_I_RemoveVoucherFromMyList getVI(){
        return this;
    }
    @Override
    public void onCompleteRemoveVoucherFromMyList(boolean isSuccessfully, int position) {
            resaurant.getListVoucher().remove(position);
            resaurant.setNum_voucher(resaurant.getNum_voucher()-1);
            notifyDataSetChanged();
    }

    private void showAlertDialogRemove(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Remove");
        builder.setMessage("Do you want to remove this voucher?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                P_RemoveVoucherFromMyList p_removeVoucherFromMyList
                        = new P_RemoveVoucherFromMyList(context,
                        getVI(),
                        resaurant,
                        position);
                p_removeVoucherFromMyList.remove();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    public int getItemCount() {
        return resaurant.getListVoucher().size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvCode, tvAddress, tvDescription;
        ImageView ivImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCode = itemView.findViewById(R.id.tvCode);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
