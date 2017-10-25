package com.hx.fdb.business.view;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

/**
 * Created by yanxin on 17/3/21.
 */

public interface IView {

    Activity getActivity();

    Context getContext();

    void showLoading();

    void showLoading(String txt);

    void hideLoading();

    /**
     * 关闭接口返回的弹框
     */
    void close();

    Handler getHandler();

}
