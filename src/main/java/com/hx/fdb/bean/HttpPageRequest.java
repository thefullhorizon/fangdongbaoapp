package com.hx.fdb.bean;

import com.hx.fdb.http.HttpRequestModel;

/**
 * Created by yanxin on 17/4/10.
 */

public class HttpPageRequest extends HttpRequestModel{

    public int pagesize;
    public int pageindex;

    public HttpPageRequest(int pageIndex, int pageSize) {
        this.pageindex = pageIndex;
        this.pagesize = pageSize;
    }

}
