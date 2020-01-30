package com.kingphung.voucher2.presenter.removeVoucherFromMyList;

import android.content.Context;

import com.kingphung.voucher2.model.entity.Resaurant;
import com.kingphung.voucher2.model.removeVoucherFromMyList.M_RemoveVoucherFromMyList;
import com.kingphung.voucher2.view.removeVoucherFromMyList.V_I_RemoveVoucherFromMyList;

public class P_RemoveVoucherFromMyList
    implements P_I_RemoveVoucherFromMyList{
    Context context;
    V_I_RemoveVoucherFromMyList v_i_removeVoucherFromMyList;
    Resaurant resaurant;
    int position;
    public P_RemoveVoucherFromMyList(Context context, V_I_RemoveVoucherFromMyList v_i_removeVoucherFromMyList, Resaurant resaurant, int position){
        this.context = context;
        this.v_i_removeVoucherFromMyList = v_i_removeVoucherFromMyList;
        this.resaurant = resaurant;
        this.position = position;
    }
    public void remove(){
        M_RemoveVoucherFromMyList m_removeVoucherFromMyList = new M_RemoveVoucherFromMyList(context, this, resaurant, position);
        m_removeVoucherFromMyList.remove();
    }

    @Override
    public void onCompleteRemoveVoucherFromMyList(boolean isSuccessfullyRemove) {
        v_i_removeVoucherFromMyList.onCompleteRemoveVoucherFromMyList(isSuccessfullyRemove, position);
    }
}
