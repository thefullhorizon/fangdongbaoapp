package com.hx.fdb.bean.user;

import com.hx.fdb.http.HttpRequestModel;

/**
 * Created by yanxin on 17/10/25.
 */

public class MockLoginRequest extends HttpRequestModel {

    public String mobile;
    public String verifyCode = "111";
    public String imei;

}
