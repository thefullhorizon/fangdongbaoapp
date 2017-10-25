package com.hx.fdb.ui.activity.main;

import android.view.View;

import com.hx.fdb.R;
import com.hx.fdb.ui.activity.common.MvpFragment;

/**
 * Created by yanxin on 17/10/25.
 */

public class HouseFragment extends MvpFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_house;
    }

    @Override
    public void initView() {
        titleBar.setTitle("房源");
        titleBar.setLeftBtnVisible(View.GONE);
    }
}
