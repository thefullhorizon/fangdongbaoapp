package com.hx.fdb.ui.widget.intercept;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.hx.fdb.bean.user.UserInfo;
import com.hx.fdb.ui.activity.user.LoginActivity;
import com.hx.fdb.util.UserInfoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录拦截
 * Activity 拦截 没有登录会跳转到登录界面
 * Created by yanxin on 17/4/3.
 */

public class LoginIntercpet implements Intercept {

    //不需要拦截登录的activity
    private final List<Class<? extends Activity>> interceptActivity = new ArrayList<>();

    public LoginIntercpet addIntercaptActivity(Class<? extends Activity> mClass) {
        interceptActivity.add(mClass);
        return this;
    }

    @Override
    public boolean intercept(Activity activity, Intent intent) {
        return intercept(activity, null, intent);
    }

    @Override
    public boolean intercept(Fragment fragment, Intent intent) {
        return intercept(null, fragment, intent);
    }

    public boolean intercept(Activity activity, Fragment fragment, Intent intent) {
        UserInfo userInfo = UserInfoUtil.instence().get();

        if(userInfo != null && !TextUtils.isEmpty(userInfo.getTicket())) {//已经登录
            return false;
        }

        for (Class mClass : interceptActivity) {
            if (mClass.getName().equals(intent.getComponent().getClassName())) {
                return false;
            }
        }

        login(activity, fragment);
        return true;
    }

    private void login(Activity activity, Fragment fragment) {
        if (activity != null) {
            Intent intent = new Intent(activity, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(intent);
        } else if (fragment != null) {
            Intent intent = new Intent(fragment.getContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            fragment.startActivity(intent);
        }
    }

}
