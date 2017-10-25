package com.hx.fdb.ui;

import com.hx.fdb.ui.widget.intercept.LoginIntercpet;

/**
 * Created by yanxin on 17/4/3.
 */

public class UiManager {

    public static void init() {
        initLoginIntercept();
    }

    private static void initLoginIntercept() {
        LoginIntercpet intercpet = new LoginIntercpet();
        ActivityController.getInstence().addIntercepts(intercpet);
    }

}
