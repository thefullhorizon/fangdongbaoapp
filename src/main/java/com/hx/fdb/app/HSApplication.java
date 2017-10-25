package com.hx.fdb.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.huoqiu.framework.app.AppConfig;
import com.huoqiu.framework.util.AppUtils;

/**
 * Created by yanxin on 17/3/21.
 */

public class HSApplication extends Application {

    public static Application application;

    public static Application getApplication() {
        return application;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        if(AppUtils.isMainProcess(this)) {
            AppConfig.application = this;
            AppManager.init(this);
        }
    }

}
