package com.hx.fdb.ui.activity.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by yanxin on 17/4/27.
 */

public abstract class BaseRecyclerViewAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {

    public BaseRecyclerViewAdapter(int layoutResId, List<T> data) {
        super(layoutResId, data);
    }

    public BaseRecyclerViewAdapter(List<T> data) {
        super(data);
    }

    public BaseRecyclerViewAdapter(int layoutResId) {
        super(layoutResId);
    }
}
