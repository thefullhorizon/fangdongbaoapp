package com.hx.fdb.ui.activity.common;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hx.fdb.ui.widget.WrapHandler;
import com.hx.fdb.ui.widget.dialog.Loading;

import de.greenrobot.event.EventBus;

/**
 * Created by jaki on 17/3/28.
 */

public class BaseFragment extends Fragment {

    private WrapHandler handler;
    protected Loading loading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    protected void onHandler(Message msg) {

    }

    /* 实现了IVIEW 接口 */
    public Handler getHandler() {
        if (handler == null) handler = new BaseFragment.MyHandler(this);
        return handler;
    }

    public Context getContext() {
        return getActivity();
    }

    public void showLoading() {
        showLoading(null);
    }

    public void showLoading(String txt) {
        if (loading == null) {
            loading = Loading.create(getActivity());
        }
        loading.show(txt);
    }

    public void hideLoading() {
        if (loading != null) {
            loading.hide();
        }
    }

    public void close() {

    }
    /* 实现了IVIEW 接口 */

    private static class MyHandler extends WrapHandler<BaseFragment> {

        public MyHandler(BaseFragment baseFragment) {
            super(baseFragment);
        }

        @Override
        protected void handlMsg(BaseFragment baseFragment, Message msg) {
            if (baseFragment == null || baseFragment.isDetached()
                    || baseFragment.isRemoving()
                    || baseFragment.getActivity() == null) {
                return;
            }
            baseFragment.onHandler(msg);
        }
    }

}
