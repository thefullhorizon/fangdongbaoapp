package com.hx.fdb.http;

import com.huoqiu.framework.app.AppConfig;

/**
 * Created by yanxin on 17/10/25.
 */

public class Api {

    private static String ROOT_URL = AppConfig.getDomain() + AppConfig.get_host_path();// 生产环境

    private static String IMGSOA_ROOT_URL = AppConfig.get_host_image_soa_url();// 生产环境

    private static String SYSTEM_REQUEST_HEAD = ROOT_URL + "/system";
    public static String TIME_STAMP_URL = SYSTEM_REQUEST_HEAD + "/current_timestamp.rest";
    public static String MOCKLOGIN_URL = SYSTEM_REQUEST_HEAD + "/mocklogin.rest";

}
