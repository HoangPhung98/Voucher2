package com.kingphung.voucher2.presenter.getListMyRestaurant;

import android.content.Context;

import com.kingphung.voucher2.model.addToMyList.M_AddToMyList;
import com.kingphung.voucher2.model.entity.Resaurant;
import com.kingphung.voucher2.model.getListMyRestaurant.M_GetListMyRestaurant;
import com.kingphung.voucher2.view.getListMyRestaurant.V_I_GetListMyRestaurant;

import java.util.ArrayList;

public class P_GetListMyRestaurant
    implements P_I_GetListMyRestaurant{

    Context context;
    V_I_GetListMyRestaurant v_i_getListMyRestaurant;

    public P_GetListMyRestaurant(Context context, V_I_GetListMyRestaurant v_i_getListMyRestaurant) {
        this.context = context;
        this.v_i_getListMyRestaurant = v_i_getListMyRestaurant;
    }
    public void get(){
        M_GetListMyRestaurant m_getListMyRestaurant = new M_GetListMyRestaurant(context, this);
        m_getListMyRestaurant.get();
    }

    @Override
    public void onCompleteGetListMyRestaurant(ArrayList<Resaurant> listMyRestaurant) {
        v_i_getListMyRestaurant.onCompleteGetListMyRestaurant(listMyRestaurant);
    }
}
