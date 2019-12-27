package com.kingphung.voucher2.ultils;

import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class Animation {
    public static void animateHeart(final ImageView view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f, android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f);
        prepareAnimation(scaleAnimation);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        prepareAnimation(alphaAnimation);

        AnimationSet animation = new AnimationSet(true);
        animation.addAnimation(alphaAnimation);
        animation.addAnimation(scaleAnimation);
        animation.setDuration(550);
        animation.setFillAfter(true);

        view.startAnimation(animation);

    }

    private static android.view.animation.Animation prepareAnimation(android.view.animation.Animation animation){
        animation.setRepeatCount(1);
        animation.setRepeatMode(android.view.animation.Animation.REVERSE);
        return animation;
    }
}
