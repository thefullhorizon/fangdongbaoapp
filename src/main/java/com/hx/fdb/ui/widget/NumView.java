package com.hx.fdb.ui.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.hx.fdb.R;
import com.huoqiu.framework.util.DensityUtil;

/**
 * Created by yanxin on 17/4/19.
 */

public class NumView extends AppCompatTextView {

    public NumView(Context context) {
        super(context);
        init();
    }

    public NumView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    public void setNum(int num) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        if(num < 10) {
            setBackgroundResource(R.drawable.circle_fd3d30);
            layoutParams.width = DensityUtil.dipToPx(getContext(),16);
        } else {
            setBackgroundResource(R.drawable.corner_fd3d30);
            layoutParams.width = DensityUtil.dipToPx(getContext(),24);
        }

        if(num < 99) {
            setText(String.valueOf(num));
        } else {
            setText("99+");
        }
    }

}
