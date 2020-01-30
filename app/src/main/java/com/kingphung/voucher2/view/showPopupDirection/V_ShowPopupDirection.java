package com.kingphung.voucher2.view.showPopupDirection;

import android.content.Context;
import android.location.Address;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.kingphung.voucher2.model.entity.Resaurant;
import com.kingphung.voucher2.view.showPopupVoucherPager.V_I_ShowPopupVoucherPager;

public class V_ShowPopupDirection {
    Context context;
    V_I_ShowPopupDirection v_i_showPopupDirection;
    ;

    //UI
    PopupWindow popupWindow;
    TextView tvRestaurantName, tvNumber, tvRestaurantAddress;
    ImageButton btBack, btCar, btBike, btWalk;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
}
