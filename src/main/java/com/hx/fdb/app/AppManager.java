package com.hx.fdb.app;

import android.content.Context;

import com.hx.fdb.ui.UiManager;
import com.hx.fdb.util.UserInfoUtil;

/**
 * Created by yanxin on 17/4/21.
 */

public class AppManager {

    public static void init(Context context) {
        UiManager.init();
        UserInfoUtil.instence().init(context);
        ConfigParse.parse();
    }

}
