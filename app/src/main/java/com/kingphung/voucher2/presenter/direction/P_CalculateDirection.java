package com.kingphung.voucher2.presenter.direction;

import android.content.Context;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.kingphung.voucher2.model.direction.M_CalculateDirection;
import com.kingphung.voucher2.view.direction.V_I_CalculateDirection;

public class P_CalculateDirection
    implements P_I_CalculateDirection {
    private Context context;
    private V_I_CalculateDirection v_i_calculateDirection;
    private GeoApiContext geoApiContext;
    private LatLng start;
    private LatLng destination;
    private String TAG_DIRECTION;

    public P_CalculateDirection(Context context, V_I_CalculateDirection v_i_calculateDirection, GeoApiContext geoApiContext, LatLng start, LatLng destination, String TAG_DIRECTION) {
        this.context = context;
        this.v_i_calculateDirection = v_i_calculateDirection;
        this.geoApiContext = geoApiContext;
        this.start = start;
        this.destination = destination;
        this.TAG_DIRECTION = TAG_DIRECTION;
    }

    public void calculate(){
        M_CalculateDirection m_calculateDirection = new M_CalculateDirection(context, this, geoApiContext, start, destination, TAG_DIRECTION);
        m_calculateDirection.calculate();
    }

    @Override
    public void onCompleteCalculateDirection(DirectionsResult directionsResult, String TAG_DIRECTION) {
        v_i_calculateDirection.onCompleteCalculateDirection(directionsResult, TAG_DIRECTION);
    }
}
