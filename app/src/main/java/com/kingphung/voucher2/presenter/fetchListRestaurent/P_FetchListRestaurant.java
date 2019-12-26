package com.kingphung.voucher2.presenter.fetchListRestaurent;

import android.content.Context;

import com.kingphung.voucher2.model.entity.Resaurant;
import com.kingphung.voucher2.model.fetchListRestaurant.M_FetchListRestaurant;
import com.kingphung.voucher2.view.fetchListRestaurant.V_I_FetchListRestaurant;

import java.util.ArrayList;

public class P_FetchListRestaurant implements P_I_FetchListRestaurant{

    Context context;
    V_I_FetchListRestaurant v_i_fetchListRestaurant;

    public P_FetchListRestaurant(Context context, V_I_FetchListRestaurant v_i_fetchListRestaurant){
        this.context = context;
        this.v_i_fetchListRestaurant = v_i_fetchListRestaurant;
    }

    public void fetch(){
        M_FetchListRestaurant m_fetchListRestaurant = new M_FetchListRestaurant(context, this);
        m_fetchListRestaurant.fetch();
    }
    @Override
    public void onCompleteFetchListRestaurant(ArrayList<Resaurant> listRestaurant) {
        v_i_fetchListRestaurant.onCompleteFetListRestaurant(listRestaurant);
    }
}
