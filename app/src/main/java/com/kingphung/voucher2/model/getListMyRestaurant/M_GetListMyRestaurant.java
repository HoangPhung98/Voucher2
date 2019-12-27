package com.kingphung.voucher2.model.getListMyRestaurant;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kingphung.voucher2.model.entity.Resaurant;
import com.kingphung.voucher2.presenter.getListMyRestaurant.P_I_GetListMyRestaurant;
import com.kingphung.voucher2.ultils.Instant;

import java.util.ArrayList;

public class M_GetListMyRestaurant {
    Context context;
    P_I_GetListMyRestaurant p_i_getListMyRestaurant;

    public M_GetListMyRestaurant(Context context, P_I_GetListMyRestaurant p_i_getListMyRestaurant) {
        this.context = context;
        this.p_i_getListMyRestaurant = p_i_getListMyRestaurant;
    }
    public void get(){
        ArrayList<Resaurant> listMyRestauran = new ArrayList<>();

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference(Instant.USER_REF);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference myRef = userRef.child(getFilteredEmail(user));
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Resaurant resaurant = dataSnapshot1.getValue(Resaurant.class);
                    listMyRestauran.add(resaurant);
                }

                p_i_getListMyRestaurant.onCompleteGetListMyRestaurant(listMyRestauran);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                p_i_getListMyRestaurant.onCompleteGetListMyRestaurant(null);
            }
        });

    }
    private String getFilteredEmail(FirebaseUser user){
        String userEmail = user.getEmail();
        userEmail = userEmail.replaceAll("[@.]","");
        return userEmail;
    }
}
