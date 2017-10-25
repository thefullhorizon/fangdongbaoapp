package com.hx.fdb.ui.widget;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;

import com.hx.fdb.R;

/**
 * Created by yanxin on 17/4/3.
 */

public class CountdownView extends AppCompatButton implements View.OnClickListener{

    private CountDownTimer countDownTimer;
    private CountDownListener countDownListener;

    public CountdownView(Context context) {
        super(context);
        init(context);
    }

    public CountdownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CountdownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setInit(true);
    }

    @Override
    protected void onDetachedFromWindow() {
        cancel();
        super.onDetachedFromWindow();
    }

    /**
     * 设置初始样式
     */
    public void setInit(boolean first) {
        if(first) setText("获取验证码");
        else setText("重新获取");
        setBackgroundResource(R.drawable.corner_dcdcdc_white);
        setTextColor(getResources().getColor(R.color.color_FF666666));
        setClickable(true);
        setOnClickListener(this);
    }

    /**
     * 设置工作中的样式
     */
    public void setWork() {
        setClickable(false);
        cancel();
        setText("60秒后可重新获取");
        setBackgroundResource(R.drawable.corner_dcdcdc_d8d8d8);
        setTextColor(getResources().getColor(R.color.color_FF999999));
        countDownTimer = new CountDownTimer(60*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setText((millisUntilFinished/1000)+"秒后可重新获取");
            }

            @Override
            public void onFinish() {
                setInit(false);
                if(countDownListener != null) {
                    countDownListener.finish();
                }
            }
        };
        countDownTimer.start();
    }

    public void cancel() {
        if(countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    @Override
    public void onClick(View v) {
        if(countDownListener != null) {
            boolean before = countDownListener.before();
            if(!before) return;
        }
        setWork();
        if(countDownListener != null) {
            countDownListener.start();
        }
    }

    public void setCountDownListener(CountDownListener countDownListener) {
        this.countDownListener = countDownListener;
    }

    public interface CountDownListener {
        boolean before();
        void start();
        void finish();
    }
}
