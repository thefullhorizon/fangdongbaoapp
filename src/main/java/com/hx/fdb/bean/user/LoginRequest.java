package com.hx.fdb.bean.user;

import com.hx.fdb.http.HttpRequestModel;

/**
 * Created by yanxin on 17/4/8.
 */

public class LoginRequest extends HttpRequestModel {

    public String cellphone;
    public String verificationCode;
    public String areacode;

    public LoginRequest(String cellphone,String verificationCode,String areacode) {
        this.cellphone = cellphone;
        this.verificationCode = verificationCode;
        this.areacode = areacode;
    }

}
