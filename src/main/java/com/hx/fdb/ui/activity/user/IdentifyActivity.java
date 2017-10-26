package com.hx.fdb.ui.activity.user;

import android.content.Intent;

import com.hx.fdb.R;
import com.hx.fdb.ui.ActivityController;
import com.hx.fdb.ui.activity.common.MvpActivity;
import com.hx.fdb.ui.activity.main.MainActivity;
import com.hx.fdb.ui.widget.CommonTitleBar;

import butterknife.OnClick;

/**
 * Created by yanxin on 17/10/26.
 */

public class IdentifyActivity extends MvpActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_identify;
    }

    @Override
    public void initView() {
        titleBar.setTitle("实名验证");
        titleBar.setTitleBarListener(new CommonTitleBar.TitleBarListener() {
            @Override
            public void leftOnClick() {
                ActivityController.getInstence().startActivity(IdentifyActivity.this,new Intent(IdentifyActivity.this, MainActivity.class));
            }

            @Override
            public void rightOnClick() {

            }
        });
    }

    @OnClick(R.id.checkBtn)
    public void clickCheckBtn() {
        ActivityController.getInstence().startActivity(IdentifyActivity.this,new Intent(IdentifyActivity.this, MainActivity.class));
    }
}
