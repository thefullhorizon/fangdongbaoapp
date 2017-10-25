package com.hx.fdb.app;

import com.huoqiu.framework.app.AppConfig;

import java.util.Properties;

/**
 * Created by yanxin on 17/10/25.
 */

public class HSConfig {

    public static final int LOGOUT_TIME = 2*1000;

    public static final String SERVICE_PHONE = "4004001111";

    public static final int PAGE_START = 1;
    public static final int PAGE_SIZE = 20;

    public static void setConfig(Properties properties) {
        AppConfig.set_host_env(properties.getProperty("HOST_ENV"));
        AppConfig.set_host_name(properties.getProperty("HOST_NAME"));

        AppConfig.set_host_port(Integer.parseInt(properties.getProperty("HOST_PORT")));

        AppConfig.set_host_protocol(properties.getProperty("HOST_PROTOCOL"));
        AppConfig.set_host_path(properties.getProperty("HOST_PATH"));

        AppConfig.set_pushserver_url(properties.getProperty("HOST_PUSHSERVER_URL"));

        AppConfig.set_host_pushserver_ip(properties.getProperty("HOST_PUSHSERVER_IP"));
        AppConfig.set_host_pushserver_port(Integer.parseInt(properties.getProperty("HOST_PUSHSERVER_PORT")));
        AppConfig.set_appkey(properties.getProperty("HOST_APPKEY"));

        AppConfig.set_host_image_soa_url(properties.getProperty("HOST_IMAGE_SOA_URL"));

        AppConfig.set_analysis_enable(Boolean.parseBoolean(properties.getProperty("ANALYSIS_ENABLE")));
        AppConfig.set_used_analysis_framework(properties.getProperty("USED_ANALYSIS_FRAMEWORK"));

        AppConfig.set_annotation_enable(Boolean.parseBoolean(properties.getProperty("ANNOTATION_ENABLE")));
        AppConfig.set_used_annotation_framework(properties.getProperty("USED_ANNOTATION_FRAMEWORK"));

        AppConfig.set_bitmap_thirdloader_enable(Boolean.parseBoolean(properties.getProperty("BITMAP_THIRDLOADER_ENABLE")));
        AppConfig.set_used_bitmap_framework(properties.getProperty("USED_BITMAP_FRAMEWORK"));

        AppConfig.set_database_framework_enable(Boolean.parseBoolean(properties.getProperty("DATABASE_FRAMEWORK_ENABLE")));
        AppConfig.set_used_database_framework(properties.getProperty("USED_DATABASE_FRAMEWORK"));

        AppConfig.set_encrypt_enable(Boolean.parseBoolean(properties.getProperty("ENCRYPT_ENABLE")));
        AppConfig.set_used_encrypt_framework(properties.getProperty("USED_ENCRYPT_FRAMEWORK"));

        AppConfig.set_httprequest_framework_enable(Boolean.parseBoolean(properties.getProperty("HTTPREQUEST_FRAMEWORK_ENABLE")));
        AppConfig.set_used_httprequest_framework(properties.getProperty("USED_HTTPREQUEST_FRAMEWORK"));

        AppConfig.set_map_enable(Boolean.parseBoolean(properties.getProperty("MAP_ENABLE")));
        AppConfig.set_used_map_framework(properties.getProperty("USED_MAP_FRAMEWORK"));

        AppConfig.set_push_enable(Boolean.parseBoolean(properties.getProperty("PUSH_ENABLE")));
        AppConfig.set_used_push_framework(properties.getProperty("USED_PUSH_FRAMEWORK"));

        AppConfig.set_open_test_code(Boolean.parseBoolean(properties.getProperty("OPEN_TEST_CODE")));

        AppConfig.set_host_gps_track_url(properties.getProperty("HOST_GPS_TRACK_URL"));//配置GPS轨迹URL
        AppConfig.setHost_video_soa(properties.getProperty("HOST_VIDEO_SOA"));//设置视频地址

        AppConfig.set_hotfix_url(properties.getProperty("HOTFIX_URL"));//热修复文件下载地址
    }

    public static boolean isMockLoginIP() {
        if (("test".equals(AppConfig.get_host_env())) || ("dev".equals(AppConfig.get_host_env()))
                || ("beta".equals(AppConfig.get_host_env()))
                ) {
            return true;
        }
        return false;
    }

}
