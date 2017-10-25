package com.hx.fdb.ui.activity;

import android.content.Intent;

import com.hx.fdb.R;
import com.hx.fdb.ui.ActivityController;
import com.hx.fdb.ui.activity.common.MvpActivity;
import com.hx.fdb.ui.activity.main.MainActivity;

/**
 * Created by yanxin on 17/5/9.
 */
public class WelcomeActivity extends MvpActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
        getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ActivityController.getInstence().startActivity(WelcomeActivity.this,new Intent(WelcomeActivity.this,MainActivity.class));
                finish();
            }
        },1000);
    }

}
