package com.hx.fdb.http;

import com.hx.fdb.util.MapUtil;
import com.huoqiu.framework.util.LogUtil;

import java.util.HashMap;

/**
 * Created by yanxin on 17/4/9.
 */

public class HttpRequestModel implements RequestModel {

    @Override
    public HashMap<String, Object> getParams() {
        try{
            return MapUtil.convertBean(this);
        }catch (Exception e) {
            LogUtil.e(HttpConfig.TAG,""+e);
        }
        return new HashMap<>();
    }

}
