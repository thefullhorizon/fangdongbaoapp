package com.hx.fdb.ui.activity.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hx.fdb.R;
import com.hx.fdb.ui.widget.CommonSearchTitleBar;
import com.hx.fdb.ui.widget.CommonTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yanxin on 17/3/31.
 */

public abstract class BaseUiFragment extends BaseFragment {

    @BindView(R.id.titleBar)
    @Nullable
    protected CommonTitleBar titleBar;
    @BindView(R.id.searchTitleBar)
    @Nullable
    protected CommonSearchTitleBar searchTitleBar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        if(getLayoutId() != 0) {
            view = inflater.inflate(getLayoutId(),container,false);
            ButterKnife.bind(this,view);
        }
        initView();

        if(view != null) {
            return view;
        }
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    public abstract int getLayoutId();
    public abstract void initView();

}
