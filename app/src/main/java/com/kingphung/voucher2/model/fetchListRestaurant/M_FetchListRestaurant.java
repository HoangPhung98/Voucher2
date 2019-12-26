package com.kingphung.voucher2.model.fetchListRestaurant;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kingphung.voucher2.model.entity.MyLatLng;
import com.kingphung.voucher2.model.entity.Resaurant;
import com.kingphung.voucher2.presenter.fetchListRestaurent.P_I_FetchListRestaurant;
import com.kingphung.voucher2.ultils.Instant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class M_FetchListRestaurant {
    ArrayList<Resaurant> listRestaurant;

    Context context;
    P_I_FetchListRestaurant p_i_fetchListRestaurant;

    public M_FetchListRestaurant(Context context, P_I_FetchListRestaurant p_i_fetchListRestaurant) {
        this.context = context;
        this.p_i_fetchListRestaurant = p_i_fetchListRestaurant;
    }

    public void fetch(){

        DatabaseReference voucherRef = FirebaseDatabase.getInstance().getReference(Instant.VOUCHER_REF);
        voucherRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Geocoder geocoder = new Geocoder(context);
                List<Address> addresses;
                Address address;

                listRestaurant = new ArrayList<>();

                for(DataSnapshot i : dataSnapshot.getChildren()){
                    Resaurant resaurant = i.getValue(Resaurant.class);
                    try {
                        addresses = geocoder.getFromLocationName(resaurant.getAddress(),1);
                        address = addresses.get(0);
                        resaurant.setPosition(new MyLatLng(address.getLatitude(),address.getLongitude()));

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(context, "Something wrong: "+e.toString(), Toast.LENGTH_SHORT).show();

                    }

                    listRestaurant.add(resaurant);
                }

                p_i_fetchListRestaurant.onCompleteFetchListRestaurant(listRestaurant);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
