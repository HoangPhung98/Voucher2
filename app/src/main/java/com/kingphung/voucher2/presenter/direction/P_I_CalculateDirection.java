package com.kingphung.voucher2.presenter.direction;

import com.google.maps.model.DirectionsResult;

public interface P_I_CalculateDirection {
    void onCompleteCalculateDirection(DirectionsResult directionsResult, String TAG_DIRECTION);
}
