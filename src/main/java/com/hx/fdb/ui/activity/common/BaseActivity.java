package com.hx.fdb.ui.activity.common;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.hx.fdb.ui.widget.KeyboardPatch;
import com.hx.fdb.ui.widget.WrapHandler;
import com.hx.fdb.ui.widget.dialog.DialogCollection;
import com.hx.fdb.ui.widget.dialog.Loading;

import de.greenrobot.event.EventBus;

/**
 * Created by jaki on 17/3/21.
 */

public class BaseActivity extends AppCompatActivity {

    private WrapHandler handler;
    protected KeyboardPatch keyboardPatch;
    protected Loading loading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(enableKeyBoard() && keyboardPatch == null) {
            keyboardPatch = new KeyboardPatch(this,findViewById(android.R.id.content));
            keyboardPatch.enable();
        }
    }

    @Override
    protected void onDestroy() {
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        DialogCollection.getInstance().remove(getClass());
        if(handler != null) {
            handler.destory();
        }
        if(keyboardPatch != null) {
            keyboardPatch.disable();
            keyboardPatch = null;
        }
        hideLoading();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isDisableBackKey()) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 解决软件盘弹出挡住 Edittext
     * @return
     */
    protected boolean enableKeyBoard() {
        return false;
    }

    /**
     * 是否禁止系统的返回键
     *
     * @return
     */
    protected boolean isDisableBackKey() {
        return false;
    }

    protected void onHandler(Message msg) {

    }

    /* 实现了IVIEW 接口 */
    public Context getContext() {
        return this;
    }

    public Handler getHandler() {
        if(handler == null) handler = new MyHandler(this);
        return handler;
    }

    public void showLoading() {
        showLoading(null);
    }

    public void showLoading(String txt) {
        if(loading == null) {
            loading = Loading.create(this);
        }
        loading.show(txt);
    }

    public void hideLoading() {
        if(loading != null) {
            loading.hide();
        }
    }

    public Activity getActivity() {
        return this;
    }

    public void close() {

    }
    /* 实现了IVIEW 接口 */

    private static class MyHandler extends WrapHandler<BaseActivity> {

        public MyHandler(BaseActivity baseActivity) {
            super(baseActivity);
        }

        @Override
        protected void handlMsg(BaseActivity baseActivity, Message msg) {
            if(baseActivity == null || baseActivity.isFinishing()) {
                return;
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if(baseActivity.isDestroyed()) {
                    return;
                }
            }
            baseActivity.onHandler(msg);
        }
    }

}
