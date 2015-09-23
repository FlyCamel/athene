package com.athene.utils;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

/**
 * AnimationUtils
 *
 * @author gxwu@lewatek.com 2013-3-21
 */
public class AnimationUtils {

    public static final long DEFAULT_OUT_ALPHA_ANIMATION = 500;
    public static final long DEFAULT_IN_ALPHA_ANIMATION = 500;
    public static final long TOPIC_DETAIL_DURATION = 150;

    public static TranslateAnimation OUT_TRANS_ANIM = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            1.0f);

    public static TranslateAnimation IN_TRANS_ANIM = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0f);

    static {
        OUT_TRANS_ANIM.setDuration(300);
        IN_TRANS_ANIM.setDuration(300);
    }

    public static AlphaAnimation getInAlphaAnimation() {
        return getInAlphaAnimation(DEFAULT_IN_ALPHA_ANIMATION);
    }

    public static AlphaAnimation getInAlphaAnimation(long durationMillis) {
        AlphaAnimation inAlphaAnimation = new AlphaAnimation(0, 1);
        inAlphaAnimation.setDuration(durationMillis);
        return inAlphaAnimation;
    }

    public static RotateAnimation getClockwiseRotateAnimation() {
        RotateAnimation clockwiseRotateAnimation = new RotateAnimation(0, 90);
        clockwiseRotateAnimation.setDuration(DEFAULT_IN_ALPHA_ANIMATION);
        return clockwiseRotateAnimation;
    }

    public static RotateAnimation getClockwiseRotateAnimation(long durationMillis) {
        RotateAnimation clockwiseRotateAnimation = new RotateAnimation(0, 90);
        clockwiseRotateAnimation.setDuration(durationMillis);
        return clockwiseRotateAnimation;
    }

    public static AlphaAnimation getOutAlphaAnimation() {
        return getOutAlphaAnimation(DEFAULT_OUT_ALPHA_ANIMATION);
    }

    public static AlphaAnimation getOutAlphaAnimation(long durationMillis) {
        AlphaAnimation outAlphaAnimation = new AlphaAnimation(1, 0);
        outAlphaAnimation.setDuration(durationMillis);
        return outAlphaAnimation;
    }

    public static void setPraiseAnimation(Animation... animations) {
        for (Animation animation : animations) {
            animation.setFillAfter(true);
            animation.setFillBefore(true);
            animation.setFillEnabled(true);
            animation.setDuration(50);
        }
    }

    public static void setTopicDetailAnimation(Animation... animations) {
        for (Animation animation : animations) {
            animation.setFillAfter(true);
            animation.setFillBefore(true);
            animation.setFillEnabled(true);
            animation.setDuration(TOPIC_DETAIL_DURATION);
        }
    }
}
