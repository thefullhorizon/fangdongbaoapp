package com.hx.fdb.business.model;

import android.content.Context;

import com.hx.fdb.bean.user.LoginResponse;
import com.hx.fdb.bean.user.MockLoginRequest;
import com.hx.fdb.http.HSHttpListener;
import com.hx.fdb.http.reqaction.UserAction;
import com.huoqiu.framework.commhttp.MCListenerObj;
import com.huoqiu.framework.commhttp.Response;
import com.huoqiu.framework.util.DeviceUtil;

/**
 * Created by yanxin on 17/10/25.
 */

public class UserModel implements IModel {

    public void mockLogin(Context mContext, String phone, HSHttpListener<LoginResponse> listener) {
        MockLoginRequest request = new MockLoginRequest();
        request.imei = DeviceUtil.getIMEI(mContext);
        request.mobile = phone;
        UserAction.mocklogin(mContext,request,listener);
    }

}
