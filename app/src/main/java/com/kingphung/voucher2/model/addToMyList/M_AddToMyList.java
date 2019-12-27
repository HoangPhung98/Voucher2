package com.kingphung.voucher2.model.addToMyList;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kingphung.voucher2.model.entity.MyLatLng;
import com.kingphung.voucher2.model.entity.Resaurant;
import com.kingphung.voucher2.model.entity.Voucher;
import com.kingphung.voucher2.presenter.addToMylist.P_I_AddToMyList;
import com.kingphung.voucher2.ultils.Instant;

import java.util.ArrayList;

public class M_AddToMyList {
    Context context;
    P_I_AddToMyList p_i_addToMyList;
    Resaurant resaurant;
    Voucher voucher;

    public M_AddToMyList(Context context, P_I_AddToMyList p_i_addToMyList, Resaurant resaurant, Voucher voucher) {
        this.context = context;
        this.p_i_addToMyList = p_i_addToMyList;
        this.resaurant = resaurant;
        this.voucher = voucher;
    }
    public void add(){
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference(Instant.USER_REF);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference myRef = userRef.child(getFilteredEmail(user));
        DatabaseReference restaurantRef = myRef.child(resaurant.getId());

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(getFilteredEmail(user))){
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChild(resaurant.getId())){
                                DatabaseReference thisResRef = myRef.child(resaurant.getId());
                                thisResRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Resaurant thisRes =  dataSnapshot.getValue(Resaurant.class);
                                        updateResWithNewVoucher(thisResRef, thisRes);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }else{
                                addNewRestaurant(restaurantRef);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else{
                    addFirstRestaurant(restaurantRef);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//


        //get num of my voucher


    }

    private void updateResWithNewVoucher(DatabaseReference thisResRef, Resaurant thisRes) {
        thisRes.getListVoucher().add(voucher);
        thisRes.setNum_voucher(thisRes.getNum_voucher()+1);

        thisResRef.setValue(thisRes);
    }

    private void addNewRestaurant(DatabaseReference restaurantRef) {
        Resaurant newRestaurant =
                new Resaurant(resaurant.getId(), resaurant.getName(),
                        resaurant.getAddress(),
                        new MyLatLng(resaurant.getPosition().latitude, resaurant.getPosition().longitude)
                        , new ArrayList<>(), 1);
        newRestaurant.getListVoucher().add(voucher);
        restaurantRef.setValue(newRestaurant);
    }

    private void addFirstRestaurant(DatabaseReference restaurantRef) {
        Resaurant newRestaurant =
                new Resaurant(resaurant.getId(), resaurant.getName(),
                        resaurant.getAddress(),
                        new MyLatLng(resaurant.getPosition().latitude, resaurant.getPosition().longitude)
                        , new ArrayList<>(), 1);
        newRestaurant.getListVoucher().add(voucher);
        restaurantRef.setValue(newRestaurant);
    }

    private String getFilteredEmail(FirebaseUser user){
        String userEmail = user.getEmail();
        userEmail = userEmail.replaceAll("[@.]","");
        return userEmail;
    }

}
