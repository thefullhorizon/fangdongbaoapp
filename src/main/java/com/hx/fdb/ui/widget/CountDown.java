package com.hx.fdb.ui.widget;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.widget.TextView;

/**
 * Created by yanxin on 17/5/19.
 */

public class CountDown {

    public final static String PLACE_HOLDER = "&&";

    private int left;
    private TextView textView;
    private String text;

    private CountDownTimer countDownTimer;
    private CountDownListener countDownListener;

    public CountDown(int left) {
        this.left = left;
    }

    /**
     * @param left 毫秒
     * @param text 需要显示时间的 用PLACE_HOLDER代替
     */
    public CountDown(int left, TextView textView,String text) {
        this.left = left;
        this.text = text;
        this.textView = textView;
    }

    public void cancel() {
        if(countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void start() {
        if(textView != null && !TextUtils.isEmpty(text)) {
            textView.setText(text.replace(PLACE_HOLDER,String.valueOf(left/1000)));
        }
        countDownTimer = new CountDownTimer(left,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(textView != null && !TextUtils.isEmpty(text)) {
                    textView.setText(text.replace(PLACE_HOLDER,String.valueOf(millisUntilFinished/1000)));
                }
                if(countDownListener != null) {
                    countDownListener.onTick(millisUntilFinished/1000);
                }
            }

            @Override
            public void onFinish() {
                if(countDownListener != null) {
                    countDownListener.finish();
                }
            }
        };
        countDownTimer.start();
    }

    public void setCountDownListener(CountDownListener countDownListener) {
        this.countDownListener = countDownListener;
    }

    public interface CountDownListener {
        void onTick(long leftSecond);
        void finish();
    }

}
