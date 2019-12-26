package com.kingphung.voucher2.view.showPopupVoucherPager;

import com.kingphung.voucher2.model.entity.Resaurant;
import com.kingphung.voucher2.model.entity.Voucher;

public interface V_I_ShowPopupVoucherPager {
    void onCompleteVoucherSelected(Resaurant resaurant, Voucher voucher, String TAG_DIRECTION);
}
