package com.hx.fdb.bean.common;

import com.hx.fdb.http.HttpRequestModel;

/**
 * Created by yanxin on 17/4/19.
 */
public class CheckUpgradeRequest extends HttpRequestModel {

    public String version;

    public CheckUpgradeRequest(String version) {
        this.version = version;
    }

}
