package com.hx.fdb.util;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.hx.fdb.bean.user.UserInfo;
import com.hx.fdb.cache.sharedpreferences.PreferenceUtil;
import com.huoqiu.framework.util.LogUtil;

/**
 * Created by jaki on 17/3/21.
 */

public class UserInfoUtil {

    private final String TAG = "USER";

    private static UserInfoUtil instence;

    private final String PREFERENCE_KEY = "user";

    private UserInfo userInfo;

    private Context context;

    private UserInfoUtil() {

    }

    public static UserInfoUtil instence() {
        if (instence == null) {
            instence = new UserInfoUtil();
        }
        return instence;
    }

    public void init(Context context) {
        this.context = context.getApplicationContext();
        get();
    }

    public boolean isLogin() {
        return get() != null;
    }

    public void save(UserInfo user) {
        if (user == null) return;
        this.userInfo = user;
        try {
            String json = JSON.toJSONString(user);
            LogUtil.i(TAG,"json: "+json);
            PreferenceUtil.putUser(PREFERENCE_KEY, json);
            //FrameworkManager.setBuglyUserId(userInfo.cellphoneno);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public UserInfo get() {
        if (userInfo == null) {
            try {
                String json = PreferenceUtil.getUser(context,PREFERENCE_KEY);
                if (TextUtils.isEmpty(json)) {
                    return null;
                }
                userInfo = JSON.parseObject(json, UserInfo.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userInfo;
    }

    public String getToken() {
        get();
        if(userInfo != null) {
            return userInfo.getTicket();
        }
        return "";
    }


    public void clear() {
        userInfo = null;
        try {
            PreferenceUtil.putUser(PREFERENCE_KEY, "");
            //FrameworkManager.setBuglyUserId("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
