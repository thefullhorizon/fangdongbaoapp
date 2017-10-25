package com.hx.fdb.ui.activity.common;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hx.fdb.R;
import com.hx.fdb.ui.widget.CommonSearchTitleBar;
import com.hx.fdb.ui.widget.CommonTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yanxin on 17/3/31.
 */

public abstract class BaseUiActivity extends BaseActivity {

    @BindView(R.id.titleBar)
    @Nullable
    protected CommonTitleBar titleBar;
    @BindView(R.id.searchTitleBar)
    @Nullable
    protected CommonSearchTitleBar searchTitleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getLayoutId() != 0) {
            setContentView(getLayoutId());
            ButterKnife.bind(this);
        }
        initView();
        refreshUI();
    }

    public abstract int getLayoutId();
    public abstract void initView();
    public void refreshUI() {

    }

}
