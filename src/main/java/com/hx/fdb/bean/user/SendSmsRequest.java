package com.hx.fdb.bean.user;

import com.hx.fdb.http.HttpRequestModel;

/**
 * Created by yanxin on 17/4/7.
 */

public class SendSmsRequest extends HttpRequestModel {

    public String cellphone;

    public SendSmsRequest(String cellphone) {
        this.cellphone = cellphone;
    }

}
