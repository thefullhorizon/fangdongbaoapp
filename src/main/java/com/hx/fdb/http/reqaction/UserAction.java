package com.hx.fdb.http.reqaction;

import android.app.Activity;
import android.content.Context;

import com.hx.fdb.bean.user.LoginResponse;
import com.hx.fdb.bean.user.MockLoginRequest;
import com.hx.fdb.http.Api;
import com.huoqiu.framework.commhttp.MCListenerObj;

/**
 * Created by yanxin on 17/10/25.
 */

public class UserAction extends HSBaseReqsAction {

    public static String mocklogin(Context mActivity, MockLoginRequest request, MCListenerObj.IObjResListener<LoginResponse> listener){
        getPostReq7HeardInfo(mActivity, Api.MOCKLOGIN_URL, request, listener, LoginResponse.class);
        return Api.MOCKLOGIN_URL;
    }

}
