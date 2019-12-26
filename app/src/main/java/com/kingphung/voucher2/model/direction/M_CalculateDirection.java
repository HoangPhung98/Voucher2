package com.kingphung.voucher2.model.direction;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;
import com.kingphung.voucher2.presenter.direction.P_I_CalculateDirection;
import com.kingphung.voucher2.ultils.Instant;

public class M_CalculateDirection {
    private Context context;
    private P_I_CalculateDirection p_i_calculateDirection;
    private GeoApiContext geoApiContext;
    private LatLng start;
    private LatLng destination;
    private String TAG_DIRECTION;

    private String TAG = "kkkM_CalculateDirection";

    public M_CalculateDirection(Context context, P_I_CalculateDirection p_i_calculateDirection, GeoApiContext geoApiContext, LatLng start, LatLng destination, String TAG_DIRECTION) {
        this.context = context;
        this.p_i_calculateDirection = p_i_calculateDirection;
        this.geoApiContext = geoApiContext;
        this.start = start;
        this.destination = destination;
        this.TAG_DIRECTION = TAG_DIRECTION;
    }

    public void calculate(){
        DirectionsApiRequest directionsApiRequest = new DirectionsApiRequest(geoApiContext);
        directionsApiRequest.alternatives(true);
        directionsApiRequest.origin(
                new com.google.maps.model.LatLng(
                        start.latitude,
                        start.longitude)
        );
        directionsApiRequest.destination(
                new com.google.maps.model.LatLng(
                        destination.latitude,
                        destination.longitude)
        );
        if(TAG_DIRECTION.equals(Instant.CAR)){
            directionsApiRequest.mode(TravelMode.DRIVING);
        } else {
            if (TAG_DIRECTION.equals(Instant.BIKE)) {
                directionsApiRequest.mode(TravelMode.BICYCLING);
            }else{
                if(TAG_DIRECTION.equals(Instant.WALK)){
                    directionsApiRequest.mode(TravelMode.WALKING);
                }
            }
        }

        directionsApiRequest.setCallback(new PendingResult.Callback<DirectionsResult>() {
            @Override
            public void onResult(DirectionsResult result) {
                p_i_calculateDirection.onCompleteCalculateDirection(result,TAG_DIRECTION);
            }

            @Override
            public void onFailure(Throwable e) {
                Log.d(TAG, e.toString());
            }
        });


    }
}
