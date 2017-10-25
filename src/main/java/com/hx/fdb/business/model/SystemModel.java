package com.hx.fdb.business.model;

import android.app.Activity;

import com.hx.fdb.http.HSHttpListener;
import com.hx.fdb.http.reqaction.CommonAction;
import com.huoqiu.framework.commhttp.MCListenerObj;
import com.huoqiu.framework.commhttp.Response;

/**
 * Created by yanxin on 17/10/25.
 */

public class SystemModel implements IModel {

    public void requestSystemTime(Activity activity, HSHttpListener<Response> listener) {
        CommonAction.getServerTime(activity,listener);
    }

}
