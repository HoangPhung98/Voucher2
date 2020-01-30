package com.kingphung.voucher2.view.openListVoucherFragment;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.kingphung.voucher2.R;
import com.kingphung.voucher2.model.entity.Resaurant;
import com.kingphung.voucher2.view.fragment.ListVoucherOfARestaurant;

public class V_OpenListVoucherFragment {
    Context context;
    Resaurant resaurant;
    public V_OpenListVoucherFragment(Context context, Resaurant resaurant){
        this.context = context;
        this.resaurant = resaurant;
    }
    public void open(){
        FragmentTransaction fragmentTransaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
        ListVoucherOfARestaurant listVoucherOfARestaurant = new ListVoucherOfARestaurant(resaurant);
        fragmentTransaction.add(R.id.fragment_container, listVoucherOfARestaurant);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
