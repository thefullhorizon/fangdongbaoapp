package com.hx.fdb.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hx.fdb.util.UserInfoUtil;
import com.huoqiu.framework.event.LoginoutEvent;

import de.greenrobot.event.EventBus;

/**
 * 接受退出登录的广播
 * 一般用于其他moudle发出请求
 * Created by yanxin on 17/5/9.
 */

public class LoginoutReceive extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        UserInfoUtil.instence().clear();
        EventBus.getDefault().post(new LoginoutEvent());
    }

}
