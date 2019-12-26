package com.kingphung.voucher2.presenter.searchVoucher;

import android.util.Log;

import com.kingphung.voucher2.model.entity.MyLatLng;
import com.kingphung.voucher2.model.entity.Resaurant;
import com.kingphung.voucher2.model.entity.Voucher;
import com.kingphung.voucher2.view.searchVoucher.V_I_SearchVoucher;

import java.util.ArrayList;

public class P_SearchVoucher {
    V_I_SearchVoucher v_i_searchVoucher;
    ArrayList<Resaurant> listRestaurant;
    CharSequence searchWord;

    public P_SearchVoucher(V_I_SearchVoucher v_i_searchVoucher, ArrayList<Resaurant> listRestaurant, CharSequence searchWord) {
        this.v_i_searchVoucher = v_i_searchVoucher;
        this.listRestaurant = listRestaurant;
        this.searchWord = searchWord;
    }
    public void search(){
        ArrayList<Resaurant> listSearchedRestaurant = new ArrayList<>();
        boolean containVoucher = false;
        int numSearchVoucher;
        for(Resaurant resaurant:listRestaurant){
            numSearchVoucher = 0;
            containVoucher = false;
            Resaurant searchRestaurant = new Resaurant();
            searchRestaurant.setListVoucher(new ArrayList<>());

            for(Voucher voucher:resaurant.getListVoucher()){
                if(voucher.getTitle().contains(searchWord)
                    || voucher.getDescription().contains(searchWord)
                    || voucher.getCode().contains(searchWord)){
                    numSearchVoucher++;
                    containVoucher = true;
                    searchRestaurant.getListVoucher().add(voucher);
                }
            }

            if(containVoucher){
                searchRestaurant.setId(resaurant.getId());
                searchRestaurant.setName(resaurant.getName());
                searchRestaurant.setAddress(resaurant.getAddress());
                searchRestaurant.setIconPicture(resaurant.getIconPicture());
                searchRestaurant.setPosition(new MyLatLng(resaurant.getPosition().latitude, resaurant.getPosition().longitude));
                searchRestaurant.setNum_voucher(numSearchVoucher);
                listSearchedRestaurant.add(searchRestaurant);
            }
        }
        v_i_searchVoucher.onCompleteSearchVoucher(listSearchedRestaurant);
    }
}
