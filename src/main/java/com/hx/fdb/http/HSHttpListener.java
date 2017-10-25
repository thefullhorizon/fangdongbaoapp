package com.hx.fdb.http;

import com.hx.fdb.ui.widget.dialog.DialogCollection;
import com.huoqiu.framework.commhttp.MCListenerObj;
import com.huoqiu.framework.commhttp.Response;

/**
 * Created by yanxin on 17/10/25.
 */

public abstract class HSHttpListener<T extends Response> implements MCListenerObj.IObjResListener<T> {

    private boolean hideLoading = true;

    public HSHttpListener() {

    }

    public HSHttpListener(boolean hideLoading) {
        this.hideLoading = hideLoading;
    }

    @Override
    public void onSuccess(T o, String url) {
        onBeforProcess();
        onRequestSuccess(o,url);
        onEndProcess();
    }

    @Override
    public void onFail(Exception exception, String url) {
        onBeforProcess();
        onRequestFail(exception,url);
        onEndProcess();
    }

    protected void onBeforProcess() {
        if(hideLoading) {
            DialogCollection.getInstance().hideLoading();
        }
    }

    protected void onEndProcess() {

    }

    protected void onRequestSuccess(T o, String url) {
        if(o.isSuccess()) {
            onBusinessScuess(o);
        } else {
            onBusinessFail(o);
        }
    }

    protected abstract void onRequestFail(Exception exception, String url);
    protected abstract void onBusinessScuess(T response);
    protected abstract void onBusinessFail(T response);
}
