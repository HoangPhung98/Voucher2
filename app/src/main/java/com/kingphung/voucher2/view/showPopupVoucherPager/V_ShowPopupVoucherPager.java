package com.kingphung.voucher2.view.showPopupVoucherPager;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.kingphung.voucher2.R;
import com.kingphung.voucher2.model.adapter.MyPagerAdapter;
import com.kingphung.voucher2.model.entity.Resaurant;
import com.kingphung.voucher2.ultils.Instant;


public class V_ShowPopupVoucherPager implements View.OnClickListener {

    Context context;
    V_I_ShowPopupVoucherPager v_i_showPopupVoucherPager;
    Resaurant resaurant;

    //UI
    PopupWindow popupWindow;
    TextView tvRestaurantName, tvNumber;
    ImageButton btBack, btCar, btBike, btWalk;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;

    public V_ShowPopupVoucherPager(Context context, V_I_ShowPopupVoucherPager v_i_showPopupVoucherPager, Resaurant resaurant) {
        this.context = context;
        this.v_i_showPopupVoucherPager = v_i_showPopupVoucherPager;
        this.resaurant = resaurant;
    }


    public void show() {
        initUI();
        mapUI(resaurant);
        setOnClick();
    }

    private void initUI() {
        LayoutInflater inflater = LayoutInflater.from(context);

        View viewLayoutPopupWindowVoucher = inflater.inflate(R.layout.popup_voucher_pager_slide, null);
        popupWindow = new PopupWindow(
                viewLayoutPopupWindowVoucher,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                true
        );
        tvRestaurantName = viewLayoutPopupWindowVoucher.findViewById(R.id.tvRestaurantName);
        tvNumber = viewLayoutPopupWindowVoucher.findViewById(R.id.tvNumberOfVoucher);

        viewPager = viewLayoutPopupWindowVoucher.findViewById(R.id.pagerVoucher);

        btBack = viewLayoutPopupWindowVoucher.findViewById(R.id.btBack);
        btCar = viewLayoutPopupWindowVoucher.findViewById(R.id.btDirectionCar);
        btBike = viewLayoutPopupWindowVoucher.findViewById(R.id.btDirectionBike);
        btWalk = viewLayoutPopupWindowVoucher.findViewById(R.id.btDirectionWalk);

        //show popup window
        popupWindow.showAtLocation(viewLayoutPopupWindowVoucher, Gravity.CENTER, 0, 0);

        //set dim behind when popup window show up
        View container = popupWindow.getContentView().getRootView();
        if (container != null) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
            p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            p.dimAmount = 0.5f;
            if (wm != null) {
                wm.updateViewLayout(container, p);
            }
        }
    }

    private void setOnClick() {
        btBack.setOnClickListener(this);
        btCar.setOnClickListener(this);
        btBike.setOnClickListener(this);
        btWalk.setOnClickListener(this);
    }

    private void mapUI(Resaurant resaurant) {
        tvRestaurantName.setText(resaurant.getName());
        tvNumber.setText(resaurant.getNum_voucher() + "");
        pagerAdapter = new MyPagerAdapter(context, resaurant.getListVoucher());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPadding(130, 0, 130, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btBack:
                handle_Back();
                break;
            case R.id.btDirectionCar:
                handle_Car();
                break;
            case R.id.btDirectionBike:
                handle_Bike();
                break;
            case R.id.btDirectionWalk:
                handle_Walk();
                break;
        }
    }

    private void handle_Car() {
        v_i_showPopupVoucherPager.onCompleteVoucherSelected(resaurant, resaurant.getListVoucher().get(viewPager.getCurrentItem()), Instant.CAR);
        popupWindow.dismiss();
    }

    private void handle_Bike() {
        v_i_showPopupVoucherPager.onCompleteVoucherSelected(resaurant, resaurant.getListVoucher().get(viewPager.getCurrentItem()), Instant.BIKE);
        popupWindow.dismiss();
    }

    private void handle_Walk() {
        v_i_showPopupVoucherPager.onCompleteVoucherSelected(resaurant, resaurant.getListVoucher().get(viewPager.getCurrentItem()), Instant.WALK);
        popupWindow.dismiss();
    }

    private void handle_Back() {
        popupWindow.dismiss();
    }
}
