package com.kingphung.voucher2.model.removeVoucherFromMyList;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kingphung.voucher2.model.entity.Resaurant;
import com.kingphung.voucher2.model.entity.Voucher;
import com.kingphung.voucher2.presenter.removeVoucherFromMyList.P_I_RemoveVoucherFromMyList;
import com.kingphung.voucher2.ultils.Instant;

import java.util.ArrayList;

public class M_RemoveVoucherFromMyList {
    Context context;
    P_I_RemoveVoucherFromMyList p_i_removeVoucherFromMyList;
    Resaurant resaurant;
    int position;

    public M_RemoveVoucherFromMyList(Context context, P_I_RemoveVoucherFromMyList p_i_removeVoucherFromMyList, Resaurant resaurant, int position) {
        this.context = context;
        this.p_i_removeVoucherFromMyList = p_i_removeVoucherFromMyList;
        this.resaurant = resaurant;
        this.position = position;
    }
    public void remove(){
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference(Instant.USER_REF);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference myRef = userRef.child(getFilteredEmail(user));
        DatabaseReference restaurantRef = myRef.child(resaurant.getId());
        DatabaseReference listVoucherRef = restaurantRef.child("listVoucher");
        ArrayList<Voucher> listTemp = new ArrayList<>();
        listTemp.addAll(resaurant.getListVoucher());
        listTemp.remove(position);
        listVoucherRef.setValue(listTemp).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                restaurantRef.child("num_voucher").setValue(resaurant.getNum_voucher()-1);
                if((resaurant.getNum_voucher()-1)==0) restaurantRef.removeValue();
                p_i_removeVoucherFromMyList.onCompleteRemoveVoucherFromMyList(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                p_i_removeVoucherFromMyList.onCompleteRemoveVoucherFromMyList(false);
            }
        });
    }

    private String getFilteredEmail(FirebaseUser user){
        String userEmail = user.getEmail();
        userEmail = userEmail.replaceAll("[@.]","");
        return userEmail;
    }
}
