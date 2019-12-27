package com.kingphung.voucher2.presenter.addToMylist;

import android.content.Context;

import com.kingphung.voucher2.model.addToMyList.M_AddToMyList;
import com.kingphung.voucher2.model.entity.Resaurant;
import com.kingphung.voucher2.model.entity.Voucher;
import com.kingphung.voucher2.view.showPopUpAddToMyList.V_I_ShowPopUpAddToMyList;

public class P_AddToMyList
    implements P_I_AddToMyList {

    Context context;
    V_I_ShowPopUpAddToMyList v_i_showPopUpAddToMyList;
    Resaurant resaurant;
    Voucher voucher;

    public P_AddToMyList(Context context, V_I_ShowPopUpAddToMyList v_i_showPopUpAddToMyList, Resaurant resaurant, Voucher voucher) {
        this.context = context;
        this.v_i_showPopUpAddToMyList = v_i_showPopUpAddToMyList;
        this.resaurant = resaurant;
        this.voucher = voucher;
    }

    public void add(){
        M_AddToMyList m_addToMyList = new M_AddToMyList(context, this, resaurant, voucher);
        m_addToMyList.add();
    }

    @Override
    public void onCompleleAddToMyList(boolean isSuccessfully) {
        v_i_showPopUpAddToMyList.onCompleteShowPopUpAddToMyList(isSuccessfully);
    }
}
