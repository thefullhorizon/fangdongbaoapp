package com.hx.fdb.ui.widget.refresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.hx.fdb.R;

/**
 * Created by yanxin on 17/4/24.
 */

public class RefreshLayout extends SwipeRefreshLayout {

    public RefreshLayout(Context context) {
        super(context);
        init();
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        setColorSchemeResources(R.color.color_FF2081F8);
    }

}
