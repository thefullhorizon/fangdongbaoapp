package com.hx.fdb.http.reqaction;

import android.app.Activity;

import com.hx.fdb.http.Api;
import com.huoqiu.framework.commhttp.MCListenerObj;
import com.huoqiu.framework.commhttp.Response;

/**
 * Created by yanxin on 17/10/25.
 */

public class CommonAction extends HSBaseReqsAction{

    public static String getServerTime(Activity mActivity, MCListenerObj.IObjResListener<Response> listener) {
        getPostReq7HeardInfo(mActivity, Api.TIME_STAMP_URL, null, listener, Response.class);
        return Api.TIME_STAMP_URL;
    }

}
